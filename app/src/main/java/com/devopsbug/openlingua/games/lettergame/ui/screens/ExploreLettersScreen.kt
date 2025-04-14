package com.devopsbug.openlingua.games.lettergame.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devopsbug.openlingua.core.interfaces.OpenLinguaGameScreenClass
import com.devopsbug.openlingua.core.interfaces.OpenLinguaGameScreenData
import com.devopsbug.openlingua.core.util.OpenLinguaAudioUtils.getAudioResourceId
import com.devopsbug.openlingua.core.util.OpenLinguaAudioUtils.playAudio
import com.devopsbug.openlingua.games.lettergame.model.Letter
import com.devopsbug.openlingua.games.lettergame.ui.state.LetterGameUiState
import com.devopsbug.openlingua.model.Language
import com.devopsbug.openlingua.ui.theme.greenButtonColor

class ExploreLettersScreen(
    screenName: String = "ExploreLetter",
    screenRoute: String = "explore_letters_screen",
    ladybugImage: Boolean = false,
    screenTitle: String = "Explore the Letters:",
    subtitle: String = "1. Click a letters to hear the pronounciation\n2. Click PLAY to start the game"
)  : OpenLinguaGameScreenClass(screenName, screenRoute, ladybugImage, screenTitle, subtitle) {

    @Composable
    override fun ScreenContent(screenData: OpenLinguaGameScreenData) {
        //initialize Setup
        val continueToRandomLetterScreen: () -> Unit = {
            screenData.gameNavController.navigate(
                screenData.gameScreenList[2].screenRoute) }
        screenData.gameUiState as LetterGameUiState
        //screenData.gameViewModel as LetterGameViewModel
        //val context = LocalContext.current

        LazyVerticalGrid(
            columns = GridCells.Fixed(5),
            contentPadding = PaddingValues(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            userScrollEnabled = true,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.8f)
                .background(color = MaterialTheme.colorScheme.background)
                .border(width = 1.dp, color = Color.DarkGray),
            content = {
                items(screenData.gameUiState.currentLetterSet) { letter ->
                    ExploreLetterTile(
                        letter = letter,
                        language = screenData.currentLanguage,
                    )
                }
            },
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = continueToRandomLetterScreen,
            colors = ButtonDefaults.buttonColors(
                containerColor =  greenButtonColor
            ),
            border = BorderStroke(width = 2.dp, color = Color.DarkGray),
            contentPadding = PaddingValues(16.dp),
            modifier = Modifier.fillMaxWidth()
        ){
            Text(
                text = "PLAY",
                fontSize = 24.sp
            )
        }
    }
}

//Function to display letter tile with audio playback when clicked
@Composable
private fun ExploreLetterTile(letter: Letter, language: Language, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    //   val mediaPlayer = MediaPlayer.create(context, letter.letterAudioGerman)
    Button(
        onClick = {
            val resourceId = getAudioResourceId(context, language.languageCode, letter.letterLiteral.lowercase())
            playAudio(context, resourceId)
        },
        modifier = modifier,
        shape = RoundedCornerShape(percent = 20),
        border = BorderStroke(1.dp, Color.DarkGray),
        contentPadding = PaddingValues(12.dp),
    ) {
        Text(
            text = letter.letterLiteral
        )
    }
}



