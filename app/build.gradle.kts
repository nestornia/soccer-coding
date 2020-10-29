import Dependencies.implementationAppModule

plugins {
    id("com.android.application")
    id("dagger.hilt.android.plugin")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("plugin.serialization")
    kotlin("kapt")
}

android {
    compileSdkVersion(AppConfigSettings.maxSdk)
    buildToolsVersion(AppConfigSettings.buildTools)

    defaultConfig {
        applicationId = AppConfigSettings.packageName
        minSdkVersion(AppConfigSettings.minSdk)
        targetSdkVersion(AppConfigSettings.maxSdk)
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables.useSupportLibrary = true
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(fileTree(mapOf("include" to listOf("*.jar"), "dir" to "libs")))
    implementationAppModule()
}