package com.example.resecondsense_v01

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.resecondsense_v01.databinding.FragmentHomeBinding
import kotlinx.coroutines.launch
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.StorageReference


//This is the home page fragment
class Home : Fragment(),RVAdapter_RecentEnty.OnItemClickListener,IminMaxUpdate {
   //binding
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerViewAdapter: RVAdapter_RecentEnty
    private var valuesUpdateListener: IminMaxUpdate? = null
    var dbhelper = DataContext
    var tempMaxValue: Int = 0
    var tempMinValue : Int = 0
    private lateinit var txtMinValue: TextView
    private lateinit var txtMaxValue: TextView
    lateinit var loadingBar : ProgressBar
    lateinit var userData:data_User

    //lateinit var barList: List<data_Category>
    private lateinit var barChart: BarChart
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root

        lifecycleScope.launch {
            // Call suspend functions here

            dbhelper.getEntries()
            dbhelper.getCategory()
            userData = dbhelper.getUserdata()


            // Initialize UI here
            var data = dbhelper.getRecentEntry()

            recyclerView = view.findViewById(R.id.recentRecyclerView)
            recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            recyclerViewAdapter = RVAdapter_RecentEnty(data)


            recyclerView.adapter = recyclerViewAdapter
            recyclerViewAdapter.itemClickListener = this@Home
            txtMinValue = view.findViewById(R.id.txtMinValue)
            txtMaxValue = view.findViewById(R.id.txtMaxValue)

            val btnCreatEntry : Button = view.findViewById(R.id.btnCreateEntry)
            btnCreatEntry.setOnClickListener {

                // Create an Intent to navigate to the target activity
                val intent = Intent(requireContext(), AddNewEntries::class.java)

                // Optionally, add extras to the Intent
                intent.putExtra("key", "value")

                // Start the activity
                startActivity(intent)
            }

            val entries = ArrayList<BarEntry>()
            var numx = 0

            for (x in dbhelper.getCatList()) {
                val number = dbhelper.getHoursPerCat(x.category_Title)
                entries.add(BarEntry(numx++.toFloat(), number.toFloat()))
            }

            val dataSet = BarDataSet(entries, "Cats")

            dataSet.setDrawValues(true) // Enable value display on the bars
            dataSet.valueTextColor = Color.BLACK // Set the text color for the values
            dataSet.valueTextSize = 12f // Set the text size for the values

            val colors = ArrayList<Int>()
            colors.add(Color.RED)
            colors.add(Color.GREEN)
            colors.add(Color.BLUE)
            dataSet.colors = colors


            val barData = BarData(dataSet)
            barChart = view.findViewById(R.id.proBarChart) // Add this line to initialize the barChart
            barChart.setFitBars(true)
            barChart.data = barData
            barChart.description.text = "Bar Chart"
            barChart.animateY(2000)

            //Progress bar for min and max values
            binding.txtMinValue.text = userData.min.toString()
            binding.txtMaxValue.text = userData.max.toString()
            loadingBar = view.findViewById<ProgressBar>(R.id.minMaxLoadingBar)

            val minValue = userData.min
            val maxValue = userData.max
            val progressValue = dbhelper.getProgress()

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                loadingBar.min = minValue
            }

            loadingBar.max = maxValue
            loadingBar.progress = progressValue
        }

        return view
    }
    @Override
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1) {
            // Check if the result code matches the one set by the other activity
            if (resultCode == Activity.RESULT_OK) {
                // Check if the intent and its extras are not null
                if (data != null && data.hasExtra("DATA_ENTRIES")) {
                    // Get the updated list of categories from the intent using the same key as before
                    val newData = dbhelper.getRecentEntry()
                    // Pass the updated list of categories to the adapter of the RecyclerView
                    (recyclerView.adapter as? RVAdapter_RecentEnty)?.updateData(newData)
                    // Notify the adapter that the data set has changed
                    recyclerView.adapter?.notifyDataSetChanged()
                }
            }
        }
    }

    override fun onItemClick(itemId: String) {
        val intent = Intent(requireContext(), EntryDetails::class.java)
        intent.putExtra("EntryId", itemId)
        startActivityForResult(intent, 1)
    }



    override fun onValuesUpdated() {
        view?.findViewById<TextView>(R.id.txtMinValue)?.text = dbhelper.min.toString()
        view?.findViewById<TextView>(R.id.txtMaxValue)?.text = dbhelper.max.toString()
        var x = view?.findViewById<ProgressBar>(R.id.minMaxLoadingBar)
        if (x != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                x.min=dbhelper.min
            }
            x.max=dbhelper.max
            x.progress=dbhelper.getProgress()
        }

    }



}