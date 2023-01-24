import org.json.JSONObject
import java.io.FileInputStream
import java.util.*

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

class TrustbadgeGradlePlugin: Plugin<Project> {

    private val propFileName = "trustbadge.properties"
    private val jsonFileName = "trustbadge-config.json"
    private val keyClientId = "client_id"
    private val keyClientSecret = "client_secret"

    private fun decodeJsonAndProduceConfigFile(
        inputPath: String,
        outputPath: String,
    ) {

        println("decoding json")
        val jsonContent = File(inputPath).readText()
        val jsonObject = JSONObject(jsonContent)

        val clientId = jsonObject.get(keyClientId)
        val clientSecret = jsonObject.get(keyClientSecret)

        println("generating $propFileName file")
        val propContent = "$keyClientId=$clientId\n$keyClientSecret=$clientSecret"
        val outputFile = File(outputPath)
        outputFile.createNewFile()
        outputFile.writeText(propContent)
    }

    override fun apply(project: Project) {
        project.tasks.register("produceConfig") {
            doLast {
                project.copy {
                    from("${project.rootDir}/$jsonFileName")
                    into("${project.projectDir}")
                }
                decodeJsonAndProduceConfigFile(
                    inputPath = "${project.projectDir}/$jsonFileName",
                    outputPath = "${project.projectDir}/$propFileName"
                )
            }
        }
    }
}

apply<TrustbadgeGradlePlugin>()
tasks.findByName("produceConfig")?.finalizedBy(tasks.assemble)

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

        named("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")

            // create an empty config file to avoid failing builds
            File("${project.projectDir}/$propFileName").apply {
                createNewFile(); writeText("$keyClientId=\n$keyClientSecret=") }

            // load properties
            val propertiesFile = project.file(propFileName)
            val properties = Properties()
            properties.load(FileInputStream(propertiesFile))

            // produce rest values available to the library
            resValue("string", keyClientId, properties.getProperty(keyClientId))
            resValue("string", keyClientSecret, properties.getProperty(keyClientSecret))
        }
        named("debug") {

            // create an empty config file to avoid failing builds
            File("${project.projectDir}/$propFileName").apply {
                createNewFile(); writeText("$keyClientId=\n$keyClientSecret=") }

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

internal val coreKtxVersion: String by project
internal val activityVersion: String by project
internal val composeVersion: String by project
internal val composeM3Version: String by project
internal val testJunitVersion: String by project
internal val androidTestJunitVersion: String by project
internal val androidTestEspressoVersion: String by project
dependencies {

    implementation("androidx.core:core-ktx:$coreKtxVersion")
    implementation("androidx.activity:activity-compose:$activityVersion")
    implementation("androidx.compose.ui:ui:$composeVersion")
    debugImplementation("androidx.compose.ui:ui-tooling:$composeVersion")
    implementation("androidx.compose.ui:ui-tooling-preview:$composeVersion")
    implementation("androidx.compose.material3:material3:$composeM3Version")

    testImplementation("junit:junit:$testJunitVersion")
    androidTestImplementation("androidx.test.ext:junit:$androidTestJunitVersion")
    androidTestImplementation("androidx.test.espresso:espresso-core:$androidTestEspressoVersion")
}