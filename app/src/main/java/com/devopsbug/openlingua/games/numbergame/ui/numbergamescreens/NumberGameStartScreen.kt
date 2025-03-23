package  com.devopsbug.openlingua.games.numbergame.ui.numbergamescreens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devopsbug.openlingua.R
import com.devopsbug.openlingua.games.numbergame.data.Numbers
import com.devopsbug.openlingua.model.Language
import com.devopsbug.openlingua.ui.theme.greenButtonColor
import com.devopsbug.openlingua.ui.theme.primaryLightMediumContrast
import com.devopsbug.openlingua.util.LanguageSelectionRow


@Composable
fun NumberGameStartScreen(
    currentLanguage: Language,
    updateLanguage: (Language) -> Unit,
    currentLevel: Int,
    updateLevel: (Int) -> Unit,
    currentSublevel: String = "A",
    updateSublevel: (String) -> Unit,
    onClickExplore: () -> Unit,
) {
    Column (){
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth(1f)
                .padding(start = 24.dp, end = 24.dp)
                //.border(width = 1.dp, color = Color.DarkGray)
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            Column(
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    //.padding(24.dp)
            ) {
                Text(
                    text = "How to start:",
                    fontSize = 30.sp,
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "1. Choose a language\n" +
                           "2. Choose a level\n" +
                           "3. Click START to begin",
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
            Spacer(modifier = Modifier.height(16.dp))
            LanguageSelectionRow(
                currentLanguage = currentLanguage,
                updateLanguage = updateLanguage,
                modifier = Modifier
                    .fillMaxWidth()
                    //.padding(start = 24.dp, end = 24.dp)
                    .border(3.dp, MaterialTheme.colorScheme.primaryContainer)

            )
            Spacer(modifier = Modifier.height(16.dp))
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
            ){
                val levelList = (1..Numbers.numbersByLevel.size).toList()
                levelList.forEach { level ->
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth(1f).padding(8.dp)
                    ) {
                        listOf("A", "B").forEach{ sublevel ->
                            Button(
                                onClick = {
                                    updateLevel(level)
                                    updateSublevel(sublevel)
                                          },
                                contentPadding = PaddingValues(top = 16.dp, bottom = 16.dp, start = 32.dp, end = 32.dp),
                                border = BorderStroke(width = 2.dp, color = Color.DarkGray),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = if (currentLevel == level && currentSublevel == sublevel) {
                                        primaryLightMediumContrast
                                    } else {
                                        ButtonDefaults.buttonColors().containerColor
                                    }
                                )
                            ) {
                                Text(
                                    text = "Level $level $sublevel",
                                    fontSize = 24.sp
                                )
                            }
                        }

                        //Spacer(modifier = Modifier.width(8.dp))
//                        Button(
//                            onClick = { updateLevel(it) },
//                            contentPadding = PaddingValues(top = 16.dp, bottom = 16.dp, start = 32.dp, end = 32.dp),
//                            border = BorderStroke(width = 2.dp, color = Color.DarkGray),
//                            colors = ButtonDefaults.buttonColors(
//                                containerColor = if (currentLevel == it) {
//                                    primaryLightMediumContrast
//                                } else {
//                                    ButtonDefaults.buttonColors().containerColor
//                                }
//                            )
//                        ) {
//                            Text(
//                                text = "Level $it B",
//                                fontSize = 24.sp
//                            )
//                        }
                    }
                }
                Row(
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth(0.8f).padding(8.dp)
                ) {
                    Spacer(modifier = Modifier.width(16.dp))
                    Button(
                        onClick = onClickExplore,
                        colors = ButtonDefaults.buttonColors(
                            containerColor =  greenButtonColor
                        ),
                        border = BorderStroke(width = 2.dp, color = Color.DarkGray),
                        contentPadding = PaddingValues(16.dp),
                        modifier = Modifier.fillMaxWidth()
                        ){
                            Text(
                                text = "START",
                                fontSize = 24.sp
                            )
                        }
                    }
            }
        }
    }
}