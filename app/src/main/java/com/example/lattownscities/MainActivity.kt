package com.example.lattownscities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

const val MAINTAG = "MAIN ACTIVITY"
var townData = mutableListOf<TownData>()
private lateinit var layoutManager : StaggeredGridLayoutManager
private lateinit var adapter : TownAdapter

class MainActivity : AppCompatActivity(), AdapterClickListener {


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
        layoutManager = StaggeredGridLayoutManager(
            resources.getInteger(R.integer.span_count), StaggeredGridLayoutManager.VERTICAL
        )
        mainItems.apply {
            layoutManager = layoutManager
            adapter = TownAdapter(this@MainActivity)
            setHasFixedSize(true)
        }
    }
    override fun showMap(coord: DoubleArray){
        val intent = Intent(this, TownMapActivity::class.java)
            .putExtra(EXTRA, coord)
        startActivityForResult(intent, REQUEST_CODE_DETAILS)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        (super.onActivityResult(requestCode, resultCode, data))
        if (requestCode == REQUEST_CODE_DETAILS && resultCode == RESULT_OK && data != null){
            //DO SOMETHING
        }
    }

    interface MyCallback {
        fun onCallback(value: MutableList<TownData>)
    }

    companion object{
        const val EXTRA = "ID"
        const val REQUEST_CODE_DETAILS = 1111

    }

}
interface AdapterClickListener {
    fun showMap(coord: DoubleArray)
}
