package  com.devopsbug.openlingua.games.picturematchinggame.ui.lettergamestate

import com.devopsbug.openlingua.games.lettergame.data.Datasource
import com.devopsbug.openlingua.games.lettergame.model.Letter

data class LetterGameUiState(
    val level: Int = 1,
    val currentLetterSet: List<Letter> = Datasource.lettersByLevel[level -1],
    val currentLetter: Letter = currentLetterSet.random(),
)

