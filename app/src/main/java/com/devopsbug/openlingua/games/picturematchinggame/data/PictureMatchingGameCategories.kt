package com.devopsbug.openlingua.games.picturematchinggame.data

import com.devopsbug.openlingua.R
import com.devopsbug.openlingua.games.picturematchinggame.data.gamecategories.FruitsAssets
import com.devopsbug.openlingua.games.picturematchinggame.data.gamecategories.VegetablesAssets
import com.devopsbug.openlingua.games.picturematchinggame.model.PictureMatchingGameCategory

object PictureMatchingGameCategories {
    val categoriesList = listOf(
        PictureMatchingGameCategory(
            categoryName = "vegetables",
            categoryCoverImage = R.drawable.category_vegetables,
            categoryGameAssets = VegetablesAssets.assetList
        ),
        PictureMatchingGameCategory(
            categoryName = "fruits",
            categoryCoverImage = R.drawable.category_fruits,
            categoryGameAssets = FruitsAssets.assetList
        ),
    )
}