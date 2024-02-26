plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")
}

kotlin {
    jvmToolchain(17)
    android()

    listOf(
//        iosX64(),
//        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.compilations.getByName("main").cinterops {
            val react by creating {
                packageName("dev.shibasis.kotlin.react")
                defFile(file("cpp/kotlin_bridge.def"))
                headers("cpp/KotlinReact.h")
            }
        }

    }

    cocoapods {
        version = "1.0"
        summary = "A React Bridge module written in Kotlin"
        homepage = "https://github.com/shibasis0801/KotlinBridgeModule"

        name = "kotlin_bridge"
        ios.deploymentTarget = "12"

        framework {
            isStatic = true
            baseName = "kotlin_bridge"
        }
    }

    sourceSets {
        val iosMain by creating
//        val iosX64Main by getting {
//            dependsOn(iosMain)
//        }
//        val iosArm64Main by getting {
//            dependsOn(iosMain)
//        }
        val iosSimulatorArm64Main by getting {
            dependsOn(iosMain)
        }
    }
}

android {
    namespace = "dev.shibasis.kotlin.bridge"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
}