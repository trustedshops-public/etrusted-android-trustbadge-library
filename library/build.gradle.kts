import java.io.FileInputStream
import java.util.*

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("de.trustedshops.gradle.trustbadge.config.produce") version "0.0.03"
}

android {
    namespace = "com.etrusted.android.trustbadge.library"
    compileSdk = 33

    defaultConfig {
        minSdk = 26

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {

        val propFileName = "trustbadge.properties"
        val keyClientId = "client_id"
        val keyClientSecret = "client_secret"

        /**
         * Create an empty properties file if non provided
         * to avoid failing builds
         */
        fun createEmptyPropFileIfNoneProvided() {
            File("${project.projectDir}/$propFileName").apply {
                if (!exists()) {
                    createNewFile()
                    writeText("$keyClientId=\n$keyClientSecret=")
                }
            }
        }

        named("release") {
            createEmptyPropFileIfNoneProvided()

            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")

            // load properties
            val propertiesFile = project.file(propFileName)
            val properties = Properties()
            properties.load(FileInputStream(propertiesFile))

            // produce rest values available to the library
            resValue("string", keyClientId, properties.getProperty(keyClientId))
            resValue("string", keyClientSecret, properties.getProperty(keyClientSecret))
        }
        named("debug") {
            createEmptyPropFileIfNoneProvided()

            // load properties
            val propertiesFile = project.file(propFileName)
            val properties = Properties()
            properties.load(FileInputStream(propertiesFile))

            // produce rest values available to the library
            resValue("string", keyClientId, properties.getProperty(keyClientId))
            resValue("string", keyClientSecret, properties.getProperty(keyClientSecret))
        }
    }
    compileOptions {
        sourceCompatibility(JavaVersion.VERSION_1_8)
        targetCompatibility(JavaVersion.VERSION_1_8)
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
}

tasks.preBuild {
    // produce the config file before assemble
    dependsOn(tasks.produce)
}

internal val coreKtxVersion: String by project
internal val activityVersion: String by project
internal val lifecycleViewModelComposeVersion: String by project
internal val composeVersion: String by project
internal val composeM3Version: String by project
internal val testJunitVersion: String by project
internal val testGoogleTruthVersion: String by project
internal val androidTestJunitVersion: String by project
internal val androidTestEspressoVersion: String by project
dependencies {

    implementation("androidx.core:core-ktx:$coreKtxVersion")
    implementation("androidx.activity:activity-compose:$activityVersion")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:$lifecycleViewModelComposeVersion")
    implementation("androidx.compose.material3:material3:$composeM3Version")
    implementation("androidx.compose.ui:ui:$composeVersion")
    implementation("androidx.compose.ui:ui-tooling-preview:$composeVersion")
    debugImplementation("androidx.compose.ui:ui-tooling:$composeVersion")

    testImplementation("junit:junit:$testJunitVersion")
    androidTestImplementation("com.google.truth:truth:$testGoogleTruthVersion")
    androidTestImplementation("androidx.test.ext:junit:$androidTestJunitVersion")
    androidTestImplementation("androidx.test.espresso:espresso-core:$androidTestEspressoVersion")
}