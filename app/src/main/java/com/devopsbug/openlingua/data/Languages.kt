package com.devopsbug.openlingua.data

import com.devopsbug.openlingua.R
import com.devopsbug.openlingua.model.Language

object Languages {
    val german = Language(
        name = "german",
        languageCode = "de",
        flagImage = R.drawable.german,
    )
    val english = Language(
        name = "english",
        languageCode = "en",
        flagImage = R.drawable.english,
    )
    val italian = Language(
        name = "italian",
        languageCode = "it",
        flagImage = R.drawable.italian,
    )
}