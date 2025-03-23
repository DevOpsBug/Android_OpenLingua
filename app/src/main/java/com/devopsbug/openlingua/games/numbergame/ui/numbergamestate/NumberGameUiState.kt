package  com.devopsbug.openlingua.games.numbergame.ui.numbergamestate

import com.devopsbug.openlingua.games.numbergame.data.Numbers

data class NumberGameUiState(
    val currentLevel: Int = 1,
    val currentSublevel: String = "A",      //A -> learn numbers only; B -> calculate with numbers
    val currentNumberSet: List<Int> = Numbers.numbersByLevel[currentLevel-1].numberList,
    val currentNumber: Int = currentNumberSet.random(),
    val currentCalcProblem: String = "" ,
    val currentCalcAnswer: Int = 0
)

