package com.devopsbug.openlingua

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.devopsbug.openlingua.ui.openlinguascreens.OpenLingua
import com.devopsbug.openlingua.ui.theme.OpenLinguaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            OpenLinguaTheme {
                OpenLingua()
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun OpenLinguaPreview() {
    OpenLinguaTheme {
        OpenLingua()
    }
}