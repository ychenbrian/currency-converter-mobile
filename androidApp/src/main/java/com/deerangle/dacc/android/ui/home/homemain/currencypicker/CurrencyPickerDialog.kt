package com.deerangle.dacc.android.ui.home.homemain.currencypicker

import androidx.fragment.app.DialogFragment
import com.deerangle.dacc.android.FragmentCurrencyPickerBinding
import com.deerangle.dacc.data.model.Currency
import com.deerangle.dacc.ui.home.homemain.HomeMainViewModel

class CurrencyPickerDialog(
    val delegate: Delegate,
    val viewModel: HomeMainViewModel, contentLayoutId: Int
): DialogFragment() {
    interface Delegate {
        fun onCurrencySelect(currency: Currency)
    }
    
    private lateinit var binding: FragmentCurrencyPickerBinding
}