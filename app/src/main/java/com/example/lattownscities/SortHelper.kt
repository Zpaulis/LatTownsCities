package com.example.lattownscities


fun sorter(sortOrder : Boolean, parameter : String) {
    return when (parameter) {
        "name" -> {
        if (sortOrder) {
            townData.sortBy { it.name }
            helperText = parameter
            madapter.notifyDataSetChanged()
        } else {
            townData.sortByDescending { it.name }
            helperText = parameter
            madapter.notifyDataSetChanged()
        }
    }
        "area" -> {
        if (sortOrder) {
            townData.sortBy { it.area }
            helperText = parameter
            madapter.notifyDataSetChanged()
        } else {
            townData.sortByDescending { it.area }
            helperText = parameter
            madapter.notifyDataSetChanged()
        }
    }
        "population" -> {
        if (sortOrder) {
            townData.sortBy { it.population }
            helperText = parameter
            madapter.notifyDataSetChanged()
        } else {
            townData.sortByDescending { it.population }
            helperText = parameter
            madapter.notifyDataSetChanged()
        }
    }
        "own" -> {
        if (sortOrder) {
            townData.sortBy { it.own }
            helperText = parameter
            madapter.notifyDataSetChanged()
        } else {
            townData.sortByDescending { it.own }
            helperText = parameter
            madapter.notifyDataSetChanged()
        }
    }
        else -> {
            return
        }
    }
}
