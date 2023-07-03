package com.example.resecondsense_v01

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Intent
import android.icu.util.Calendar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.resecondsense_v01.databinding.FragmentCategoryBinding

class CategoriesForACategory : AppCompatActivity(), RVAdapter_Entries.OnItemClickListener {

    private var _binding: FragmentCategoryBinding? = null
    private lateinit var recyclerViewAdapter: RVAdapter_Entries
    val dbhelper = DataContext
    lateinit var output:TextView
    lateinit var recyclerView : RecyclerView
    private var mYear = 0
    private  var mMonth:kotlin.Int = 0
    private  var mDay:kotlin.Int = 0
    private lateinit var TotalHours : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categories_for_acategory)
        TotalHours = findViewById(R.id.txtTotal)
        val backbutton: Button = findViewById(R.id.btnBackHome)
        var txtCategoryTitle : TextView = findViewById(R.id.txtCategoryTitle)
        var btnAnalytics : Button = findViewById(R.id.btnAnalytics)
        var btnAll : Button = findViewById(R.id.btnViewAll)
        var btnCustom : Button = findViewById(R.id.btnCustom)

        //the value being passed from the entries view when and item is clicked
        var itemId = intent.getStringExtra("categoryId")
        dbhelper.selectedCat = itemId.toString()
        var data = dbhelper.getEntriesCategory(itemId.toString())
        recyclerView= findViewById(R.id.lvEntriesForCat)
        //establishing the view that will display the different categories
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        // Create the adapter for the recycler view only once
        recyclerViewAdapter = RVAdapter_Entries(data)
        recyclerView.adapter = recyclerViewAdapter

        recyclerViewAdapter.itemClickListener = this
        //setting the title
        txtCategoryTitle.setText(itemId.toString())

        //calculating the total
        var total = dbhelper.calculateHours(data)

        //setting the total text
        TotalHours.setText("Total = "+ total+" ")
        //back button

        backbutton.setOnClickListener {
            onBackPressed()
        }
        btnAnalytics.setOnClickListener {
            val intent = Intent(this, AnalyticsForACategory::class.java)
            intent.putExtra("EntryId", itemId)
            startActivityForResult(intent, 1)
        }

        btnCustom.setOnClickListener {
            showPopupDialog()
        }
        btnAll.setOnClickListener {
            var allEntries = dbhelper.getEntriesCategory(itemId.toString())
            recyclerViewAdapter.setFilteredList(allEntries)
            TotalHours.setText("Total: "+dbhelper.calculateHours(allEntries))
        }


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


            filterData(startDate, endDate)
            dialog.dismiss()
        }

        dialog.show()
    }

    private fun filterData(startDate: String, endDate: String) {
        val startDateObj = dbhelper.convertStringToDate(startDate,dbhelper.dateFormat)
        val endDateObj = dbhelper.convertStringToDate(endDate,dbhelper.dateFormat)

        val filteredArrayList = dbhelper.filterObjectsByDate(startDateObj,endDateObj,dbhelper.getEntries())// Convert the filtered list to an ArrayList
        //recyclerView.adapter = RVAdapter_Entries(filteredArrayList)
        recyclerViewAdapter.setFilteredList(filteredArrayList)
        TotalHours.setText("Total: "+dbhelper.calculateHours(filteredArrayList))
        // Show a toast message to indicate the data has been filtered
        Toast.makeText(this, "Data filtered successfully", Toast.LENGTH_SHORT).show()
    }
    override fun onItemClick(itemId: String) {
        val intent = Intent(this, EntryDetails::class.java)
        intent.putExtra("EntryId", itemId)
        startActivityForResult(intent, 1)
    }


}