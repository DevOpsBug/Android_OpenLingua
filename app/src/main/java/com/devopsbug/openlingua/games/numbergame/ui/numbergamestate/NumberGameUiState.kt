package  com.devopsbug.openlingua.games.numbergame.ui.numbergamestate

import com.devopsbug.openlingua.games.numbergame.data.Numbers

data class NumberGameUiState(
    val currentLevel: Int = 1,
    val currentNumberSet: List<Int> = Numbers.numbersByLevel[currentLevel-1],
    val currentNumber: Int = currentNumberSet.random(),
)

