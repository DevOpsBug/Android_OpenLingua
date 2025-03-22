package com.devopsbug.openlingua.util

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devopsbug.openlingua.R
import com.devopsbug.openlingua.model.Language
import com.devopsbug.openlingua.util.OpenLinguaUtils.getAudioResourceId
import com.devopsbug.openlingua.util.OpenLinguaUtils.playAudio

//Util Composable Function to display a tile with a speaker icon to reveal audio
@Composable
fun AudioTile(language: Language, audioFilePostfix: String, onCompletion: () -> Unit) {
    val context = LocalContext.current
    Button(
        onClick = {
            val resourceId = getAudioResourceId(context, language.audioFilePrefix, audioFilePostfix)
            playAudio(context, resourceId, onCompletion = onCompletion)
        },
        shape = RoundedCornerShape(percent = 20),
        border = BorderStroke(5.dp, Color.DarkGray),
        contentPadding = PaddingValues(12.dp),
    ) {
        Image(
            painter = painterResource(R.drawable.volume_up_24px),
            contentDescription = "Speaker",
            modifier = Modifier.size(72.dp),

        )
    }
}