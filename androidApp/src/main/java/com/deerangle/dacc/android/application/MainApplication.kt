package com.deerangle.dacc.android.application

import android.app.Application
import android.content.Context
import com.deerangle.dacc.di.initKoin
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier

@Suppress("EXPERIMENTAL_API_USAGE")
class MainApplication : Application() {

    companion object {
        private lateinit var instance: MainApplication

        fun getAppContext(): Context = instance.applicationContext
    }

    override fun onCreate() {
        super.onCreate()

        instance = this

        Napier.base(DebugAntilog())

        initKoin {
            modules(AppModules.modules)
        }
    }
}
