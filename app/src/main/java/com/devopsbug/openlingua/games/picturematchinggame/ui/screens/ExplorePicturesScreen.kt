package com.devopsbug.openlingua.games.picturematchinggame.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devopsbug.openlingua.core.interfaces.OpenLinguaGameScreenClass
import com.devopsbug.openlingua.core.interfaces.OpenLinguaGameScreenData
import com.devopsbug.openlingua.core.ui.GridImageButtonTile
import com.devopsbug.openlingua.core.util.OpenLinguaAudioUtils.playAudio
import com.devopsbug.openlingua.games.picturematchinggame.ui.state.PictureMatchingGameUiState
import com.devopsbug.openlingua.games.picturematchinggame.ui.state.PictureMatchingGameViewModel
import com.devopsbug.openlingua.ui.theme.greenButtonColor

class ExplorePicturesScreen(
    screenName: String = "Explore Pictures",
    screenRoute: String = "explore_pictures_screen",
    ladybugImage: Boolean = false,
    screenTitle: String = "Explore Pictures",
    subtitle: String = ""
)  : OpenLinguaGameScreenClass(screenName, screenRoute, ladybugImage, screenTitle, subtitle) {

    @Composable
    override fun ScreenContent(screenData: OpenLinguaGameScreenData) {
        val continueToNextScreen: () -> Unit = {
            screenData.gameNavController.navigate(screenData.gameScreenList[1].screenRoute)
        }
        screenData.gameUiState as PictureMatchingGameUiState
        screenData.gameViewModel as PictureMatchingGameViewModel
        val context = LocalContext.current
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
        ){
            LazyVerticalGrid(
                columns = GridCells.Fixed(4),
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
                    items(screenData.gameUiState.currentPictureGameCategory.categoryGameAssets) { gameAsset ->
                        GridImageButtonTile(
                            imageResource = gameAsset.imageResource,
                            onClick = {
                                val resourceId = gameAsset.audioResources.getValue(screenData.currentLanguage.languageCode)
                                playAudio(context, resourceId)
                            }
                        )
                    }
                },
            )
            Spacer(modifier = Modifier.height(16.dp))
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
                    text = "PLAY",
                    fontSize = 24.sp
                )
            }
        }
    }
}