package com.example.lattownscities

data class TownData (
    val area :Long = 0L,
    val coat :String = "",
    val location :List<Double> = listOf(),
    val name :String = "",
    val own :Int = 0,
    val population :Int = 0,
    val region :String = "",
    val url :String = "",
    val wiki :String = "",
    val zip :String = ""
)