package com.deerangle.dacc.android.ui.home.homemain

import androidx.core.widget.addTextChangedListener
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
import java.text.DecimalFormat
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeMainFragment : BaseFragment<FragmentHomeMainBinding, HomeMainViewModel>(R.layout.fragment_home_main), CurrencyPickerDialog.Delegate {

    override val viewModel: HomeMainViewModel by viewModel()
    var flag = true

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
                    HomeMainCommand.FinishCalculation -> {
                        updateToAmount(binding?.frgHomeMainEtFromAmount?.text.toString())
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

        binding?.frgHomeMainEtFromAmount?.addTextChangedListener {
            updateToAmount(it.toString())
        }

        binding?.frgHomeMainEtToAmount?.addTextChangedListener {
            updateFromAmount(it.toString())
        }

        binding?.frgHomeMainEtCommission?.addTextChangedListener {
            var decodeS: String = it.toString()
            if (decodeS == "") decodeS = "0.0"
            viewModel.commissionRate = decodeS.toDouble() / 100.0
            updateToAmount(binding?.frgHomeMainEtFromAmount?.text.toString())
        }
    }

    private fun updateToAmount(from: String) {
        var decodeS: String = from
        if (decodeS == "") decodeS = "0.0"
        var resultTwo: Double = decodeS.toDouble() * viewModel.getResultFromRate() * (1.0 + viewModel.commissionRate)
        if (resultTwo > 99999999999.0) {
            resultTwo = -1.0
        }
        val finStrTwo: String
        val resultFormat = DecimalFormat("0.00")
        finStrTwo = resultFormat.format(resultTwo)
        val subOne = if (finStrTwo.length <= 11) finStrTwo.length else 11
        if (flag) {
            flag = false
            if (decodeS == "0.0") {
                binding?.frgHomeMainEtToAmount?.setText("")
            } else {
                binding?.frgHomeMainEtToAmount?.setText(finStrTwo.substring(0, subOne))
            }
        } else {
            flag = true
        }
    }

    private fun updateFromAmount(to: String) {
        var decodeS: String = to
        if (decodeS == "") decodeS = "0.0"
        var resultOne: Double = decodeS.toDouble() * viewModel.getResultToRate() * (1.0 + viewModel.commissionRate)
        if (resultOne > 99999999999.0) {
            resultOne = -1.0
        }
        val finStrOne: String
        val resultFormat = DecimalFormat("0.00")
        finStrOne = resultFormat.format(resultOne)
        val subTwo = if (finStrOne.length <= 11) finStrOne.length else 11
        if (flag) {
            flag = false
            if (decodeS == "0.0") {
                binding?.frgHomeMainEtFromAmount?.setText("")
            } else {
                binding?.frgHomeMainEtFromAmount?.setText(finStrOne.substring(0, subTwo))
            }
        } else {
            flag = true
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
