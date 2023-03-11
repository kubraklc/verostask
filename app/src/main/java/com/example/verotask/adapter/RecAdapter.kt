package com.example.verotask.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.verotask.R
import com.example.verotask.retrofit.models.TaskResponse

class RecAdapter(private val veriler : ArrayList<TaskResponse>) : RecyclerView.Adapter<RecHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecHolder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.item_vero, parent, false)
        return RecHolder(view)
    }

    override fun onBindViewHolder(holder: RecHolder, position: Int) {
        val veri = veriler[position]

        holder.title.text = veri.title
        holder.descriptor.text = veri.description
        holder.sort.text = veri.sort
        holder.wageType.text = veri.wageType
    }

    override fun getItemCount(): Int {
        return veriler.size
    }

}

class RecHolder(itemView: View) :RecyclerView.ViewHolder(itemView) {
    val title: TextView = itemView.findViewById(R.id.select_item_title)
    val descriptor: TextView = itemView.findViewById(R.id.select_item_desc)
    val sort : TextView = itemView.findViewById(R.id.select_item_sort)
    val wageType : TextView = itemView.findViewById(R.id.select_item_type)
}
