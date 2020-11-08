package com.example.lattownscities

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.content.getSystemService
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

//const val MAINTAG = "MAIN ACTIVITY"
var townData = mutableListOf<TownData>()
private lateinit var layoutManager : StaggeredGridLayoutManager
lateinit var madapter : TownAdapter
var sortOrder = true
var sortParameter = "name"
var queryTarget = "name"
var helperText = ""

class MainActivity : AppCompatActivity(), AdapterClickListener {

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu_settings, menu)
        val manager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchItem = menu?.findItem(R.id.query)
        val searchView = searchItem?.actionView as SearchView

        searchView.setSearchableInfo(manager.getSearchableInfo(componentName))

        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView.clearFocus()
//                searchView.setQuery("", false)
                searchItem.collapseActionView()
                Toast.makeText(this@MainActivity, "Meklē $query", Toast.LENGTH_SHORT).show()
                return false
            }
            override fun onQueryTextChange(newText:String?): Boolean {
                madapter.filter.filter(newText)
                return false
            }
        })
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        setSupportActionBar(toolbar)
        loadTowns(object : MyCallback {
            override fun onCallback(value: MutableList<TownData>) {
//                Log.d(MAINTAG, townData.size.toString())
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
            madapter = TownAdapter(this@MainActivity)
            adapter = madapter
//            adapter.filter.filter(newText)
            setHasFixedSize(true)
        }
    }
// Switch to Show Town in Map Activity
    override fun showMap(coord: DoubleArray){
        val intent = Intent(this, TownMapActivity::class.java)
            .putExtra(EXTRA, coord)
        startActivityForResult(intent, REQUEST_CODE_DETAILS)
    }
// Switch to Detail View Activity
    override fun showDetails() {
        startActivity(Intent(this, TownDetailActivity::class.java))
    }
    interface MyCallback {
        fun onCallback(value: MutableList<TownData>)
    }
// Return from Detail View Activity
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        (super.onActivityResult(requestCode, resultCode, data))
        if (requestCode == REQUEST_CODE_DETAILS && resultCode == RESULT_OK && data != null){
            TODO("Not yet implemented")
        }
    }
// Action menu
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.getItemId()) {
            // Order menu
            R.id.app_bar_switch -> {
                item.isChecked = !item.isChecked
                sortOrder = item.isChecked
                item.setChecked(sortOrder)
                sorter (sortOrder, sortParameter)
                if (sortOrder) item.setIcon(R.drawable.ic_baseline_arrow_downward_24)
                else item.setIcon(R.drawable.ic_baseline_arrow_upward_24)
                true }
            R.id.order_name -> {
                sortParameter = "name"
                sorter (sortOrder, sortParameter)
                true }
            R.id.order_area -> {
                sortParameter = "area"
                sorter (sortOrder, sortParameter)
                true }
            R.id.order_population -> {
                sortParameter = "population"
                sorter (sortOrder, sortParameter)
                true }
            R.id.order_own -> {
                sortParameter = "own"
                sorter (sortOrder, sortParameter)
                true }
            // Query menu
            R.id.query_name -> {
                queryTarget = "name"
                true }
            R.id.query_own -> {
                queryTarget = "own"
                true }
            R.id.query_area -> {
                queryTarget = "area"
                true }
            R.id.query_population -> {
                queryTarget = "population"
                true }
            R.id.query_coa -> {
                queryTarget = "coa"
                true }
            else -> super.onOptionsItemSelected(item)
        }
    }

    companion object{
        const val EXTRA = "ID"
        const val REQUEST_CODE_DETAILS = 1111
        var town : TownData? = null
    }
}
interface AdapterClickListener {
    fun showMap(coord: DoubleArray)
    fun showDetails()
}
