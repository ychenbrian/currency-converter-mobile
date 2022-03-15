package com.deerangle.dacc.android.ui.home.homemain

import com.deerangle.dacc.android.FragmentHomeMainBinding
import com.deerangle.dacc.android.ui.home.homemain.currencypicker.CurrencyPickerDialog
import com.deerangle.dacc.android.utils.base.BaseFragment
import com.deerangle.dacc.android.utils.helpers.RateHelper
import com.deerangle.dacc.data.model.Currency
import com.deerangle.dacc.ui.home.homemain.HomeMainCommand
import com.deerangle.dacc.ui.home.homemain.HomeMainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeMainFragment : BaseFragment<FragmentHomeMainBinding, HomeMainViewModel>(R.layout.fragment_home_main), CurrencyPickerDialog.Delegate {

    override val viewModel: HomeMainViewModel by viewModel()

    override fun setupObservers() {
        viewModel.command.addObserver { command ->
            if (command != null) {
                when (command) {
                    HomeMainCommand.ChooseFromCurrency -> {
                        showCurrencyPicker()
                    }
                    else -> {}
                }
            }
        }
    }

    override fun setupView() {
        viewModel.currency = RateHelper.getByCurrencyCode(viewModel.currency.code?.lowercase() ?: "gbp")
    }

    private fun showCurrencyPicker() {
        val dialog = CurrencyPickerDialog(this)
        childFragmentManager.let {
            dialog.show(it, tag)
        }
    }

    override fun onCurrencySelect(currency: Currency) {

    }
}
