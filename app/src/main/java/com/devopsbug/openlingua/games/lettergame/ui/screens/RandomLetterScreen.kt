package  com.devopsbug.openlingua.games.lettergame.ui.screens

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
import com.devopsbug.openlingua.games.lettergame.model.Letter
import com.devopsbug.openlingua.model.Language
import com.devopsbug.openlingua.core.util.LanguageLevelRow
import com.devopsbug.openlingua.core.util.OpenLinguaAudioUtils.getAudioResourceId
import com.devopsbug.openlingua.core.util.OpenLinguaAudioUtils.playAudio
import com.devopsbug.openlingua.core.util.TextAudioTile


@Composable
fun RandomLetterScreen(
    currentLanguage: Language,
    currentLetter: Letter,
    currentLevel: Int,
    newRandomLetter: () -> Unit
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
                text = "Can you say " +
                        "this letter ?",
                fontSize = 30.sp,
                lineHeight = 42.sp
            )
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
        Row (
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ){
            TextAudioTile(
                language = currentLanguage,
                audioFilePostfix = currentLetter.letterLiteral.lowercase(),
                tileText = currentLetter.letterLiteral,
                onCompletion = newRandomLetter
            )
//            RandomLetterTile(
//                letter = currentLetter,
//                language = currentLanguage,
//                newRandomLetter = newRandomLetter,
//                modifier = Modifier
//                    .height(248.dp)
//                    .width(248.dp)
//
//            )
        }
    }
}

//Function to display letter tile with audio playback when clicked
@Composable
private fun RandomLetterTile(letter: Letter, language: Language, newRandomLetter: () -> Unit, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    Button(
        onClick = {
            val resourceId = getAudioResourceId(context, language.languageCode, letter.letterLiteral.lowercase())
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


