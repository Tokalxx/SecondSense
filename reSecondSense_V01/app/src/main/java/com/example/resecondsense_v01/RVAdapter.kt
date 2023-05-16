package com.example.resecondsense_v01

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RVAdapter(val entryList: ArrayList<RecentEntry>) : RecyclerView.Adapter<RVAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val recentView = LayoutInflater.from(parent.context).inflate(R.layout.custom_list_view_template,
            parent,false)
        return ViewHolder(recentView)
    }

    override fun getItemCount(): Int {
        return entryList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       val currentItem = entryList[position]
        holder.imageView.setImageResource(currentItem.imageResId)
        holder.textView.text = currentItem.title
        holder.DateView.text = currentItem.date
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val imageView : ImageView = itemView.findViewById(R.id.imgViewRecent)
        val textView : TextView = itemView.findViewById(R.id.txtEntryTitle)
        val DateView : TextView = itemView.findViewById(R.id.txtEntryDate)
    }

}