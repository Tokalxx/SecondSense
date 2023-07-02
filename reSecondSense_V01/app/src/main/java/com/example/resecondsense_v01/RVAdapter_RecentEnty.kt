package com.example.resecondsense_v01

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

//This is the recycler view adapter that connects the template_recententry to the date
// just needs an Array list with the data of the Recent entry, but can be change to some other list
// This method returns a the Recyler view
class RVAdapter_RecentEnty(var entryList: List<data_Entries>) : RecyclerView.Adapter<RVAdapter_RecentEnty.ViewHolder>() {


    public var itemClickListener: RVAdapter_RecentEnty.OnItemClickListener? = null
    var dbhelper = DataContext
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
        var entryimage:entryImages
            if (!currentItem.imageData.equals("")){
              entryimage = dbhelper.getEntryImage(currentItem.entryId)!!}else{entryimage = entryImages("","",1)
            //holder.imageView?.setImageURI(Uri.parse(currentItem.imageData))

        }
        if(!entryimage.ImageUrl.equals("")){
            Picasso.get().load(entryimage.ImageUrl).into(holder.imageView)}
        else {
            holder.imageView?.setImageResource(R.drawable.default_large)
        }

        holder.itemView.setOnClickListener {
            val clickedItemId = currentItem.entryId.toString()
            itemClickListener?.onItemClick(clickedItemId)
        }
        holder.textView.text = currentItem.entry_Title
        holder.dateView.text = currentItem.entryDate
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val imageView : ImageView? = itemView.findViewById(R.id.imgViewRecent)
        val textView : TextView = itemView.findViewById(R.id.txtEntryTitle)
        val dateView : TextView = itemView.findViewById(R.id.txtEntryDate)

    }


    fun updateData(newData: List<data_Entries>) {
        entryList = newData
        notifyDataSetChanged()
    }
    interface OnItemClickListener {
        fun onItemClick(itemId: String)
    }
}