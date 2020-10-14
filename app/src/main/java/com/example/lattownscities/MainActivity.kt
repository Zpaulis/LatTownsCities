package com.example.lattownscities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

const val MAINTAG = "MAIN ACTIVITY"
var townData = mutableListOf<TownData>()

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadTowns(object : MyCallback {
            override fun onCallback(value: MutableList<TownData>) {
                Log.d(MAINTAG, townData.size.toString())
            }
        } )
    }

    interface MyCallback {
        fun onCallback(value: MutableList<TownData>)
    }

}