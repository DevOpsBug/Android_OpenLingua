package  com.devopsbug.openlingua.games.picturematchinggame.ui.state

import androidx.lifecycle.ViewModel
import com.devopsbug.openlingua.games.picturematchinggame.model.PictureGameCategory
import com.devopsbug.openlingua.games.picturematchinggame.model.PictureMatchingGameAsset
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class PictureMatchingGameViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(PictureMatchingGameUiState())
    val uiState: StateFlow<PictureMatchingGameUiState> = _uiState.asStateFlow()

    init {
        newRandomPicture()
    }

    fun updateCategory(newCategory: PictureGameCategory) {
        _uiState.value = _uiState.value.copy(
            currentPictureGameCategory = newCategory
        )
        newRandomPicture()
    }

    fun newRandomPicture() {
        // Randomize the list
        var shuffledList: List<PictureMatchingGameAsset>
        var currentCorrectGameAsset: PictureMatchingGameAsset
        var currentWrongGameAssets: List<PictureMatchingGameAsset>

        // Ensure the same asset doen't get selected twice in a row
        do {
            shuffledList = _uiState.value.currentPictureGameCategory.categoryGameAssets.shuffled()
            currentCorrectGameAsset = shuffledList[0]
            currentWrongGameAssets = shuffledList.subList(1,4)
        } while (shuffledList[0] == _uiState.value.currentCorrectGameAsset )


        val randomizedGameAssetDisplayList = (currentWrongGameAssets + currentCorrectGameAsset).shuffled()
        _uiState.value = _uiState.value.copy(
            currentCorrectGameAsset = currentCorrectGameAsset,
            currentWrongGameAssets = currentWrongGameAssets,
            randomizedGameAssetDisplayList = randomizedGameAssetDisplayList
        )
    }

}