package com.devopsbug.openlingua.core.interfaces

import androidx.compose.runtime.Composable

interface GameScreen {
    val name: String
    @Composable
    fun DisplayScreen(category: String)
}