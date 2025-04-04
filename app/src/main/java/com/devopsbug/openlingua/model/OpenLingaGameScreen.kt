package com.devopsbug.openlingua.model

import androidx.compose.runtime.Composable

data class OpenLingaGameScreen(
    val name: String,
    val screenContent: @Composable () -> Unit
)

