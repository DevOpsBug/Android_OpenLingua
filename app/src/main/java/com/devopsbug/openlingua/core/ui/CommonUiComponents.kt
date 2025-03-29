package com.devopsbug.openlingua.core.ui

import androidx.annotation.DrawableRes
import androidx.annotation.RawRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devopsbug.openlingua.R
import com.devopsbug.openlingua.data.Languages
import com.devopsbug.openlingua.model.Language
import com.devopsbug.openlingua.core.util.OpenLinguaAudioUtils.playAudio

//Util Composable Function to display an image tile which reveals audio when clicked
@Composable
fun ImageAudioTile(
    @RawRes audioResource: Int,
    @DrawableRes imageResource: Int = R.drawable.volume_up_24px,
    onCompletion: () -> Unit = {}
) {
    val context = LocalContext.current
    Column (
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LargeImageButtonTile(
            imageResource = imageResource,
            onClick = {
                playAudio(context, audioResource, onCompletion)
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "click to hear the answer",
            fontSize = 20.sp,
        )
    }
}

//Util Composable Function to display an text tile which reveals audio when clicked
//@Composable
//fun TextAudioTile(
//    language: Language,
//    audioFilePostfix: String,
//    tileText: String,
//    onCompletion: () -> Unit
//) {
//    val context = LocalContext.current
//    Column (
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        LargeButtonTile(
//            text = tileText,
//            onClick = {
//                playAudio(context, resourceId, onCompletion)
//            }
//        )
//        Spacer(modifier = Modifier.height(16.dp))
//        Text(
//            text = "click to hear the answer",
//            fontSize = 20.sp,
//        )
//    }
//}

//Template for large button tile
@Composable
fun LargeImageButtonTile(
    imageResource: Int,
    onClick: () -> Unit
) {
    val buttonSize = 248.dp
    ImageButtonTile(
        imageResource = imageResource,
        onClick = onClick,
        modifier = Modifier.size(buttonSize)
    )


}


//Template for square button tile inside grid, size scales according to grid
@Composable
fun GridImageButtonTile(
    imageResource: Int,
    onClick: () -> Unit
) {
    ImageButtonTile(
        imageResource = imageResource,
        onClick = onClick,
    )
}


//Template for button Tile
@Composable
fun ImageButtonTile(
    imageResource: Int,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Button(
        onClick = { onClick() },
        shape = RoundedCornerShape(percent = 20),
        border = BorderStroke(5.dp, Color.DarkGray),
        contentPadding = PaddingValues(0.dp),
        modifier = modifier.aspectRatio(1f),
    ) {
        Image(
            painter = painterResource(id = imageResource),
            contentDescription = "Button Icon",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    }
}

//function to display language selection row
@Composable
fun LanguageSelectionRow(
    currentLanguage: Language = Languages.german,
    updateLanguage: (Language) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxWidth()
    ) {
        //Text(text = "Choose Language")
        Row {
            listOf(Languages.german, Languages.italian, Languages.english).forEach { language ->

                Button(
                    onClick = { updateLanguage(language) },
                    shape = RectangleShape,
                    //border = BorderStroke(5.dp, MaterialTheme.colorScheme.primaryContainer),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (currentLanguage == language) Color.LightGray else Color.White,
                        contentColor = MaterialTheme.colorScheme.onSecondaryContainer
                    ),
                    modifier = Modifier
                        .fillMaxWidth(fraction = 0.2f)
                        .weight(1f),
                ) {
                    Image(
                        painter = painterResource(language.flagImage),
                        contentDescription = language.name,
                    )
                }
            }
        }
    }
}

@Composable
fun LanguageLevelRow(
    currentLanguage: Language,
    currentLevel: Int
){
    Row (
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
        //.padding(start = 24.dp, end = 24.dp)

    ){
        Image(
            painter = painterResource(currentLanguage.flagImage),
            contentDescription = currentLanguage.name,
            modifier = Modifier.border(width = 1.dp, color = Color.DarkGray)
        )
        Text(
            text = "Level $currentLevel",
            fontSize = 24.sp,
            color = MaterialTheme.colorScheme.primaryContainer,
            //modifier = Modifier.border(width = 1.dp, color = Color.DarkGray)

        )
    }
}


