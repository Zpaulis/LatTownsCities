package com.example.lattownscities


fun sorter(sortOrder : Boolean, parameter : String) {
    return when (parameter) {
        "name" -> {
        if (sortOrder) {
            townData.sortByDescending { it.name }
            helperText = "name"
            madapter.notifyDataSetChanged()
        } else {
            townData.sortBy { it.name }
            helperText = "name"
            madapter.notifyDataSetChanged()
        }
    }
        "area" -> {
        if (sortOrder) {
            townData.sortByDescending { it.area }
            helperText = "area"
            madapter.notifyDataSetChanged()
        } else {
            townData.sortBy { it.area }
            helperText = "area"
            madapter.notifyDataSetChanged()
        }
    }
        "population" -> {
        if (sortOrder) {
            townData.sortByDescending { it.population }
            helperText = "population"
            madapter.notifyDataSetChanged()
        } else {
            townData.sortBy { it.population }
            helperText = "population"
            madapter.notifyDataSetChanged()
        }
    }
        "own" -> {
        if (sortOrder) {
            townData.sortByDescending { it.own }
            helperText = "own"
            madapter.notifyDataSetChanged()
        } else {
            townData.sortBy { it.own }
            helperText = "own"
            madapter.notifyDataSetChanged()
        }
    }
        else -> {
            return
        }
    }
}
