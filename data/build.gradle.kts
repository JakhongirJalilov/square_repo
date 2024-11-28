plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    kotlin("plugin.serialization")
}

android {
    namespace = "com.example.data"
}

dependencies {
    api(project(":domain"))

    // Network
    api(libs.bundles.ktor)

    // Test Libs
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.mockk)
    testImplementation(libs.junit)
    testImplementation(libs.ktor.client.mock)
}