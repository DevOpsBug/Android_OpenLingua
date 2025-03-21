package  com.devopsbug.openlingua.games.numbergame.ui.state

import com.devopsbug.openlingua.games.numbergame.data.Numbers
import com.devopsbug.openlingua.games.numbergame.model.Number

data class NumberGameUiState(
    val currentLevel: Int = 1,
    val currentNumberSet: List<Int> = Numbers.numbersByLevel[currentLevel-1],
    val currentNumber: Int = currentNumberSet.random(),
)

