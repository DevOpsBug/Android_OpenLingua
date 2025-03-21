package  com.devopsbug.openlingua.games.numbergame.ui.numbergamescreens

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
import androidx.compose.foundation.layout.width
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
import com.devopsbug.openlingua.util.OpenLinguaUtils.LanguageLevelRow
import com.devopsbug.openlingua.util.OpenLinguaUtils.getRawResourceId
import com.devopsbug.openlingua.util.OpenLinguaUtils.playAudio
import com.devopsbug.openlingua.R
import com.devopsbug.openlingua.model.Language


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
                text = "How to play:",
                fontSize = 30.sp,
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "1. Try to say the sound of the Number\n" +
                        "2. Click the number to hear the correct sound",
                fontSize = 16.sp
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
        Spacer(modifier = Modifier.height(32.dp))
        Row (
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ){
            RandomNumberTile(
                number = currentNumber,
                language = currentLanguage,
                newRandomNumber = newRandomNumber,
                modifier = Modifier
                    .height(248.dp)
                    .width(248.dp)

            )
        }
    }
}

//Function to display number tile with audio playback when clicked
@Composable
private fun RandomNumberTile(number: Int, language: Language, newRandomNumber: () -> Unit, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    Button(
        onClick = {
            val resourceId = getRawResourceId(context, language.audioFilePrefix, number.toString())
            playAudio(context, resourceId, onCompletion = { newRandomNumber() })
        },
        modifier = modifier,
        shape = RoundedCornerShape(percent = 20),
        border = BorderStroke(5.dp, Color.DarkGray),
        contentPadding = PaddingValues(12.dp),
    ) {
        Text(
            text = number.toString(),
            fontSize = 100.sp
        )
    }
}


