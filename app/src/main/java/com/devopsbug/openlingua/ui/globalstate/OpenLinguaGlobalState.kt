package com.devopsbug.openlingua.ui.globalstate

import com.devopsbug.openlingua.data.Languages
import com.devopsbug.openlingua.model.Language

enum class Game(val gameName: String) {
    LETTERGAME(gameName = "lettergame"),
    NUMBERGAME(gameName = "numbergame"),

}

data class OpenLinguaGlobalState(
    val currentLanguage: Language = Languages.english,
    val currentGame: Game = Game.LETTERGAME
)
