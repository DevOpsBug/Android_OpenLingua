package  com.devopsbug.openlingua.games.lettergame.ui.state

import androidx.lifecycle.ViewModel

import com.devopsbug.openlingua.games.lettergame.data.Datasource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class LetterGameViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(LetterGameUiState())
    val uiState: StateFlow<LetterGameUiState> = _uiState.asStateFlow()

    fun updateLevel(level: Int) {
        val currentLetterSet = Datasource.lettersByLevel[level -1]
        _uiState.value = _uiState.value.copy(
            currentLevel = level,
            currentLetterSet = currentLetterSet,
            currentLetter = currentLetterSet.random()
        )
    }

    fun newRandomLetter() {
        val currentLetter = _uiState.value.currentLetter
        var newLetter = _uiState.value.currentLetterSet.random()
        while (newLetter == currentLetter) {
            newLetter = uiState.value.currentLetterSet.random()
        }
        _uiState.value = _uiState.value.copy(
            currentLetter = newLetter
        )
    }
}