package com.example.resecondsense_v01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputLayout
import java.util.Date

class AddNewEntries : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_entries)
      val entryTtile: TextView = findViewById(R.id.txtEntryTitle)
        val date: TextView = findViewById(R.id.txtEntryDate)
        val startTime: TextView = findViewById(R.id.txtEntryStartTime)
        val endTime: TextView = findViewById(R.id.txtEntryEndTime)
        val category: TextView = findViewById(R.id.cmbCategory)
        val description: TextView = findViewById(R.id.descriptionEditText)


        //Filling the category drop down
        val items = listOf("DummyData","Math","English","Afrikaans")//This is dummy data for what will go in the drop down
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
        val doneEntrybtn: Button = findViewById(R.id.btnCreateEntry)
        doneEntrybtn.setOnClickListener {
            val entryObj = DataContext()
            // This code allows you to add the data that is in the input fields for the entry page to a list
            entryObj.createEntry(entryTtile.text.toString(),date.text.toString(), startTime.text.toString(),
             endTime.text.toString(),category.text.toString(), description.text.toString())

            // this is too allow one to extract the title so that it can be added to the array list of entries
           // entryObj.extractDetails(entryTtile.text.toString())


            // Show a toast message to indicate the data has been added
            Toast.makeText(this, "Data added successfully", Toast.LENGTH_SHORT).show()
            //navigate back to fragment
            val desiredFragmentIndex = 1 // Set the desired fragment index to navigate to

            val intent = Intent(this, HomeActivity::class.java)
            intent.putExtra("FRAGMENT_INDEX", desiredFragmentIndex)
            startActivity(intent)
        }



    }
}