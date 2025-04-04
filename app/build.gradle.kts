plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.devopsbug.openlingua"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.devopsbug.openlingua"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.ui.text.google.fonts)
    implementation(libs.androidx.navigation.runtime.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.savedstate)
    implementation(libs.androidx.navigation.compose)


    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}




tasks.register("generatePictureGameAssets") {

    val categories = listOf(
        "vegetables" to "src/main/assets/vegetables/vegetables_assets.csv",
        "fruits" to "src/main/assets/fruits/fruits_assets.csv"
    )


    doLast {
        categories.forEach { (categoryName, inputPath) ->
            val outputFileName = categoryName.replaceFirstChar { it.uppercase() } + "Assets.kt"
            val outputKt = file("src/main/assets/$categoryName/$outputFileName")
            val inputCsv = file(inputPath)
            val lines = inputCsv.readLines().filter { it.isNotBlank() }
            val headers = lines.first().split(";")
            val rows = lines.drop(1)

            if (headers.size != 15) {
                error("❌ CSV header should contain exactly 15 columns, but found ${headers.size}")
            }

            val objectName = categoryName.replaceFirstChar { it.uppercase() } + "Assets"
            val ktCode = buildString {
                appendLine("package com.devopsbug.openlingua.games.picturematchinggame.data.gamecategories\n")
                appendLine("import com.devopsbug.openlingua.R")
                appendLine("import com.devopsbug.openlingua.games.picturematchinggame.model.PictureMatchingGameAsset\n")
                appendLine("object $objectName {")
                appendLine("    val assetList = listOf(")

                for ((lineIndex, row) in rows.withIndex()) {
                    val fields = row.split(";").map { it.trim() }


                    if (fields.size != 15) {
                        error("❌ Error in CSV row ${lineIndex + 2}: Expected 15 columns, but got ${fields.size}")
                    }

                    val assetName = fields[0]
                    val assetCategory = fields[1]
                    val englishWord = fields[2]
                    val germanWord = fields[3]
                    val italianWord = fields[4]
                    val imageFilename = fields[5]
                    val fileType = fields[6]
                    val imageWidth = fields[7].toIntOrNull()?.takeIf { it > 0 } ?: -1
                    val imageHeight = fields[8].toIntOrNull()?.takeIf { it > 0 } ?: -1
                    val source = fields[9]
                    val url = fields[10]
                    val license = fields[11]
                    val downloadDate = fields[12]
                    val attributionText = fields[13]
                    val attributionHtml = fields[14]


                    val nameForImageResource = assetCategory + "_" + assetName

                    appendLine(
                        """
        PictureMatchingGameAsset(
            assetName = "$assetName",
            assetCategory = "$assetCategory",
            translations = mapOf(
                "en" to "$englishWord",
                "de" to "$germanWord",
                "it" to "$italianWord",
            ),
            audioResources = mapOf(
                "en" to R.raw.en_$assetName,
                "de" to R.raw.de_$assetName,
                "it" to R.raw.it_$assetName,
            ),
            imageResource = R.drawable.${nameForImageResource},
            imageFileType = "$fileType",
            imageWidth = $imageWidth,
            imageHeight = $imageHeight,
            imageSource = "$source",
            imageDownloadUrl = "$url",
            imageLicense = "$license",
            imageDownloadDate = "$downloadDate",
            imageAttributionText = "${attributionText.replace("\u00A0", " ")}",
            imageAttributionHtml = ${attributionHtml.replace("\"\"", "\\\"")}
        ),
                """.trimMargin()
                    )
                }

                appendLine("    )")
                appendLine("}")
            }

            outputKt.writeText(ktCode)
            println("✅ Generated: ${outputKt.absolutePath}")
        }
    }
}

tasks.named("preBuild").configure {
    dependsOn("generatePictureGameAssets")
}