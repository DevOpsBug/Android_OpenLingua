package com.devopsbug.openlingua.model

import androidx.annotation.DrawableRes

data class Language(
    val name: String,
    val languageCode: String,
    @DrawableRes val flagImage: Int,
)
