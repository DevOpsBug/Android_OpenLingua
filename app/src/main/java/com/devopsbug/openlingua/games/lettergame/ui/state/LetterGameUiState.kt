package  com.devopsbug.openlingua.games.lettergame.ui.state

import com.devopsbug.openlingua.core.interfaces.OpenLinguaGameUiState
import com.devopsbug.openlingua.games.lettergame.data.Datasource
import com.devopsbug.openlingua.games.lettergame.model.Letter

data class LetterGameUiState(
    override val gameName: String = "Letter Game",
    val currentLevel: Int = 1,
    val currentLetterSet: List<Letter> = Datasource.lettersByLevel[currentLevel -1],
    val currentLetter: Letter = currentLetterSet.random(),
) : OpenLinguaGameUiState