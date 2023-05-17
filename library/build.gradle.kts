import java.io.FileInputStream
import java.util.*

group = "io.github.trustedshops-public"
version = "0.0.${System.getenv("CIRCLE_BUILD_NUM") ?: "1"}-SNAPSHOT"

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("de.trustedshops.gradle.trustbadge.config.produce") version "0.0.03"
    id("jacoco")
    `maven-publish`
    signing
}

android {
    namespace = "com.etrusted.android.trustbadge.library"
    compileSdk = 33

    defaultConfig {

        aarMetadata {
            minCompileSdk = 26
        }
        minSdk = 26
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        testInstrumentationRunnerArguments["CI"] = System.getenv("CI") ?: "false"
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
        create("debugTestStage") {
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
            enableUnitTestCoverage = true
            enableAndroidTestCoverage = true

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
    composeOptions {
        val composeCompilerVersion: String by project
        kotlinCompilerExtensionVersion = composeCompilerVersion
    }
    testOptions {
        animationsDisabled = true

        managedDevices {
            devices {
                maybeCreate<com.android.build.api.dsl.ManagedVirtualDevice>("pixel2api30")
                    .apply {
                        device = "Pixel 2"
                        apiLevel = 30
                        systemImageSource = "google"
                    }
            }
        }
    }
}

jacoco {
    val jacocoVersion: String by project
    toolVersion = jacocoVersion
    reportsDirectory.set(layout.buildDirectory.dir("mergedReportDir"))
}

tasks.register<JacocoReport>("jacocoTestReport") {

    dependsOn("testDebugUnitTest")
    dependsOn("pixel2api30DebugAndroidTest")

    reports {
        xml.required.set(true)
        html.required.set(true)
        csv.required.set(false)
    }

    val fileFilter = listOf("**/R.class", "**/R$*.class", "**/BuildConfig.*", "**/Manifest*.*", "**/*Test*.*", "android/**/*.*")
    val debugTree = fileTree("${buildDir}/tmp/kotlin-classes/debug") { exclude(fileFilter) }
    val mainSrc = "${project.projectDir}/src/main/java"

    sourceDirectories.from(files(setOf(mainSrc)))
    classDirectories.from(files(setOf(debugTree)))
    executionData.from(fileTree(buildDir) { include(setOf(
        "outputs/unit_test_code_coverage/debugUnitTest/testDebugUnitTest.exec",
        "outputs/managed_device_code_coverage/pixel2api30/coverage.ec"
    ))})
}

tasks.preBuild {
    // produce the config file before assemble
    dependsOn(tasks.produce)
}

tasks.register("prepareGeneratingFreshGolden") {
    android.defaultConfig.testInstrumentationRunnerArguments["GENERATE_FRESH_GOLDEN"]="true"
}
tasks.register<Copy>("copyFreshGoldenToAssets") {
    println("copying screenshots")
    from(layout.buildDirectory.dir("outputs/managed_device_android_test_additional_output/pixel2api30/screenshots"))
    include("*.png")
    into(layout.projectDirectory.dir("src/androidTest/assets/screenshots"))
}
tasks.register("generateFreshGolden") {
    dependsOn(tasks.clean)
    dependsOn(tasks.findByName("prepareGeneratingFreshGolden"))
    dependsOn(tasks.findByName("pixel2api30DebugAndroidTest"))
    finalizedBy(tasks.findByName("copyFreshGoldenToAssets"))
}

publishing {
    publications {
        register<MavenPublication>("release") {
            groupId = "io.github.trustedshops-public"
            artifactId = "etrusted-android-trustbadge-library"
            version = "0.1.${System.getenv("CIRCLE_BUILD_NUM") ?: "1"}-SNAPSHOT"

            afterEvaluate {
                from(components["release"])
            }

            pom {
                name.set("Trustbadge")
                description.set("Show the TrustBadge on your Android app")
                url.set("https://github.com/trustedshops-public/etrusted-android-trustbadge-library")
                licenses {
                    license {
                        name.set("The MIT License")
                        url.set("https://github.com/trustedshops-public/etrusted-android-trustbadge-library/blob/main/LICENSE")
                    }
                }
                developers {
                    developer {
                        id.set("superus8r")
                        name.set("Ali Kabiri")
                        email.set("ali.kabiri@trustedshops.de")
                    }
                }
                scm {
                    connection.set("scm:git:git://github.com/trustedshops-public/etrusted-android-trustbadge-library.git")
                    developerConnection.set("scm:git:ssh://github.com/trustedshops-public/etrusted-android-trustbadge-library.git")
                    url.set("https://github.com/trustedshops-public/etrusted-android-trustbadge-library")
                }
            }
        }
    }
    repositories {
        maven {
            name = "Trustbadge"
            val releasesRepoUrl = uri("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
            val snapshotsRepoUrl = uri("https://s01.oss.sonatype.org/content/repositories/snapshots")
            url = if (version.toString().endsWith("SNAPSHOT")) snapshotsRepoUrl else releasesRepoUrl
            credentials {
                username = System.getenv("OSSRH_USERNAME")
                password = System.getenv("OSSRH_TOKEN")
            }
        }
    }
}

signing {
    sign(publishing.publications.findByName("release"))
}

internal val coreKtxVersion: String by project
internal val activityVersion: String by project
internal val lifecycleViewModelComposeVersion: String by project
internal val composeBomVersion: String by project
internal val composeVersion: String by project
internal val composeM3Version: String by project
internal val testJunitVersion: String by project
internal val testGoogleTruthVersion: String by project
internal val androidTestJunitVersion: String by project
internal val androidTestEspressoVersion: String by project
internal val okhttpVersion: String by project
internal val kotlinCoroutinesVersion: String by project
internal val coilVersion: String by project

dependencies {

    implementation("androidx.core:core-ktx:$coreKtxVersion")
    implementation("androidx.activity:activity-compose:$activityVersion")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:$lifecycleViewModelComposeVersion")

    val composeBom = platform("androidx.compose:compose-bom:$composeBomVersion")
    implementation(composeBom)
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.ui:ui")
    implementation("io.coil-kt:coil-compose:$coilVersion")

    // test dependencies
    testImplementation("junit:junit:$testJunitVersion")
    androidTestImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:$kotlinCoroutinesVersion")
    androidTestImplementation("com.google.truth:truth:$testGoogleTruthVersion")
    androidTestImplementation("androidx.test.ext:junit:$androidTestJunitVersion")
    androidTestImplementation("androidx.test.espresso:espresso-core:$androidTestEspressoVersion")
    androidTestImplementation("com.squareup.okhttp3:mockwebserver:$okhttpVersion")
    androidTestImplementation("com.squareup.okhttp3:okhttp-tls:$okhttpVersion")
    // ui tests
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:$composeVersion")
    androidTestImplementation("io.coil-kt:coil-test:$coilVersion")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
    debugImplementation("androidx.compose.ui:ui-tooling")
}