plugins {
    plugin(Plugins.androidLibrary)
    plugin(Plugins.kotlinMultiplatform)
    plugin(Plugins.kotlinSerialization)
    plugin(Plugins.mobileMultiplatform)
    plugin(Plugins.iosFramework)
    plugin(Plugins.kotlinParcelize)
    plugin(Plugins.mokoNetwork)
    plugin(Plugins.sqlDelight)
}

val mppLibs = listOf(
    Deps.MultiPlatform.multiplatformSettings,
    Deps.MultiPlatform.napier,
    Deps.MultiPlatform.mokoParcelize,
    Deps.MultiPlatform.mokoMvvmCore,
    Deps.MultiPlatform.mokoMvvmLiveData,
    Deps.MultiPlatform.mokoMvvmState,
    Deps.MultiPlatform.mokoUnits,
    Deps.MultiPlatform.mokoFields
)

dependencies {
    commonMainImplementation(Deps.MultiPlatform.kotlinSerializationJson)
    commonMainImplementation(Deps.MultiPlatform.multiplatformSettingsNoArg)
    commonMainImplementation(Deps.MultiPlatform.coroutines)

    commonMainImplementation(Deps.MultiPlatform.ktorClient)
    commonMainImplementation(Deps.MultiPlatform.ktorClientLogging)
    commonMainImplementation(Deps.MultiPlatform.ktorClientAuth)
    commonMainImplementation(Deps.MultiPlatform.ktorSerialization)

    commonMainApi(Deps.MultiPlatform.koinTest)
    commonMainApi(Deps.MultiPlatform.koinCore)
    commonMainImplementation(Deps.MultiPlatform.mokoParcelize.common)
    commonMainImplementation(Deps.MultiPlatform.mokoNetwork.common)

    androidMainImplementation(Deps.Android.lifecycle)
    androidMainApi(Deps.Android.mokoMvvmDataBinding)

    mppLibs.forEach { commonMainApi(it.common) }

    commonTestImplementation(Deps.MultiPlatform.Tests.mokoTestCore)
    commonTestImplementation(Deps.MultiPlatform.Tests.mokoMvvmTest)
    commonTestImplementation(Deps.MultiPlatform.Tests.mokoUnitsTest)
    commonTestImplementation(Deps.MultiPlatform.Tests.multiplatformSettingsTest)
    commonTestImplementation(Deps.MultiPlatform.Tests.ktorClientMock)

    commonMainImplementation(Deps.SqlDelight.runtime)
    androidMainImplementation(Deps.SqlDelight.androidDriver)
    iosMainImplementation(Deps.SqlDelight.nativeDriver)
}

android {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

framework {
    mppLibs.forEach { export(it) }
}
