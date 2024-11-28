import com.android.build.gradle.BaseExtension

buildscript {
    dependencies {
        classpath(libs.kotlin.serialization)
    }
}

subprojects {
    plugins.applyBaseConfig(project)
}

fun BaseExtension.baseConfig() {
    compileSdkVersion(34)

    defaultConfig.apply {
        minSdk = 26
        targetSdk = 34

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
            kotlinOptions {
                jvmTarget = JavaVersion.VERSION_17.toString()
            }
        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_17
            targetCompatibility = JavaVersion.VERSION_17
        }
    }
}

fun PluginContainer.applyBaseConfig(project: Project) {
    whenPluginAdded {
        when (this) {
            is com.android.build.gradle.AppPlugin -> {
                project.extensions.getByType<com.android.build.gradle.AppExtension>().apply {
                    baseConfig()
                }
            }

            is com.android.build.gradle.LibraryPlugin -> {
                project.extensions.getByType<com.android.build.gradle.LibraryExtension>().apply {
                    baseConfig()
                }
            }
        }
    }
}

@Suppress("DSL_SCOPE_VIOLATION") plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.compose.compiler) apply false
    alias(libs.plugins.jetbrains.kotlin.jvm) apply false
}