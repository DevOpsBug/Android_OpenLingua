package com.devopsbug.openlingua.games.numbergame.model

data class NumberGameLevel(
    val level: Int,
    val numberList: List<Int>,
    val numberSetDescription: String,
    val maxNumberForCalculation: Int,
)
