package com.example.lattownscities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.lattownscities.MainActivity.Companion.town
import kotlinx.android.synthetic.main.activity_town_detail.*

class TownDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_town_detail)

        detail_coat_of_arms.loadImg(town!!.coat)
        detail_town_name.text = town!!.name
        detail_own.text = town!!.own.toString()
        detail_population.text = town!!.population.toString()
        detail_area.text = town!!.area.toString()
        detail_region_zip.text = getString(R.string.region, town!!.region, town!!.zip)

    }
}