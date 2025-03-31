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
import com.devopsbug.openlingua.core.ui.LanguageLevelRow
import com.devopsbug.openlingua.core.ui.TextAudioTile
import com.devopsbug.openlingua.core.util.OpenLinguaAudioUtils.getAudioResourceId
import com.devopsbug.openlingua.core.util.OpenLinguaAudioUtils.playAudio
import com.devopsbug.openlingua.games.lettergame.model.Letter
import com.devopsbug.openlingua.model.Language


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
        }
    }
}
