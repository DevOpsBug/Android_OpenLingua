package com.devopsbug.openlingua.core.ui

import android.util.Log
import androidx.annotation.DrawableRes
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
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devopsbug.openlingua.R
import com.devopsbug.openlingua.data.Languages
import com.devopsbug.openlingua.model.Language
import com.devopsbug.openlingua.util.ImageAsset
import com.devopsbug.openlingua.util.OpenLinguaAudioUtils.getAudioResourceId
import com.devopsbug.openlingua.util.OpenLinguaAudioUtils.playAudio
import com.devopsbug.openlingua.util.OpenLinguaImageAssetUtils.imageBitmapFromAsset

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
    imageAsset: ImageAsset? = null,
    onClick: () -> Unit
) {
    val buttonSize = 248.dp
    val buttonFontSize = 100.sp
    val buttonImageSize = 100.dp
    Button(
        onClick = { onClick() },
        shape = RoundedCornerShape(percent = 20),
        border = BorderStroke(5.dp, Color.DarkGray),
        contentPadding = PaddingValues(12.dp),
        modifier = Modifier.size(buttonSize),
    ) {
        if (text != null) {
            Text(
                text = text,
                fontSize = buttonFontSize
            )
        } else if (imageRessource != null) {
            Image(
                painter = painterResource(id = imageRessource),
                contentDescription = "Button Icon",
                modifier = Modifier.size(buttonImageSize)
            )
        } else if (imageAsset != null) {
            Log.d("ASSET", "LargeButton loading $imageAsset")
            val bitmap = imageBitmapFromAsset(imageAsset.image_filename, imageAsset.asset_category)
            if (bitmap != null) {
                Image(
                    bitmap = bitmap,
                    contentDescription = imageAsset.asset_name,
                    modifier = Modifier.size(buttonImageSize)
                )
            }
        }

    }
}


//Template for square button tile inside grid, scales according to grid
@Composable
fun GridButtonTile(
    text: String? = null,
    imageRessource: Int? = null,
    imageAsset: ImageAsset? = null,
    onClick: () -> Unit
) {
    ButtonTile(
        text = text,
        imageRessource = imageRessource,
        imageAsset = imageAsset,
        buttonModifier = Modifier.aspectRatio(1f),
        buttonFontSize = 50.sp,
        onClick = onClick,
        //buttonImageSize = 50.dp
    )
}
//    Button(
//        onClick = { onClick() },
//        shape = RoundedCornerShape(percent = 20),
//        border = BorderStroke(5.dp, Color.DarkGray),
//        contentPadding = PaddingValues(12.dp),
//        modifier = Modifier.aspectRatio(1f),    //size(124.dp).
//    ) {
//        if (text != null) {
//            Text(
//                text = text,
//                fontSize = 50.sp
//            )
//        } else if (imageAssetName != null && imageAssetCategory != null) {
//            val bitmap = imageBitmapFromAsset(imageAssetName, imageAssetCategory)
//            if (bitmap != null) {
//                Image(
//                    bitmap = bitmap,
//                    contentDescription = "Button Icon",
//                    modifier = Modifier.size(50.dp)
//                )
//             }
//        else {
//                Text(text = "No Asset found")
//                Log.e("ASSET", "No asset found")
//
//            }
//        }
//
//    }
//}

//Template for button Tile
@Composable
fun ButtonTile(
    text: String? = null,
    imageRessource: Int? = null,
    imageAsset: ImageAsset? = null,
    buttonModifier: Modifier,
    buttonFontSize: TextUnit,
    //buttonImageSize: Dp,
    onClick: () -> Unit
) {
    Button(
        onClick = { onClick() },
        shape = RoundedCornerShape(percent = 20),
        border = BorderStroke(5.dp, Color.DarkGray),
        //contentPadding = PaddingValues(12.dp),
        contentPadding = PaddingValues(0.dp),
        modifier = buttonModifier,
    ) {
        if (text != null) {
            Text(
                text = text,
                fontSize = buttonFontSize
            )
        } else if (imageRessource != null) {
            Image(
                painter = painterResource(id = imageRessource),
                contentDescription = "Button Icon",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize() //size(buttonImageSize)
            )
        } else if (imageAsset != null) {
            Log.d("ASSET", "LargeButton loading $imageAsset")
            val bitmap = imageBitmapFromAsset(imageAsset.image_filename, imageAsset.asset_category)

            if (bitmap != null) {
                Image(
                    bitmap = bitmap,
                    contentDescription = imageAsset.asset_name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }

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

