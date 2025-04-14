package  com.devopsbug.openlingua.games.numbergame.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.devopsbug.openlingua.core.interfaces.OpenLinguaGameScreenClass
import com.devopsbug.openlingua.core.interfaces.OpenLinguaGameScreenData
import com.devopsbug.openlingua.core.ui.TextAudioTile
import com.devopsbug.openlingua.games.numbergame.ui.state.NumberGameUiState
import com.devopsbug.openlingua.games.numbergame.ui.state.NumberGameViewModel

class RandomNumberScreen(
    screenName: String = "RandomNumber",
    screenRoute: String = "random_number_screen",
    ladybugImage: Boolean = true,
    screenTitle: String = "Can you say this number",
    subtitle: String = ""
)  : OpenLinguaGameScreenClass(screenName, screenRoute, ladybugImage, screenTitle, subtitle) {

    @Composable
    override fun ScreenContent(screenData: OpenLinguaGameScreenData) {
        screenData.gameUiState as NumberGameUiState
        screenData.gameViewModel as NumberGameViewModel
        Column (
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ){
            TextAudioTile(
                language = screenData.currentLanguage,
                audioFilePostfix = screenData.gameUiState.currentNumber.toString(),
                tileText = screenData.gameUiState.currentNumber.toString(),
                onCompletion = { screenData.gameViewModel.newRandomNumber() }
            )
        }
    }
}


