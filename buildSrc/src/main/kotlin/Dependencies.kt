import org.gradle.api.artifacts.dsl.DependencyHandler

const val kotlinVersion = "1.4.10"

object AppConfigSettings {
    const val minSdk = 21
    const val maxSdk = 30
    const val buildTools = "30.0.2"
    const val packageName = "com.example.soccercoding"
}

object GradlePlugins {
    const val android = "com.android.tools.build:gradle:4.0.1"
    const val safeArgs = "androidx.navigation:navigation-safe-args-gradle-plugin:2.3.0"
    const val hilt = "com.google.dagger:hilt-android-gradle-plugin:2.28-alpha"
}

object Dependencies {
    object Kotlin {
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlinVersion"
        const val fragmentKtx = "androidx.fragment:fragment-ktx:_"
    }

    object AndroidX {
        const val appcompat = "androidx.appcompat:appcompat:_"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:_"
        const val coreKtx = "androidx.core:core-ktx:_"
        const val recyclerView = "androidx.recyclerview:recyclerview:_"
        const val materialDesign = "com.google.android.material:material:_"
    }

    object Injection {
        const val hilt = "com.google.dagger:hilt-android:_"
        const val hiltAndroidCompiler = "com.google.dagger:hilt-android-compiler:_"
        const val hiltViewModel = "androidx.hilt:hilt-lifecycle-viewmodel:_"
        const val hiltCompiler = "androidx.hilt:hilt-compiler:_"
    }

    object Coroutines {
        const val common = "org.jetbrains.kotlinx:kotlinx-coroutines-core:_"
        const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:_"
        const val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:_"
    }

    object Navigation {
        const val navKtx = "androidx.navigation:navigation-fragment-ktx:_"
        const val navUiKtx = "androidx.navigation:navigation-ui-ktx:_"
    }

    object Network {
        const val retrofit = "com.squareup.retrofit2:retrofit:_"
        const val retrofitCallAdapter = "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:_"
        const val retrofitConverter = "com.squareup.retrofit2:converter-moshi:_"
        const val retrofitClient = "com.squareup.okhttp3:logging-interceptor:_"
    }

    object Parsing {
        const val moshi = "com.squareup.moshi:moshi-kotlin:_"
        const val moshiCodeGen = "com.squareup.moshi:moshi-kotlin-codegen:_"
        const val moshiAdapters = "com.squareup.moshi:moshi-adapters:_"
    }

    object LocaleDb {
        const val room = "androidx.room:room-runtime:_"
        const val roomCompiler = "androidx.room:room-compiler:_"
        const val roomKotlin = "androidx.room:room-ktx:_"
    }

    fun DependencyHandler.implementationAppModule() {
        add("implementation", Kotlin.fragmentKtx)
        add("implementation", Kotlin.stdlib)
        add("implementation", AndroidX.appcompat)
        add("implementation", AndroidX.coreKtx)
        add("implementation", AndroidX.constraintLayout)
        add("implementation", AndroidX.recyclerView)
        add("implementation", AndroidX.materialDesign)
        add("implementation", Navigation.navKtx)
        add("implementation", Navigation.navUiKtx)
        add("implementation", Network.retrofit)
        add("implementation", Network.retrofitClient)
        add("implementation", Network.retrofitCallAdapter)
        add("implementation", Network.retrofitConverter)
        add("implementation", Parsing.moshi)
        add("implementation", Parsing.moshiAdapters)
        add("kapt", Parsing.moshiCodeGen)
        add("implementation", Coroutines.android)
        add("implementation", Coroutines.common)
        add("implementation", Injection.hilt)
        add("implementation", Injection.hiltViewModel)
        add("kapt", Injection.hiltAndroidCompiler)
        add("kapt", Injection.hiltCompiler)
    }
}