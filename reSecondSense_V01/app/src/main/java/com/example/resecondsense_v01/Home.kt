package com.example.resecondsense_v01

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.resecondsense_v01.databinding.FragmentHomeBinding
import java.util.Date

//This is the home page fragment
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
        var DBObj = DataContext



        val recyclerView: RecyclerView = view.findViewById(R.id.recentRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.adapter = RVAdapter_RecentEnty(DBObj.getRecentEntry())

        val btnCreatEntry : Button = view.findViewById(R.id.btnCreateEntry)
        btnCreatEntry.setOnClickListener {

            // Create an Intent to navigate to the target activity
            val intent = Intent(requireContext(), AddNewEntries::class.java)

            // Optionally, add extras to the Intent
            intent.putExtra("key", "value")

            // Start the activity
            startActivity(intent)
        }

        //Progress bar for min and max values
        val loadingBar = view.findViewById<ProgressBar>(R.id.minMaxLoadingBar)
        val minValue = 2
        val maxValue = 100
        val progressValue = 80

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            loadingBar.min = minValue
        }

        loadingBar.max = maxValue
        loadingBar.progress = progressValue


        return view



    }


}