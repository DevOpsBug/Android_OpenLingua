package  com.devopsbug.openlingua.games.lettergame.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devopsbug.openlingua.core.interfaces.OpenLinguaGameScreenClass
import com.devopsbug.openlingua.core.interfaces.OpenLinguaGameScreenData
import com.devopsbug.openlingua.games.lettergame.ui.state.LetterGameUiState
import com.devopsbug.openlingua.games.lettergame.ui.state.LetterGameViewModel
import com.devopsbug.openlingua.ui.theme.greenButtonColor
import com.devopsbug.openlingua.ui.theme.primaryLightMediumContrast

class LetterGameStartScreen(
    screenName: String = "LetterGameStart",
    screenRoute: String = "letter_game_start_screen",
    ladybugImage: Boolean = true,
    screenTitle: String = "How to start:",
    subtitle: String = "1. Choose a level\n2. Click START to begin"
)  : OpenLinguaGameScreenClass(screenName, screenRoute, ladybugImage, screenTitle, subtitle) {

    @Composable
    override fun ScreenContent(screenData: OpenLinguaGameScreenData) {
        //initialize Setup
        val continueToNextScreen: () -> Unit = {
            screenData.gameNavController.navigate(
                screenData.gameScreenList[1].screenRoute) }
        screenData.gameUiState as LetterGameUiState
        screenData.gameViewModel as LetterGameViewModel
        //val context = LocalContext.current

        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .fillMaxWidth()
            //.border(1.dp, Color.DarkGray),
        ){
            listOf(1, 2, 3, 4).forEach {
                Row(
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth(0.8f).padding(8.dp)
                ) {
                    Spacer(modifier = Modifier.width(16.dp))
                    Button(
                        onClick = { screenData.gameViewModel.updateLevel(it) },
                        contentPadding = PaddingValues(16.dp),
                        border = BorderStroke(width = 2.dp, color = Color.DarkGray),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (screenData.gameUiState.currentLevel == it) {
                                primaryLightMediumContrast
                            } else {
                                ButtonDefaults.buttonColors().containerColor
                            }
                        )
                    ) {
                        Text(
                            text = "Level $it",
                            fontSize = 24.sp
                        )
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        text = when (it) {
                            1 -> "[A-M]"
                            2 -> "[A-Z}"
                            3 -> "[A-Z; a-m]"
                            else -> "[A-Z; a-z]"
                        },
                        fontSize = 24.sp
                    )
                }
            }
            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth(0.8f).padding(8.dp)
            ) {
                Spacer(modifier = Modifier.width(16.dp))
                Button(
                    onClick = continueToNextScreen,
                    colors = ButtonDefaults.buttonColors(
                        containerColor =  greenButtonColor
                    ),
                    border = BorderStroke(width = 2.dp, color = Color.DarkGray),
                    contentPadding = PaddingValues(16.dp),
                    modifier = Modifier.fillMaxWidth()
                ){
                    Text(
                        text = "START",
                        fontSize = 24.sp
                    )
                }
            }
        }
    }
}

