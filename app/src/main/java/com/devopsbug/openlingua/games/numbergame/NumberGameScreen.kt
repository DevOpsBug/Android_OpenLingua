package com.devopsbug.openlingua.games.numbergame

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
import com.devopsbug.openlingua.games.numbergame.ui.screens.ExploreNumbersScreen
import com.devopsbug.openlingua.games.numbergame.ui.screens.NumberGameStartScreen
import com.devopsbug.openlingua.games.numbergame.ui.screens.RandomNumberScreen
import com.devopsbug.openlingua.games.numbergame.ui.state.NumberGameViewModel
import com.devopsbug.openlingua.ui.globalstate.OpenLinguaGlobalViewModel
import com.devopsbug.openlingua.ui.theme.OpenLinguaTheme

enum class NumberGameScreen(@StringRes val title: Int) {
    start(title = R.string.numbergame_start_screen),
    exploreNumbers(title = R.string.numbergame_explore_screen),
    randomNumber(title = R.string.numbergame_random_number_screen)
}

@Preview
@Composable
fun NumberGame() {

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
    val openLinguaGlobalViewModel: OpenLinguaGlobalViewModel = viewModel()

    OpenLinguaTheme {
        Scaffold(
            topBar = {
                NumberGameTopAppBar(
                    navigateHome = { navController.navigate(NumberGameScreen.start.name) },
                    currentScreenTitle = currentScreen.title,
                )
            },
            modifier = Modifier
                .background(color = MaterialTheme.colorScheme.background)
        ) { innerPadding ->
            val numberGameUiState by numberGameViewModel.uiState.collectAsState()
            val openLinguaGlobalState by openLinguaGlobalViewModel.uiState.collectAsState()

            NavHost(
                navController = navController,
                startDestination = NumberGameScreen.start.name,
                modifier = Modifier.padding(innerPadding)
            ) {
                composable(route = NumberGameScreen.start.name) {
                    Log.d(TAG, "navHost Calling route = ${NumberGameScreen.start.name}")
                    NumberGameStartScreen(
                        onClickExplore = { navController.navigate(NumberGameScreen.exploreNumbers.name) },
                        updateLanguage = { openLinguaGlobalViewModel.updateLanguage(it) },
                        currentLevel = numberGameUiState.currentLevel,
                        updateLevel = { numberGameViewModel.updateLevel(it) },
                        currentLanguage = openLinguaGlobalState.currentLanguage,
                    )
                }
                composable(route = NumberGameScreen.exploreNumbers.name) {
                    Log.d(
                        TAG,
                        "navHost: Calling route = ${NumberGameScreen.exploreNumbers.name}"
                    )
                    ExploreNumbersScreen(
                        currentLanguage = openLinguaGlobalState.currentLanguage,
                        currentLevel = numberGameUiState.currentLevel,
                        currentNumberSet = numberGameUiState.currentNumberSet,
                        onClickContinue = { navController.navigate(NumberGameScreen.randomNumber.name) },
                    )
                }
                composable(route = NumberGameScreen.randomNumber.name) {
                    Log.d(TAG, "navHost: Calling route = ${NumberGameScreen.randomNumber.name}")
                    RandomNumberScreen(
                        currentLanguage = openLinguaGlobalState.currentLanguage,
                        currentNumber = numberGameUiState.currentNumber,
                        currentLevel = numberGameUiState.currentLevel,
                        newRandomNumber = { numberGameViewModel.newRandomNumber() },
                    )
                }
            }

        }

    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NumberGameTopAppBar(
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
                        contentDescription = "Number Game Icon",
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