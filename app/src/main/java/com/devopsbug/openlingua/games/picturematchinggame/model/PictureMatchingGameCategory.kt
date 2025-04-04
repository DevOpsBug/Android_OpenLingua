package com.devopsbug.openlingua.games.picturematchinggame.model

data class PictureMatchingGameCategory(
    val categoryName: String,
    val categoryCoverImage: Int,
    val categoryGameAssets: List<PictureMatchingGameAsset>
)
