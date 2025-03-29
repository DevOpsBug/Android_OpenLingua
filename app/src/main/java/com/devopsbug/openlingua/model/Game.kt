package com.devopsbug.openlingua.model

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import com.devopsbug.openlingua.ui.globalstate.OpenLinguaGlobalState
import com.devopsbug.openlingua.ui.globalstate.OpenLinguaGlobalViewModel

data class Game(
    val gameName: String,
    @DrawableRes val gameButtonImage: Int,
    val gameEntry: @Composable (currentLanguage: Language) -> Unit
){
    lateinit var navigateToStart: () -> Unit    //will be initialized after navController
}