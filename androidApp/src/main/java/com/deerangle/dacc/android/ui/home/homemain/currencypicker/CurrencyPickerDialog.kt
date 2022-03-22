package com.deerangle.dacc.android.ui.home.homemain.currencypicker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.deerangle.dacc.android.FragmentCurrencyPickerBinding
import com.deerangle.dacc.android.R
import com.deerangle.dacc.android.utils.helpers.CurrencyHelper
import com.deerangle.dacc.data.model.Currency

class CurrencyPickerDialog(
    val viewTag: String,
    val delegate: Delegate
) : DialogFragment() {
    interface Delegate {
        fun onCurrencySelect(viewTag: String, currency: Currency)
    }

    private lateinit var binding: FragmentCurrencyPickerBinding
    private var currencyAdapter: CurrencyItemAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = DataBindingUtil.inflate<FragmentCurrencyPickerBinding>(
            layoutInflater,
            R.layout.fragment_currency_picker,
            null,
            false
        ).also {
            it.lifecycleOwner = viewLifecycleOwner
            binding = it
        }.root

        currencyAdapter = CurrencyItemAdapter(object : CurrencyItemCallback {
            override fun currencyClicked(currency: Currency) {
                delegate.onCurrencySelect(viewTag, currency)
                dismiss()
            }
        })

        binding.frgCurrencyPickerRvMain.apply {
            adapter = currencyAdapter
            layoutManager = LinearLayoutManager(context)
        }

        binding.frgCurrencyPickerEtSearch.addTextChangedListener {
            val input: String = it.toString().lowercase()
            val filtered = CurrencyHelper.getCurrencies(input)
            (binding.frgCurrencyPickerRvMain.adapter as CurrencyItemAdapter).submitList(filtered)
        }

        val currencies = CurrencyHelper.getCurrencies()
        (binding.frgCurrencyPickerRvMain.adapter as CurrencyItemAdapter).submitList(currencies)

        return view
    }
}
