plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")
}

kotlin {
    jvmToolchain(17)
    android()
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    cocoapods {
        version = "1.0"
        summary = "A React Bridge module written in Kotlin"
        homepage = "https://github.com/shibasis0801/KotlinBridgeModule"

        name = "kotlin_bridge"
        ios.deploymentTarget = "12"
        podfile = file("../../ios/Podfile")

        framework {
            isStatic = true
            baseName = "kotlin_bridge"
        }
    }

    sourceSets {}
}

android {
    namespace = "dev.shibasis.kotlin.bridge"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
}