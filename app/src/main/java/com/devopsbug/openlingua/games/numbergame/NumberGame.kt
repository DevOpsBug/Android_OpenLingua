package com.devopsbug.openlingua.games.numbergame

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
import com.devopsbug.openlingua.games.numbergame.ui.screens.ExploreNumbersScreen
import com.devopsbug.openlingua.games.numbergame.ui.screens.NumberCalcScreen
import com.devopsbug.openlingua.games.numbergame.ui.screens.NumberGameStartScreen
import com.devopsbug.openlingua.games.numbergame.ui.screens.RandomNumberScreen
import com.devopsbug.openlingua.games.numbergame.ui.state.NumberGameViewModel
import com.devopsbug.openlingua.model.Language

class NumberGame(
    override val currentLanguage: Language
) : OpenLinguaGameEntry {

    @Composable
    override fun GameNavigation() {
        // Initialize State and Navigation
        val numberGameNavController: NavHostController = rememberNavController()
        val backStackEntry by numberGameNavController.currentBackStackEntryAsState()
        val numberGameViewModel: NumberGameViewModel = viewModel()
        val numberGameUiState by numberGameViewModel.uiState.collectAsState()

        val numberGameScreenData = OpenLinguaGameScreenData(
            gameName = "NumberGame",
            currentLanguage = currentLanguage,
            gameCoverImage = R.drawable.number_game,
            gameNavController = numberGameNavController,
            gameUiState = numberGameUiState,
            gameViewModel = numberGameViewModel,
            gameScreenList = listOf(
                NumberGameStartScreen(),
                ExploreNumbersScreen(),
                RandomNumberScreen(),
                NumberCalcScreen()
            )
        )

        NavHost(
            navController = numberGameNavController,
            startDestination = numberGameScreenData.gameScreenList[0].screenRoute
        ) {
            numberGameScreenData.gameScreenList.forEach { screen ->
                composable(route = screen.screenRoute) {
                    screen.DisplayScreen(numberGameScreenData)
                }
            }
        }
    }
}
