package  com.devopsbug.openlingua.games.numbergame.ui.numbergamestate

import androidx.lifecycle.ViewModel

import com.devopsbug.openlingua.games.numbergame.data.Numbers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class NumberGameViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(NumberGameUiState())
    val uiState: StateFlow<NumberGameUiState> = _uiState.asStateFlow()

    fun updateLevel(level: Int) {
        val currentNumberSet = Numbers.numbersByLevel[level -1].numberList
        _uiState.value = _uiState.value.copy(
            currentLevel = level,
            currentNumberSet = currentNumberSet,
            currentNumber = currentNumberSet.random()
        )
    }

    fun newRandomNumber() {
        val currentNumber = _uiState.value.currentNumber
        var newNumber = _uiState.value.currentNumberSet.random()
        while (newNumber == currentNumber) {
            newNumber = uiState.value.currentNumberSet.random()
        }
        _uiState.value = _uiState.value.copy(
            currentNumber = newNumber
        )
    }
}