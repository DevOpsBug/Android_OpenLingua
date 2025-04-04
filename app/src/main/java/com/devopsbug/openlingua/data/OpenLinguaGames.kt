package com.devopsbug.openlingua.data

import com.devopsbug.openlingua.R
import com.devopsbug.openlingua.games.lettergame.LetterGame
import com.devopsbug.openlingua.games.numbergame.NumberGame
import com.devopsbug.openlingua.games.picturematchinggame.PictureMatchingGame
import com.devopsbug.openlingua.games.picturematchinggame.data.PictureMatchingGameCategories
import com.devopsbug.openlingua.model.OpenLinguaGame

object OpenLinguaGames {
    val openLinguaGame = OpenLinguaGame(
        gameName = "OpenLingua",
        gameButtonImage = R.drawable.ic_launcher_foreground,
        gameEntry = { }
    )

    val pictureMatchingGames = PictureMatchingGameCategories.categoriesList.map { category ->
        OpenLinguaGame(
            gameName = category.categoryName, // Use category name
            gameButtonImage = category.categoryCoverImage, // Use category image
            gameEntry = { PictureMatchingGame(currentLanguage = it, currentPictureGameCategory = category).GameNavigation() }
        )
    }

    val openLinguaGameLists: List<OpenLinguaGame> = listOf(
        OpenLinguaGame(
            gameName = "Lettergame",
            gameButtonImage = R.drawable.letter_game,
            gameEntry = { LetterGame(currentLanguage = it).GameNavigation() }
        ),
        OpenLinguaGame(
            gameName = "Numbergame",
            gameButtonImage = R.drawable.number_game,
            gameEntry = { NumberGame(currentLanguage = it).GameNavigation() }
        ),
        *pictureMatchingGames.toTypedArray() // Add picture matching games
    )
}