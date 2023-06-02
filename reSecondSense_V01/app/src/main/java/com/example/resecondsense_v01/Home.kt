package com.example.resecondsense_v01

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.resecondsense_v01.databinding.FragmentHomeBinding
import java.util.Date


class Home : Fragment() {
   //binding
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_home, container, false)
        //View binding
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root

        var reRVAdapterRecentEnty: RVAdapter_RecentEnty
        var newRecentRecView: RecyclerView
        val currentDate: Date = Date()
        //This is dummy data to test the view
        val data = arrayListOf(
            data_RecentEntry(R.drawable.the_goat, "Title 1", currentDate),
            data_RecentEntry(R.drawable.the_cow, "Title 2", currentDate),
            data_RecentEntry(R.drawable.the_other_goat, "Title 3", currentDate),
            data_RecentEntry(R.drawable.the_goat, "Title 1", currentDate),
            // Add more items as needed
        )

        val recyclerView: RecyclerView = view.findViewById(R.id.recentRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.adapter = RVAdapter_RecentEnty(data)

        //binding.recentListView.adapter = CustomAdapter(this.requireActivity(),data)



        return view



    }


}