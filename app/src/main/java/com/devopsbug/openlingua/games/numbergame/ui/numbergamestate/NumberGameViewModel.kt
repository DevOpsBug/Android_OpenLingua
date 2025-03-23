package  com.devopsbug.openlingua.games.numbergame.ui.numbergamestate

import androidx.lifecycle.ViewModel

import com.devopsbug.openlingua.games.numbergame.data.Numbers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class NumberGameViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(NumberGameUiState())
    val uiState: StateFlow<NumberGameUiState> = _uiState.asStateFlow()

    init {
        newCalcProblem()
    }

    fun updateLevel(level: Int) {
        val currentNumberSet = Numbers.numbersByLevel[level -1].numberList
        _uiState.value = _uiState.value.copy(
            currentLevel = level,
            currentNumberSet = currentNumberSet,
            currentNumber = currentNumberSet.random()
        )
    }

    fun updateSublevel(newSublevel: String) {
        _uiState.value = _uiState.value.copy(
            currentSublevel = newSublevel,
            currentNumber = _uiState.value.currentNumberSet.random()
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

    fun newCalcProblem(){
        val numberList = _uiState.value.currentNumberSet
        var a: Int
        var b: Int
        var result: Int
        var operator: String

        do {
            a = numberList.random()
            b = numberList.random()
            operator = if ((0..1).random() == 0) "+" else "-"
            result = if (operator == "+") a + b else a - b
        } while (result !in numberList) // Ensure the result is within the number set

        val problem = "$a $operator $b"

        _uiState.value = _uiState.value.copy(
            currentCalcProblem = problem,
            currentCalcAnswer = result
        )
    }
}