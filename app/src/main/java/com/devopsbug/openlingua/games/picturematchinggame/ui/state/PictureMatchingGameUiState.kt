package  com.devopsbug.openlingua.games.picturematchinggame.ui.state

import com.devopsbug.openlingua.games.picturematchinggame.data.PictureMatchingGameCategories
import com.devopsbug.openlingua.games.picturematchinggame.data.gamecategories.VegetablesAssets
import com.devopsbug.openlingua.games.picturematchinggame.model.PictureMatchingGameCategory
import com.devopsbug.openlingua.games.picturematchinggame.model.PictureMatchingGameAsset

data class PictureMatchingGameUiState(
    val currentPictureGameCategory: PictureMatchingGameCategory = PictureMatchingGameCategories.categoriesList[0],
    val currentWrongGameAssets: List<PictureMatchingGameAsset> = VegetablesAssets.assetList.subList(0,3),
    val currentCorrectGameAsset: PictureMatchingGameAsset = VegetablesAssets.assetList[3],
    val randomizedGameAssetDisplayList: List<PictureMatchingGameAsset> = (currentWrongGameAssets + currentCorrectGameAsset).shuffled()
)

