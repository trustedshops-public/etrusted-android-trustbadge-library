plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.etrusted.android.trustbadgeexample"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.etrusted.android.trustbadgeexample"
        minSdk = 26
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }
    buildTypes {
        named("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
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
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.2.0"
    }
    packagingOptions {
        resources {
            excludes += setOf("/META-INF/{AL2.0,LGPL2.1}")
        }
    }
}

tasks.register("createEmptyConfigFile") {
    doLast {
        val configFileName = "trustbadge-config.json"
        File("${rootDir}/$configFileName").apply {
            if (!exists()) {
                createNewFile()
                writeText("""
                    {
                      "client_id": "",
                      "client_secret": ""
                    }
                """.trimIndent())
                println("empty config file created")
            } else {
                println("config file already exists")
            }
        }
    }
}

tasks.register("generateTrustbadgeConfigFile") {
    doLast {
        val configFileName = "trustbadge-config.json"
        File("${rootDir}/$configFileName").apply {
            if (!exists()) {
                createNewFile()
                val configContent = System.getenv("APP_DIST_TRUSTBADGE_CONFIG")
                writeText(configContent)
                println("empty config file created")
            } else {
                println("config file already exists")
            }
        }
    }
}

tasks.register("generateAppDistKey") {
    doLast {
        val jsonFileName = "app-dist-key.json"
        val fileContent = System.getenv("GOOGLE_APP_DIST_FASTLANE_SERVICE_ACCOUNT")
        File(rootDir, jsonFileName).apply {
            if (!exists()) {
                createNewFile()
                writeText(fileContent)
                println("Firebase AppDistribution key generated")
            } else {
                println("Firebase AppDistribution key already exists")
            }
        }
    }
}

val kotlinVersion: String by project
val activityVersion: String by project
val composeVersion: String by project
val composeM3Version: String by project
val composeNavVersion: String by project
dependencies {

    implementation(project(":library"))

    // activate kotlin reflection in the project (used for mapping children of Screen sealed class)
    implementation("org.jetbrains.kotlin:kotlin-reflect:$kotlinVersion")

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.5.1")

    implementation("androidx.activity:activity-compose:$activityVersion")
    implementation("androidx.compose.ui:ui:$composeVersion")
    implementation("androidx.compose.ui:ui-tooling-preview:$composeVersion")
    implementation("androidx.compose.material3:material3:$composeM3Version")
    implementation("androidx.navigation:navigation-compose:$composeNavVersion")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:$composeVersion")
    debugImplementation("androidx.compose.ui:ui-tooling:$composeVersion")
    debugImplementation("androidx.compose.ui:ui-test-manifest:$composeVersion")
}