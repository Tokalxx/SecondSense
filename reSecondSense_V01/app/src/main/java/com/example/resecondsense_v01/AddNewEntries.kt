package com.example.resecondsense_v01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class AddNewEntries : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_entries)

        //back button
        val button: Button = findViewById(R.id.btnBackHome)
        button.setOnClickListener {
            onBackPressed()
        }
    }
}