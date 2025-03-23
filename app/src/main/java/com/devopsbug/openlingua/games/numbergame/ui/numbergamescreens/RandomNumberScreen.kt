package  com.devopsbug.openlingua.games.numbergame.ui.numbergamescreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devopsbug.openlingua.R
import com.devopsbug.openlingua.model.Language
import com.devopsbug.openlingua.util.LanguageLevelRow
import com.devopsbug.openlingua.util.TextAudioTile


@Composable
fun RandomNumberScreen(
    currentLanguage: Language,
    currentNumber: Int,
    currentLevel: Int,
    newRandomNumber: () -> Unit
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
                        "this number ?",
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
        Column (
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ){
            TextAudioTile(
                language = currentLanguage,
                audioFilePostfix = currentNumber.toString(),
                tileText = currentNumber.toString(),
                onCompletion = newRandomNumber
            )
        }
    }
}


