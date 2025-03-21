package com.devopsbug.openlingua.ui.openlinguascreens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.devopsbug.openlingua.model.Language

@Composable
fun OpenLinguaStartScreen(
    onClickLetterGame: () -> Unit,
    onClickNumberGame: () -> Unit,
    updateLanguage: (Language) -> Unit,
    currentLanguage: Language,)
{
    Column {
        Text(text = "OpenLingua Start Screen")
        Button(onClick = onClickLetterGame) {
            Text(text = "Letter Game")
        }
        Button(onClick = onClickNumberGame) {
            Text(text = "Number Game")
        }
    }
}
