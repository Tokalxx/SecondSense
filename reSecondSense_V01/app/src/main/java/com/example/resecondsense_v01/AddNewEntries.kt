package com.example.resecondsense_v01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.Toast

class AddNewEntries : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_entries)


        //Filling the category drop down
        val items = listOf("DummyData","Math","English","Afrikaans")
        val autoComplete : AutoCompleteTextView = findViewById(R.id.cmbCategory)

        val adapter = ArrayAdapter(this,R.layout.template_categorydropdown,items)
        autoComplete.setAdapter(adapter)

        autoComplete.onItemClickListener = AdapterView.OnItemClickListener { adapterView, view, i, l ->
            val selectedItem =  adapterView.getItemAtPosition(i)
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
            startActivity(intent)
        }

    }
}