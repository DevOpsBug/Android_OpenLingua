package com.devopsbug.openlingua.games.lettergame

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
import com.devopsbug.openlingua.core.interfaces.OpenLinguaGameScreenData
import com.devopsbug.openlingua.games.lettergame.ui.screens.ExploreLettersScreen
import com.devopsbug.openlingua.games.lettergame.ui.screens.LetterGameStartScreen
import com.devopsbug.openlingua.games.lettergame.ui.screens.RandomLetterScreen
import com.devopsbug.openlingua.games.lettergame.ui.state.LetterGameViewModel
import com.devopsbug.openlingua.model.Language

class LetterGame(
    override val currentLanguage: Language
) : OpenLinguaGameEntry {

    @Composable
    override fun GameNavigation() {
        // Initialize State and Navigation
        val letterGameNavController: NavHostController = rememberNavController()
        val backStackEntry by letterGameNavController.currentBackStackEntryAsState()
        val letterGameViewModel: LetterGameViewModel = viewModel()
        val letterGameUiState by letterGameViewModel.uiState.collectAsState()

        // Initialize GameScreenData
        val letterGameScreenData = OpenLinguaGameScreenData(
            gameName = "LetterGame",
            currentLanguage = currentLanguage,
            gameCoverImage = R.drawable.letter_game,
            gameNavController = letterGameNavController,
            gameUiState = letterGameUiState,
            gameViewModel = letterGameViewModel,
            gameScreenList = listOf(
                LetterGameStartScreen(),
                ExploreLettersScreen(),
                RandomLetterScreen()
            )
        )

        NavHost(
            navController = letterGameNavController,
            startDestination = letterGameScreenData.gameScreenList[0].screenRoute
        ) {
            letterGameScreenData.gameScreenList.forEach { screen ->
                composable(route = screen.screenRoute) {
                    screen.DisplayScreen(letterGameScreenData)
                }
            }
        }
    }
}
