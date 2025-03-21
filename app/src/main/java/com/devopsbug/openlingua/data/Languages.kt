package com.devopsbug.openlingua.data

import com.devopsbug.openlingua.R
import com.devopsbug.openlingua.model.Language

object Languages {
    val german = Language(
        name = "german",
        audioFilePrefix = "de",
        flagImage = R.drawable.german,
    )
    val english = Language(
        name = "english",
        audioFilePrefix = "en",
        flagImage = R.drawable.english,
    )
    val italian = Language(
        name = "italian",
        audioFilePrefix = "it",
        flagImage = R.drawable.italian,
    )
}