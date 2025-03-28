package com.devopsbug.openlingua.util

import android.content.Context
import android.graphics.BitmapFactory
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import java.io.BufferedReader
import java.io.InputStreamReader


data class ImageAsset(
    val asset_name: String = "",
    val asset_category: String = "",
    val translations: Map<String, String> = emptyMap(),
    val audio_files: Map<String, String> = emptyMap(),
    val image_filename: String = "",
    val file_type: String = "",
    val source: String = "",
    val url: String = "",
    val license: String = "",
    val download_date: String = "",
    val attribution_text: String = "",
    val attribution_html: String = ""
)

object OpenLinguaImageAssetUtils {
    //private const val CSV_FILE_PATH = "pixabay/pixabay_assets.csv"
    @Composable
    fun loadCategoryAssetsFromCsvFile(context: Context, category: String): List<ImageAsset> {

        val assetFileName = "$category/${category}_assets.csv"
        Log.d("ASSET", "loadCategoryAssetsFromCsvFile: $assetFileName")
        val inputStream = try {
            context.assets.open(assetFileName)
        } catch (e: Exception) {
            // Handle the case where the file is not found
            e.printStackTrace()
            return emptyList()
        }

        val reader = BufferedReader(InputStreamReader(inputStream))
        val lines = reader.readLines()
        reader.close()

        if (lines.isEmpty()) {
            //Log.d("ASSET", "No lines found in asset file: $assetFileName")
            return emptyList()
        }


        val header = lines.first().split(";")
        val columnIndices = header.withIndex().associate { it.value to it.index }

        return lines.drop(1).mapNotNull { line ->
            val row = line.split(";")
            Log.d("ASSET", "Row: $row")
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

            Log.d("ASSET", "Asset loaded: ${row.getOrNull(columnIndices["asset_name"] ?: -1).orEmpty()}")

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

    @Composable
    fun imageBitmapFromAsset(assetFileName: String, assetCategory: String) : ImageBitmap? {
        val assetPath = "${assetCategory}/images/$assetFileName"
        //Log.d("ASSET", "assetPath: $assetPath")
        val context = LocalContext.current
        val imageBitmap = rememberImageBitmapFromAsset(context, assetPath)
        return imageBitmap
    }

    @Composable
    fun rememberImageBitmapFromAsset(context: Context, assetPath: String): ImageBitmap? {
        return remember(assetPath) {
            try {
                context.assets.open(assetPath).use { inputStream ->
                    val bitmap = BitmapFactory.decodeStream(inputStream)
                    bitmap?.asImageBitmap()
                }
            } catch (e: Exception) {
                Log.e("ASSET", "Error loading asset: $assetPath", e)
                e.printStackTrace()
                null
            }
        }
    }
}