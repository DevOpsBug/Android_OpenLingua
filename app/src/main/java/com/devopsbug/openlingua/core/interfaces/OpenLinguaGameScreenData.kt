package com.devopsbug.openlingua.core.interfaces

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.devopsbug.openlingua.model.Language

data class OpenLinguaGameScreenData (
    val gameName: String,
    val currentLanguage: Language,
    val gameCoverImage: Int,
    val gameNavController: NavController,
    val gameUiState: OpenLinguaGameUiState,
    val gameViewModel: ViewModel,
    val gameScreenList: List<OpenLinguaGameScreenClass>
)

