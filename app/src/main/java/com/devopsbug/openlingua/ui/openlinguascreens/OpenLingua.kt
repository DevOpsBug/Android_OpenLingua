package com.devopsbug.openlingua.ui.openlinguascreens

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
import androidx.navigation.compose.rememberNavController
import com.devopsbug.openlingua.R
import com.devopsbug.openlingua.data.OpenLinguaGames
import com.devopsbug.openlingua.ui.globalstate.OpenLinguaGlobalViewModel
import com.devopsbug.openlingua.ui.theme.OpenLinguaTheme

@Preview
@Composable
fun OpenLingua() {

    val openLinguaGlobalViewModel: OpenLinguaGlobalViewModel = viewModel()
    val openLinguaGlobalState by openLinguaGlobalViewModel.uiState.collectAsState()
    val navController: NavHostController = rememberNavController()

    OpenLinguaGames.openLinguaGameLists.forEach { game ->
        game.navigateToGameEntry = {
            navController.navigate(game.gameName) }
    }

    OpenLinguaGames.openLinguaGame.navigateToGameEntry = {
        navController.navigate(OpenLinguaGames.openLinguaGame.gameName) }

    OpenLinguaTheme {

        Scaffold(
            topBar = {
                OpenLinguaTopAppBar(
                    navigateHome = {
                        OpenLinguaGames.openLinguaGame.navigateToGameEntry()
                    },
                    currentGameName = openLinguaGlobalState.currentOpenLinguaGame.gameName,
                )
            },
            modifier = Modifier
                .background(color = MaterialTheme.colorScheme.background)
        ) { innerPadding ->


            NavHost(
                navController = navController,
                startDestination = OpenLinguaGames.openLinguaGame.gameName,
                modifier = Modifier.padding(innerPadding)
            ) {
                composable(route = OpenLinguaGames.openLinguaGame.gameName) {
                    openLinguaGlobalViewModel.updateGame(newOpenLinguaGame = OpenLinguaGames.openLinguaGame)
                    OpenLinguaStartScreen(
                        updateLanguage = { openLinguaGlobalViewModel.updateLanguage(it) },
                        currentLanguage = openLinguaGlobalState.currentLanguage,
                    )
                }
                OpenLinguaGames.openLinguaGameLists.forEach { game ->
                    composable(route = game.gameName) {
                        openLinguaGlobalViewModel.updateGame(newOpenLinguaGame = game)
                        game.gameEntry(openLinguaGlobalState.currentLanguage)
                    }
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OpenLinguaTopAppBar(
    navigateHome: () -> Unit = {},
    currentGameName: String,
    ){
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
            navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
        ),
        title = {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(onClick = navigateHome) {
                    Icon(
                        imageVector = Icons.Outlined.Home,
                        contentDescription = stringResource(R.string.back_button),
                        modifier = Modifier.fillMaxHeight()
                    )
                }
                //Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "OpenLingua", //currentGameName,
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

    )
}






