package com.example.resecondsense_v01

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
//This adapter helps connect the custom_list_view_template to the data,
//not currrently being used
class CustomAdapter (val context: Activity, val arrayList: ArrayList<data_RecentEntry>) :
    ArrayAdapter<data_RecentEntry>(context,R.layout.template_recententry,arrayList)
{
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater : LayoutInflater = LayoutInflater.from(context)
        val myview : View = inflater.inflate(R.layout.template_recententry,null)

        val imageView : ImageView = myview.findViewById(R.id.imgViewRecent)
        val textView : TextView = myview.findViewById(R.id.txtEntryTitle)
        val DateView : TextView = myview.findViewById(R.id.txtEntryDate)

        imageView.setImageResource(arrayList[position].imageResId)
        textView.text = arrayList[position].title
        DateView.text = arrayList[position].date

        return myview
    }
}