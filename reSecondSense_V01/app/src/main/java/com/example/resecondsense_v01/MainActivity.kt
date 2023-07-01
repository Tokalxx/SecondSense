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
import com.google.firebase.auth.FirebaseAuth
import java.util.Date
//Main activity for Login and sign up
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySigninPageBinding
    private lateinit var firebaseAuth: FirebaseAuth
    val dbhelper = DataContext
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySigninPageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth = FirebaseAuth.getInstance()
            //setContentView(R.layout.activity_signin_page)


        //User Credentials
        val UserID : TextView = findViewById(R.id.txtUsername)
        val Password : TextView = findViewById(R.id.txtPassword)

        binding.btnLogin.setOnClickListener {
            // Create an Intent to navigate to the target activity
            var Username = binding.txtUsername.text.toString()
            var password = binding.txtPassword.text.toString()

            if(Username.isNotEmpty() && password.isNotEmpty()) {

                Username = dbhelper.removeWhitespaces(Username)

                firebaseAuth.signInWithEmailAndPassword(Username,password).addOnCompleteListener {
                    if(it.isSuccessful){
                        dbhelper.Username = Username.toLowerCase()
                        val intent = Intent(this, HomeActivity::class.java)

                        startActivity(intent)
                    } else{
                        Toast.makeText(this, "Login Failed, Username or Password is incorrect", Toast.LENGTH_SHORT).show()
                    }
                }


            }

        }
        binding.txtSignUp.setOnClickListener {  // Create an Intent to navigate to the target activity
            val intent = Intent(this, SignUpActivity::class.java)
            // Start the activity
            startActivity(intent) }

    }
    fun invalidCredentials(context: Context) {
        val message = "Incorrect username or password."
        val duration = Toast.LENGTH_SHORT

        Toast.makeText(context, message, duration).show()
    }
}