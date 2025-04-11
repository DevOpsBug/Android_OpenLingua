package com.devopsbug.openlingua.games.picturematchinggame.ui.screens

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.devopsbug.openlingua.core.interfaces.OpenLinguaGameScreenClass
import com.devopsbug.openlingua.core.interfaces.OpenLinguaGameScreenData
import com.devopsbug.openlingua.core.interfaces.OpenLinguaGameUiState
import com.devopsbug.openlingua.model.Language

data class PictureMatchingGameScreenData(
    override val gameName: String,
    override val currentLanguage: Language,
    override val gameCoverImage: Int,
    override val gameNavController: NavController,
    override val gameUiState: OpenLinguaGameUiState,
    override val gameViewModel: ViewModel,
    override val gameScreenList: List<OpenLinguaGameScreenClass>,
) : OpenLinguaGameScreenData


