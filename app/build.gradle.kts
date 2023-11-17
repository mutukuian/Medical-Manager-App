plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    id("kotlin-kapt")
    id("com.google.gms.google-services")

}

android {
    namespace = "com.medicalmanager"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.medicalmanager"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(Deps.core)
    implementation (CoroutinesLifecycleScope.lifeCycleRuntime)
    implementation(JetpackCompose.composeActivity)
//    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation(JetpackCompose.composeUi)
//    implementation("androidx.compose.ui:ui-graphics")
    implementation(JetpackCompose.composeUiToolingPreview)
    implementation(JetpackCompose.composeMaterial)
    implementation("androidx.compose.material3:material3:1.1.2")
    implementation("com.google.firebase:firebase-auth-ktx:22.1.2")
    implementation("com.google.firebase:firebase-firestore-ktx:24.8.1")
    implementation("com.google.firebase:firebase-firestore:24.8.1")
    implementation("com.google.android.gms:play-services-auth:20.7.0")


    testImplementation(TestImplementation.junit)
    androidTestImplementation(AndroidTestImplementation.junit)
    androidTestImplementation(AndroidTestImplementation.espresso)


    androidTestImplementation(ComposeAndroidTestImplementation.composeUiTest)
    debugImplementation(ComposeDebugImplementation.toolingUi)
    debugImplementation(ComposeDebugImplementation.manifestTest)

    implementation (DaggerHilt.hilt)
    implementation (DaggerHilt.hiltComposeNavigation)
    kapt (DaggerHilt.hiltCompiler)

    implementation(JetpackCompose.navigation)
    implementation(CoilImageLoadingLib.coil)

    // Timber
    implementation("com.jakewharton.timber:timber:4.7.1")

    //material 3 core
    implementation("com.maxkeppeler.sheets-compose-dialogs:core:1.0.2")
    //material 3 - Calendar
    implementation("com.maxkeppeler.sheets-compose-dialogs:calendar:1.0.2")
    //material 3 clock
    implementation("com.maxkeppeler.sheets-compose-dialogs:clock:1.0.2")



    // JUnit for unit testing
    testImplementation("junit:junit:4.13.2")
    testImplementation ("org.mockito:mockito-core:3.11.2")
    testImplementation ("androidx.arch.core:core-testing:2.2.0")

    // AndroidJUnitRunner and rules for UI testing
    androidTestImplementation("androidx.test:runner:1.5.2")
    androidTestImplementation("androidx.test:rules:1.5.0")

    // Espresso for UI testing
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    // Firebase Test Lab for Firebase service testing
    androidTestImplementation("com.google.firebase:firebase-test-lab:1.3.6")

    //assertion
    testImplementation("com.google.truth:truth:1.1")

    testImplementation ("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.0")

}