package com.example.resecondsense_v01

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.icu.util.Calendar
import android.net.Uri
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.resecondsense_v01.databinding.ActivityAddNewEntriesBinding
import java.io.IOException
import java.io.Serializable


class AddNewEntries : AppCompatActivity() {
    //binding

    private var mYear = 0
    private  var mMonth:kotlin.Int = 0
    private  var mDay:kotlin.Int = 0
    private  var mHour:kotlin.Int = 0
    private  var mMinute:kotlin.Int = 0
    private lateinit var binding: ActivityAddNewEntriesBinding
    private lateinit var imageView: ImageView
    private var imgUri: Uri? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView( R.layout.activity_add_new_entries)

        imageView = findViewById(R.id.imgEntryImage)

        //binding = setContentView( R.layout.activity_add_new_entries) as ActivityAddNewEntriesBinding
        val Dbhelper = DataContext
        //variables
        val backbutton: Button = findViewById(R.id.btnBackHome)
        val addPicbutton: Button = findViewById(R.id.btnAddPicture)
        val txtDescription : TextView = findViewById(R.id.txtEntryDescription)
        var txtDate : TextView = findViewById(R.id.txtEntryDate)
        var txtStartTime : TextView = findViewById(R.id.txtEntryStartTime)
        var txtEndTime : TextView = findViewById(R.id.txtEntryStartTime)
        var txtEntryTitle : TextView = findViewById(R.id.txtEntryTitle)
        //date picker buttons
        val btnEntryDatebutton: Button = findViewById(R.id.btnEntryDatepicker)
        val btnStartTime: Button = findViewById(R.id.btnStartTimePicker)
        val btnEndTimebutton: Button = findViewById(R.id.btnEndTimePicker)
        val btnDone : Button = findViewById(R.id.btnFinalEntryCreate)



        //Filling the category drop down
        val items = Dbhelper.getCategory().map { it.category_Title }
        val autoComplete : AutoCompleteTextView = findViewById(R.id.cmbCategory)

        val adapter = ArrayAdapter(this,R.layout.template_categorydropdown,items)
        autoComplete.setAdapter(adapter)
        autoComplete.onItemClickListener = AdapterView.OnItemClickListener { adapterView, view, i, l ->
            val selectedItem =  adapterView.getItemAtPosition(i)
            Toast.makeText(this, "Item ${selectedItem}", Toast.LENGTH_SHORT).show()
        }
        //back button

        backbutton.setOnClickListener {
            onBackPressed()
        }
        //add picture button

        addPicbutton.setOnClickListener {
            val intent = Intent(this, addEntryPicture::class.java)
            // start your next activity
            //startActivity(intent)
            pickImageFromGallery()
        }

        btnEntryDatebutton.setOnClickListener {
            // Get Current Date
            val c: Calendar = Calendar.getInstance()
            mYear = c.get(Calendar.YEAR)
            mMonth = c.get(Calendar.MONTH)
            mDay = c.get(Calendar.DAY_OF_MONTH)


            val datePickerDialog = DatePickerDialog(this,
                { view, year, monthOfYear, dayOfMonth -> txtDate!!.setText(dayOfMonth.toString() + "-" + (monthOfYear + 1) + "-" + year) },
                mYear,
                mMonth,
                mDay
            )
            datePickerDialog.show()
        }

        btnStartTime.setOnClickListener {
            // Get Current Time
            val c = Calendar.getInstance()
            mHour = c[Calendar.HOUR_OF_DAY]
            mMinute = c[Calendar.MINUTE]

            // Launch Time Picker Dialog

            // Launch Time Picker Dialog
            val timePickerDialog = TimePickerDialog(this,
                { view, hourOfDay, minute -> txtStartTime.setText("$hourOfDay:$minute") },
                mHour,
                mMinute,
                false
            )
            timePickerDialog.show()
        }
        btnEndTimebutton.setOnClickListener {
            // Get Current Time
            val c = Calendar.getInstance()
            mHour = c[Calendar.HOUR_OF_DAY]
            mMinute = c[Calendar.MINUTE]

            // Launch Time Picker Dialog
            val timePickerDialog = TimePickerDialog(this,
                { view, hourOfDay, minute -> txtEndTime.setText("$hourOfDay:$minute") },
                mHour,
                mMinute,
                false
            )
            timePickerDialog.show()

        }
        //once alll details have been entered
        btnDone.setOnClickListener{
            var dataEntries :data_Entries
            dataEntries = data_Entries(
                txtEntryTitle.text.toString(),
                22,
                txtDate.text.toString(),
                Dbhelper.Username,
                txtDescription.text.toString(),
                imgUri.toString()

            )

            Dbhelper.createEntry(dataEntries)

            // Show a toast message to indicate the data has been added
            Toast.makeText(this, "Data added successfully", Toast.LENGTH_SHORT).show()

            // Create an Intent to hold the result data
            val intent = Intent()

            // Put the updated list of categories as an extra in the intent using the same key as before
            intent.putExtra("DATA_ENTRIES", Dbhelper.getEntries() as Serializable)

            // Set the result code and the intent
            setResult(RESULT_OK, intent)

            // Finish the activity and resume the HomeActivity
            finish()


        }



    }

    private fun pickImageFromGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, addEntryPicture.IMAGE_REQUEST_CODE)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == addEntryPicture.IMAGE_REQUEST_CODE && resultCode == RESULT_OK) {
            val imageUri = imageView.setImageURI(data?.data)
            
            imgUri = data?.data
            imageView.setImageURI(imgUri)

        }
    }

    private fun saveImageToInternalStorage(bitmap: Bitmap) {
        val filename = "image.jpg" // Specify the desired filename for your image

        try {
            val outputStream = openFileOutput(filename, Context.MODE_PRIVATE)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
            outputStream.flush()
            outputStream.close()
            Toast.makeText(this, "Image saved", Toast.LENGTH_SHORT).show()
        } catch (e: IOException) {
            e.printStackTrace()
            Toast.makeText(this, "Failed to save image", Toast.LENGTH_SHORT).show()
        }
    }



}