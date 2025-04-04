package com.devopsbug.openlingua.games.lettergame

import android.content.ContentValues.TAG
import android.util.Log
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.devopsbug.openlingua.R
import com.devopsbug.openlingua.core.interfaces.OpenLinguaGameEntry
import com.devopsbug.openlingua.games.lettergame.ui.screens.ExploreLettersScreen
import com.devopsbug.openlingua.games.lettergame.ui.screens.LetterGameStartScreen
import com.devopsbug.openlingua.games.lettergame.ui.screens.RandomLetterScreen
import com.devopsbug.openlingua.games.lettergame.ui.state.LetterGameViewModel
import com.devopsbug.openlingua.model.Language
import com.devopsbug.openlingua.model.OpenLingaGameScreen

class LetterGame(
    override val currentLanguage: Language
) : OpenLinguaGameEntry {

    @Composable
    override fun GameNavigation() {
        // Initialize State and Navigation
        val navController: NavHostController = rememberNavController()
        val backStackEntry by navController.currentBackStackEntryAsState()
        val letterGameViewModel: LetterGameViewModel = viewModel()
        val letterGameUiState by letterGameViewModel.uiState.collectAsState()

        //Define List of Screens for this game
        var letterGameScreens = mutableListOf<OpenLingaGameScreen>()
        letterGameScreens = mutableListOf(
            OpenLingaGameScreen(
                name = "LetterGame Start Screen",
                screenContent = {
                    LetterGameStartScreen(
                        onClickExplore = { navController.navigate(letterGameScreens[1].name) },
                        currentLevel = letterGameUiState.level,
                        updateLevel = { letterGameViewModel.updateLevel(it) },
                        currentLanguage = currentLanguage,
                    )
                }
            ),
            OpenLingaGameScreen(
                name = "Explore Letters Screen",
                screenContent = {
                    ExploreLettersScreen(
                        currentLanguage = currentLanguage,
                        currentLevel = letterGameUiState.level,
                        currentLetterSet = letterGameUiState.currentLetterSet,
                        continueToRandomLetterScreen = { navController.navigate(letterGameScreens[2].name) }
                    )
                }
            ),
            OpenLingaGameScreen(
                name = "Random Letter Screen",
                screenContent = {
                    RandomLetterScreen(
                        currentLanguage = currentLanguage,
                        currentLetter = letterGameUiState.currentLetter,
                        currentLevel = letterGameUiState.level,
                        newRandomLetter = { letterGameViewModel.newRandomLetter() },
                    )
                }
            )
        )

        val currentScreen = backStackEntry?.destination?.route ?: letterGameScreens.first().name

        NavHost(
            navController = navController,
            startDestination = letterGameScreens[0].name
        ) {
            letterGameScreens.forEach { screen ->
                composable(screen.name) {
                    screen.screenContent()
                }
            }
        }
    }
}
