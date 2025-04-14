package com.devopsbug.openlingua.core.interfaces

import androidx.compose.runtime.Composable
import com.devopsbug.openlingua.core.ui.GameScreenBase

abstract class OpenLinguaGameScreenClass (
    val screenName: String,
    val screenRoute: String,
    val ladybugImage: Boolean = false,
    val screenTitle: String = "",
    val subtitle: String = ""
){
    @Composable
    fun DisplayScreen(screenData: OpenLinguaGameScreenData) {
        GameScreenBase(
            gameScreenData = screenData,
            gameScreenContent = { ScreenContent(screenData) },
            ladybugImage = ladybugImage,
            screenTitle = screenTitle,
            subtitle = subtitle
        )
    }

    @Composable
    abstract fun ScreenContent(screenData: OpenLinguaGameScreenData)
}