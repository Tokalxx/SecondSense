package com.example.resecondsense_v01

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.resecondsense_v01.databinding.FragmentCategoryBinding
import java.util.Date

class Category : Fragment(), RVAdapter_Category.OnItemClickListener {
    //binding
    private var _binding: FragmentCategoryBinding? = null
    private lateinit var recyclerViewAdapter: RVAdapter_Category
    val dataObj = DataContext
    lateinit var output:TextView
    lateinit var recyclerView : RecyclerView

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentCategoryBinding.inflate(inflater, container, false)
        val view = binding.root

        recyclerView= view.findViewById(R.id.lvCategories)

        //establishing the view that will display the different categories
        recyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        // Create the adapter for the recycler view only once
        recyclerViewAdapter = RVAdapter_Category(dataObj.getCategory())

        recyclerViewAdapter.itemClickListener = this
        // Set the adapter to the recycler view
        recyclerView.adapter = recyclerViewAdapter

        output =view.findViewById(R.id.txtTotal)

        output.setText(dataObj.run{ calavulateCat().toString()})


        //Create button for Category
        val CreateCatbtnClick = view.findViewById<Button>(R.id.btnCreateCategory)
        CreateCatbtnClick.setOnClickListener {
            // Create an Intent to navigate to the target activity
            val intent = Intent(requireContext(), AddNewCategories::class.java)

            // Use a consistent key for passing data between activities and fragments
            intent.putExtra("DATA", "CATEGORY")

            // Start the activity with a request code
            startActivityForResult(intent, 1)
        }
        return view
    }


    override fun onResume() {
        super.onResume()

        // No need to create a new adapter here
        // Just notify the existing adapter of any data changes
        recyclerViewAdapter.notifyDataSetChanged()

    }
    fun updateRecyclerView(newItem: data_Category) {
        // Add the new item to the dataset
        dataObj.Cat.add(newItem)

        // Notify the adapter of the data change
        recyclerViewAdapter.notifyDataSetChanged()
    }
    @Override
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1) {
            // Check if the result code matches the one set by the other activity
            if (resultCode == RESULT_OK) {
                // Check if the intent and its extras are not null
                if (data != null && data.hasExtra("DATA")) {
                    // Get the updated list of categories from the intent using the same key as before
                    val newData = data.getSerializableExtra("DATA") as List<data_Category>
                    // Pass the updated list of categories to the adapter of the RecyclerView
                    recyclerView.adapter = RVAdapter_Category(newData)
                    output.setText(dataObj.run{ calavulateCat().toString()})
                    // Notify the adapter that the data set has changed
                    recyclerView.adapter?.notifyDataSetChanged()
                }
            }
        }
    }

    override fun onItemClick(itemId: String) {
        val intent = Intent(requireContext(), CategoriesForACategory::class.java)
        intent.putExtra("categoryId", itemId)
        startActivityForResult(intent, 1)
    }


}