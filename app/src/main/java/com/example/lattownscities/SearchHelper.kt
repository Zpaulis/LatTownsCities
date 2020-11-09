package com.example.lattownscities

import java.util.*

fun yearSearcher (string: String, own : Int) : Boolean {
    var yearMin = 0
    var yearMax :Int = Calendar.getInstance().get(Calendar.YEAR)
    if (string.toIntOrNull() == null) {
        val sign = string[0]
        if (string.length != 5 && string.length != 9){
            return true
// search older than YYYY
        } else if ("-<".contains(sign)){
            yearMin = 0
            yearMax = minOf(yearMax, string.slice(1..4).toInt())
// searc younger than YYYY
        } else if (">".contains(sign)){
            yearMin =  minOf(yearMax, string.slice(1..4).toInt())
// search from YYYY to YYYY
        } else if (string.length == 9 && string.slice(0..3).toIntOrNull() != null &&  string.slice(5..8).toIntOrNull() != null) {
            yearMin = minOf(yearMax, string.slice(0..3).toInt())
            yearMax = minOf(yearMax, string.slice(5..8).toInt())
        } else return true
// search for Millenium, Century, Decade, exact Year
    } else {
        val yearNum : Int = string.toInt()
        when (yearNum) {
            in 0..9 -> {
                yearMin = yearNum * 1000
                yearMax = minOf(yearMax, yearMin + 999)
            }
            in 10..99 -> {
                yearMin = yearNum * 100
                yearMax = minOf(yearMax, yearMin + 99)
            }
            in 100..999 -> {
                yearMin = yearNum * 10
                yearMax = minOf(yearMax, yearMin + 9)
            }
            in 1000..9999 -> {
                yearMin = minOf(yearNum, yearMax)
                yearMax = minOf(yearMax, yearMin)
            }
        }
    }
    return own in yearMin..yearMax
}