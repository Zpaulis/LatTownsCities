package com.example.lattownscities

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.lattownscities.MainActivity.Companion.town
import kotlinx.android.synthetic.main.card_town_short.view.*
import java.util.*


class TownAdapter(private val listener: AdapterClickListener): RecyclerView.Adapter<TownAdapter.TownViewHolder>(), Filterable {
    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    townFilterList = townData
                } else {
                    var resultList = mutableListOf<TownData>()
                    when (queryTarget) {
                    "name" -> {for (row in townData){
                        if ((row.name.toLowerCase(Locale.ROOT)).contains(charSearch.toLowerCase(Locale.ROOT)))
                        resultList.add(row)
                    }
                        }
                    "coa" -> {for (row in townData){
                        if ((row.coa.toLowerCase(Locale.ROOT)).contains(charSearch.toLowerCase(Locale.ROOT)))
                        resultList.add(row)
                    }
                        }
                    "own" -> {
                        resultList = townData.filter { yearSearcher(charSearch, it.own) }.toMutableList()
                    }
                    "area" -> {
                        resultList = townData.filter { numSearcher(charSearch, it.area.toInt()) }.toMutableList()
                        }
                    "population" -> {
                        resultList = townData.filter { numSearcher(charSearch, it.population) }.toMutableList()
                        }
                            }
                    townFilterList = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = townFilterList
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                townFilterList = results?.values as MutableList<TownData>
                notifyDataSetChanged()
            }
        }
    }

    var townFilterList = mutableListOf<TownData>()
    init {
        townFilterList = townData
    }
override fun getItemCount(): Int = townFilterList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TownViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_town_short, parent, false)
        return TownViewHolder(view)
    }

    override fun onBindViewHolder(holder: TownViewHolder, position: Int) {
        holder.bind(townFilterList[position])
        // Listener to switch to MAP view
        holder.itemView.open_map_button.setOnClickListener{
            val coord = townFilterList[position].location.toDoubleArray()
            listener.showMap(coord)
        }
        // Listener to switch to DETAIL view
        holder.itemView.coat_of_arms.setOnClickListener {
            town = townFilterList[position]
            listener.showDetails()
        }
    }

    inner class TownViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(town: TownData) {
            val context = itemView.context
            itemView.town_name.text = town.name
            itemView.coat_of_arms.loadImg(town.coat)
            return when (helperText)
            {"name" -> {
                    itemView.sorting_textView.text = ""
                }
            "population" -> {
                itemView.sorting_textView.text = context.getString(R.string.population,town.population.toString())
            }
            "area" -> {
                itemView.sorting_textView.text = context.getString(R.string.sqrkm,town.area.toString())
            }
            "own" -> {
                itemView.sorting_textView.text = context.getString(R.string.own,town.own.toString())
            }
            else -> return}
        }
    }
}