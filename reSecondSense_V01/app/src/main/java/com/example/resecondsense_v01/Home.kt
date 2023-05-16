package com.example.resecondsense_v01

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.resecondsense_v01.databinding.FragmentHomeBinding


class Home : Fragment() {
   //binding
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_home, container, false)
        //View binding
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root

        var reRVAdapter: RVAdapter
        var newRecentRecView: RecyclerView

        val data = arrayListOf(
            RecentEntry(R.drawable.the_goat, "Title 1", "Date 1"),
            RecentEntry(R.drawable.the_cow, "Title 2", "Date 2"),
            RecentEntry(R.drawable.the_other_goat, "Title 3", "Date 3")
            // Add more items as needed
        )

        val recyclerView: RecyclerView = view.findViewById(R.id.recentRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.adapter = RVAdapter(data)

        //binding.recentListView.adapter = CustomAdapter(this.requireActivity(),data)
        return view



    }


}