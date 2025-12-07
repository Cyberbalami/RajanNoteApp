plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.rajannoteapp"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.rajannoteapp"
        minSdk = 29
        targetSdk = 36
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

    testOptions {
        unitTests.isIncludeAndroidResources = true
    }
}

dependencies {

    // --- Normal App Dependencies ---
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)

    // --- Unit Test Dependencies ---
    testImplementation(libs.junit) // your existing JUnit version
    testImplementation("org.robolectric:robolectric:4.10.3")  // Android-like environment
    testImplementation("org.mockito:mockito-core:5.4.0")      // mocking framework
    testImplementation("androidx.test:core:1.5.0")            // ApplicationProvider

    // --- Android Instrumented UI Test Dependencies ---
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation("androidx.test.espresso:espresso-intents:3.5.1")
    androidTestImplementation("androidx.test:rules:1.5.0")
    androidTestImplementation("androidx.test:runner:1.5.2")
}
