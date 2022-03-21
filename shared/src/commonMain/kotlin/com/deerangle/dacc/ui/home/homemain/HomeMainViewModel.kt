package com.deerangle.dacc.ui.home.homemain

import com.deerangle.dacc.data.model.Currency
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

    var fromCurrency: Currency? = null
    var toCurrency: Currency? = null

    fun chooseFrom() {
        command.postValue(HomeMainCommand.ChooseFromCurrency)
    }

    fun chooseTo() {
        command.postValue(HomeMainCommand.ChooseToCurrency)
    }
}
