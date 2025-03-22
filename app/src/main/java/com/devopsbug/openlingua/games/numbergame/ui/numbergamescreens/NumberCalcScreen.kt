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
import com.devopsbug.openlingua.util.OpenLinguaUtils.LanguageLevelRow
import com.devopsbug.openlingua.R
import com.devopsbug.openlingua.model.Language
import com.devopsbug.openlingua.util.AudioTile


@Composable
fun NumberCalcScreen(
    currentLanguage: Language,
    //currentNumber: Int,
    currentLevel: Int,
    currentNumberList: List<Int>,
    //newRandomNumber: () -> Unit
    ) {
    val (problem, answer) = generateMathProblem(currentNumberList)

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
                text = "1. Try to say the answer\n" +
                        "2. Click the tile to hear the correct answer",
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
        Column (
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ){
            Text(
                text = problem,
                fontSize = 100.sp
            )
            AudioTile(language = currentLanguage, audioFilePostfix = answer.toString(), onCompletion = {})
        }
    }
}

fun generateMathProblem(numberList: List<Int>): Pair<String, Int> {
    var a: Int
    var b: Int
    var result: Int
    var operator: String

    do {
        a = numberList.random()
        b = numberList.random()
        operator = if ((0..1).random() == 0) "+" else "-"
        result = if (operator == "+") a + b else a - b
    } while (result !in numberList) // Ensure the result is within the number set

    val problem = "$a $operator $b"
    return problem to result
}


//Function to display number tile with audio playback when clicked



