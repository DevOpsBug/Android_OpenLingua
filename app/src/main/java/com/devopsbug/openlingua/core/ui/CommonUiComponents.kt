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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devopsbug.openlingua.R
import com.devopsbug.openlingua.core.util.OpenLinguaAudioUtils.getAudioResourceId
import com.devopsbug.openlingua.data.Languages
import com.devopsbug.openlingua.model.Language
import com.devopsbug.openlingua.core.util.OpenLinguaAudioUtils.playAudio

//Template for square button tile inside grid, size scales according to grid
@Composable
fun GridImageButtonTile(
    imageResource: Int,
    borderColor: Color = Color.DarkGray,
    onClick: () -> Unit
) {
    ImageButtonTile(
        imageResource = imageResource,
        borderColor = borderColor,
        onClick = onClick,
    )
}


//Template for button Tile
@Composable
fun ImageButtonTile(
    imageResource: Int,
    modifier: Modifier = Modifier,
    borderColor: Color = Color.DarkGray,
    onClick: () -> Unit
) {
    Button(
        onClick = { onClick() },
        shape = RoundedCornerShape(percent = 20),
        border = BorderStroke(5.dp, borderColor),
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

enum class TileSize(val dpSize: Dp) {
    SMALL(dpSize = 64.dp),
    MEDIUM(dpSize = 128.dp),
    LARGE(dpSize = 254.dp)
}

//Util Composable Function to display an image tile which reveals audio when clicked
@Composable
fun ImageAudioTile(
    tileSize: TileSize = TileSize.LARGE,
    language: Language,
    audioFilePostfix: String,
    @DrawableRes imageRessource: Int = R.drawable.volume_up_24px,
    onCompletion: () -> Unit,
    caption: String = "click to hear the answer",
    borderColor: Color = Color.DarkGray
) {
    val tileDpSize = tileSize.dpSize
    val context = LocalContext.current
    Column (
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ButtonTile(
            imageRessource = imageRessource,
            onClick = {
                val resourceId =
                    getAudioResourceId(context, language.languageCode, audioFilePostfix)
                playAudio(context, resourceId, onCompletion)
            },
            tileDpSize = tileDpSize,
            borderColor = borderColor
        )
        if (caption != "") {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = caption,
                fontSize = 20.sp,
            )
        }

    }
}

//Util Composable Function to display an text tile which reveals audio when clicked
@Composable
fun TextAudioTile(
    language: Language,
    audioFilePostfix: String,
    tileText: String,
    onCompletion: () -> Unit,
    tileDpSize: Dp = 254.dp,
    borderColor: Color = Color.DarkGray
) {
    val context = LocalContext.current
    Column (
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ButtonTile(
            text = tileText,
            onClick = {
                val resourceId =
                    getAudioResourceId(context, language.languageCode, audioFilePostfix)
                playAudio(context, resourceId, onCompletion)
            },
            borderColor = borderColor,
            tileDpSize = tileDpSize
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "click to hear the answer",
            fontSize = 20.sp,
        )
    }
}

//Template for large button tile
@Composable
fun ButtonTile(
    text: String? = null,
    imageRessource: Int? = null,
    onClick: () -> Unit,
    tileDpSize: Dp = 254.dp,
    borderColor: Color = Color.DarkGray
) {
    Button(
        onClick = { onClick() },
        shape = RoundedCornerShape(percent = 20),
        border = BorderStroke(5.dp, borderColor),
        contentPadding = PaddingValues(12.dp),
        modifier = Modifier.size(tileDpSize),
    ) {
        if (text != null) {
            Text(
                text = text,
                fontSize = 100.sp
            )
        } else if (imageRessource != null) {
            Image(
                painter = painterResource(id = imageRessource),
                contentDescription = "Button Icon",
                modifier = Modifier.size(100.dp)
            )
        }

    }
}

//function to display language selection row
@Composable
fun LanguageSelectionRow(
    currentLanguage: Language = Languages.german,
    updateLanguage: (Language) -> Unit,
    modifier: Modifier = Modifier.fillMaxWidth()
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
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
                        contentDescription = null,
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
        if (currentLevel >= 0) {
            Text(
                text = "Level $currentLevel",
                fontSize = 24.sp,
                color = MaterialTheme.colorScheme.primaryContainer,
                //modifier = Modifier.border(width = 1.dp, color = Color.DarkGray)

            )
        }
    }
}




