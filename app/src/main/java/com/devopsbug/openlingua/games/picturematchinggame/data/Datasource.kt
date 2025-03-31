package com.devopsbug.openlingua.games.picturematchinggame.data

import com.devopsbug.openlingua.R
import com.devopsbug.openlingua.games.picturematchinggame.model.PictureGameCategory

object Datasource {
    val gameCategories = listOf(
        PictureGameCategory(
            categoryName = "vegetables",
            categoryCoverImage = R.drawable.category_vegetables,
            categoryGameAssets = VegetableAssets.assetList
        ),
    )

}