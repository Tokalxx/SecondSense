package com.example.resecondsense_v01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class EntryDetails : AppCompatActivity() {

    private lateinit var TimesheetItem : data_Entries

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entry_details)
        var dbhelper = DataContext
        //variables
        var txtEntryTitle : TextView = findViewById(R.id.txtEntryTitle)
        val btnBack: Button = findViewById(R.id.btnBackHome)
        var imgEntryImage : ImageView = findViewById(R.id.imgEntryImage)
        var txtEntryDate : TextView = findViewById(R.id.txtDate)
        var txtEntryDuration : TextView = findViewById(R.id.txtDuration)
        var txtCategory : TextView = findViewById(R.id.txtCategory)
        var txtDescription : TextView = findViewById(R.id.txtDescription)
        //the value being passed from the entries view when and item is clicked
        var itemId = intent.getStringExtra("EntryId")
        //getting an entry with a certain id
        if (itemId != null) {
            TimesheetItem = dbhelper.getTimeSheetEntry( itemId.toInt())
        }
        //setting the data on the screen
        txtEntryTitle.setText(TimesheetItem.entry_Title)

        //Go back home

        btnBack.setOnClickListener {
            onBackPressed()
        }

    }
}