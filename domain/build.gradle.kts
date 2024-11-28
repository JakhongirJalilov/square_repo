plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.example.domain"
}

dependencies {
    implementation(libs.androidx.lifecycle.runtime.compose)
}