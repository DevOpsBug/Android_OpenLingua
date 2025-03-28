package  com.devopsbug.openlingua.games.picturematchinggame.ui.state

import androidx.lifecycle.ViewModel
import com.devopsbug.openlingua.util.OpenLinguaImageAssetUtils.loadCategoryAssetsFromCsvFile

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class PictureMatchingGameViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(PictureMatchingGameUiState())
    val uiState: StateFlow<PictureMatchingGameUiState> = _uiState.asStateFlow()

//    init {
//        categoryAssets = loadCategoryAssetsFromCsvFile(
//            context = LocalContext.current,
//            category = _uiState.value.currentCategory
//        )
//    }

    fun updateCategory(newCategory: String) {
        _uiState.value = _uiState.value.copy(
            currentCategory = newCategory
        )
    }

    fun newRandomImage() {

    }

}