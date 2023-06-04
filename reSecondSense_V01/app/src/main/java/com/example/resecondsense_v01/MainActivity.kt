package com.example.resecondsense_v01

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
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
        //User Credentials
        val UserID : TextView = findViewById(R.id.txtUsername)
        val Password : TextView = findViewById(R.id.txtPassword)
        //Buttons
        val btnSignIn : Button = findViewById(R.id.btnLogin)
        val txtSignUp : TextView = findViewById(R.id.txtSignUp)
        btnSignIn.setOnClickListener {
            // Create an Intent to navigate to the target activity
            val intent = Intent(this, HomeActivity::class.java)
            //checking if user exists
            if (findUser(UserID.text.toString(), Password.text.toString())){
            startActivity(intent)} else {
                invalidCredentials(this)
            }


        }
        txtSignUp.setOnClickListener {  // Create an Intent to navigate to the target activity
            val intent = Intent(this, SignUpActivity::class.java)
            // Start the activity
            startActivity(intent) }

    }

    fun findUser(UserID : String, Password : String): Boolean {
        //Dummy Users
        val Users = listOf<data_User>(
            data_User("Jerry","User1","Pass1"),
            data_User("Sam","User2","Pass1"),
            data_User("Fiona","User3","Pass1")
        )

        for (i in 0..Users.size) {
            return UserID.equals(Users[i].UserID) && (Password.equals(Users[i].Password))
        }
        return false
    }
    fun invalidCredentials(context: Context) {
        val message = "Incorrect username or password."
        val duration = Toast.LENGTH_SHORT

        Toast.makeText(context, message, duration).show()
    }
}
