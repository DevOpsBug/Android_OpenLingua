package  com.devopsbug.openlingua.games.picturematchinggame.ui.state

import android.util.Log
import androidx.lifecycle.ViewModel
import com.devopsbug.openlingua.games.picturematchinggame.model.PictureMatchingGameCategory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class PictureMatchingGameViewModel : ViewModel() {
    private val tag = "PictureMatchingGameViewModel"
    private val _uiState = MutableStateFlow(PictureMatchingGameUiState())
    val uiState: StateFlow<PictureMatchingGameUiState> = _uiState.asStateFlow()


    fun updateCategory(newCategory: PictureMatchingGameCategory) {
        Log.d(tag, "updateCategory")
        _uiState.value = _uiState.value.copy(
            currentPictureGameCategory = newCategory
        )
    }
}