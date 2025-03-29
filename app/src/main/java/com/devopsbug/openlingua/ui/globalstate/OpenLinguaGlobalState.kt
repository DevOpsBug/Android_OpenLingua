package com.devopsbug.openlingua.ui.globalstate

import com.devopsbug.openlingua.data.Games
import com.devopsbug.openlingua.data.Languages
import com.devopsbug.openlingua.model.Game
import com.devopsbug.openlingua.model.Language


data class OpenLinguaGlobalState(
    val currentLanguage: Language = Languages.english,
    val currentGame: Game = Games.openLinguaGame,
)
