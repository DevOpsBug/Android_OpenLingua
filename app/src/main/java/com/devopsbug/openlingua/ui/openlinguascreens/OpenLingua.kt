package com.devopsbug.openlingua.ui.openlinguascreens

import android.content.ContentValues.TAG
import android.util.Log
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.devopsbug.openlingua.R
import com.devopsbug.openlingua.games.lettergame.LetterGame
import com.devopsbug.openlingua.games.lettergame.LetterGameScreen
import com.devopsbug.openlingua.games.lettergame.ui.lettergamescreens.ExploreLettersScreen
import com.devopsbug.openlingua.games.lettergame.ui.lettergamescreens.LetterGameStartScreen
import com.devopsbug.openlingua.games.lettergame.ui.lettergamescreens.RandomLetterScreen
import com.devopsbug.openlingua.games.numbergame.NumberGame
import com.devopsbug.openlingua.ui.globalstate.OpenLinguaGlobalViewModel
import com.devopsbug.openlingua.ui.theme.OpenLinguaTheme

enum class OpenLinguaScreen(@StringRes val title: Int) {
    start(title = R.string.app_name),
    lettergame(title = R.string.lettergame_game_name),
    numbergame(title = R.string.numbergame_game_name),
    //exploreLetters(title = R.string.lettergame_explore_screen),
    //randomLetter(title = R.string.lettergame_random_letter_screen)
}

@Preview
@Composable
fun OpenLingua() {

    // Initialize navController
    val navController: NavHostController = rememberNavController()

    // Get current back stack entry
    val backStackEntry by navController.currentBackStackEntryAsState()

    // Get the name of the current screen
    val currentScreen = OpenLinguaScreen.valueOf(
        backStackEntry?.destination?.route ?: OpenLinguaScreen.start.name
    )

    // Create ViewModel
    val openLinguaGlobalViewModel: OpenLinguaGlobalViewModel = viewModel()

    OpenLinguaTheme {
        Scaffold(
            topBar = {
                OpenLinguaTopAppBar(
                    navigateHome = { navController.navigate(OpenLinguaScreen.start.name) },
                    currentScreenTitle = currentScreen.title,
                )
            },
            modifier = Modifier
                .background(color = MaterialTheme.colorScheme.background)
        ) { innerPadding ->
            val openLinguaGlobalState by openLinguaGlobalViewModel.uiState.collectAsState()

            NavHost(
                navController = navController,
                startDestination = OpenLinguaScreen.start.name,
                modifier = Modifier.padding(innerPadding)
            ) {
                composable(route = OpenLinguaScreen.start.name) {
                    Log.d(TAG, "navHost Calling route = ${OpenLinguaScreen.start.name}")
                    OpenLinguaStartScreen(
                        onClickLetterGame = { navController.navigate(OpenLinguaScreen.lettergame.name) },
                        onClickNumberGame = { navController.navigate(OpenLinguaScreen.numbergame.name) },
                        updateLanguage = { openLinguaGlobalViewModel.updateLanguage(it) },
                        currentLanguage = openLinguaGlobalState.currentLanguage,
                    )
                }
                composable(route = OpenLinguaScreen.lettergame.name) {
                    Log.d(
                        TAG,
                        "navHost: Calling route = ${OpenLinguaScreen.lettergame.name}"
                    )
                    OpenLinguaTheme {
                        LetterGame(innerPadding)
                    }
                }
                composable(route = OpenLinguaScreen.numbergame.name) {
                    Log.d(TAG, "navHost: Calling route = ${OpenLinguaScreen.numbergame.name}")
                    OpenLinguaTheme {
                        NumberGame(innerPadding)
                    }
                }
            }

        }

    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OpenLinguaTopAppBar(
    navigateHome: () -> Unit,
    @StringRes currentScreenTitle: Int,
) {
        TopAppBar(
            title = {
                Row (
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    IconButton(onClick = navigateHome) {
                        Icon(
                            imageVector = Icons.Outlined.Home,
                            contentDescription = stringResource(R.string.back_button),
                            modifier = Modifier.fillMaxHeight()
                        )
                    }
                    //Spacer(modifier = Modifier.weight(1f))
                    Text(
                        stringResource(currentScreenTitle),
                        color = Color.White,
                        modifier = Modifier
                            .fillMaxWidth(0.7f)
                    )
                    Image(
                        painter = painterResource(R.drawable.ic_launcher_foreground),
                        contentDescription = "OpenLingua Icon",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth(fraction = 1f)
                            .padding(6.dp)
                    )
                }
                    },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary,
                titleContentColor = MaterialTheme.colorScheme.onPrimary,
                navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
            ),

        )
}