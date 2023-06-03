package com.example.resecondsense_v01

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class AddNewCategories : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_categories)




        // Example navigation from activity to fragment
        val button: Button = findViewById(R.id.btnBackHome)
        button.setOnClickListener {
            onBackPressed()
        }
    }
}