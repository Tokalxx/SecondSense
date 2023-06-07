package com.example.resecondsense_v01

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import java.io.IOException

class AddNewEntries : AppCompatActivity() {

    private lateinit var imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_entries)

        imageView = findViewById(R.id.imgEntryImage)


        //Filling the category drop down
        val items = listOf(
            "DummyData",
            "Math",
            "English",
            "Afrikaans"
        )//This is dummy data for what will go in the drop down
        val autoComplete: AutoCompleteTextView = findViewById(R.id.cmbCategory)

        val adapter = ArrayAdapter(this, R.layout.template_categorydropdown, items)
        autoComplete.setAdapter(adapter)
        autoComplete.onItemClickListener =
            AdapterView.OnItemClickListener { adapterView, view, i, l ->
                val selectedItem = adapterView.getItemAtPosition(i)
                Toast.makeText(this, "Item ${selectedItem}", Toast.LENGTH_SHORT).show()
            }
        //back button
        val backbutton: Button = findViewById(R.id.btnBackHome)
        backbutton.setOnClickListener {
            onBackPressed()
        }
        //add picture button
        val addPicbutton: Button = findViewById(R.id.btnAddPicture)
        addPicbutton.setOnClickListener {
            val intent = Intent(this, addEntryPicture::class.java)
            // start your next activity
            //startActivity(intent)
            pickImageFromGallery()
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
