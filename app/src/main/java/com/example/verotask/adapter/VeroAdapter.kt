package com.example.verotask.adapter

import android.content.Context
import android.graphics.Color
import com.example.verotask.databinding.RecyclerRowBinding
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.collections.ArrayList

class VeroAdapter(private var veroList: ArrayList<String>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>(),Filterable {
    var veroFilterList = ArrayList<String>()
    lateinit var mContext: Context

    class VeroHolder(var viewBinding: RecyclerRowBinding) :
        RecyclerView.ViewHolder(viewBinding.root)

    init {
        veroFilterList = veroList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding =
            RecyclerRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val sch = VeroHolder(binding)
        mContext = parent.context
        return sch
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val veroHolder = holder as VeroHolder
        veroHolder.viewBinding.rowContainer.setBackgroundColor(Color.TRANSPARENT)
        veroHolder.viewBinding.rowText.setTextColor(Color.BLACK)
        veroHolder.viewBinding.rowText.text = veroFilterList[position]
    }

    override fun getItemCount(): Int {
        return veroFilterList.size
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                // Aranan Yazı
                val charSearch = constraint.toString()

                if (charSearch.isEmpty()) {
                    veroFilterList = veroList
                } else {
                    val resultList = ArrayList<String>()
                    if(charSearch.length > 5) {
                        for (row in veroList) {
                            // aranan add metodu ile listeye eklenir
                            if (row.lowercase().contains(charSearch)) {
                                resultList.add(row)
                            }
                        }
                    }
                    veroFilterList = resultList
                }

                val filterResults = FilterResults()
                filterResults.values = veroFilterList
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                veroFilterList = results?.values as ArrayList<String>
                notifyDataSetChanged()
            }
        }
    }
}