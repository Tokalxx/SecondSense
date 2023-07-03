package com.example.resecondsense_v01

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.graphics.Color
import android.icu.util.Calendar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.resecondsense_v01.databinding.ActivityAnalyticsForAcategoryBinding
import com.example.resecondsense_v01.databinding.ActivitySigninPageBinding
import com.example.resecondsense_v01.databinding.FragmentCategoryBinding
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter

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
    lateinit var txtCattotal : TextView
    lateinit var Data : List<data_Entries>
    var total : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // binding = ActivityAnalyticsForAcategoryBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_analytics_for_acategory)

        val backbutton: Button = findViewById(R.id.btnBackHome)
        val btnCustom : Button = findViewById(R.id.btnCustom)
        val btnAll: Button = findViewById(R.id.btnViewAll)
        txtCattotal  = findViewById(R.id.txtCategoryTitle)
        TotalHours = findViewById(R.id.txtTotal)

        Data = dbhelper.getEntriesCategory(dbhelper.selectedCat)
        total = dbhelper.getHoursPerCat(dbhelper.selectedCat)
        TotalHours.setText("Total :"+total)
        txtCattotal.setText("Analytics for "+dbhelper.selectedCat)
        LoadGraph()

        btnCustom.setOnClickListener {
            showPopupDialog()

        }
        btnAll.setOnClickListener {
            Data = dbhelper.getEntriesCategory(dbhelper.selectedCat)
            LoadGraph()
        }
        backbutton.setOnClickListener {
            onBackPressed()
        }

//        val entries = ArrayList<BarEntry>()
//        val labels = ArrayList<String>()
//        var numx = 0
//
//        for (x in Data) {
//            val number = x.hoursSpent
//            labels.add(x.entryDate)
//            entries.add(BarEntry(numx++.toFloat(), number.toFloat()))
//        }
//
//        val dataSet = BarDataSet(entries, "Cats")
//
//        dataSet.setDrawValues(true) // Enable value display on the bars
//        dataSet.valueTextColor = Color.BLACK // Set the text color for the values
//        dataSet.valueTextSize = 12f // Set the text size for the values
//
//        val colors = ArrayList<Int>()
//        colors.add(Color.GRAY)
//        dataSet.colors = colors
//
//
//        val barData = BarData(dataSet)
//        barChart = findViewById(R.id.analyticBarChart) // Add this line to initialize the barChart
//        barChart.setFitBars(true)
//        barChart.data = barData
//
//        val xAxis = barChart.xAxis
//        xAxis.valueFormatter = IndexAxisValueFormatter(labels)
//        xAxis.position = XAxis.XAxisPosition.BOTTOM
//        xAxis.setDrawGridLines(false)
//        xAxis.granularity = 1f
//        xAxis.setCenterAxisLabels(true)
//        xAxis.isGranularityEnabled = true
//
//        barChart.description.text = "Bar Chart"
//        barChart.animateY(2000)


    }
    fun LoadGraph(){
        val entries = ArrayList<BarEntry>()
        val labels = ArrayList<String>()
        var numx = 0

        for (x in Data) {
            val number = x.hoursSpent
            labels.add(x.entryDate)
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

        val xAxis = barChart.xAxis
        xAxis.valueFormatter = IndexAxisValueFormatter(labels)
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.setDrawGridLines(false)
        xAxis.granularity = 1f
        xAxis.setCenterAxisLabels(true)
        xAxis.isGranularityEnabled = true

        barChart.description.text = "Bar Chart"
        barChart.animateY(2000)
    }

    private fun showPopupDialog() {
        val popupView = layoutInflater.inflate(R.layout.popup_date_range, null)
        val dialogBuilder = AlertDialog.Builder(this)
            .setView(popupView)
        val dialog = dialogBuilder.create()

        val enterStartDate: EditText = popupView.findViewById(R.id.txtStartdate)
        val enterEndDate: EditText = popupView.findViewById(R.id.txtEnddate)
        val cancelButton: Button = popupView.findViewById(R.id.CanButton)
        val doneButton: Button = popupView.findViewById(R.id.DnButton)

        cancelButton.setOnClickListener {
            dialog.dismiss()
        }

        enterStartDate.setOnClickListener {
            val c: Calendar = Calendar.getInstance()
            mYear = c.get(Calendar.YEAR)
            mMonth = c.get(Calendar.MONTH)
            mDay = c.get(Calendar.DAY_OF_MONTH)


            val datePickerDialog = DatePickerDialog(this,
                { view, year, monthOfYear, dayOfMonth -> enterStartDate!!.setText(dayOfMonth.toString() + "-" + (monthOfYear + 1) + "-" + year) },
                mYear,
                mMonth,
                mDay
            )
            datePickerDialog.show()
        }
        enterEndDate.setOnClickListener {
            val c: Calendar = Calendar.getInstance()
            mYear = c.get(Calendar.YEAR)
            mMonth = c.get(Calendar.MONTH)
            mDay = c.get(Calendar.DAY_OF_MONTH)


            val datePickerDialog = DatePickerDialog(this,
                { view, year, monthOfYear, dayOfMonth -> enterEndDate!!.setText(dayOfMonth.toString() + "-" + (monthOfYear + 1) + "-" + year) },
                mYear,
                mMonth,
                mDay
            )
            datePickerDialog.show()
        }

        doneButton.setOnClickListener {
            val startDate = enterStartDate.text.toString()
            val endDate = enterEndDate.text.toString()


            filterData(startDate,endDate)
            dialog.dismiss()
        }

        dialog.show()
    }

    private fun filterData(startDate: String, endDate: String) {
        val startDateObj = dbhelper.convertStringToDate(startDate,dbhelper.dateFormat)
        val endDateObj = dbhelper.convertStringToDate(endDate,dbhelper.dateFormat)

        Data = dbhelper.getEntriesForCatBetweenADate(startDateObj,endDateObj)// Convert the filtered list to an ArrayList
        //recyclerView.adapter = RVAdapter_Entries(filteredArrayList)
        LoadGraph()

    }
}