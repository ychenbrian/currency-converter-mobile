import io.gitlab.arturbosch.detekt.Detekt
import io.gitlab.arturbosch.detekt.DetektCreateBaselineTask

plugins {
    plugin(Plugins.detekt) apply true
}

buildscript {
    repositories {
        mavenCentral()
        google()
        gradlePluginPortal()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.1.1")
        plugin(Plugins.mokoNetwork)
        plugin(Plugins.mokoUnits)
        plugin(Plugins.sqlDelight)
        plugin(Plugins.kotlinSerialization)
    }
}

allprojects {
    apply("$rootDir/config/ktlint.gradle")

    repositories {
        google()
        mavenCentral()
        maven(url = "https://jitpack.io")
    }

    plugins.withId(Plugins.androidLibrary.id) {
        configure<com.android.build.gradle.LibraryExtension> {
            compileSdkVersion(Configs.AndroidVersion.compile)

            defaultConfig {
                minSdkVersion(Configs.AndroidVersion.minimum)
                targetSdkVersion(Configs.AndroidVersion.target)
            }
        }
    }

    configurations.configureEach {
        resolutionStrategy {
            force(Deps.MultiPlatform.coroutines)
        }
    }
}

tasks.register("clean", Delete::class).configure {
    group = "build"
    delete(rootProject.buildDir)
}

val analysisDir = file(projectDir)
val baselineFile = file("$rootDir/config/detekt/baseline.xml")
val configFile = file("$rootDir/config/detekt/detekt.yml")
val statisticsConfigFile = file("$rootDir/config/detekt/statistics.yml")

val kotlinFiles = "**/*.kt"
val kotlinScriptFiles = "**/*.kts"
val resourceFiles = "**/resources/**"
val buildFiles = "**/build/**"
val buildSrcDir = "buildSrc/**"

val detektFormat by tasks.registering(Detekt::class) {
    description = "Formats whole project."
    parallel = true
    disableDefaultRuleSets = true
    buildUponDefaultConfig = true
    autoCorrect = true
    setSource(analysisDir)
    config.setFrom(listOf(statisticsConfigFile, configFile))
    include(kotlinFiles)
    include(kotlinScriptFiles)
    exclude(resourceFiles)
    exclude(buildFiles)
    exclude(buildSrcDir)
    baseline.set(baselineFile)
    reports {
        xml.enabled = false
        html.enabled = true
        txt.enabled = false
        sarif.enabled = false
    }
}

val detektAll by tasks.registering(Detekt::class) {
    description = "Runs the whole project at once."
    parallel = true
    buildUponDefaultConfig = true
    setSource(analysisDir)
    config.setFrom(listOf(statisticsConfigFile, configFile))
    include(kotlinFiles)
    include(kotlinScriptFiles)
    exclude(resourceFiles)
    exclude(buildFiles)
    exclude(buildSrcDir)
    baseline.set(baselineFile)
    reports {
        xml.enabled = false
        html.enabled = true
        txt.enabled = false
        sarif.enabled = false
    }
}

// DO NOT RUN THIS COMMAND
val detektProjectBaseline by tasks.registering(DetektCreateBaselineTask::class) {
    description = "Overrides current baseline."
    buildUponDefaultConfig.set(true)
    ignoreFailures.set(true)
    parallel.set(true)
    setSource(analysisDir)
    config.setFrom(listOf(statisticsConfigFile, configFile))
    include(kotlinFiles)
    include(kotlinScriptFiles)
    exclude(resourceFiles)
    exclude(buildFiles)
    exclude(buildSrcDir)
    baseline.set(baselineFile)
}
