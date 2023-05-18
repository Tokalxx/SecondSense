package com.example.resecondsense_v01

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RVAdapter_Category(val categoryList: ArrayList<data_Category>) : RecyclerView.Adapter<RVAdapter_Category.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val categoryView = LayoutInflater.from(parent.context).inflate(R.layout.template_category,
            parent,false)
        return ViewHolder(categoryView)
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = categoryList[position]
        holder.hoursView.text = currentItem.hoursSpent
        holder.titleView.text = currentItem.category_Title
        holder.dateView.text = currentItem.categoryDate
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val titleView : TextView = itemView.findViewById(R.id.txtCatTitle)
        val hoursView : TextView = itemView.findViewById(R.id.txtCatHours)
        val dateView : TextView = itemView.findViewById(R.id.txtCatDate)
    }
}