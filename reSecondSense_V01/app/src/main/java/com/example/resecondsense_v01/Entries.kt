package com.example.resecondsense_v01

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Entries.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Entries().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}