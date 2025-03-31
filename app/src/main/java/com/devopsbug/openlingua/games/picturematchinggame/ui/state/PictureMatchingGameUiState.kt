package  com.devopsbug.openlingua.games.picturematchinggame.ui.state

import com.devopsbug.openlingua.games.picturematchinggame.data.Datasource
import com.devopsbug.openlingua.games.picturematchinggame.data.VegetableAssets
import com.devopsbug.openlingua.games.picturematchinggame.model.PictureGameCategory
import com.devopsbug.openlingua.games.picturematchinggame.model.PictureMatchingGameAsset

data class PictureMatchingGameUiState(
    val currentPictureGameCategory: PictureGameCategory = Datasource.gameCategories[0],
    val currentWrongGameAssets: List<PictureMatchingGameAsset> = VegetableAssets.assetList.subList(0,3),
    val currentCorrectGameAsset: PictureMatchingGameAsset = VegetableAssets.assetList[3],
    val randomizedGameAssetDisplayList: List<PictureMatchingGameAsset> = (currentWrongGameAssets + currentCorrectGameAsset).shuffled()
)

