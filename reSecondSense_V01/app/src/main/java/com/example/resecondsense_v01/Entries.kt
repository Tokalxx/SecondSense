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
import com.example.resecondsense_v01.databinding.FragmentEntriesBinding
import java.util.Date

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Entries.newInstance] factory method to
 * create an instance of this fragment.
 */
class Entries : Fragment() {
    //binding
    private var _binding: FragmentEntriesBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentEntriesBinding.inflate(inflater, container, false)
        val view = binding.root
        val currentDate: Date = Date()
        val data = arrayListOf(
            data_Entries("Math", "2 hrs", currentDate),
            data_Entries("Science", "3 hrs", currentDate),
            data_Entries("English", "3 hrs", currentDate),
            data_Entries("Math", "2 hrs", currentDate),
            data_Entries("Science", "3 hrs", currentDate),
            data_Entries("English", "3 hrs", currentDate),
            data_Entries("Math", "2 hrs", currentDate),
            data_Entries("Science", "3 hrs", currentDate),
            data_Entries("English", "3 hrs", currentDate),
            data_Entries("Math", "2 hrs", currentDate),
            data_Entries("Science", "3 hrs", currentDate),
            data_Entries("English", "3 hrs", currentDate),
            data_Entries("English", "88 hrs", currentDate),
            // Add more items as needed
        )
        // Inflate the layout for this fragment
       // return inflater.inflate(R.layout.fragment_entries, container, false)
        //establishing the view that will display the different categories
        val recyclerView: RecyclerView = view.findViewById(R.id.lvEntries)
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = RVAdapter_Entries(data)

        val CreateEntybtnClick = view.findViewById<Button>(R.id.btnCreateEntry)
        CreateEntybtnClick.setOnClickListener {

            // Create an Intent to navigate to the target activity
            val intent = Intent(requireContext(), AddNewEntries::class.java)

            // Optionally, add extras to the Intent

            // Optionally, add extras to the Intent
            intent.putExtra("key", "value")

            // Start the activity

            // Start the activity
            startActivity(intent)
        }

        return view
    }


}