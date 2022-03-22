package com.deerangle.dacc.android.ui.home.homemain

import com.deerangle.dacc.android.FragmentHomeMainBinding
import com.deerangle.dacc.android.R
import com.deerangle.dacc.android.ui.home.homemain.currencypicker.CurrencyPickerDialog
import com.deerangle.dacc.android.utils.base.BaseFragment
import com.deerangle.dacc.android.utils.helpers.CurrencyHelper
import com.deerangle.dacc.android.utils.helpers.RateHelper
import com.deerangle.dacc.data.model.Currency
import com.deerangle.dacc.data.model.CurrencyRate
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
                        showCurrencyPicker("FROM_CURRENCY")
                    }
                    HomeMainCommand.ChooseToCurrency -> {
                        showCurrencyPicker("TO_CURRENCY")
                    }
                    else -> {}
                }
            }
        }
        viewModel.fromCurrency.addObserver { currency ->
            if (currency != null) {
                binding?.frgHomeMainTvFromCode?.text = currency.code
                binding?.frgHomeMainIvFromFlag?.setImageResource(currency.flag)

                if (currency.code?.lowercase() == "usd") {
                    viewModel.setFromRate(CurrencyRate(1.0, 1.0, 1.0, 1.0, 1.0))
                } else {
                    val rate = RateHelper.getByCurrencyCode(currency.code ?: "cny")
                    viewModel.setFromRate(rate)
                }
            }
        }
        viewModel.toCurrency.addObserver { currency ->
            if (currency != null) {
                binding?.frgHomeMainTvToCode?.text = currency.code
                binding?.frgHomeMainIvToFlag?.setImageResource(currency.flag)

                if (currency.code?.lowercase() == "usd") {
                    viewModel.setToRate(CurrencyRate(1.0, 1.0, 1.0, 1.0, 1.0))
                } else {
                    val rate = RateHelper.getByCurrencyCode(currency.code ?: "cny")
                    viewModel.setToRate(rate)
                }
            }
        }
        viewModel.fromRate.addObserver { rate ->
            if (rate != null) {
                viewModel.calculateRate()
            }
        }
        viewModel.toRate.addObserver { rate ->
            if (rate != null) {
                viewModel.calculateRate()
            }
        }
        viewModel.resultFromRate.addObserver { rate ->
            if (rate != null) {
                binding?.frgHomeMainTvFromRate?.text = rate.toString()
            }
        }
        viewModel.resultToRate.addObserver { rate ->
            if (rate != null) {
                binding?.frgHomeMainTvToRate?.text = rate.toString()
            }
        }
    }

    override fun setupView() {
        viewModel.setFromCurrency(CurrencyHelper.getCurrencies("gbp")[0])
        viewModel.setToCurrency(CurrencyHelper.getCurrencies("cny")[0])

        binding?.frgHomeMainSbgBank?.setOnPositionChangedListener {
            viewModel.bankOrg = it
            viewModel.calculateRate()
        }
    }

    private fun showCurrencyPicker(viewTag: String) {
        val dialog = CurrencyPickerDialog(viewTag, this)
        childFragmentManager.let {
            dialog.show(it, tag)
        }
    }

    override fun onCurrencySelect(viewTag: String, currency: Currency) {
        if (viewTag == "FROM_CURRENCY") {
            viewModel.setFromCurrency(currency)
        } else if (viewTag == "TO_CURRENCY") {
            viewModel.setToCurrency(currency)
        }
    }
}
