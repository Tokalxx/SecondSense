package com.example.resecondsense_v01

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class RVAdapter_Category(var categoryList: List<data_Category>) : RecyclerView.Adapter<RVAdapter_Category.ViewHolder>() {
    public var data: List<data_Category>? = null
    fun RVAdapter_Category( ) {
        this.data = categoryList
    }
    public var itemClickListener: RVAdapter_Category.OnItemClickListener? = null
    var dbhelper = DataContext
    fun clear() {
        // Get the size of the data set before clearing it
        val size = data!!.size
        // Clear the data set
        data = emptyList()
        // Notify the adapter that all the items have been removed
        notifyItemRangeRemoved(0, size)
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val categoryView = LayoutInflater.from(parent.context).inflate(R.layout.template_category,
            parent,false)
        return ViewHolder(categoryView)
    }

    fun addAll(newData: List<data_Category>?) {
        // Get the current size of the data set before adding new items
        val size = data!!.size
        // Add all the new items to the data set
        data = newData
        // Notify the adapter that new items have been inserted
        if (newData != null) {
            notifyItemRangeInserted(size, newData.size)
        }
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = categoryList[position]

        //click event for a category
        holder.itemView.setOnClickListener {
        val clickedItemId = currentItem.category_Title.toString()
        itemClickListener?.onItemClick(clickedItemId)
        }
        holder.hoursView.text = dbhelper.calavulateCat(currentItem.category_Title).toString()
        holder.titleView.text = currentItem.category_Title
        holder.dateView.text = currentItem.categoryDate.toString()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val titleView : TextView = itemView.findViewById(R.id.txtCatTitle)
        val hoursView : TextView = itemView.findViewById(R.id.txtCatHours)
        val dateView : TextView = itemView.findViewById(R.id.txtCatDate)
    }

    fun updateData(newData: List<data_Category>) {
        categoryList = newData
        notifyDataSetChanged()
    }

    interface OnItemClickListener {
        fun onItemClick(itemId: String)
    }
}