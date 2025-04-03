package com.devopsbug.openlingua.data

import com.devopsbug.openlingua.R
import com.devopsbug.openlingua.games.lettergame.LetterGame
import com.devopsbug.openlingua.games.numbergame.NumberGame
import com.devopsbug.openlingua.games.picturematchinggame.PictureMatchingGame
import com.devopsbug.openlingua.games.picturematchinggame.data.PictureMatchingGameCategories
import com.devopsbug.openlingua.model.OpenLinguaGame

object OpenLinguaGames {
    val openLinguaGame = OpenLinguaGame(
        gameName = "Open Lingua",
        gameButtonImage = R.drawable.ic_launcher_foreground,
        gameEntry = { }
    )
    val openLinguaGameLists: List<OpenLinguaGame> = listOf(
        OpenLinguaGame(
            gameName = "Letter OpenLinguaGame",
            gameButtonImage = R.drawable.letter_game,
            gameEntry = { LetterGame(currentLanguage = it) }
        ),
        OpenLinguaGame(
            gameName = "Number OpenLinguaGame",
            gameButtonImage = R.drawable.number_game,
            gameEntry = { NumberGame(currentLanguage = it) }
        ),
        OpenLinguaGame(
            gameName = "Vegetables OpenLinguaGame",
            gameButtonImage = R.drawable.category_vegetables,
            gameEntry = { PictureMatchingGame(currentLanguage = it, currentPictureGameCategory = PictureMatchingGameCategories.categoriesList[0]) }
        ),
        OpenLinguaGame(
            gameName = "Fruits OpenLinguaGame",
            gameButtonImage = R.drawable.category_fruits,
            gameEntry = { PictureMatchingGame(currentLanguage = it, currentPictureGameCategory = PictureMatchingGameCategories.categoriesList[1]) }

        )
    )
}