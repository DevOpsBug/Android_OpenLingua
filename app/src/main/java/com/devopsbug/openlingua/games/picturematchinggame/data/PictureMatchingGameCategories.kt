package com.devopsbug.openlingua.games.picturematchinggame.data

import com.devopsbug.openlingua.R
import com.devopsbug.openlingua.games.picturematchinggame.data.gamecategories.FruitAssets
import com.devopsbug.openlingua.games.picturematchinggame.data.gamecategories.VegetableAssets
import com.devopsbug.openlingua.games.picturematchinggame.data.gamecategories.VegetableAssets2
import com.devopsbug.openlingua.games.picturematchinggame.model.PictureMatchingGameCategory

object PictureMatchingGameCategories {
    val categoriesList = listOf(
        PictureMatchingGameCategory(
            categoryName = "vegetables",
            categoryCoverImage = R.drawable.category_vegetables,
            categoryGameAssets = VegetableAssets.assetList
        ),
        PictureMatchingGameCategory(
            categoryName = "fruits",
            categoryCoverImage = R.drawable.category_fruits,
            categoryGameAssets = FruitAssets.assetList
        ),
        PictureMatchingGameCategory(
            categoryName = "vegetables2",
            categoryCoverImage = R.drawable.category_vegetables,
            categoryGameAssets = VegetableAssets2.assetList
        ),
    )

}