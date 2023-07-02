package com.example.resecondsense_v01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class AnalyticsForACategory : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_analytics_for_acategory)

        val backbutton: Button = findViewById(R.id.btnBackHome)

        backbutton.setOnClickListener {
            onBackPressed()
        }
    }
}