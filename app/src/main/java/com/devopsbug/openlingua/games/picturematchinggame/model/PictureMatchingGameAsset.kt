package com.devopsbug.openlingua.games.picturematchinggame.model

import androidx.annotation.DrawableRes

data class PictureMatchingGameAsset(
    val assetName: String,
    val assetCategory: String,
    val translations: Map<String, String>,
    val audioResources: Map<String, Int>,
    @DrawableRes val imageResource: Int,
    val imageFileType: String,
    val imageWidth: Int,
    val imageHeight: Int,
    val imageSource: String,
    val imageDownloadUrl: String,
    val imageLicense: String,
    val imageDownloadDate: String,
    val imageAttributionText: String,
    val imageAttributionHtml: String
)
