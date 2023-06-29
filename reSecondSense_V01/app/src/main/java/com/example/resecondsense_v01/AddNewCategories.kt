package com.example.resecondsense_v01

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.Navigation
import androidx.viewpager.widget.ViewPager
import com.example.resecondsense_v01.databinding.ActivityAddNewCategoriesBinding
import com.google.android.material.textfield.TextInputLayout
import java.io.Serializable
import java.util.Date


class AddNewCategories : AppCompatActivity() {


    private lateinit var binding: ActivityAddNewCategoriesBinding
    var catDBObj = DataContext
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_categories)

        // Example navigation from activity to fragment
        val btnbackHome: Button = findViewById(R.id.btnBackHome)
        btnbackHome.setOnClickListener {
            onBackPressed()
        }


        val catTempText: TextView = findViewById(R.id.txtCategoryName)
        val btnCatCreate: Button = findViewById(R.id.btnCatCreate)
        val catTextBoxLayout = findViewById<TextInputLayout>(R.id.catTextBoxLayout)
        val redColor = ContextCompat.getColor(applicationContext, R.color.lightRed)
        val blackColor = ContextCompat.getColor(applicationContext, R.color.black)


        catTextBoxLayout.boxStrokeColor = blackColor // Set the outer line color to red
        catTextBoxLayout.defaultHintTextColor = ColorStateList.valueOf(blackColor) // Set the hint color to red




        catTempText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // This method is called before the text is changed
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // This method is called when the text is being changed
                val updatedTitle = s.toString()
                val isTitleAlreadyExists = catDBObj.isCategoryAlreadyExists(updatedTitle)
                if (isTitleAlreadyExists) {
                    btnCatCreate.isEnabled = false // Disable the "Done" button
                    Toast.makeText(applicationContext, "Title already exists", Toast.LENGTH_SHORT).show()
                    catTextBoxLayout.boxStrokeColor = redColor // Set the outer line color to red
                    catTextBoxLayout.defaultHintTextColor = ColorStateList.valueOf(redColor) // Set the hint color to red

                } else {
                    btnCatCreate.isEnabled = true // Enable the "Done" button
                    catTextBoxLayout.boxStrokeColor = blackColor // Set the outer line color to red
                    catTextBoxLayout.defaultHintTextColor = ColorStateList.valueOf(blackColor) // Set the hint color to red

                }
            }

            override fun afterTextChanged(s: Editable?) {
                // This method is called after the text has changed
            }
        })

        // Code below is suppose to add the details into he arrayList.
        btnCatCreate.setOnClickListener {
            var responseMsg = catDBObj.createCategory(catTempText.text.toString())


            // Show a toast message to indicate the data has been added
            Toast.makeText(this, responseMsg, Toast.LENGTH_SHORT).show()

            // Create an Intent to hold the result data
            val intent = Intent()

            // Put the updated list of categories as an extra in the intent using the same key as before
            intent.putExtra("DATA", catDBObj.getCategory() as Serializable)

            // Set the result code and the intent
            setResult(RESULT_OK, intent)

            // Finish the activity and resume the HomeActivity
            finish()
        }
    }


}