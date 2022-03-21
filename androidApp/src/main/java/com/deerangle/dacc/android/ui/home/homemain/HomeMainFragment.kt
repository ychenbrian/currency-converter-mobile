package com.deerangle.dacc.android.ui.home.homemain

import com.deerangle.dacc.android.FragmentHomeMainBinding
import com.deerangle.dacc.android.R
import com.deerangle.dacc.android.ui.home.homemain.currencypicker.CurrencyPickerDialog
import com.deerangle.dacc.android.utils.base.BaseFragment
import com.deerangle.dacc.android.utils.helpers.CurrencyHelper
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
                        showCurrencyPicker("FROM_CURRENCY")
                    }
                    HomeMainCommand.ChooseToCurrency -> {
                        showCurrencyPicker("TO_CURRENCY")
                    }
                    else -> {}
                }
            }
        }
    }

    override fun setupView() {
        viewModel.fromCurrency = CurrencyHelper.getCurrencies("gbp").get(0)
        viewModel.toCurrency = CurrencyHelper.getCurrencies("cny").get(0)

        binding?.apply {
            frgHomeMainTvFromCode.text = this@HomeMainFragment.viewModel.fromCurrency?.code
            frgHomeMainIvFromFlag.setImageResource(this@HomeMainFragment.viewModel.fromCurrency?.flag ?: R.drawable.flag_gbp)

            frgHomeMainTvToCode.text = this@HomeMainFragment.viewModel.toCurrency?.code
            frgHomeMainIvToFlag.setImageResource(this@HomeMainFragment.viewModel.toCurrency?.flag ?: R.drawable.flag_cny)
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
            binding?.apply {
                frgHomeMainTvFromCode.text = currency.code
                frgHomeMainIvFromFlag.setImageResource(currency.flag)
            }
        } else if (viewTag == "TO_CURRENCY") {
            binding?.apply {
                frgHomeMainTvToCode.text = currency.code
                frgHomeMainIvToFlag.setImageResource(currency.flag)
            }
        }
    }
}
