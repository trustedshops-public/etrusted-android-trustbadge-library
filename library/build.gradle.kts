plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

abstract class TrustbadgeGradlePlugin: Plugin<Project> {

    override fun apply(project: Project) {
        project.task("produceConfiguration") {
            doLast {
                project.copy {
                    from("${project.rootDir}/app/trustbadge-config.json")
                    into("${project.buildDir}/generated/res/values")
                }
            }
        }
    }
}

apply<TrustbadgeGradlePlugin>()

android {
    namespace = "com.etrusted.android.trustbadge.library"
    compileSdk = 33

    defaultConfig {
        minSdk = 26

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        named("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
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

internal val activity_version: String by project
internal val compose_version: String by project
internal val compose_m3_version: String by project
dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.activity:activity-compose:$activity_version")
    implementation("androidx.compose.ui:ui:$compose_version")
    debugImplementation("androidx.compose.ui:ui-tooling:$compose_version")
    implementation("androidx.compose.ui:ui-tooling-preview:$compose_version")
    implementation("androidx.compose.material3:material3:$compose_m3_version")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}