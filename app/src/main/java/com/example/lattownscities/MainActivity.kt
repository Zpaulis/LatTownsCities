package com.example.lattownscities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

const val MAINTAG = "MAIN ACTIVITY"
var townData = mutableListOf<TownData>()
private lateinit var layoutManager : StaggeredGridLayoutManager
private lateinit var adapter : TownAdapter

class MainActivity : AppCompatActivity(), AdapterClickListener {

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu_settings, menu)
        return true
    }

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
            TODO("Not yet implemented")
        }
    }

    override fun showDetails() {
        startActivity(Intent(this, TownDetailActivity::class.java))
    }
    interface MyCallback {
        fun onCallback(value: MutableList<TownData>)
    }

    companion object{
        const val EXTRA = "ID"
        const val REQUEST_CODE_DETAILS = 1111
        var town : TownData? = null
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.getItemId()){
            R.id.order_name -> {
                townData.sortByDescending { it.name }
                true }
            R.id.order_area -> {
                townData.sortByDescending { it.area }
//                adapter.notifyDataSetChanged()
                true }
            R.id.order_population -> {
                townData.sortByDescending { it.population }
                true }
            R.id.order_own -> {
                townData.sortBy { it.own }
                true }
            else -> super.onOptionsItemSelected(item)
        }
    }

}
interface AdapterClickListener {
    fun showMap(coord: DoubleArray)
    fun showDetails()
}
