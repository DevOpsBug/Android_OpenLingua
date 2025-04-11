package  com.devopsbug.openlingua.games.picturematchinggame.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.devopsbug.openlingua.R
import com.devopsbug.openlingua.core.interfaces.OpenLinguaGameScreenClass
import com.devopsbug.openlingua.core.interfaces.OpenLinguaGameScreenData
import com.devopsbug.openlingua.core.ui.GridImageButtonTile
import com.devopsbug.openlingua.core.ui.ImageAudioTile
import com.devopsbug.openlingua.core.ui.TileSize
import com.devopsbug.openlingua.core.util.OpenLinguaAudioUtils.getAudioResourceId
import com.devopsbug.openlingua.core.util.OpenLinguaAudioUtils.playAudio
import com.devopsbug.openlingua.games.picturematchinggame.model.PictureMatchingGameAsset
import com.devopsbug.openlingua.games.picturematchinggame.ui.state.PictureMatchingGameUiState
import com.devopsbug.openlingua.games.picturematchinggame.ui.state.PictureMatchingGameViewModel
import com.devopsbug.openlingua.ui.theme.greenButtonColor
import kotlinx.coroutines.delay

class RandomPictureScreen(
    screenName: String = "Random Picture",
    screenRoute: String = "random_picture_screen",
    ladybugImage: Boolean = false,
    screenTitle: String = "Random Pictures",
    subtitle: String = ""
)  : OpenLinguaGameScreenClass(screenName, screenRoute, ladybugImage, screenTitle, subtitle) {

    @Composable
    override fun ScreenContent(screenData: OpenLinguaGameScreenData) {
        RandomPictureScreenContent(screenData as PictureMatchingGameScreenData)
    }
}

@Composable
fun RandomPictureScreenContent(
    pictureMatchingGameScreenData: PictureMatchingGameScreenData,
    ) {
    pictureMatchingGameScreenData.gameUiState as PictureMatchingGameUiState
    pictureMatchingGameScreenData.gameViewModel as PictureMatchingGameViewModel

    val currentLanguage = pictureMatchingGameScreenData.currentLanguage

    val initialShuffledList = pictureMatchingGameScreenData.gameUiState.currentPictureGameCategory.categoryGameAssets.shuffled().subList(0,4)
    var currentCorrectGameAsset by remember { mutableStateOf<PictureMatchingGameAsset>(initialShuffledList.random()) }
    var randomizedGameAssetDisplayList by remember { mutableStateOf<List<PictureMatchingGameAsset>>(initialShuffledList) }


    fun newRandomPicture() {
        // Access category from ViewModel
        val shuffledList = pictureMatchingGameScreenData.gameUiState.currentPictureGameCategory.categoryGameAssets.shuffled().subList(0,4)
        currentCorrectGameAsset = shuffledList.random()
        randomizedGameAssetDisplayList = shuffledList.shuffled()
    }

    var correctBorderColor: Color by remember { mutableStateOf(Color.DarkGray) }
    var wrongBorderColor: Color by remember { mutableStateOf(Color.DarkGray) }
    var showResultOverlay: Boolean by remember { mutableStateOf(false) }
    var answerIsCorrect: Boolean by remember { mutableStateOf(false) }
    var isNewRound: Boolean by remember { mutableStateOf(false) }

    // Play audio once on initial display
    val context = LocalContext.current
    val resourceId = getAudioResourceId(
        context,
        currentLanguage.languageCode,
        currentCorrectGameAsset.assetName
    )
    LaunchedEffect(isNewRound) { // Use Unit as the stable key
        playAudio(context, resourceId)
    }


//    Column(
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center,
//        modifier = Modifier
//            .fillMaxWidth(1f)
//            .padding(start = 24.dp, end = 24.dp)
//    ) {
//        Spacer(modifier = Modifier.height(16.dp))
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .fillMaxWidth()
        ) {
//            LanguageLevelRow(
//                currentLanguage = currentLanguage,
//                currentLevel = -1
//            )
//        }
//        Spacer(modifier = Modifier.height(16.dp))
//            Row {
//                Image(
//                    painter = painterResource(R.drawable.devopsbug_bug_158x100),
//                    contentDescription = "Ladybug icon",
//                    modifier = Modifier.fillMaxWidth(fraction = 0.2f)
//                )
//                Spacer(modifier = Modifier.weight(1f))
//            }
//            Spacer(modifier = Modifier.height(16.dp))
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                ImageAudioTile(
                    tileSize = TileSize.MEDIUM,
                    language = currentLanguage,
                    audioFilePostfix = currentCorrectGameAsset.assetName,
                    imageRessource = R.drawable.volume_up_24px,
                    onCompletion = {},
                    caption = "",
                )

            }
            Spacer(modifier = Modifier.height(16.dp))

            Box (
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
            ){
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    userScrollEnabled = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = MaterialTheme.colorScheme.background)
                        .border(width = 1.dp, color = Color.DarkGray),
                    content = {
                        items(randomizedGameAssetDisplayList) { gameAsset ->
                            if (gameAsset == currentCorrectGameAsset) {
                                GridImageButtonTile(
                                    imageResource = gameAsset.imageResource,
                                    borderColor = correctBorderColor,
                                    onClick = {
                                        wrongBorderColor = Color.Red
                                        correctBorderColor = Color.Green
                                        //newRandomPicture()
                                        showResultOverlay = true
                                        answerIsCorrect = true

                                    }
                                )
                            } else {
                                GridImageButtonTile(
                                    imageResource = gameAsset.imageResource,
                                    borderColor = wrongBorderColor,
                                    onClick = {
                                        wrongBorderColor = Color.Red
                                        correctBorderColor = Color.Green
                                        //newRandomPicture()
                                        showResultOverlay = true
                                        answerIsCorrect = false


                                    }
                                )
                            }
                        }
                    },
                )
                if (showResultOverlay) {
                    val tintColor = if (answerIsCorrect) greenButtonColor else Color.Red
                    val iconResource = if (answerIsCorrect) R.drawable.correct else R.drawable.wrong
                    val  contentDescription = if (answerIsCorrect) "Correct" else "Wrong"
                    val borderColor = if (answerIsCorrect) greenButtonColor else Color.Red
                    Image(
                        painter = painterResource(id = iconResource),
                        contentDescription = contentDescription,
                        colorFilter = ColorFilter.tint(tintColor), // Change color
                        modifier = Modifier
                            .size(TileSize.MEDIUM.dpSize)
                            .border(width = 10.dp, color = borderColor)
                            .background(Color.White),
                    )
                }
                LaunchedEffect(showResultOverlay) {
                    if (showResultOverlay) {
                        delay(2000) // Wait 2 seconds
                        showResultOverlay = false
                        newRandomPicture()
                        correctBorderColor = Color.DarkGray
                        wrongBorderColor = Color.DarkGray
                        isNewRound = !isNewRound
                    }
                }

            }
        //}
    }
}

@Composable
fun RandomPictureScreenContent_OLD(
    pictureMatchingGameScreenData: PictureMatchingGameScreenData,
) {
    pictureMatchingGameScreenData.gameUiState as PictureMatchingGameUiState
    pictureMatchingGameScreenData.gameViewModel as PictureMatchingGameViewModel

    val currentLanguage = pictureMatchingGameScreenData.currentLanguage

    val initialShuffledList = pictureMatchingGameScreenData.gameUiState.currentPictureGameCategory.categoryGameAssets.shuffled().subList(0,4)
    var currentCorrectGameAsset by remember { mutableStateOf<PictureMatchingGameAsset>(initialShuffledList.random()) }
    var randomizedGameAssetDisplayList by remember { mutableStateOf<List<PictureMatchingGameAsset>>(initialShuffledList) }


    fun newRandomPicture() {
        // Access category from ViewModel
        val shuffledList = pictureMatchingGameScreenData.gameUiState.currentPictureGameCategory.categoryGameAssets.shuffled().subList(0,4)
        currentCorrectGameAsset = shuffledList.random()
        randomizedGameAssetDisplayList = shuffledList.shuffled()
    }

    var correctBorderColor: Color by remember { mutableStateOf(Color.DarkGray) }
    var wrongBorderColor: Color by remember { mutableStateOf(Color.DarkGray) }
    var showResultOverlay: Boolean by remember { mutableStateOf(false) }
    var answerIsCorrect: Boolean by remember { mutableStateOf(false) }
    var isNewRound: Boolean by remember { mutableStateOf(false) }

    // Play audio once on initial display
    val context = LocalContext.current
    val resourceId = getAudioResourceId(
        context,
        currentLanguage.languageCode,
        currentCorrectGameAsset.assetName
    )
    LaunchedEffect(isNewRound) { // Use Unit as the stable key
        playAudio(context, resourceId)
    }


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth(1f)
            .padding(start = 24.dp, end = 24.dp)
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .fillMaxWidth()
        ) {
//            LanguageLevelRow(
//                currentLanguage = currentLanguage,
//                currentLevel = -1
//            )
//        }
//        Spacer(modifier = Modifier.height(16.dp))
            Row {
                Image(
                    painter = painterResource(R.drawable.devopsbug_bug_158x100),
                    contentDescription = "Ladybug icon",
                    modifier = Modifier.fillMaxWidth(fraction = 0.2f)
                )
                Spacer(modifier = Modifier.weight(1f))
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                ImageAudioTile(
                    tileSize = TileSize.MEDIUM,
                    language = currentLanguage,
                    audioFilePostfix = currentCorrectGameAsset.assetName,
                    imageRessource = R.drawable.volume_up_24px,
                    onCompletion = {},
                    caption = "",
                )

            }
            Spacer(modifier = Modifier.height(16.dp))

            Box (
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
            ){
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    userScrollEnabled = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = MaterialTheme.colorScheme.background)
                        .border(width = 1.dp, color = Color.DarkGray),
                    content = {
                        items(randomizedGameAssetDisplayList) { gameAsset ->
                            if (gameAsset == currentCorrectGameAsset) {
                                GridImageButtonTile(
                                    imageResource = gameAsset.imageResource,
                                    borderColor = correctBorderColor,
                                    onClick = {
                                        wrongBorderColor = Color.Red
                                        correctBorderColor = Color.Green
                                        //newRandomPicture()
                                        showResultOverlay = true
                                        answerIsCorrect = true

                                    }
                                )
                            } else {
                                GridImageButtonTile(
                                    imageResource = gameAsset.imageResource,
                                    borderColor = wrongBorderColor,
                                    onClick = {
                                        wrongBorderColor = Color.Red
                                        correctBorderColor = Color.Green
                                        //newRandomPicture()
                                        showResultOverlay = true
                                        answerIsCorrect = false


                                    }
                                )
                            }
                        }
                    },
                )
                if (showResultOverlay) {
                    val tintColor = if (answerIsCorrect) greenButtonColor else Color.Red
                    val iconResource = if (answerIsCorrect) R.drawable.correct else R.drawable.wrong
                    val  contentDescription = if (answerIsCorrect) "Correct" else "Wrong"
                    val borderColor = if (answerIsCorrect) greenButtonColor else Color.Red
                    Image(
                        painter = painterResource(id = iconResource),
                        contentDescription = contentDescription,
                        colorFilter = ColorFilter.tint(tintColor), // Change color
                        modifier = Modifier
                            .size(TileSize.MEDIUM.dpSize)
                            .border(width = 10.dp, color = borderColor)
                            .background(Color.White),
                    )
                }
                LaunchedEffect(showResultOverlay) {
                    if (showResultOverlay) {
                        delay(2000) // Wait 2 seconds
                        showResultOverlay = false
                        newRandomPicture()
                        correctBorderColor = Color.DarkGray
                        wrongBorderColor = Color.DarkGray
                        isNewRound = !isNewRound
                    }
                }

            }
        }
    }
}