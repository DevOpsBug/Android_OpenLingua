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
import com.devopsbug.openlingua.core.interfaces.OpenLinguaGameScreenData
import com.devopsbug.openlingua.games.picturematchinggame.model.PictureMatchingGameCategory
import com.devopsbug.openlingua.games.picturematchinggame.ui.screens.ExplorePicturesScreen
import com.devopsbug.openlingua.games.picturematchinggame.ui.screens.RandomPictureScreen
import com.devopsbug.openlingua.games.picturematchinggame.ui.state.PictureMatchingGameViewModel
import com.devopsbug.openlingua.model.Language


class PictureMatchingGame(
    override val currentLanguage: Language,
    val currentPictureGameCategory: PictureMatchingGameCategory
) : OpenLinguaGameEntry {


    @Composable
    override fun GameNavigation(
    ) {
        // Initialize State and Navigation
        val pictureMatchingGameNavController: NavHostController = rememberNavController()
        val backStackEntry by pictureMatchingGameNavController.currentBackStackEntryAsState()
        val pictureMatchingGameViewModel: PictureMatchingGameViewModel = viewModel()
        val pictureMatchingGameUiState by pictureMatchingGameViewModel.uiState.collectAsState()

        // Initialize PictureMatchingGameScreenData
        val pictureMatchingGameScreenData = OpenLinguaGameScreenData(
            gameName = "PictureMatchingGame",
            currentLanguage = currentLanguage,
            gameCoverImage = currentPictureGameCategory.categoryCoverImage,
            gameNavController = pictureMatchingGameNavController,
            gameUiState = pictureMatchingGameUiState,
            gameViewModel = pictureMatchingGameViewModel,
            gameScreenList = listOf(
                ExplorePicturesScreen(),
                RandomPictureScreen()
            )
        )


        //Update Game Category
        pictureMatchingGameViewModel.updateCategory(currentPictureGameCategory)

        NavHost(
            navController = pictureMatchingGameNavController,
            startDestination = pictureMatchingGameScreenData.gameScreenList[0].screenRoute
        ) {
            pictureMatchingGameScreenData.gameScreenList.forEach { screen ->
                composable(route = screen.screenRoute) {
                    screen.DisplayScreen(pictureMatchingGameScreenData)
                }
            }
        }
    }
}

