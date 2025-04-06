import org.jetbrains.kotlin.konan.properties.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    kotlin("plugin.serialization") version "2.0.0"
}

val localProps = Properties()
val localPropertiesFile = File(rootProject.rootDir, "raffi.properties")
if(localPropertiesFile.exists() && localPropertiesFile.isFile){
    localPropertiesFile.inputStream().use {
        localProps.load(it)
    }
}

android {
    namespace = "kalbe.corp.genexsupabasepoc"
    compileSdk = 35

    defaultConfig {
        applicationId = "kalbe.corp.genexsupabasepoc"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
            buildConfigField("String", "SUPABASE_ANON_KEY", localProps.getProperty("SUPABASE_ANON_KEY"))
            buildConfigField("String", "SUPABASE_URL", localProps.getProperty("SUPABASE_URL"))
        }

        debug {
            buildConfigField("String", "SUPABASE_ANON_KEY", localProps.getProperty("SUPABASE_ANON_KEY"))
            buildConfigField("String", "SUPABASE_URL", localProps.getProperty("SUPABASE_URL"))
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
        buildConfig = true
        viewBinding = true
    }
}

dependencies {
    // Google
    implementation(libs.androidx.credentials)
    implementation(libs.androidx.credentials.play.services.auth)
    implementation(libs.googleid)

    // Supabase
    implementation (platform (libs.supabase.bom))
    implementation (libs.auth.kt)
    implementation (libs.ktor.client.okhttp)
    implementation (libs.realtime.kt)
    implementation (libs.postgrest.kt)
    implementation (libs.ktor.client.android)
    implementation (libs.kotlinx.serialization.json)

    implementation(libs.androidx.navigation.compose)
    implementation(libs.coil.compose)
    implementation(libs.ktor.client.android)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.material)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    implementation(libs.androidx.material.icons.extended)
    implementation(libs.hilt.android)
    implementation(libs.androidx.hilt.lifecycle.viewmodel)
    implementation(libs.androidx.activity.ktx)
    implementation(libs.androidx.fragment.ktx)
}