package com.devopsbug.openlingua.games.picturematchinggame.ui.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.devopsbug.openlingua.core.interfaces.GameScreen

object StartScreen : GameScreen {
    override val name = "Picture Game Start Screen"

    @Composable
    override fun DisplayScreen(category: String) {
        Text(text = "$category Game")
    }
}