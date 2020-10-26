package com.example.lattownscities


fun sorter(sortOrder : Boolean, parameter : String) {
    return when (parameter) {
        "name" -> {
        if (sortOrder) {
            townData.sortByDescending { it.name }
            madapter.notifyDataSetChanged()
        } else {
            townData.sortBy { it.name }
            madapter.notifyDataSetChanged()
        }
    }
        "area" -> {
        if (sortOrder) {
            townData.sortByDescending { it.area }
            madapter.notifyDataSetChanged()
        } else {
            townData.sortBy { it.area }
            madapter.notifyDataSetChanged()
        }
    }
        "population" -> {
        if (sortOrder) {
            townData.sortByDescending { it.population }
            madapter.notifyDataSetChanged()
        } else {
            townData.sortBy { it.population }
            madapter.notifyDataSetChanged()
        }
    }
        "own" -> {
        if (sortOrder) {
            townData.sortByDescending { it.own }
            madapter.notifyDataSetChanged()
        } else {
            townData.sortBy { it.own }
            madapter.notifyDataSetChanged()
        }
    }
        else -> {
            return
        }
    }
}
