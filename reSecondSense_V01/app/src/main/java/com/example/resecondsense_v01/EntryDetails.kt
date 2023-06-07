package com.example.resecondsense_v01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class EntryDetails : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entry_details)

        //variables
        val txtEntryTitle : TextView = findViewById(R.id.txtEntryTitle)
        val imgEntryImage : ImageView = findViewById(R.id.imgEntryImage)
        val txtEntryDate : TextView = findViewById(R.id.txtDate)
        val txtEntryDuration : TextView = findViewById(R.id.txtDuration)
        val txtCategory : TextView = findViewById(R.id.txtCategory)
        val txtDescription : TextView = findViewById(R.id.txtDescription)




    }
}