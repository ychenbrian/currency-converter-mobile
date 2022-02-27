package com.deerangle.dacc.di

import kotlinx.serialization.ExperimentalSerializationApi
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

@ExperimentalSerializationApi
fun initKoin(appDeclaration: KoinAppDeclaration = {}) = startKoin {
    appDeclaration()
}

// called by iOS etc
@ExperimentalSerializationApi
fun initKoin() = initKoin {}
