package com.example.resecondsense_v01

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.resecondsense_v01.databinding.ActivityAnalyticsForAcategoryBinding
import com.example.resecondsense_v01.databinding.ActivitySigninPageBinding
import com.example.resecondsense_v01.databinding.FragmentCategoryBinding
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry

class AnalyticsForACategory : AppCompatActivity() {

    private var _binding: FragmentCategoryBinding? = null
    private lateinit var recyclerViewAdapter: RVAdapter_Entries
    val dbhelper = DataContext
    lateinit var output: TextView
    lateinit var recyclerView : RecyclerView
    private var mYear = 0
    private  var mMonth:kotlin.Int = 0
    private  var mDay:kotlin.Int = 0
    private lateinit var TotalHours : TextView
    private lateinit var barChart: BarChart
    private lateinit var binding: ActivityAnalyticsForAcategoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // binding = ActivityAnalyticsForAcategoryBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_analytics_for_acategory)

        val backbutton: Button = findViewById(R.id.btnBackHome)
        var txtCattotal : TextView = findViewById(R.id.txtCategoryTitle)
        TotalHours = findViewById(R.id.txtTotal)

        var Data = dbhelper.getEntriesCategory(dbhelper.selectedCat)
        var total = dbhelper.getHoursPerCat(dbhelper.selectedCat)
        TotalHours.setText("Total :"+total)
        txtCattotal.setText("Analytics for "+dbhelper.selectedCat)

        backbutton.setOnClickListener {
            onBackPressed()
        }

        val entries = ArrayList<BarEntry>()
        var numx = 0

        for (x in Data) {
            val number = x.hoursSpent
            entries.add(BarEntry(numx++.toFloat(), number.toFloat()))
        }

        val dataSet = BarDataSet(entries, "Cats")

        dataSet.setDrawValues(true) // Enable value display on the bars
        dataSet.valueTextColor = Color.BLACK // Set the text color for the values
        dataSet.valueTextSize = 12f // Set the text size for the values

        val colors = ArrayList<Int>()
        colors.add(Color.GRAY)
        dataSet.colors = colors


        val barData = BarData(dataSet)
        barChart = findViewById(R.id.analyticBarChart) // Add this line to initialize the barChart
        barChart.setFitBars(true)
        barChart.data = barData
        barChart.description.text = "Bar Chart"
        barChart.animateY(2000)


    }
}