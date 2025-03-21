package com.devopsbug.openlingua.games.numbergame.data

import com.devopsbug.openlingua.R
import com.devopsbug.openlingua.games.numbergame.model.Number


object Numbers {
    val numbersByLevel: List<List<Int>> = listOf(
        (0..9).toList(),
        (0..19).toList(),
        (0..29).toList(),
        (0..39).toList(),
    )

//    val allNumbers: List<Number> = listOf(
//        Number(
//            numberLiteral = "0",
//            value = 0,
//            audioFiles = mapOf(
//                "de" to R.raw.de_a,
//                "en" to R.raw.en_a,
//                "it" to R.raw.it_a
//            )
//        ),
//        Number(
//            numberLiteral = "1",
//            value = 1,
//            audioFiles = mapOf(
//                "de" to R.raw.de_a,
//                "en" to R.raw.en_a,
//                "it" to R.raw.it_a
//            )
//        ),
//        Number(
//            numberLiteral = "2",
//            value = 2,
//            audioFiles = mapOf(
//                "de" to R.raw.de_a,
//                "en" to R.raw.en_a,
//                "it" to R.raw.it_a
//            )
//        ),
//        Number(
//            numberLiteral = "3",
//            value = 3,
//            audioFiles = mapOf(
//                "de" to R.raw.de_a,
//                "en" to R.raw.en_a,
//                "it" to R.raw.it_a
//            )
//        ),
//    )


}