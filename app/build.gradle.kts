plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.github_viewer"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.github_viewer"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.gson)
    implementation (libs.logging.interceptor) // Para debug
    implementation (libs.picasso) // Para carregar imagens

    // OkHttp (for logging)
    implementation(libs.logging.interceptor.v493)


    // Glide (for image loading)
    implementation(libs.github.glide)
    annotationProcessor(libs.compiler)

    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
    implementation(libs.kotlinx.coroutines.core)

}