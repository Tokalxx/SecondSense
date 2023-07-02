package com.example.resecondsense_v01

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import com.google.firebase.storage.ktx.storageMetadata
import com.squareup.picasso.Picasso
import java.io.File


class EntryDetails : AppCompatActivity() {

    private var mStorageRef: StorageReference? = null
    private lateinit var TimesheetItem : data_Entries
    lateinit var entryImage:entryImages
    var dbhelper = DataContext

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entry_details)

        mStorageRef= FirebaseStorage.getInstance().getReference("entryImages");

        //variables
        var txtEntryTitle : TextView = findViewById(R.id.txtEntryTitle)
        val btnBack: Button = findViewById(R.id.btnBackHome)
        var imgEntryImage : ImageView = findViewById(R.id.imgEntryImage)
        var txtEntryDate : TextView = findViewById(R.id.txtDate)
        var txtEntryDuration : TextView = findViewById(R.id.txtDuration)
        var txtCategory : TextView = findViewById(R.id.txtCategory)
        var txtDescription : TextView = findViewById(R.id.txtDescription)
        //the value being passed from the entries view when and item is clicked
        var itemId = intent.getStringExtra("EntryId")
        //getting an entry with a certain id
        if (itemId != null) {
            TimesheetItem = dbhelper.getTimeSheetEntry( itemId.toInt())
            if (!TimesheetItem.imageData.equals("")){
            entryImage = dbhelper.getEntryImage(itemId.toInt())!!}else{entryImage = entryImages("","",1)}
        }
        //setting the data on the screen
        txtEntryTitle.setText(TimesheetItem.entry_Title)
        txtEntryDate.setText(TimesheetItem.entryDate)
        txtEntryDuration.setText(TimesheetItem.hoursSpent.toString())
        txtCategory.setText(TimesheetItem.CategoryTitle)
        txtDescription.setText(TimesheetItem.Description)






        if(!entryImage.ImageUrl.equals("")){
            val imageUrl = TimesheetItem.imageData.toString()
            Picasso.get().load(entryImage.ImageUrl).into(imgEntryImage)
    }else{
        imgEntryImage.setImageResource(R.drawable.default_large)
    }



        //Go back home

        btnBack.setOnClickListener {
            onBackPressed()
        }

    }

}