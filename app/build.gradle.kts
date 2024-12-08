plugins {
    alias(libs.plugins.android.application)
    id("com.google.gms.google-services") // Google Services plugin for Firebase
}

android {
    namespace = "com.example.flavorfinderapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.flavorfinderapp"
        minSdk = 29
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    // Firebase BOM (Bill of Materials) to manage versions consistently
    implementation(platform("com.google.firebase:firebase-bom:32.7.0")) // Use the latest BOM version

    // Firebase dependencies
    implementation("com.google.firebase:firebase-auth")             // Firebase Authentication
    implementation("com.google.firebase:firebase-firestore")         // Firebase Firestore
    implementation("com.google.firebase:firebase-appcheck-playintegrity") // Firebase App Check

    // Firebase UI for Authentication
    implementation("com.firebaseui:firebase-ui-auth:8.0.0")

    // Core AndroidX dependencies
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.constraintlayout)

    // Lifecycle components
    implementation(libs.lifecycle.livedata.ktx)
    implementation(libs.lifecycle.viewmodel.ktx)

    // Navigation components
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)

    // Retrofit and Gson dependencies for API calls
    implementation("com.squareup.retrofit2:retrofit:2.9.0")          // Retrofit library
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")    // Gson converter for Retrofit
    implementation("com.squareup.okhttp3:logging-interceptor:4.10.0") // Logging interceptor

    // Glide for image loading
    implementation("com.github.bumptech.glide:glide:4.14.2")
    annotationProcessor("com.github.bumptech.glide:compiler:4.14.2")

    // Test dependencies
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}
