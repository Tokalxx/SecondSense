package com.example.resecondsense_v01

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.net.Uri
import android.os.Bundle
import android.webkit.MimeTypeMap
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.resecondsense_v01.databinding.ActivityAddNewEntriesBinding
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.StorageTask
import com.google.firebase.storage.UploadTask
import com.squareup.picasso.Picasso
import java.io.IOException
import java.io.Serializable
import java.lang.System.currentTimeMillis
import java.util.Date
import java.util.concurrent.TimeUnit


class AddNewEntries : AppCompatActivity() {
    //binding
    private var mYear = 0
    private  var mMonth:kotlin.Int = 0
    private  var mDay:kotlin.Int = 0
    private  var mHour:kotlin.Int = 0
    private  var mMinute:kotlin.Int = 0
    private lateinit var binding: ActivityAddNewEntriesBinding
    private lateinit var imageView: ImageView
    private var imgUri: Uri? = null;
    lateinit var dataEntries :data_Entries
    val Dbhelper = DataContext
    private val PICK_IMAGE_REQUEST = 1

    private var mStorageRef: StorageReference? = null
    private var mUploadTask: StorageTask<*>? = null
    var imageUploadURL : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView( R.layout.activity_add_new_entries)

        imageView = findViewById(R.id.imgEntryImage)

        //binding = setContentView( R.layout.activity_add_new_entries) as ActivityAddNewEntriesBinding
        mStorageRef = FirebaseStorage.getInstance().getReference("entryImages");


        //variables
        val backbutton: Button = findViewById(R.id.btnBackHome)
        val addPicbutton: Button = findViewById(R.id.btnAddPicture)
        val txtDescription : TextView = findViewById(R.id.txtEntryDescription)
        var txtDate : TextView = findViewById(R.id.txtEntryDate)
        var txtStartTime : TextView = findViewById(R.id.txtEntryStartTime)
        var txtEndTime : TextView = findViewById(R.id.txtEntryEndTime)
        var txtEntryTitle : TextView = findViewById(R.id.txtEntryTitle)
        var txtEntryCategory : AutoCompleteTextView = findViewById(R.id.cmbCategory)
        //date picker buttons
        val btnDone : Button = findViewById(R.id.btnFinalEntryCreate)

        //Filling the category drop down
        val items = Dbhelper.catTempList
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

            // start your next activity
            //startActivity(intent)
            pickImageFromGallery()
        }

        //setting the date and time
        txtDate.setOnClickListener{  // Get Current Date
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
            datePickerDialog.show()}

        txtStartTime.setOnClickListener {
            // Get Current Time
            val c = Calendar.getInstance()
            mHour = c[Calendar.HOUR_OF_DAY]
            mMinute = c[Calendar.MINUTE]
            // Launch Time Picker Dialog
            val timePickerDialog = TimePickerDialog(this,
                { view, hourOfDay, minute -> txtStartTime.setText("$hourOfDay:$minute") },
                mHour,
                mMinute,
                false
            )
            timePickerDialog.show()
        }
        txtEndTime.setOnClickListener {
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
        //once all details have been entered
        btnDone.setOnClickListener{
            //converting the time to hours and minutes
            val formatter = SimpleDateFormat("HH:mm")
            var startTime: Date = formatter.parse(txtStartTime.text.toString()) // Parse the start time string
            var endTime: Date = formatter.parse(txtEndTime.text.toString())
            //checking if start time is before end time
            val isEndTimeBeforeStartTime = Dbhelper.checkDate(startTime, endTime)
            if (isEndTimeBeforeStartTime) {
            if (imgUri != null) {
                val fileReference = mStorageRef!!.child(currentTimeMillis().toString() + "." + getFileExtension(imgUri!!));

                mUploadTask = fileReference.putFile(imgUri!!)
                    .addOnSuccessListener {
                            imageUploadURL = fileReference.downloadUrl.toString()
                    }.addOnFailureListener { exception ->
                        // Error occurred while getting the download URL
                        Toast.makeText(this, "Failed to retrieve image URL", Toast.LENGTH_SHORT).show()
                    }
                }

                //finding the duration which is the differenc e between the start time and end time
                var duration = endTime.time - startTime.time
                val hours = TimeUnit.MILLISECONDS.toHours(duration).toInt()

                dataEntries = data_Entries(
                    Dbhelper.generateEntryId(),
                    txtEntryTitle.text.toString(),
                    hours,
                    txtDate.text.toString(),
                    Dbhelper.Username,
                    txtDescription.text.toString(),
                    txtEntryCategory.text.toString(),
                    imageUploadURL

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
            else
            {Toast.makeText(this, "Error, end time can't be before start time. ", Toast.LENGTH_SHORT).show()}
        }


    }



    private fun pickImageFromGallery() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(intent, PICK_IMAGE_REQUEST)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
            && data != null && data.getData() != null) {
            val imageUri = imageView.setImageURI(data?.data)
            
            imgUri = data?.data
            Picasso.get().load(imgUri).into(imageView)


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

    private fun getFileExtension(uri: Uri): String? {
        val cR: ContentResolver = getContentResolver()
        val mime = MimeTypeMap.getSingleton()
        return mime.getExtensionFromMimeType(cR.getType(uri))
    }
}