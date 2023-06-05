package com.example.resecondsense_v01

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.resecondsense_v01.databinding.FragmentCategoryBinding
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

        val DCCategoryObj = DataContext()


        //establishing the view that will display the different categories
        val recyclerView: RecyclerView = view.findViewById(R.id.lvCategories)
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = RVAdapter_Category(DCCategoryObj.getCategory())
        onResume()
        //Create button for Category
        val CreateCatbtnClick = view.findViewById<Button>(R.id.btnCreateCategory)
        CreateCatbtnClick.setOnClickListener {

            // Create an Intent to navigate to the target activity
            val intent = Intent(requireContext(), AddNewCategories::class.java)





            // Start the activity
            startActivity(intent)
        }

        return view
    }


    override fun onResume(){
        super.onResume()
        val DCCategoryObj = DataContext()
        val adapter = RVAdapter_Category(DCCategoryObj.getCategory())
        adapter.notifyDataSetChanged()

    }
}