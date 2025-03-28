package  com.devopsbug.openlingua.games.picturematchinggame.ui.state

import com.devopsbug.openlingua.core.ui.LanguageSelectionRow
import com.devopsbug.openlingua.data.Languages
import com.devopsbug.openlingua.model.Language
import com.devopsbug.openlingua.util.ImageAsset

data class PictureMatchingGameUiState(
    val currentCategory: String = "",
    val categoryAssets: List<ImageAsset> = emptyList(),
    val correctImage: ImageAsset = ImageAsset(),
    val wrongImages: List<ImageAsset> = emptyList(),
    val currentLanguage: Language = Languages.english,
)

