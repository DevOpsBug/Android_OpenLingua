package com.devopsbug.openlingua.util

import android.content.Context
import android.media.MediaPlayer
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devopsbug.openlingua.data.Languages
import com.devopsbug.openlingua.games.numbergame.NumberGameScreen
import com.devopsbug.openlingua.model.Language


object OpenLinguaUtils {
    fun getAudioResourceId(context: Context, audioFileLanguagePrefix: String, audioFilePostfix: String): Int {
        val resourceName = "${audioFileLanguagePrefix}_$audioFilePostfix"
        return context.resources.getIdentifier(resourceName, "raw", context.packageName)
    }

    fun playAudio(context: Context, resourceId: Int, onCompletion: () -> Unit = {}) {
        try {
            val mediaPlayer = MediaPlayer.create(context, resourceId)
            mediaPlayer.setOnCompletionListener {
                mediaPlayer.release()
                onCompletion()
            }
            mediaPlayer.start()
        } catch (e: Exception) {
            Log.e("LetterGameUtils", "Error playing audio", e)
            onCompletion()
        }
    }






}