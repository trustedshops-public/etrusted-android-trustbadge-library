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
        versionCode = System.getenv("CIRCLE_BUILD_NUM")?.toIntOrNull() ?: 1
        versionName = "1.0.${System.getenv("CIRCLE_BUILD_NUM") ?: 0}"

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
        create("debugTestStage") {
            applicationIdSuffix = ".test.debug"
            signingConfig = signingConfigs.getByName("debug")
            enableUnitTestCoverage = true
            buildConfigField("String", "STAGE", "\"debugTestStage\"")
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
        val composeCompilerVersion: String by project
        kotlinCompilerExtensionVersion = composeCompilerVersion
    }
    packagingOptions {
        resources {
            excludes += setOf("/META-INF/{AL2.0,LGPL2.1}")
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
val composeBomVersion: String by project
val composeVersion: String by project
val composeM3Version: String by project
val composeNavVersion: String by project
dependencies {

    implementation(project(":library"))

    // activate kotlin reflection in the project (used for mapping children of Screen sealed class)
    implementation("org.jetbrains.kotlin:kotlin-reflect:$kotlinVersion")

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")

    val composeBom = platform("androidx.compose:compose-bom:$composeBomVersion")
    implementation(composeBom)
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.ui:ui")
    implementation("androidx.navigation:navigation-compose:$composeNavVersion")
    implementation("androidx.activity:activity-compose:$activityVersion")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:$composeVersion")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
    debugImplementation("androidx.compose.ui:ui-tooling")
}