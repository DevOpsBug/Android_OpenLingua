package  com.devopsbug.openlingua.games.lettergame.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.devopsbug.openlingua.core.interfaces.OpenLinguaGameScreenClass
import com.devopsbug.openlingua.core.interfaces.OpenLinguaGameScreenData
import com.devopsbug.openlingua.core.ui.TextAudioTile
import com.devopsbug.openlingua.games.lettergame.ui.state.LetterGameUiState
import com.devopsbug.openlingua.games.lettergame.ui.state.LetterGameViewModel


class RandomLetterScreen(
    screenName: String = "RandomLetter",
    screenRoute: String = "random_letters_screen",
    ladybugImage: Boolean = true,
    screenTitle: String = "How do you say this letter",
    subtitle: String = ""
)  : OpenLinguaGameScreenClass(screenName, screenRoute, ladybugImage, screenTitle, subtitle) {

    @Composable
    override fun ScreenContent(screenData: OpenLinguaGameScreenData) {
        screenData.gameUiState as LetterGameUiState
        screenData.gameViewModel as LetterGameViewModel
        Row (
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ){
            TextAudioTile(
                language = screenData.currentLanguage,
                audioFilePostfix = screenData.gameUiState.currentLetter.letterLiteral.lowercase(),
                tileText = screenData.gameUiState.currentLetter.letterLiteral,
                onCompletion = { screenData.gameViewModel.newRandomLetter() }
            )
        }
    }
}
