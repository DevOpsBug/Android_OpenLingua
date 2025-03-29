package com.devopsbug.openlingua.games.picturematchinggame

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
import com.devopsbug.openlingua.games.picturematchinggame.ui.screens.ExplorePicturesScreen
import com.devopsbug.openlingua.games.picturematchinggame.ui.screens.RandomPictureScreen
import com.devopsbug.openlingua.games.picturematchinggame.ui.state.PictureMatchingGameViewModel
import com.devopsbug.openlingua.model.Language
import com.devopsbug.openlingua.ui.globalstate.OpenLinguaGlobalState
import com.devopsbug.openlingua.ui.globalstate.OpenLinguaGlobalViewModel

enum class PictureMatchingGameScreen(@StringRes val title: Int) {
    start(title =R.string.picturematchinggame_explore_screen),
    explore(title = R.string.picturematchinggame_explore_screen),
    random(title = R.string.picturematchinggame_random_picture_screen)
}

//@Preview
@Composable
fun PictureMatchingGame(
    currentLanguage: Language
    //openLinguaGlobalViewModel: OpenLinguaGlobalViewModel,
    //openLinguaGlobalState: OpenLinguaGlobalState
) {

    // Initialize navController
    val navController: NavHostController = rememberNavController()

    // Get current back stack entry
    val backStackEntry by navController.currentBackStackEntryAsState()

    // Get the name of the current screen
    val currentScreen = PictureMatchingGameScreen.valueOf(
        backStackEntry?.destination?.route ?: PictureMatchingGameScreen.start.name
    )

    // Create ViewModel
    val pictureMatchingGameViewModel: PictureMatchingGameViewModel = viewModel()

    val pictureMatchingGameUiState by pictureMatchingGameViewModel.uiState.collectAsState()

    NavHost(
        navController = navController,
        startDestination = PictureMatchingGameScreen.start.name,
    ) {
        composable(route = PictureMatchingGameScreen.start.name) {
            Log.d(TAG, "navHost Calling route = ${PictureMatchingGameScreen.start.name}")
            ExplorePicturesScreen(
                currentLanguage = currentLanguage,
                currentGameCategory = pictureMatchingGameUiState.currentGameCategory,
                continueToNextScreen = { navController.navigate(PictureMatchingGameScreen.random.name) }
            )
        }
        composable(route = PictureMatchingGameScreen.explore.name) {
            Log.d(
                TAG,
                "navHost: Calling route = ${PictureMatchingGameScreen.explore.name}"
            )
            ExplorePicturesScreen(
                currentLanguage = currentLanguage,
                currentGameCategory = pictureMatchingGameUiState.currentGameCategory,
                continueToNextScreen = { navController.navigate(PictureMatchingGameScreen.random.name) }
            )
        }
        composable(route = PictureMatchingGameScreen.random.name) {
            Log.d(TAG, "navHost: Calling route = ${PictureMatchingGameScreen.random.name}")
            RandomPictureScreen(
                currentLanguage = currentLanguage,
                currentGameCategory = pictureMatchingGameUiState.currentGameCategory,
                newRandomPicture = {  },
            )
        }
    }
}