package com.devopsbug.openlingua.data

import com.devopsbug.openlingua.R
import com.devopsbug.openlingua.games.lettergame.LetterGame
import com.devopsbug.openlingua.games.numbergame.NumberGame
import com.devopsbug.openlingua.games.picturematchinggame.PictureMatchingGame
import com.devopsbug.openlingua.model.Game
import com.devopsbug.openlingua.ui.openlinguascreens.OpenLingua

object Games {
    val openLinguaGame = Game(
        gameName = "Open Lingua",
        gameButtonImage = R.drawable.ic_launcher_foreground,
        gameEntry = { }
    )
    val gameList: List<Game> = listOf(
        Game(
            gameName = "Letter Game",
            gameButtonImage = R.drawable.letter_game,
            gameEntry = { LetterGame(currentLanguage = it) }
        ),
        Game(
            gameName = "Number Game",
            gameButtonImage = R.drawable.number_game,
            gameEntry = { NumberGame(currentLanguage = it) }
        ),
        Game(
            gameName = "Vegetables Game",
            gameButtonImage = R.drawable.category_vegetables,
            gameEntry = { PictureMatchingGame(currentLanguage = it) }
        )
    )
}