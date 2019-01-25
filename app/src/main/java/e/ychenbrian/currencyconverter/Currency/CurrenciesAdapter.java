package e.ychenbrian.currencyconverter.Currency;

/**
 * @author ychenbrian
 * @version 1.0
 * @since 5/7/2018.
 */

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import e.ychenbrian.currencyconverter.R;

public class CurrenciesAdapter extends
        RecyclerView.Adapter<CurrenciesAdapter.ViewHolder> {

    private OnItemClickListener listener;
    private List<Currency> currencies;
    private Context context;

    /**
     * Constructor to fill the information with context, currencies and listener.
     *
     * @param context    the content that need to be adapted
     * @param currencies the array of currencies that need to be adapted
     * @param listener   the listener that need to be adapted
     */
    public CurrenciesAdapter(Context context, List<Currency> currencies,
                             OnItemClickListener listener) {
        this.context = context;
        this.currencies = currencies;
        this.listener = listener;
    }

    /**
     * Override the onCreateViewHolder to list the currencies.
     * Because the name of currencies are different, so it need
     * to be adjusted dynamically. Return the ViewHolder after the
     * setting of view group and view type.
     *
     * @param parent a ViewGroup object for the attachment
     * @param viewType the selected view type
     * @return a new ViewHolder object that fits the requirement
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_currency, parent, false);
        return new ViewHolder(v);
    }

    /**
     * Adjust and bind the ViewHolder to the give position.
     *
     * @param holder   the holder to be displayed
     * @param position set the position of this holder
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final Currency currency = currencies.get(position);
        holder.currencyNameText.setText(currency.getName());
        currency.loadFlagByCode(context);
        if (currency.getFlag() != -1) {
            holder.currencyFlagImageView.setImageResource(currency.getFlag());
        }
        holder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClicked(currency);
            }
        });
    }

    /**
     * Return the number of currencies.
     *
     * @return the number of currencies
     */
    @Override
    public int getItemCount() {
        return currencies.size();
    }

    /**
     * The class for the view of currencies, it will display the currency
     * flags and names.
     */
    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView currencyFlagImageView;
        private TextView currencyNameText;
        private LinearLayout rootView;

        /**
         * Display the currency flags and titles.
         *
         * @param itemView the View that need to be displayed
         */
        ViewHolder(View itemView) {
            super(itemView);
            currencyFlagImageView = itemView.findViewById(R.id.currency_flag);
            currencyNameText = itemView.findViewById(R.id.currency_title);
            rootView = itemView.findViewById(R.id.rootView);
        }
    }
}
