package  com.devopsbug.openlingua.games.numbergame.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devopsbug.openlingua.R
import com.devopsbug.openlingua.core.interfaces.OpenLinguaGameScreenClass
import com.devopsbug.openlingua.core.interfaces.OpenLinguaGameScreenData
import com.devopsbug.openlingua.core.ui.ImageAudioTile
import com.devopsbug.openlingua.games.numbergame.ui.state.NumberGameUiState
import com.devopsbug.openlingua.games.numbergame.ui.state.NumberGameViewModel

class NumberCalcScreen(
    screenName: String = "NumberCalc",
    screenRoute: String = "number_calc_screen",
    ladybugImage: Boolean = true,
    screenTitle: String = "What is the answer ?",
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
            Text(
                text = screenData.gameUiState.currentCalcProblem,
                fontSize = 48.sp,
            )
            Spacer(modifier = Modifier.height(16.dp))
            ImageAudioTile(
                language = screenData.currentLanguage,
                audioFilePostfix = screenData.gameUiState.currentCalcAnswer.toString(),
                imageRessource = R.drawable.volume_up_24px,
                onCompletion = { screenData.gameViewModel.newCalcProblem() },
            )
        }
    }
}





