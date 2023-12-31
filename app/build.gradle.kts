plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.gms.google-services")
    id ("kotlin-kapt")
    id("com.google.devtools.ksp") version "1.8.10-1.0.9" apply false
    id ("kotlin-parcelize")
}

android {
    namespace = "com.example.appbanhang"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.appbanhang"
        minSdk = 24
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
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        dataBinding = true
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation ("com.squareup.picasso:picasso:2.71828")

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    implementation("com.google.firebase:firebase-database:20.3.0")
    implementation("com.google.firebase:firebase-storage:20.3.0")
    implementation("com.google.firebase:firebase-auth-ktx:22.2.0")
    implementation("com.google.firebase:firebase-auth:22.2.0")
    implementation("com.google.android.gms:play-services-auth:20.7.0")
    implementation("com.google.firebase:firebase-firestore-ktx:24.9.0")
    implementation("com.google.firebase:firebase-firestore:24.9.1")
    implementation("com.google.firebase:firebase-messaging:20.1.7")

    implementation("androidx.room:room-common:2.6.0")
    implementation("androidx.room:room-ktx:2.6.0")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    implementation ("androidx.viewpager2:viewpager2:1.0.0")
    implementation ("com.github.bumptech.glide:glide:4.15.1")
    implementation ("io.reactivex.rxjava3:rxjava:3.0.0")

    implementation ("org.jetbrains.kotlin:kotlin-stdlib:1.9.0")
    implementation ("org.jetbrains.kotlin:kotlin-reflect:1.8.10")
    implementation ("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.9.0")
    implementation ("androidx.core:core-ktx:1.12.0")
    implementation ("androidx.fragment:fragment-ktx:1.6.2")
    implementation ("androidx.activity:activity-ktx:1.8.1")
    implementation ("com.google.dagger:hilt-android:2.38.1")
    kapt ("com.google.dagger:hilt-android-compiler:2.38.1")

    implementation ("androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-alpha03")
    kapt ("androidx.hilt:hilt-compiler:1.0.0")

    implementation ("com.squareup.retrofit2:retrofit:2.6.2")
    implementation ("com.squareup.retrofit2:converter-gson:2.6.0")
}