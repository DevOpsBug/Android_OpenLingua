package com.devopsbug.openlingua.core.interfaces

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.devopsbug.openlingua.model.Language
import com.devopsbug.openlingua.ui.globalstate.OpenLinguaGlobalState
import com.devopsbug.openlingua.ui.globalstate.OpenLinguaGlobalViewModel

interface Game {
    val gameName: String

    fun navigateToEntry(navController: NavController)
    fun navigateToExplore(navController: NavController)
    fun navigateToRandom(navController: NavController)

    @Composable
    fun GameNavigation(
        openLinguaGlobalViewModel: OpenLinguaGlobalViewModel,
        openLinguaGlobalState: OpenLinguaGlobalState
    )

    @Composable
    fun GameButton()

    @Composable
    fun EntryScreen()

    @Composable
    fun ExploreScreen()

    @Composable
    fun RandomScreen(currentLanguage: Language)

}