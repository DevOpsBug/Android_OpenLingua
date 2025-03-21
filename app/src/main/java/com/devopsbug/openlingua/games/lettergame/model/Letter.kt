package com.devopsbug.openlingua.games.lettergame.model

import androidx.annotation.RawRes

data class Letter(
    val letterLiteral: String,
    @RawRes val letterAudioGerman: Int
)
