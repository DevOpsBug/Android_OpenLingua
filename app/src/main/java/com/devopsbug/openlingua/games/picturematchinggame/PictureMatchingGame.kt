package com.devopsbug.openlingua.games.picturematchinggame

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
import com.devopsbug.openlingua.games.picturematchinggame.model.PictureMatchingGameCategory
import com.devopsbug.openlingua.games.picturematchinggame.ui.screens.ExplorePicturesScreen
import com.devopsbug.openlingua.games.picturematchinggame.ui.screens.RandomPictureScreen
import com.devopsbug.openlingua.games.picturematchinggame.ui.state.PictureMatchingGameViewModel
import com.devopsbug.openlingua.model.Language
import com.devopsbug.openlingua.model.OpenLingaGameScreen


class PictureMatchingGame(
    override val currentLanguage: Language,
    val currentPictureGameCategory: PictureMatchingGameCategory
) : OpenLinguaGameEntry {

    @Composable
    override fun GameNavigation(
    ) {
        // Initialize State and Navigation
        val navController: NavHostController = rememberNavController()
        val backStackEntry by navController.currentBackStackEntryAsState()
        val pictureMatchingGameViewModel: PictureMatchingGameViewModel = viewModel()
        val pictureMatchingGameUiState by pictureMatchingGameViewModel.uiState.collectAsState()

        //Update Game Category
        pictureMatchingGameViewModel.updateCategory(currentPictureGameCategory)

        //Define List of Screens for this game
        var pictureMatchingGameScreens = mutableListOf<OpenLingaGameScreen>()

        pictureMatchingGameScreens = mutableListOf(
            OpenLingaGameScreen(
                name = "explore pictures screen",
                screenContent = {
                    ExplorePicturesScreen(
                        currentLanguage = currentLanguage,
                        currentPictureGameCategory = pictureMatchingGameUiState.currentPictureGameCategory,
                        continueToNextScreen = { navController.navigate(pictureMatchingGameScreens[1].name) }
                    )
                }
            ),
            OpenLingaGameScreen(
                name = "random picture screen",
                screenContent = {
                    RandomPictureScreen(
                        currentLanguage = currentLanguage,
                        currentCorrectGameAsset = pictureMatchingGameUiState.currentCorrectGameAsset,
                        randomizedGameAssetDisplayList = pictureMatchingGameUiState.randomizedGameAssetDisplayList,
                        newRandomPicture = { pictureMatchingGameViewModel.newRandomPicture() },
                    )
                }
            )

        )

        val currentScreen = backStackEntry?.destination?.route ?: pictureMatchingGameScreens.first().name

        NavHost(
            navController = navController,
            startDestination = pictureMatchingGameScreens[0].name
        ) {
            pictureMatchingGameScreens.forEach { screen ->
                composable(screen.name) {
                    screen.screenContent()
                }
            }
        }
    }
}

