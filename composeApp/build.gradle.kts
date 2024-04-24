import org.jetbrains.compose.ExperimentalComposeLibrary

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsCompose)
    
    alias(libs.plugins.kotlinxSerialization)
    alias(libs.plugins.sqlDelight)
//    alias(libs.plugins.swiftklib)

}

sqldelight {
    databases {
        create("MirrorLocalDatabase") {
            packageName.set("org.example.mirror.database")
        }
    }
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
        //for swiftklib
//        iosTarget.compilations {
//            val main by getting {
//                cinterops {
//                    create("AudioRecorderImpl")
//                }
//            }
//        }
    }
    
    sourceSets {
        
        androidMain.dependencies {
//            implementation(libs.compose.ui)
            implementation(libs.compose.ui.tooling.preview)
            implementation(libs.androidx.activity.compose)

            //koin dependency for android-viewModel
            implementation(libs.koin.android)

            //ktor android client
            implementation(libs.ktor.client.android)

            //sqlDelight android driver
            implementation(libs.sqlDelight.android.driver)

            implementation (libs.accompanist.permissions)
        }
        iosMain.dependencies {
            //ktor ios client
            implementation(libs.ktor.client.darwin)

            //sqlDelight native driver
            implementation(libs.sqlDelight.native.driver)
        }

        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            @OptIn(ExperimentalComposeLibrary::class)
            implementation(compose.components.resources)
            implementation(compose.ui)

            //koin
            implementation(libs.koin.core)
            implementation(libs.koin.compose)

            //ktor
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.serialization.kotlinx.json)
            implementation(libs.ktor.client.logging)

            //supabase-BaaS
            implementation(libs.supabase.gotrue)
            implementation(libs.supabase.postgrest)
            implementation(libs.supabase.compose.auth)
            implementation(libs.supabase.compose.auth.ui)

            //moko-mvvm
            implementation(libs.moko.mvvm.core)
            implementation(libs.moko.mvvm.compose)

            //multiplatform-settings for key value pair persistence
            implementation(libs.multiplatform.settings)

            //voyager for navigation
            implementation(libs.voyager.navigator)
            implementation(libs.voyager.transitions)

            //kotlinx-datetime
            implementation(libs.kotlinx.datetime)

            //image loading library Kamel
            implementation(libs.kamel)


        }
    }
}

android {
    namespace = "org.example.mirror"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].resources.srcDirs(
        "src/commonMain/resources",
        "src/androidMain/resources"
    )
    sourceSets["main"].res.srcDirs(
        "src/commonMain/resources",
        "src/androidMain/resources"
    )


    defaultConfig {
        applicationId = "org.example.mirror"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    dependencies {
        debugImplementation(libs.compose.ui.tooling)
    }
}

//swiftklib {
//    create("AudioRecorderImpl") {
//        path = file("native")
//        packageName("org.example.mirror")
//    }
//}

