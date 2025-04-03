package com.devopsbug.openlingua.games.picturematchinggame.data

import com.devopsbug.openlingua.R
import com.devopsbug.openlingua.games.picturematchinggame.data.gamecategories.FruitAssets
import com.devopsbug.openlingua.games.picturematchinggame.data.gamecategories.VegetableAssets
import com.devopsbug.openlingua.games.picturematchinggame.model.PictureGameCategory

object PictureMatchingGameCategories {
    val categoriesList = listOf(
        PictureGameCategory(
            categoryName = "vegetables",
            categoryCoverImage = R.drawable.category_vegetables,
            categoryGameAssets = VegetableAssets.assetList
        ),
        PictureGameCategory(
            categoryName = "fruits",
            categoryCoverImage = R.drawable.category_fruits,
            categoryGameAssets = FruitAssets.assetList
        ),
    )

}