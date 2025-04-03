package com.devopsbug.openlingua.core.interfaces

import androidx.compose.runtime.Composable
import com.devopsbug.openlingua.model.Language

interface OpenLinguaGameEntry {
    val currentLanguage: Language
    val gameScreens: List<String>


    @Composable
    fun GameNavigation()

}
