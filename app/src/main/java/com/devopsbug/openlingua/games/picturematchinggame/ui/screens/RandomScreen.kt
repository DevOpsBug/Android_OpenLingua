package  com.devopsbug.openlingua.games.picturematchinggame.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devopsbug.openlingua.R
import com.devopsbug.openlingua.core.ui.GridButtonTile
import com.devopsbug.openlingua.core.ui.ImageAudioTile
import com.devopsbug.openlingua.games.lettergame.model.Letter
import com.devopsbug.openlingua.model.Language
import com.devopsbug.openlingua.core.ui.LanguageLevelRow
import com.devopsbug.openlingua.util.OpenLinguaAudioUtils.getAudioResourceId
import com.devopsbug.openlingua.util.OpenLinguaAudioUtils.playAudio
import com.devopsbug.openlingua.core.ui.TextAudioTile
import com.devopsbug.openlingua.util.ImageAsset


@Composable
fun RandomPictureScreen(
    currentLanguage: Language,
    currentLevel: Int = 1,
    wrongPictures: List<ImageAsset>,
    correctPicture: ImageAsset,
    ) {
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth(1f)
            .padding(start = 24.dp, end = 24.dp)
    ){
        Spacer(modifier = Modifier.height(16.dp))
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            LanguageLevelRow(
                currentLanguage = currentLanguage,
                currentLevel = currentLevel
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Do you know the answer ?",
                fontSize = 26.sp,
            )
            Spacer(modifier = Modifier.height(16.dp))

        }
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            Image(
                painter = painterResource(R.drawable.devopsbug_bug_158x100),
                contentDescription = "Ladybug icon",
                modifier = Modifier.fillMaxWidth(fraction = 0.2f)
            )
            Spacer(modifier = Modifier.weight(1f))
        }
        Spacer(modifier = Modifier.height(16.dp))
        Column (
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ){
            Spacer(modifier = Modifier.height(16.dp))
            ImageAudioTile(
                language = currentLanguage,
                audioFilePostfix = correctPicture.asset_name,
                imageRessource = R.drawable.volume_up_24px,
                onCompletion = {},
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items( (wrongPictures+correctPicture).shuffled() ) { picture ->
                GridButtonTile(
                    imageAsset = picture,
                    onClick = {}
                )
            }
        }

    }
}

//Function to display letter tile with audio playback when clicked
@Composable
private fun RandomLetterTile(letter: Letter, language: Language, newRandomLetter: () -> Unit, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    Button(
        onClick = {
            val resourceId = getAudioResourceId(context, language.audioFilePrefix, letter.letterLiteral.lowercase())
            playAudio(context, resourceId, onCompletion = { newRandomLetter() })
        },
        modifier = modifier,
        shape = RoundedCornerShape(percent = 20),
        border = BorderStroke(5.dp, Color.DarkGray),
        contentPadding = PaddingValues(12.dp),
    ) {
        Text(
            text = letter.letterLiteral,
            fontSize = 100.sp
        )
    }
}


