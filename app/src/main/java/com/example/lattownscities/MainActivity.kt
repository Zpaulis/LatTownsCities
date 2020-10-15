package com.example.lattownscities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

const val MAINTAG = "MAIN ACTIVITY"
var townData = mutableListOf<TownData>()

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        setSupportActionBar(toolbar)
        loadTowns(object : MyCallback {
            override fun onCallback(value: MutableList<TownData>) {
                Log.d(MAINTAG, townData.size.toString())
                setupRecyclerView()
            }
        })
    }
    private fun setupRecyclerView() {
        mainItems.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = TownAdapter()
            setHasFixedSize(true)
        }
    }


    interface MyCallback {
        fun onCallback(value: MutableList<TownData>)
    }
}