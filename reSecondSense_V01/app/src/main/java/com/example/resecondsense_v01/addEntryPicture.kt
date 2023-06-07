package com.example.resecondsense_v01

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.Toast
import android.widget.ImageView
import com.example.resecondsense_v01.DataContext.data
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream


class addEntryPicture : AppCompatActivity() {

    private lateinit var button1: Button
    private lateinit var imageView: ImageView
    private lateinit var button2: Button

    val data_entries = mutableListOf<data_Entries>() // Create an empty list


    companion object{
        val IMAGE_REQUEST_CODE = 100
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_entry_picture)

        button1 = findViewById(R.id.btnAddPicture)
        button2 = findViewById(R.id.btnDone)
        imageView = findViewById(R.id.imgEntryImage)

        button1.setOnClickListener{
            pickImageFromGallery()
        }

        button2.setOnClickListener{
            val bitmap = (imageView.drawable as? BitmapDrawable)?.bitmap
            if (bitmap != null) {
                saveImageToInternalStorage(bitmap)
                // Add the data entry object to the list here, after saving the image
                val imageUri = intent.data // Get the URI from the intent parameter, not from the data class variable
                val dataEntry = data_Entries("Some title", "Some hours", "Some date", "Some user", imageUri.toString()) // Create a data entry object with the image URI
                data_entries.add(dataEntry) // Add the object to your list

            } else {
                Toast.makeText(this, "Failed to get image", Toast.LENGTH_SHORT).show()
            }
        }



        //back button
        val backbutton: Button = findViewById(R.id.btnBackHome)
        backbutton.setOnClickListener {
            onBackPressed()
        }
    }

    private fun pickImageFromGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == IMAGE_REQUEST_CODE && resultCode == RESULT_OK) {
            imageView.setImageURI(data?.data)
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
