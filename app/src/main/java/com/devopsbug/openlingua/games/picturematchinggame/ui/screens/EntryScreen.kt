package com.devopsbug.openlingua.games.picturematchinggame.ui.screens

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
import com.devopsbug.openlingua.games.lettergame.ui.lettergamescreens.ExploreLettersScreen
import com.devopsbug.openlingua.games.lettergame.ui.lettergamescreens.LetterGameStartScreen
import com.devopsbug.openlingua.games.lettergame.ui.lettergamestate.LetterGameViewModel
import com.devopsbug.openlingua.games.lettergame.ui.lettergamescreens.RandomLetterScreen
import com.devopsbug.openlingua.ui.globalstate.OpenLinguaGlobalState
import com.devopsbug.openlingua.ui.globalstate.OpenLinguaGlobalViewModel

enum class PictureMatchingGameScreen(@StringRes val title: Int) {
    explore(title = R.string.picturematchinggame_explore_screen),
    random(title = R.string.picturematchinggame_random_screen),
}

//@Preview
@Composable
fun PictureMatchingGame(
    openLinguaGlobalViewModel: OpenLinguaGlobalViewModel,
    openLinguaGlobalState: OpenLinguaGlobalState
) {

    // Initialize navController
    val navController: NavHostController = rememberNavController()

    // Get current back stack entry
    val backStackEntry by navController.currentBackStackEntryAsState()

    // Get the name of the current screen
    val currentScreen = PictureMatchingGameScreen.valueOf(
        backStackEntry?.destination?.route ?: PictureMatchingGameScreen.explore.name
    )

    // Create ViewModel
    val letterGameViewModel: LetterGameViewModel = viewModel()
    //val openLinguaGlobalViewModel: OpenLinguaGlobalViewModel = viewModel()

    val letterGameUiState by letterGameViewModel.uiState.collectAsState()
    //val openLinguaGlobalState by openLinguaGlobalViewModel.uiState.collectAsState()

    NavHost(
        navController = navController,
        startDestination = PictureMatchingGameScreen.explore.name,
        //modifier = Modifier.padding(innerPadding)
    ) {
        composable(route = PictureMatchingGameScreen.explore.name) {
            Log.d(TAG, "navHost Calling route = ${PictureMatchingGameScreen.explore.name}")
            LetterGameStartScreen(
                onClickExplore = { navController.navigate(PictureMatchingGameScreen.explore.name) },
                //onClickLanguage = { navController.navigate(LetterGameScreen.randomLetter.name) },
                updateLanguage = { openLinguaGlobalViewModel.updateLanguage(it) },
                currentLevel = letterGameUiState.level,
                updateLevel = { letterGameViewModel.updateLevel(it) },
                currentLanguage = openLinguaGlobalState.currentLanguage,
            )
        }
        composable(route = PictureMatchingGameScreen.explore.name) {
            Log.d(
                TAG,
                "navHost: Calling route = ${PictureMatchingGameScreen.explore.name}"
            )
            ExploreLettersScreen(
                currentLanguage = openLinguaGlobalState.currentLanguage,
                updateLanguage = { openLinguaGlobalViewModel.updateLanguage(it) },
                currentLevel = letterGameUiState.level,
                currentLetterSet = letterGameUiState.currentLetterSet,
                continueToRandomLetterScreen = { navController.navigate(PictureMatchingGameScreen.random.name) }
            )
        }
        composable(route = PictureMatchingGameScreen.random.name) {
            Log.d(TAG, "navHost: Calling route = ${PictureMatchingGameScreen.random.name}")
            RandomLetterScreen(
                currentLanguage = openLinguaGlobalState.currentLanguage,
                currentLetter = letterGameUiState.currentLetter,
                currentLevel = letterGameUiState.level,
                newRandomLetter = { letterGameViewModel.newRandomLetter() },
            )
        }
    }
}