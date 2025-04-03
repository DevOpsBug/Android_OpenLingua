package com.devopsbug.openlingua.ui.globalstate

import com.devopsbug.openlingua.data.OpenLinguaGames
import com.devopsbug.openlingua.data.Languages
import com.devopsbug.openlingua.model.OpenLinguaGame
import com.devopsbug.openlingua.model.Language


data class OpenLinguaGlobalState(
    val currentLanguage: Language = Languages.english,
    val currentOpenLinguaGame: OpenLinguaGame = OpenLinguaGames.openLinguaGame,
    //val currentPictureGameCategory: PictureGameCategory = Datasource.gameCategories[0],
)
