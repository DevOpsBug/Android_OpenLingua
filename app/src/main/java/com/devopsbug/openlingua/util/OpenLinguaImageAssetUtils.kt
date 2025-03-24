package com.devopsbug.openlingua.util

import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.size
import androidx.compose.ui.geometry.isEmpty
import androidx.compose.ui.semantics.getOrNull
import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader
import kotlin.text.associate
import kotlin.text.drop
import kotlin.text.first
import kotlin.text.lowercase
import kotlin.text.mapNotNull
import kotlin.text.orEmpty
import kotlin.text.replace
import kotlin.text.split
import kotlin.text.withIndex


data class ImageAsset(
    val asset_name: String,
    val asset_category: String,
    val translations: Map<String, String>,
    val audio_files: Map<String, String>,
    val image_filename: String,
    val file_type: String,
    val source: String,
    val url: String,
    val license: String,
    val download_date: String,
    val attribution_text: String,
    val attribution_html: String
)

object OpenLinguaImageAssetUtils {
    //private const val CSV_FILE_PATH = "pixabay/pixabay_assets.csv"

    fun loadCategoryAssetsFromCsvFile(context: Context, category: String): List<ImageAsset> {

        val assetFileName = "$category/${category}_assets.csv"
        val inputStream = try {
            context.assets.open(assetFileName)
        } catch (e: Exception) {
            // Handle the case where the file is not found
            println("Error opening asset file: $assetFileName")
            e.printStackTrace()
            return emptyList()
        }

        val reader = BufferedReader(InputStreamReader(inputStream))
        val lines = reader.readLines()
        reader.close()

        if (lines.isEmpty()) {
            Log.d("ASSET", "No lines found in asset file: $assetFileName")
            return emptyList()
        } else {
            lines.forEach {
                Log.d("ASSET", it)
            }
        }

        val header = lines.first().split(";")
        val columnIndices = header.withIndex().associate { it.value to it.index }

        return lines.drop(1).mapNotNull { line ->
            val row = line.split(";")
            if (row.size < header.size) return@mapNotNull null // Skip invalid rows

            val translations = mapOf(
                "en" to row.getOrNull(columnIndices["english_word"] ?: -1).orEmpty(),
                "de" to row.getOrNull(columnIndices["german_word"] ?: -1).orEmpty(),
                "it" to row.getOrNull(columnIndices["italian_word"] ?: -1).orEmpty()
            )

            val englishWord = row.getOrNull(columnIndices["english_word"] ?: -1).orEmpty().lowercase().replace(" ", "_")
            val audioFiles = mapOf(
                "en" to "en_${englishWord}.mp3",
                "de" to "de_${englishWord}.mp3",
                "it" to "it_${englishWord}.mp3"
            )

            ImageAsset(
                asset_name = row.getOrNull(columnIndices["asset_name"] ?: -1).orEmpty(),
                asset_category = row.getOrNull(columnIndices["asset_category"] ?: -1).orEmpty(),
                translations = translations,
                audio_files = audioFiles,
                image_filename = row.getOrNull(columnIndices["image_filename"] ?: -1).orEmpty(),
                file_type = row.getOrNull(columnIndices["file_type"] ?: -1).orEmpty(),
                source = row.getOrNull(columnIndices["source"] ?: -1).orEmpty(),
                url = row.getOrNull(columnIndices["url"] ?: -1).orEmpty(),
                license = row.getOrNull(columnIndices["license"] ?: -1).orEmpty(),
                download_date = row.getOrNull(columnIndices["download_date"] ?: -1).orEmpty(),
                attribution_text = row.getOrNull(columnIndices["attribution_text"] ?: -1).orEmpty(),
                attribution_html = row.getOrNull(columnIndices["attribution_html"] ?: -1).orEmpty()
            )
        }
    }

//    fun getImageByName(context: Context, name: String): ImageAsset? {
//        return loadAssetsFromCsvFile(context).find { it.assetName.equals(name, ignoreCase = false) }
//    }
//
//    fun getImagesByCategory(context: Context, category: String): List<ImageAsset> {
//        return loadAssetsFromCsvFile(context).filter { it.assetCategory.equals(category, ignoreCase = false) }
//    }
}