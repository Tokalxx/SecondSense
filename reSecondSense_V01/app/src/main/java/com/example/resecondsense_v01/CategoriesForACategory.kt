package com.example.resecondsense_v01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.resecondsense_v01.databinding.FragmentCategoryBinding

class CategoriesForACategory : AppCompatActivity(), RVAdapter_Entries.OnItemClickListener {

    private var _binding: FragmentCategoryBinding? = null
    private lateinit var recyclerViewAdapter: RVAdapter_Entries
    val dataObj = DataContext
    lateinit var output:TextView
    lateinit var recyclerView : RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categories_for_acategory)
        var txtTotal: TextView = findViewById(R.id.txtTotal)
        val backbutton: Button = findViewById(R.id.btnBackHome)
        var txtCategoryTitle : TextView = findViewById(R.id.txtCategoryTitle)
        //the value being passed from the entries view when and item is clicked
        var itemId = intent.getStringExtra("categoryId")

        var data = dataObj.getEntriesCategory(itemId.toString())
        recyclerView= findViewById(R.id.lvEntriesForCat)
        //establishing the view that will display the different categories
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        // Create the adapter for the recycler view only once
        recyclerViewAdapter = RVAdapter_Entries(data)
        recyclerView.adapter = recyclerViewAdapter

        recyclerViewAdapter.itemClickListener = this
        //setting the title
        txtCategoryTitle.setText(itemId.toString())

        //calculating the total
        var total = dataObj.calavulateCat(data)

        //setting the total text
        txtTotal.setText("Total = "+ total+" ")
        //back button

        backbutton.setOnClickListener {
            onBackPressed()
        }


    }

    override fun onItemClick(itemId: String) {
        val intent = Intent(this, EntryDetails::class.java)
        intent.putExtra("EntryId", itemId)
        startActivityForResult(intent, 1)
    }
}