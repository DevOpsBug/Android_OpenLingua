package com.devopsbug.openlingua.games.numbergame.data

import com.devopsbug.openlingua.games.numbergame.model.NumberGameLevel


object Numbers {
    val numbersByLevel: List<NumberGameLevel> = listOf(
        NumberGameLevel(
            level = 1,
            numberList = (0..10).toList(),
            numberSetDescription = "[0-10]",
            maxNumberForCalculation = 10
        ),
        NumberGameLevel(
            level = 2,
            numberList = (0..20).toList(),
            numberSetDescription = "[0-20]",
            maxNumberForCalculation = 20
        ),
        NumberGameLevel(
            level = 3,
            numberList = (0..30).toList() + listOf(40, 50, 60, 70, 80, 90, 100),
            numberSetDescription = "[0-30]+[40,50..90,100]",
            maxNumberForCalculation = 30
        ),
        NumberGameLevel(
            level = 4,
            numberList = (0..100).toList() + listOf(200, 300, 400, 500, 600, 700, 800, 900, 1000),
            numberSetDescription = "[0-100, 200, 300 ... 1000]",
            maxNumberForCalculation = 100
        )
    )
}