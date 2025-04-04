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
import com.devopsbug.openlingua.core.interfaces.OpenLinguaGameEntry
import com.devopsbug.openlingua.games.numbergame.ui.screens.ExploreNumbersScreen
import com.devopsbug.openlingua.games.numbergame.ui.screens.NumberCalcScreen
import com.devopsbug.openlingua.games.numbergame.ui.screens.NumberGameStartScreen
import com.devopsbug.openlingua.games.numbergame.ui.screens.RandomNumberScreen
import com.devopsbug.openlingua.games.numbergame.ui.state.NumberGameViewModel
import com.devopsbug.openlingua.model.Language
import com.devopsbug.openlingua.model.OpenLingaGameScreen

class NumberGame(
    override val currentLanguage: Language
) : OpenLinguaGameEntry {

    @Composable
    override fun GameNavigation() {
        // Initialize State and Navigation
        val navController: NavHostController = rememberNavController()
        val backStackEntry by navController.currentBackStackEntryAsState()
        val numberGameViewModel: NumberGameViewModel = viewModel()
        val numberGameUiState by numberGameViewModel.uiState.collectAsState()

        //Define List of Screens for this game
        var numberGameScreens = mutableListOf<OpenLingaGameScreen>()
        numberGameScreens = mutableListOf(
            OpenLingaGameScreen(
                name = "NumberLetterGame Start Screen",
                screenContent = {
                    NumberGameStartScreen(
                        onClickExplore = { navController.navigate(numberGameScreens[1].name) },
                        currentLevel = numberGameUiState.currentLevel,
                        updateLevel = { numberGameViewModel.updateLevel(it) },
                        currentLanguage = currentLanguage,
                        currentSublevel = numberGameUiState.currentSublevel,
                        updateSublevel = { numberGameViewModel.updateSublevel(it) },
                    )
                }
            ),
            OpenLingaGameScreen(
                name = "Explore Numbers Screen",
                screenContent = {
                    ExploreNumbersScreen(
                        currentLanguage = currentLanguage,
                        currentLevel = numberGameUiState.currentLevel,
                        currentNumberSet = numberGameUiState.currentNumberSet,
                        onClickContinue = {
                            if (numberGameUiState.currentSublevel == "A") {
                                navController.navigate(numberGameScreens[2].name)
                            } else {
                                navController.navigate(numberGameScreens[3].name)
                            }
                        }
                    )
                }
            ),
            OpenLingaGameScreen(
                name = "Random Number Screen",
                screenContent = {
                    RandomNumberScreen(
                        currentLanguage = currentLanguage,
                        currentNumber = numberGameUiState.currentNumber,
                        currentLevel = numberGameUiState.currentLevel,
                        newRandomNumber = { numberGameViewModel.newRandomNumber() },
                    )
                }
            ),
            OpenLingaGameScreen(
                name = "Number Calc Screen",
                screenContent = {
                    NumberCalcScreen(
                        currentLanguage = currentLanguage,
                        currentLevel = numberGameUiState.currentLevel,
                        currentCalcProblem = numberGameUiState.currentCalcProblem,
                        currentCalcAnswer = numberGameUiState.currentCalcAnswer,
                        onClick = { numberGameViewModel.newCalcProblem() }
                    )
                }
            )
        )

        val currentScreen = backStackEntry?.destination?.route ?: numberGameScreens.first().name

        NavHost(
            navController = navController,
            startDestination = numberGameScreens[0].name
        ) {
            numberGameScreens.forEach { screen ->
                composable(screen.name) {
                    screen.screenContent()
                }
            }
        }
    }
}
