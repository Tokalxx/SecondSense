package com.example.resecondsense_v01

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.resecondsense_v01.databinding.FragmentHomeBinding


class Category : Fragment() {
    //binding
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_category, container, false)
        //This is dummy data to test the view
        val data = arrayListOf(
            data_Category("Math", "2 hrs", "01/01/2023"),
            data_Category("Science", "3 hrs", "01/01/2023"),
            data_Category("English", "3 hrs", "01/01/2023"),
            data_Category("Biology", "10 hrs", "01/01/2023"),
            // Add more items as needed
        )
        val recyclerView: RecyclerView = view.findViewById(R.id.recentRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = RVAdapter_Category(data)
        return view
    }


}