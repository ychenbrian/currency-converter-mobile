object Versions {
    const val kotlinTest = "1.5.30"
    const val kotlinxSerializationPlugin = "1.5.30"
    const val kotlinxSerialization = "1.2.2"
    const val kotlinxSerializationJson = "1.2.1"
    const val kotlinxDateTime = "0.1.1"
    const val coroutines = "1.5.1-native-mt"
    const val buildKonfig = "0.9.0"
    const val ktorClient = "1.6.1"
    const val koin = "3.1.2"
    const val detekt = "1.17.0"
    const val kodein = "7.7.0"
    const val sqlDelight = "1.5.0"

    // Moko
    const val mokoGraphics = "0.8.0"
    const val mokoParcelize = "0.7.1"
    const val mokoResources = "0.16.2"
    const val mokoMvvm = "0.11.0"
    const val mokoErrors = "0.4.0"
    const val mokoNetwork = "0.16.0"
    const val mokoUnits = "0.6.1"
    const val mokoPermissions = "0.10.1"
    const val mokoMedia = "0.9.0"
    const val mokoFields = "0.8.0"
    const val mokoTest = "0.4.0"

    const val multiplatformSettings = "0.7.7"
    const val napier = "1.5.0"

    // Android
    const val epoxy = "3.8.0"
    const val spinKit = "1.4.0"
    const val ktxNavigation = "2.3.5"

    const val material = "1.4.0"
    const val recyclerView = "1.2.1"
    const val swipeRefreshLayout = "1.1.0"
    const val constraintLayout = "2.0.4"
    const val lifecycle = "2.2.0"
    const val glide = "4.12.0"
    const val hilt = "2.38.1"
    const val androidAppCompat = "1.3.0"
    const val espressoCore = "3.2.0"
    const val testRunner = "1.2.0"
    const val testExtJunit = "1.1.1"
    const val multidex = "2.0.1"
}

object Plugins {
    val androidApplication = GradlePlugin(id = "com.android.application")
    val androidLibrary = GradlePlugin(id = "com.android.library")
    val kotlinMultiplatform = GradlePlugin(id = "org.jetbrains.kotlin.multiplatform")
    val kotlinKapt = GradlePlugin(id = "kotlin-kapt")
    val kotlinAndroid = GradlePlugin(id = "kotlin-android")
    val kotlinParcelize = GradlePlugin(id = "kotlin-parcelize")
    val kotlinSerialization = GradlePlugin(
        id = "org.jetbrains.kotlin.plugin.serialization",
        module = "org.jetbrains.kotlin:kotlin-serialization:${Versions.kotlinxSerializationPlugin}"
    )
    val sqlDelight = GradlePlugin(
        id = "com.squareup.sqldelight",
        module = "com.squareup.sqldelight:gradle-plugin:${Versions.sqlDelight}"
    )

    val buildKonfig = GradlePlugin(id = "com.codingfeline.buildkonfig", version = Versions.buildKonfig)
    val mobileMultiplatform = GradlePlugin(id = "dev.icerock.mobile.multiplatform")
    val iosFramework = GradlePlugin(id = "dev.icerock.mobile.multiplatform.ios-framework")

    val mokoNetwork = GradlePlugin(
        id = "dev.icerock.mobile.multiplatform-network-generator",
        module = "dev.icerock.moko:network-generator:${Versions.mokoNetwork}"
    )
    val mokoResources = GradlePlugin(
        id = "dev.icerock.mobile.multiplatform-resources",
        module = "dev.icerock.moko:resources-generator:${Versions.mokoResources}"
    )
    val mokoUnits = GradlePlugin(
        id = "dev.icerock.mobile.multiplatform-units",
        module = "dev.icerock.moko:units-generator:${Versions.mokoUnits}"
    )

    val detekt = GradlePlugin(
        id = "io.gitlab.arturbosch.detekt",
        version = Versions.detekt
    )
}

object Deps {
    object Koin {
        const val test = "io.insert-koin:koin-test:${Versions.koin}"
        const val core = "io.insert-koin:koin-core:${Versions.koin}"
        const val android = "io.insert-koin:koin-android:${Versions.koin}"
        const val androidCompat = "io.insert-koin:koin-android-compat:${Versions.koin}"
        const val androidWorkmanager = "io.insert-koin:koin-androidx-workmanager:${Versions.koin}"
        const val androidCompose = "io.insert-koin:koin-androidx-compose:${Versions.koin}"
    }

    object Android {
        const val appCompat = "androidx.appcompat:appcompat:${Versions.androidAppCompat}"
        const val material = "com.google.android.material:material:${Versions.material}"
        const val recyclerView = "androidx.recyclerview:recyclerview:${Versions.recyclerView}"
        const val swipeRefreshLayout = "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.swipeRefreshLayout}"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
        const val navigationUI = "androidx.navigation:navigation-ui-ktx:${Versions.ktxNavigation}"
        const val navigationFragment = "androidx.navigation:navigation-fragment-ktx:${Versions.ktxNavigation}"
        const val spinKit = "com.github.ybq:Android-SpinKit:${Versions.spinKit}"
        const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
        const val lifecycle = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle}"
        const val ktorClientOkHttp = "io.ktor:ktor-client-okhttp:${Versions.ktorClient}"
        const val mokoMvvmDataBinding = "dev.icerock.moko:mvvm-databinding:${Versions.mokoMvvm}"

        object Epoxy {
            const val android = "com.airbnb.android:epoxy:${Versions.epoxy}"
            const val processor = "com.airbnb.android:epoxy-processor:${Versions.epoxy}" // kapt
            const val databinding = "com.airbnb.android:epoxy-databinding:${Versions.epoxy}"
        }

        object Tests {
            const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espressoCore}"
            const val kotlinTestJUnit = "org.jetbrains.kotlin:kotlin-test-junit:${Versions.kotlinTest}"
            const val testCore = "androidx.test:core:1.3.0"
            const val robolectric = "org.robolectric:robolectric:4.3"
            const val testRunner = "androidx.test:runner:${Versions.testRunner}"
            const val testRules = "androidx.test:rules:${Versions.testRunner}"
            const val testExtJunit = "androidx.test.ext:junit:${Versions.testExtJunit}"
        }
    }

    object MultiPlatform {
        const val kotlinSerialization =
            "org.jetbrains.kotlinx:kotlinx-serialization-core:${Versions.kotlinxSerialization}"
        const val kotlinSerializationJson =
            "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.kotlinxSerializationJson}"
        const val kotlinxDateTime = "org.jetbrains.kotlinx:kotlinx-datetime:${Versions.kotlinxDateTime}"
        const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
        const val ktorClient = "io.ktor:ktor-client-core:${Versions.ktorClient}"
        const val ktorClientLogging = "io.ktor:ktor-client-logging:${Versions.ktorClient}"
        const val ktorClientAuth = "io.ktor:ktor-client-auth:${Versions.ktorClient}"
        const val ktorSerialization = "io.ktor:ktor-client-serialization:${Versions.ktorClient}"
        const val buildKonfig = "com.codingfeline.buildkonfig:buildkonfig-gradle-plugin:${Versions.buildKonfig}"
        const val koinCore = "io.insert-koin:koin-core:${Versions.koin}"
        const val koinTest = "io.insert-koin:koin-test:${Versions.koin}"
        val mokoResources = "dev.icerock.moko:resources:${Versions.mokoResources}".defaultMPL(ios = true)
        val mokoParcelize = "dev.icerock.moko:parcelize:${Versions.mokoParcelize}".defaultMPL(ios = true)
        val mokoGraphics = "dev.icerock.moko:graphics:${Versions.mokoGraphics}".defaultMPL(ios = true)
        val mokoMvvmCore = "dev.icerock.moko:mvvm-core:${Versions.mokoMvvm}".defaultMPL(ios = true)
        val mokoMvvmLiveData = "dev.icerock.moko:mvvm-livedata:${Versions.mokoMvvm}".defaultMPL(ios = true)
        val mokoMvvmState = "dev.icerock.moko:mvvm-state:${Versions.mokoMvvm}".defaultMPL(ios = true)
        val mokoErrors = "dev.icerock.moko:errors:${Versions.mokoErrors}".defaultMPL(ios = true)
        val mokoNetwork = "dev.icerock.moko:network:${Versions.mokoNetwork}".defaultMPL(ios = true)
        val mokoNetworkErrors = "dev.icerock.moko:network-errors:${Versions.mokoNetwork}".defaultMPL(ios = true)
        val mokoPermissions = "dev.icerock.moko:permissions:${Versions.mokoPermissions}".defaultMPL(ios = true)
        val mokoMedia = "dev.icerock.moko:media:${Versions.mokoMedia}".defaultMPL(ios = true)
        val mokoUnits = "dev.icerock.moko:units:${Versions.mokoUnits}".defaultMPL(ios = true)
        val mokoFields = "dev.icerock.moko:fields:${Versions.mokoFields}".defaultMPL(ios = true)
        val multiplatformSettings =
            "com.russhwolf:multiplatform-settings:${Versions.multiplatformSettings}".defaultMPL(ios = true)
        const val multiplatformSettingsNoArg =
            "com.russhwolf:multiplatform-settings-no-arg:${Versions.multiplatformSettings}"
        val napier = "io.github.aakira:napier:${Versions.napier}".let {
            MultiPlatformLibrary(
                common = it
            )
        }

        object Tests {
            const val kotlinTest = "org.jetbrains.kotlin:kotlin-test-common:${Versions.kotlinTest}"
            const val kotlinTestAnnotations =
                "org.jetbrains.kotlin:kotlin-test-annotations-common:${Versions.kotlinTest}"
            const val mokoMvvmTest = "dev.icerock.moko:mvvm-test:${Versions.mokoMvvm}"
            const val mokoTestCore = "dev.icerock.moko:test-core:${Versions.mokoTest}"
            const val mokoUnitsTest = "dev.icerock.moko:units-test:${Versions.mokoUnits}"
            const val multiplatformSettingsTest =
                "com.russhwolf:multiplatform-settings-test:${Versions.multiplatformSettings}"
            const val ktorClientMock = "io.ktor:ktor-client-mock:${Versions.ktorClient}"
        }
    }

    object Detekt {
        const val detektFormatting = "io.gitlab.arturbosch.detekt:detekt-formatting:${Versions.detekt}"
    }

    object SqlDelight {
        const val runtime = "com.squareup.sqldelight:runtime:${Versions.sqlDelight}"
        const val androidDriver = "com.squareup.sqldelight:android-driver:${Versions.sqlDelight}"
        const val nativeDriver = "com.squareup.sqldelight:native-driver:${Versions.sqlDelight}"
    }
}