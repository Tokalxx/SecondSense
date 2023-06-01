package com.example.resecondsense_v01

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RVAdapter_Entries(val entryList: ArrayList<data_Entries>) : RecyclerView.Adapter<RVAdapter_Entries.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RVAdapter_Entries.ViewHolder {
        val entryView = LayoutInflater.from(parent.context).inflate(R.layout.template_category,
            parent,false)
        return ViewHolder(entryView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = entryList[position]
        holder.hoursView.text = currentItem.hoursSpent
        holder.titleView.text = currentItem.entry_Title
        holder.dateView.text = currentItem.entryDate.toString()
    }

    override fun getItemCount(): Int {
        return entryList.size
    }



    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val titleView : TextView = itemView.findViewById(R.id.txtCatTitle)
        val hoursView : TextView = itemView.findViewById(R.id.txtCatHours)
        val dateView : TextView = itemView.findViewById(R.id.txtCatDate)
    }

}