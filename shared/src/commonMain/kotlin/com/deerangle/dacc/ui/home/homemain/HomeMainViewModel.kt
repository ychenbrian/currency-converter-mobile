package com.deerangle.dacc.ui.home.homemain

import dev.icerock.moko.mvvm.livedata.MutableLiveData
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import org.koin.core.component.KoinComponent

enum class HomeMainCommand

class HomeMainViewModel() : ViewModel(), KoinComponent {

    val command = MutableLiveData<HomeMainCommand?>(null)
    val error = MutableLiveData<String?>(null)
    val loading = MutableLiveData(false)
}
