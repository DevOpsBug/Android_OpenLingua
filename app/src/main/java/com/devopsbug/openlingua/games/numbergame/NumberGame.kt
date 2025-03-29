package com.devopsbug.openlingua.games.numbergame

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
import com.devopsbug.openlingua.games.numbergame.ui.screens.ExploreNumbersScreen
import com.devopsbug.openlingua.games.numbergame.ui.screens.NumberCalcScreen
import com.devopsbug.openlingua.games.numbergame.ui.screens.NumberGameStartScreen
import com.devopsbug.openlingua.games.numbergame.ui.screens.RandomNumberScreen
import com.devopsbug.openlingua.games.numbergame.ui.state.NumberGameViewModel
import com.devopsbug.openlingua.model.Language
import com.devopsbug.openlingua.ui.globalstate.OpenLinguaGlobalState
import com.devopsbug.openlingua.ui.globalstate.OpenLinguaGlobalViewModel

enum class NumberGameScreen(@StringRes val title: Int) {
    start(title = R.string.numbergame_start_screen),
    exploreNumbers(title = R.string.numbergame_explore_screen),
    randomNumber(title = R.string.numbergame_random_number_screen),
    numberCalc(title = R.string.numbergame_number_calc_screen)

}

@Composable
fun NumberGame(
    currentLanguage: Language,
) {

    // Initialize navController
    val navController: NavHostController = rememberNavController()

    // Get current back stack entry
    val backStackEntry by navController.currentBackStackEntryAsState()

    // Get the name of the current screen
    val currentScreen = NumberGameScreen.valueOf(
        backStackEntry?.destination?.route ?: NumberGameScreen.start.name
    )

    // Create ViewModel
    val numberGameViewModel: NumberGameViewModel = viewModel()

    // Create UI State
    val numberGameUiState by numberGameViewModel.uiState.collectAsState()

    NavHost(
        navController = navController,
        startDestination = NumberGameScreen.start.name,
    ) {
        composable(route = NumberGameScreen.start.name) {
            Log.d(TAG, "navHost Calling route = ${NumberGameScreen.start.name}")
            NumberGameStartScreen(
                onClickExplore = { navController.navigate(NumberGameScreen.exploreNumbers.name) },
                currentLevel = numberGameUiState.currentLevel,
                updateLevel = { numberGameViewModel.updateLevel(it) },
                currentLanguage = currentLanguage,
                currentSublevel = numberGameUiState.currentSublevel,
                updateSublevel = { numberGameViewModel.updateSublevel(it) },
            )
        }
        composable(route = NumberGameScreen.exploreNumbers.name) {
            Log.d(
                TAG,
                "navHost: Calling route = ${NumberGameScreen.exploreNumbers.name}"
            )
            ExploreNumbersScreen(
                currentLanguage = currentLanguage,
                currentLevel = numberGameUiState.currentLevel,
                currentNumberSet = numberGameUiState.currentNumberSet,
                onClickContinue = {
                    if (numberGameUiState.currentSublevel == "A") {
                        navController.navigate(NumberGameScreen.randomNumber.name)
                    } else {
                        navController.navigate(NumberGameScreen.numberCalc.name)
                    }
                }
            )
        }

        composable(route = NumberGameScreen.randomNumber.name) {
            Log.d(TAG, "navHost: Calling route = ${NumberGameScreen.randomNumber.name}")
            RandomNumberScreen(
                currentLanguage = currentLanguage,
                currentNumber = numberGameUiState.currentNumber,
                currentLevel = numberGameUiState.currentLevel,
                newRandomNumber = { numberGameViewModel.newRandomNumber() },
            )
        }
        composable(route = NumberGameScreen.numberCalc.name) {
            Log.d(TAG, "navHost: Calling route = ${NumberGameScreen.numberCalc.name}")
            NumberCalcScreen(
                currentLanguage = currentLanguage,
                currentLevel = numberGameUiState.currentLevel,
                currentCalcProblem = numberGameUiState.currentCalcProblem,
                currentCalcAnswer = numberGameUiState.currentCalcAnswer,
                onClick = { numberGameViewModel.newCalcProblem() }
            )
        }
    }
}
