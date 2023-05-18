package com.example.resecondsense_v01

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
//This is the recycler view adapter that connects the template_recententry to the date
// just needs an Array list with the data of the Recent entry, but can be change to some other list
// This method returns a the Recyler view
class RVAdapter_RecentEnty(val entryList: ArrayList<data_RecentEntry>) : RecyclerView.Adapter<RVAdapter_RecentEnty.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val recentView = LayoutInflater.from(parent.context).inflate(R.layout.template_recententry,
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
        holder.dateView.text = currentItem.date
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val imageView : ImageView = itemView.findViewById(R.id.imgViewRecent)
        val textView : TextView = itemView.findViewById(R.id.txtEntryTitle)
        val dateView : TextView = itemView.findViewById(R.id.txtEntryDate)
    }

}