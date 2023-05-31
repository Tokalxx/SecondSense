package com.example.resecondsense_v01

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.resecondsense_v01.databinding.FragmentCategoryBinding
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Date

class Category : Fragment() {
    //binding
    private var _binding: FragmentCategoryBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentCategoryBinding.inflate(inflater, container, false)
        val view = binding.root
        val currentDate: Date = Date()
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_category, container, false)
        //This is dummy data to test the view
        val data = arrayListOf(
            data_Category("Math", "2 hrs", currentDate),
            data_Category("Science", "3 hrs", currentDate),
            data_Category("English", "3 hrs", currentDate),
            data_Category("Biology", "10 hrs", currentDate),
            data_Category("Math", "2 hrs", currentDate),
            data_Category("Science", "3 hrs", currentDate),
            data_Category("English", "3 hrs", currentDate),
            data_Category("Biology", "10 hrs", currentDate),
            data_Category("Math", "2 hrs", currentDate),
            data_Category("Science", "3 hrs", currentDate),
            data_Category("English", "3 hrs", currentDate),
            data_Category("Biology", "10 hrs", currentDate),
            data_Category("Biology", "133 hrs", currentDate),
            data_Category("Biology", "1388 hrs", currentDate)

            // Add more items as needed
        )
        //establishing the view that will display the different categories
        val recyclerView: RecyclerView = view.findViewById(R.id.lvCategories)
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = RVAdapter_Category(data)
        return view
    }


}