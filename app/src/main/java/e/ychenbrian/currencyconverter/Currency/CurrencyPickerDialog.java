package e.ychenbrian.currencyconverter.Currency;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import e.ychenbrian.currencyconverter.R;

/**
 * @author ychenbrian
 * @version 1.0
 * @since 5/7/2018.
 */

public class CurrencyPickerDialog extends DialogFragment implements OnItemClickListener {

    private CurrencyPickerDialogInteractionListener dialogInteractionListener;
    private EditText searchEditText;
    private RecyclerView currenciesRecyclerView;
    private CurrenciesAdapter adapter;
    private List<Currency> searchResults;
    private OnCurrencyPickerListener listener;

    /**
     * Return a new CurrencyPickerDialog
     *
     * @return a new CurrencyPickerDialog
     */
    public static CurrencyPickerDialog newInstance() {
        return new CurrencyPickerDialog();
    }

    /**
     * Override the onCreateView to setup the fragment for search. It required the
     * container and inflater and a Bundle object for the setting.
     *
     * @param inflater a layoutInflater object to adjust the size of the fragment
     * @param container the container that contain the fragment
     * @param savedInstanceState set the fragment with the saved instances state
     * @return the View after the creation with the give parameters
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.currency_picker, null);
        getDialog().setTitle(R.string.currency_picker_header);
        searchEditText = view.findViewById(R.id.currency_code_picker_search);
        currenciesRecyclerView = view.findViewById(R.id.currencies_recycler_view);
        setupRecyclerView();
        if (!dialogInteractionListener.canSearch()) {
            searchEditText.setVisibility(View.GONE);
        }
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable searchQuery) {
                search(searchQuery.toString());
            }
        });
        return view;
    }

    /**
     * The interface to for the currency that clicked by the user.
     *
     * @param currency the currency that clicked by the user
     */
    @Override
    public void onItemClicked(Currency currency) {
        if (listener != null) {
            listener.onSelectCurrency(currency);
        }
        dismiss();
    }

    /**
     * Set the currency picker listener with the OnCurrencyPickerListener
     * interface.
     *
     * @param listener the OnCurrencyPickerListener interface
     */
    public void setCurrencyPickerListener(OnCurrencyPickerListener listener) {
        this.listener = listener;
    }

    /**
     * Set the dialog interaction listener
     * @param dialogInteractionListener the CurrencyPickerDialogInteractionListener interface
     */
    public void setDialogInteractionListener(
            CurrencyPickerDialogInteractionListener dialogInteractionListener) {
        this.dialogInteractionListener = dialogInteractionListener;
    }

    /**
     * The search of the currencies.
     *
     * @param searchQuery the entered String by the user
     */
    private void search(String searchQuery) {
        searchResults.clear();
        for (Currency currency : dialogInteractionListener.getAllCurrencies()) {
            if (currency.getName().toLowerCase(Locale.ENGLISH).contains(searchQuery.toLowerCase())) {
                searchResults.add(currency);
            }
        }
        dialogInteractionListener.sortCurrencies(searchResults);
        adapter.notifyDataSetChanged();
    }

    /**
     * Setup the recycler for the currency picker dialog.
     */
    private void setupRecyclerView() {
        searchResults = new ArrayList<>();
        searchResults.addAll(dialogInteractionListener.getAllCurrencies());
        adapter = new CurrenciesAdapter(getActivity(), searchResults, this);
        currenciesRecyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        currenciesRecyclerView.setLayoutManager(layoutManager);
        currenciesRecyclerView.setAdapter(adapter);
    }


    /**
     * The interface for the currency picker dialog interaction listener.
     */
    public interface CurrencyPickerDialogInteractionListener {
        List<Currency> getAllCurrencies();

        /**
         * This interface required the currencies sort method to display
         * the results after the search based on the enter by users.
         *
         * @param searchResults the currencies after the search
         */
        void sortCurrencies(List<Currency> searchResults);

        boolean canSearch();
    }
}