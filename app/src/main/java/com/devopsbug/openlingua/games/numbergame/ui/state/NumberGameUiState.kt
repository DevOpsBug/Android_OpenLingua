package  com.devopsbug.openlingua.games.numbergame.ui.state

import com.devopsbug.openlingua.core.interfaces.OpenLinguaGameUiState
import com.devopsbug.openlingua.games.numbergame.data.Numbers

data class NumberGameUiState(
    override val gameName: String = "NumberGame",
    val currentLevel: Int = 1,
    val currentSublevel: String = "A",      //A -> learn numbers only; B -> calculate with numbers
    val currentNumberSet: List<Int> = Numbers.numbersByLevel[currentLevel-1].numberList,
    val currentNumber: Int = currentNumberSet.random(),
    val currentCalcProblem: String = "" ,
    val currentCalcAnswer: Int = 0
) : OpenLinguaGameUiState

