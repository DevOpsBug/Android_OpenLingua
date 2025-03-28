package com.devopsbug.openlingua.games.picturematchinggame

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.devopsbug.openlingua.core.interfaces.Game
import com.devopsbug.openlingua.core.ui.GridButtonTile
import com.devopsbug.openlingua.games.picturematchinggame.ui.screens.RandomPictureScreen
import com.devopsbug.openlingua.games.picturematchinggame.ui.screens.StartScreen
import com.devopsbug.openlingua.games.picturematchinggame.ui.state.PictureMatchingGameViewModel
import com.devopsbug.openlingua.model.Language
import com.devopsbug.openlingua.ui.globalstate.OpenLinguaGlobalState
import com.devopsbug.openlingua.ui.globalstate.OpenLinguaGlobalViewModel
import com.devopsbug.openlingua.util.OpenLinguaImageAssetUtils.loadCategoryAssetsFromCsvFile

class PictureMatchingGame(
    override val gameName: String,
    private val assetCategory: String,
) : Game {

    // Property to store the navigation lambda
    private var navigateToStart: () -> Unit = {}

    @Composable
    override fun GameNavigation(
        openLinguaGlobalViewModel: OpenLinguaGlobalViewModel,
        openLinguaGlobalState: OpenLinguaGlobalState
    ) {
        val navController: NavHostController = rememberNavController()
        val backStackEntry by navController.currentBackStackEntryAsState()
//        val currentScreen = GameScreen.valueOf(
//            backStackEntry?.destination?.route ?: GameScreen.start.name
//        )
        val pictureMatchingGameViewModel: PictureMatchingGameViewModel = viewModel()
        val pictureMatchingGameUiState by pictureMatchingGameViewModel.uiState.collectAsState()



        NavHost(
            navController = navController,
            startDestination = "start",
        ) {
            composable(route = "start") {
                EntryScreen()
            }

        }
        // Initialize the navigation lambda
        navigateToStart = { navController.navigate(route = "start") }
    }


    override fun navigateToEntry(navController: NavController) {}
    override fun navigateToExplore(navController: NavController) {}
    override fun navigateToRandom(navController: NavController) {}

    @Composable
    override fun GameButton() {
        val categoryAssets = loadCategoryAssetsFromCsvFile(
            context = LocalContext.current,
            category = "categories"
        )
        Log.d("ASSET", "GameButton: $categoryAssets")
        Log.d("ASSET", "categoryAsset: ${categoryAssets.find { it.asset_name == assetCategory }}")
        GridButtonTile(
            imageAsset = categoryAssets.find { it.asset_name == assetCategory },
            onClick = navigateToStart
        )

    }

    @Composable
    override fun EntryScreen() {
        StartScreen.DisplayScreen(category = assetCategory)
    }

    @Composable
    override fun ExploreScreen() {

    }

    @Composable
    override fun RandomScreen(
        currentLanguage: Language,
    ) {
        val categoryAssets = loadCategoryAssetsFromCsvFile(
            context = LocalContext.current,
            category = assetCategory
        ).toSet()
        val correctPicture = categoryAssets.random()
        val wrongPictures = categoryAssets.filter { it != correctPicture }.shuffled().take(3)
        RandomPictureScreen(
            currentLanguage = currentLanguage,
            wrongPictures = wrongPictures,
            correctPicture = correctPicture
        )

    }
}