package com.deerangle.dacc.android.application

import android.content.ContentResolver
import com.deerangle.dacc.android.utils.helpers.ResourceProvider
import com.deerangle.dacc.android.utils.helpers.ResourceProviderImpl
import com.deerangle.dacc.ui.home.HomeViewModel
import com.deerangle.dacc.ui.home.homemain.HomeMainViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

@Suppress("EXPERIMENTAL_API_USAGE")
object AppModules {

    private val viewModels = module {
        viewModel { HomeViewModel() }
        viewModel { HomeMainViewModel() }
    }

    private val helpersModule = module {
        single<ResourceProvider> { ResourceProviderImpl }
        single<ContentResolver> { androidContext().contentResolver }
    }

    val modules = listOf(viewModels, helpersModule)
}
