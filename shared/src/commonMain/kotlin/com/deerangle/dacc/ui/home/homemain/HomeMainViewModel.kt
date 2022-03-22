package com.deerangle.dacc.ui.home.homemain

import com.deerangle.dacc.data.model.Currency
import com.deerangle.dacc.data.model.CurrencyRate
import dev.icerock.moko.mvvm.livedata.MutableLiveData
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import org.koin.core.component.KoinComponent

enum class HomeMainCommand {
    ChooseFromCurrency,
    ChooseToCurrency
}

class HomeMainViewModel() : ViewModel(), KoinComponent {

    val command = MutableLiveData<HomeMainCommand?>(null)
    val error = MutableLiveData<String?>(null)
    val loading = MutableLiveData(false)

    var fromCurrency = MutableLiveData<Currency?>(null)
    var toCurrency = MutableLiveData<Currency?>(null)

    // Rate to USD
    var fromRate = MutableLiveData<CurrencyRate?>(null)
    var toRate = MutableLiveData<CurrencyRate?>(null)

    // Rate to each other
    var resultFromRate = MutableLiveData<Double?>(null)
    var resultToRate = MutableLiveData<Double?>(null)

    var bankOrg = 0

    fun setFromCurrency(currency: Currency) {
        fromCurrency.postValue(currency)
    }

    fun setToCurrency(currency: Currency) {
        toCurrency.postValue(currency)
    }

    fun calculateRate() {
        val from = fromRate.value ?: CurrencyRate(1.0, 1.0, 1.0, 1.0, 1.0)
        val to = toRate.value ?: CurrencyRate(1.0, 1.0, 1.0, 1.0, 1.0)
        when (bankOrg) {
            0 -> {
                resultFromRate.postValue(from.visaRate / to.visaRate)
                resultToRate.postValue(to.visaRate / from.visaRate)
            }
            1 -> {
                resultFromRate.postValue(from.mastercardRate / to.mastercardRate)
                resultToRate.postValue(to.mastercardRate / from.mastercardRate)
            }
            2 -> {
                resultFromRate.postValue(from.jcbRate / to.jcbRate)
                resultToRate.postValue(to.jcbRate / from.jcbRate)
            }
            3 -> {
                resultFromRate.postValue(from.dinersClubRate / to.dinersClubRate)
                resultToRate.postValue(to.dinersClubRate / from.dinersClubRate)
            }
            else -> {}
        }
    }

    fun setFromRate(rate: CurrencyRate) {
        fromRate.postValue(rate)
    }

    fun setToRate(rate: CurrencyRate) {
        toRate.postValue(rate)
    }

    fun chooseFrom() {
        command.postValue(HomeMainCommand.ChooseFromCurrency)
    }

    fun chooseTo() {
        command.postValue(HomeMainCommand.ChooseToCurrency)
    }
}
