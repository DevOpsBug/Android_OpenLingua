package com.devopsbug.openlingua.games.picturematchinggame.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devopsbug.openlingua.R
import com.devopsbug.openlingua.core.ui.GridImageButtonTile
import com.devopsbug.openlingua.core.ui.LanguageLevelRow
import com.devopsbug.openlingua.core.util.OpenLinguaAudioUtils.playAudio
import com.devopsbug.openlingua.data.Languages
import com.devopsbug.openlingua.games.picturematchinggame.model.PictureGameCategory
import com.devopsbug.openlingua.model.Language
import com.devopsbug.openlingua.ui.theme.greenButtonColor


@Composable
fun ExplorePicturesScreen(
    currentLanguage: Language = Languages.german,
    currentPictureGameCategory: PictureGameCategory,
    continueToNextScreen: () -> Unit
    ) {
    val context = LocalContext.current
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth(1f)
            .padding(start = 24.dp, end = 24.dp)
    ){
        Spacer(modifier = Modifier.height(16.dp))
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .fillMaxWidth()
        ) {

            LanguageLevelRow(
                currentLanguage = currentLanguage,
                currentLevel = -1
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Explore the Images:",
                fontSize = 30.sp,
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "1. Click a picture to hear the word\n" +
                        "2. Click PLAY to start the game",
                fontSize = 16.sp
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            Image(
                painter = painterResource(R.drawable.devopsbug_bug_158x100),
                contentDescription = "Ladybug icon",
                modifier = Modifier.fillMaxWidth(fraction = 0.2f)
            )
            Spacer(modifier = Modifier.weight(1f))
        }
        Spacer(modifier = Modifier.height(16.dp))
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
                items(currentPictureGameCategory.categoryGameAssets) { gameAsset ->
                    GridImageButtonTile(
                        imageResource = gameAsset.imageResource,
                        onClick = {
                            val resourceId = gameAsset.audioResources.getValue(currentLanguage.languageCode)
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





