package com.devopsbug.openlingua.util

import android.content.Context

data class ImageAsset(
    val assetName: String,
    val assetCategory: String,
    val filename: String,
    val fileType: String,
    val source: String,
    val url: String,
    val license: String,
    val downloadDate: String,
    val author: String,
    val attribution: String
)

object OpenLinguaImageAssetUtils {
    private const val CSV_FILE_PATH = "pixabay/pixabay_assets.csv"

    private fun loadAssets(context: Context): List<ImageAsset> {
        val inputStream = context.assets.open(CSV_FILE_PATH)
        val reader = inputStream.bufferedReader()

        return reader.useLines { lines ->
            lines.drop(1) // Skip header row
                .map { it.split(";") }
                .filter { it.size == 10 } // Ensure all columns exist
                .map { ImageAsset(
                    assetName = it[0],
                    assetCategory = it[1],
                    filename = it[2],
                    fileType = it[3],
                    source = it[4],
                    url = it[5],
                    license = it[6],
                    downloadDate = it[7],
                    author = it[8],
                    attribution = it[9],
                ) }
                .toList()
        }
    }

    fun getImageByName(context: Context, name: String): ImageAsset? {
        return loadAssets(context).find { it.assetName.equals(name, ignoreCase = false) }
    }

    fun getImagesByCategory(context: Context, category: String): List<ImageAsset> {
        return loadAssets(context).filter { it.assetCategory.equals(category, ignoreCase = false) }
    }
}