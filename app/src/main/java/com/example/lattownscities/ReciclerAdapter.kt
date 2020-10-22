package com.example.lattownscities

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lattownscities.MainActivity.Companion.town
import kotlinx.android.synthetic.main.card_town_short.view.*


class TownAdapter(private val listener: AdapterClickListener): RecyclerView.Adapter<TownAdapter.TownViewHolder>() {

override fun getItemCount(): Int = townData.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TownViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_town_short, parent, false)
        return TownViewHolder(view)
    }

    override fun onBindViewHolder(holder: TownViewHolder, position: Int) {
        holder.bind(townData[position])

        holder.itemView.open_map_button.setOnClickListener{
            val coord = townData[position].location.toDoubleArray()
            listener.showMap(coord)
        }
        holder.itemView.coat_of_arms.setOnClickListener {
            town = townData[position]
            listener.showDetails()
        }
    }

    inner class TownViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(town: TownData) {
            itemView.town_name.text = town.name
            itemView.coat_of_arms.loadImg(town.coat)
        }
    }
}