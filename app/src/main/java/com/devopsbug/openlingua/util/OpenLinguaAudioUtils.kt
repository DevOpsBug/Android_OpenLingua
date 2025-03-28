package com.devopsbug.openlingua.util

import android.content.Context
import android.media.MediaPlayer
import android.util.Log


object OpenLinguaAudioUtils {
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