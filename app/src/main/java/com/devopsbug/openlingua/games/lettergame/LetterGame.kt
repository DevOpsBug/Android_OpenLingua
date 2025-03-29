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
import com.devopsbug.openlingua.games.lettergame.ui.screens.ExploreLettersScreen
import com.devopsbug.openlingua.games.lettergame.ui.screens.LetterGameStartScreen
import com.devopsbug.openlingua.games.lettergame.ui.state.LetterGameViewModel
import com.devopsbug.openlingua.games.lettergame.ui.screens.RandomLetterScreen
import com.devopsbug.openlingua.model.Language
import com.devopsbug.openlingua.ui.globalstate.OpenLinguaGlobalState
import com.devopsbug.openlingua.ui.globalstate.OpenLinguaGlobalViewModel

enum class LetterGameScreen(@StringRes val title: Int) {
    start(title = R.string.app_name),
    exploreLetters(title = R.string.lettergame_explore_screen),
    randomLetter(title = R.string.lettergame_random_letter_screen)
}

//@Preview
@Composable
fun LetterGame(
    currentLanguage: Language
    //openLinguaGlobalViewModel: OpenLinguaGlobalViewModel,
    //openLinguaGlobalState: OpenLinguaGlobalState
) {

    // Initialize navController
    val navController: NavHostController = rememberNavController()

    // Get current back stack entry
    val backStackEntry by navController.currentBackStackEntryAsState()

    // Get the name of the current screen
    val currentScreen = LetterGameScreen.valueOf(
        backStackEntry?.destination?.route ?: LetterGameScreen.start.name
    )

    // Create ViewModel
    val letterGameViewModel: LetterGameViewModel = viewModel()

    val letterGameUiState by letterGameViewModel.uiState.collectAsState()

    NavHost(
        navController = navController,
        startDestination = LetterGameScreen.start.name,
    ) {
        composable(route = LetterGameScreen.start.name) {
            Log.d(TAG, "navHost Calling route = ${LetterGameScreen.start.name}")
            LetterGameStartScreen(
                onClickExplore = { navController.navigate(LetterGameScreen.exploreLetters.name) },
                //onClickLanguage = { navController.navigate(LetterGameScreen.randomLetter.name) },
                //updateLanguage = { openLinguaGlobalViewModel.updateLanguage(it) },
                currentLevel = letterGameUiState.level,
                updateLevel = { letterGameViewModel.updateLevel(it) },
                currentLanguage = currentLanguage,
            )
        }
        composable(route = LetterGameScreen.exploreLetters.name) {
            Log.d(
                TAG,
                "navHost: Calling route = ${LetterGameScreen.exploreLetters.name}"
            )
            ExploreLettersScreen(
                currentLanguage = currentLanguage,
                currentLevel = letterGameUiState.level,
                currentLetterSet = letterGameUiState.currentLetterSet,
                continueToRandomLetterScreen = { navController.navigate(LetterGameScreen.randomLetter.name) }
            )
        }
        composable(route = LetterGameScreen.randomLetter.name) {
            Log.d(TAG, "navHost: Calling route = ${LetterGameScreen.randomLetter.name}")
            RandomLetterScreen(
                currentLanguage = currentLanguage,
                currentLetter = letterGameUiState.currentLetter,
                currentLevel = letterGameUiState.level,
                newRandomLetter = { letterGameViewModel.newRandomLetter() },
            )
        }
    }
}