import java.util.Properties

plugins {
    plugin(Plugins.androidApplication)
    plugin(Plugins.kotlinAndroid)
    plugin(Plugins.kotlinKapt)
    plugin(Plugins.mokoUnits)
}

allprojects {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
        maven(url = "https://jitpack.io")
    }
}

android {
    compileSdkVersion(Configs.AndroidVersion.compile)

    buildFeatures.dataBinding = true

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }

    dexOptions {
        javaMaxHeapSize = "2g"
    }

    defaultConfig {
        minSdkVersion(Configs.AndroidVersion.minimum)
        targetSdkVersion(Configs.AndroidVersion.target)

        applicationId = Configs.Production.id

        versionCode = 1
        versionName = "0.1.0"

        vectorDrawables.useSupportLibrary = true
    }

    val keystoreProperties = Properties().apply {
        load(project.rootProject.file("androidApp/keystore.properties").inputStream())
    }
    val currencyKeystorePath = keystoreProperties.getProperty("keystorePath")
    val currencyKeystorePassword = keystoreProperties.getProperty("keystorePassword")
    val currencyKeyAlias = keystoreProperties.getProperty("keyAlias")
    val currencyKeyPassword = keystoreProperties.getProperty("keyPassword")

    signingConfigs {
        register("release") {
            storeFile = file(currencyKeystorePath)
            storePassword = currencyKeystorePassword
            keyAlias = currencyKeyAlias
            keyPassword = currencyKeyPassword
        }
    }

    buildTypes {
        getByName("release") {
            signingConfig = signingConfigs.getByName("release")
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
        getByName("debug") {
            signingConfig = signingConfigs.getByName("debug")
            isDebuggable = true
            applicationIdSuffix = ".debug"
        }
    }

    flavorDimensions("default")

    productFlavors {
        create("develop") {
            applicationId = Configs.Develop.id
            manifestPlaceholders["appLabel"] = "@string/app_name_dev"
            minSdkVersion(Configs.AndroidVersion.minimum)
            targetSdkVersion(Configs.AndroidVersion.target)
        }
        create("production") {
            applicationId = Configs.Production.id
            manifestPlaceholders["appLabel"] = "@string/app_name_prod"
            minSdkVersion(Configs.AndroidVersion.minimum)
            targetSdkVersion(Configs.AndroidVersion.target)
        }
    }

    packagingOptions {
        exclude("META-INF/*.kotlin_module")
    }
}

dependencies {
    implementation(Deps.Android.navigationUI)
    implementation(Deps.Android.navigationFragment)
    implementation(Deps.Android.appCompat)
    implementation(Deps.Android.material)
    implementation(Deps.Android.recyclerView)
    implementation(Deps.Android.swipeRefreshLayout)
    implementation(Deps.Android.mokoMvvmDataBinding)

    // Koin
    implementation(Deps.Koin.android)
    implementation(Deps.Koin.androidCompat)
    implementation(Deps.Koin.androidCompose)
    implementation(Deps.Koin.androidWorkmanager)

    // UI
    implementation(Deps.Android.constraintLayout)
    implementation(Deps.Android.spinKit)
    implementation(Deps.Android.glide)
    implementation("com.github.CymChad:BaseRecyclerViewAdapterHelper:3.0.4")
    implementation("com.qmuiteam:qmui:2.0.1")

    // Epoxy
    implementation(Deps.Android.Epoxy.android)
    implementation(Deps.Android.Epoxy.databinding)
    kapt(Deps.Android.Epoxy.processor)

    // Helper
    implementation("com.akexorcist:localization:1.2.10")

    // Modules
    implementation(project(":shared"))

    debugImplementation("com.amitshekhar.android:debug-db:1.0.6")
}

multiplatformUnits {
    classesPackage = "${Configs.packageId}.android"
    dataBindingPackage = "${Configs.packageId}.android"
    layoutsSourceSet = "main"
}

fun com.android.build.api.dsl.BaseFlavor.buildConfigString(name: String, value: String) =
    buildConfigField("String", name, "\"$value\"")
