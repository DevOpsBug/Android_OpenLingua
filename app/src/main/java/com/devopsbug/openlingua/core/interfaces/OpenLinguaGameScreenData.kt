package com.devopsbug.openlingua.core.interfaces

import androidx.annotation.DrawableRes
import com.devopsbug.openlingua.model.Language

interface OpenLinguaGameScreenData {
    val screenTitle: String
    val currentLanguage: Language
    val gameCoverImage: Int
    val subtitle: String
    val ladybugImage: Boolean
}

