package com.example.resecondsense_v01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.example.resecondsense_v01.databinding.ActivitySigninPageBinding
import java.util.Date
//Main activity for Login and sign up
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySigninPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin_page)

        val btnSignIn : Button = findViewById(R.id.btnLogin)
        val txtSignUp : TextView = findViewById(R.id.txtSignUp)
        btnSignIn.setOnClickListener {
            // Create an Intent to navigate to the target activity
            val intent = Intent(this, HomeActivity::class.java)
            // Start the activity
            startActivity(intent)
        }
        txtSignUp.setOnClickListener {  // Create an Intent to navigate to the target activity
            val intent = Intent(this, SignUpActivity::class.java)
            // Start the activity
            startActivity(intent) }

    }
}
