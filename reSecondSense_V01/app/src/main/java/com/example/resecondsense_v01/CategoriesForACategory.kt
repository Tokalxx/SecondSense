package com.example.resecondsense_v01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.resecondsense_v01.databinding.FragmentCategoryBinding

class CategoriesForACategory : AppCompatActivity() {

    private var _binding: FragmentCategoryBinding? = null
    private lateinit var recyclerViewAdapter: RVAdapter_Category
    val dataObj = DataContext
    lateinit var output:TextView
    lateinit var recyclerView : RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categories_for_acategory)

        var txtCategoryTitle : TextView = findViewById(R.id.txtCategoryTitle)


    }
}