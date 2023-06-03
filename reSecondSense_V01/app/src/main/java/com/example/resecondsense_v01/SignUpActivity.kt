package com.example.resecondsense_v01

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup_page)

        val loginText : TextView = findViewById(R.id.txtSignIn)
        loginText.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            // Start the activity
            startActivity(intent)
        }
    }
}