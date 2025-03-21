package com.devopsbug.openlingua.games.numbergame.model

import com.devopsbug.openlingua.R

data class Number(
    val numberLiteral: String,
    val value: Int,
    val audioFiles: Map<String, Int>
)
