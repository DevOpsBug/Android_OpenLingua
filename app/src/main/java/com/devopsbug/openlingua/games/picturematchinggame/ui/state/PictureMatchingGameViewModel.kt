package  com.devopsbug.openlingua.games.picturematchinggame.ui.state

import androidx.lifecycle.ViewModel
import com.devopsbug.openlingua.games.picturematchinggame.model.GameCategory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class PictureMatchingGameViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(PictureMatchingGameUiState())
    val uiState: StateFlow<PictureMatchingGameUiState> = _uiState.asStateFlow()

    fun updateCategory(newCategory: GameCategory) {
        _uiState.value = _uiState.value.copy(
            currentGameCategory = newCategory
        )
    }

}