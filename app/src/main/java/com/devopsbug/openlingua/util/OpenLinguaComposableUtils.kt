package com.devopsbug.openlingua.util

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devopsbug.openlingua.R
import com.devopsbug.openlingua.data.Languages
import com.devopsbug.openlingua.model.Language
import com.devopsbug.openlingua.util.OpenLinguaUtils.getAudioResourceId
import com.devopsbug.openlingua.util.OpenLinguaUtils.playAudio

//Util Composable Function to display an image tile which reveals audio when clicked
@Composable
fun ImageAudioTile(
    language: Language,
    audioFilePostfix: String,
    @DrawableRes imageRessource: Int = R.drawable.volume_up_24px,
    onCompletion: () -> Unit
) {
    val context = LocalContext.current
    Column (
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LargeButtonTile(
            imageRessource = imageRessource,
            onClick = {
                val resourceId =
                    getAudioResourceId(context, language.audioFilePrefix, audioFilePostfix)
                playAudio(context, resourceId, onCompletion)
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
@Composable
fun TextAudioTile(
    language: Language,
    audioFilePostfix: String,
    tileText: String,
    onCompletion: () -> Unit
) {
    val context = LocalContext.current
    Column (
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LargeButtonTile(
            text = tileText,
            onClick = {
                val resourceId =
                    getAudioResourceId(context, language.audioFilePrefix, audioFilePostfix)
                playAudio(context, resourceId, onCompletion)
            }
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
fun LargeButtonTile(
    text: String? = null,
    imageRessource: Int? = null,
    onClick: () -> Unit
) {
    val context = LocalContext.current
    Button(
        onClick = { onClick() },
        shape = RoundedCornerShape(percent = 20),
        border = BorderStroke(5.dp, Color.DarkGray),
        contentPadding = PaddingValues(12.dp),
        modifier = Modifier.size(248.dp),
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