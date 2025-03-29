package com.devopsbug.openlingua.games.picturematchinggame.data

import com.devopsbug.openlingua.R
import com.devopsbug.openlingua.games.picturematchinggame.model.GameCategory
import com.devopsbug.openlingua.games.picturematchinggame.model.PictureMatchingGameAsset

object Datasource {
    val gameCategories = listOf(
        GameCategory(
            categoryName = "vegetables",
            categoryCoverImage = R.drawable.category_vegetables,
            categoryGameAssets = VegetableAssets.assetList
        ),
    )

}