package  com.devopsbug.openlingua.games.picturematchinggame.ui.state

import com.devopsbug.openlingua.core.interfaces.OpenLinguaGameUiState
import com.devopsbug.openlingua.games.picturematchinggame.data.PictureMatchingGameCategories
import com.devopsbug.openlingua.games.picturematchinggame.model.PictureMatchingGameCategory

data class PictureMatchingGameUiState(
    override val gameName: String = "PictureMatchingGame",
    val currentPictureGameCategory: PictureMatchingGameCategory = PictureMatchingGameCategories.categoriesList[0],
) : OpenLinguaGameUiState

