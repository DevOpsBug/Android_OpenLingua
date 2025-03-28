package com.devopsbug.openlingua.ui.openlinguascreens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devopsbug.openlingua.R
import com.devopsbug.openlingua.core.ui.GridButtonTile
import com.devopsbug.openlingua.model.Language
import com.devopsbug.openlingua.core.ui.LanguageSelectionRow
import com.devopsbug.openlingua.games.picturematchinggame.PictureMatchingGame
import com.devopsbug.openlingua.util.OpenLinguaImageAssetUtils

@Composable
fun OpenLinguaStartScreen(
    //onClickLetterGame: () -> Unit,
    //onClickNumberGame: () -> Unit,
    onClickGame: Map<String, () -> Unit>,
    updateLanguage: (Language) -> Unit,
    currentLanguage: Language
) {
    Column {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth(1f)
                .padding(start = 24.dp, end = 24.dp)
            //.border(width = 1.dp, color = Color.DarkGray)
        ) {
            val vegetableAssets = OpenLinguaImageAssetUtils.loadCategoryAssetsFromCsvFile(
                context = LocalContext.current,
                category = "vegetables"
            )
            Log.d("ASSET", vegetableAssets[5].toString())
            val carrotAsset = vegetableAssets.find { it.asset_name == "carrot" }
            if (carrotAsset != null) {
                Log.d("ASSET", "carrotAsset: ${carrotAsset.attribution_text}")
                Text(text = "${carrotAsset.attribution_text}")
            }

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
                            "2. Choose a Game Category",
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
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                items(onClickGame.keys.toList()) { item ->
                    GridButtonTile(
                        text = item,
                        onClick = onClickGame.getValue(key = item)
                    )
                }

            }

        }
    }
}
