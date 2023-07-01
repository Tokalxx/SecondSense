package com.example.resecondsense_v01

import android.content.ContentResolver
import android.content.ContentValues.TAG
import android.net.Uri
import android.util.Log
import android.webkit.MimeTypeMap
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.StorageReference
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


object DataContext {


    //variables
    lateinit var entList: List<data_Entries>
    lateinit var catList: List<data_Category>
    val currentDate: Date = Date()
    val dateFormat: String = "dd-MM-yyyy"
    var Username: String = " "
    var clickedCategory: String = ""
    var min:Int = 0;
    var max: Int = 999;
    lateinit var catTempList: List<String>
    val db = Firebase.firestore
    private val mStorageRef: StorageReference? = null

        //function to add a new category
        fun createCategory(catName: String) :String {
            var response :String = "Successfully added"
            var newCat= data_Category(catName, 0, currentDate.toString(), Username)
            addDataCategoryToFirestore(newCat)
            return response
        }

        //function to get all entries that belong to a specific user
        fun getEntries(): List<data_Entries> {
            var tempTimesheet: List<data_Entries>
            tempTimesheet = entList
            return tempTimesheet
        }

        //function to get all categories that belong to a specific user
        fun getCategory(): List<data_Category> {
            var tempCategories: List<data_Category>
            tempCategories = catList
            return tempCategories
        }

        //functions to get all recent entries that belong to a specific user
        fun getRecentEntry(): List<data_Entries> {
            var tempRecent: List<data_Entries>

            tempRecent = getTimeSheetEntryToFirestore()
            tempRecent = sortBydate(tempRecent)
            if (tempRecent.size < 7) {
                return tempRecent
            } else {
                return tempRecent.take(7)
            }
        }

        //function to create a new entry
        fun createEntry(dataEntries: data_Entries) {


            addTimeSheetEntryToFirestore(dataEntries)
            var reCategory: data_Category
//            Cat.filter { it.UserId == Username && it.category_Title == dataEntries.CategoryTitle }
//                .first().hoursSpent += dataEntries.hoursSpent

        }




        //function to calculate the total hours for all entries
        fun calavulateent(): Int {
            var tempTimesheetEntires: List<data_Entries>
            tempTimesheetEntires = getTimeSheetEntryToFirestore()
            val total = tempTimesheetEntires.sumOf { it.hoursSpent }
            return total
        }

        fun calavulateCat(): Int {
            var tempCatCalculate: List<data_Category>
            tempCatCalculate = getDataCategoryFromFirestore()
            var total = tempCatCalculate.sumOf { it.hoursSpent }
            return total
        }



        //function to get a specific entry and the details
        fun getTimeSheetEntry(EntryId: Int): data_Entries {
            var data_Entries = data_Entries()
            return data_Entries
        }

        //function to check if duplicate categories exist
        fun checkDuplicatCategory(categoryTitle: String) {}

        //function to sort the entry list by date

        fun sortBydate(entries: List<data_Entries>): List<data_Entries> {

            val dateFormat = SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.getDefault())
            return entries.sortedBy { entry ->
                try {
                    dateFormat.parse(entry.entryDate)
                } catch (e: Exception) {
                    null
                }

            }

        }

        fun getEntriesCategory(categoryName: String): List<data_Entries> {
            var tempentries: List<data_Entries> = getEntries()
            tempentries = tempentries.filter { it.CategoryTitle == categoryName }
            return tempentries
        }

        fun calavulateCat(cetainList: List<data_Entries>): Int {
            var total = 0
            total = cetainList.sumOf { it.hoursSpent }
            return total
        }

        fun calavulateCat(cetainCategoryName: String): Int {
            var total = 0
            var tempList = getEntriesCategory(cetainCategoryName)
            total = tempList.sumOf { it.hoursSpent }
            return total
        }

        //function to check if the start time is before end time
        fun checkDate(startTime: Date, endTime: Date): Boolean {
            return startTime.before(endTime)
        }

        //function to check if category exists
        fun isCategoryAlreadyExists(updatedTitle: String): Boolean {
            return getCategory().any { it.category_Title == updatedTitle }
        }

        //function that takes 2 dates and returns the list of entries made between 2 dates
        fun filterObjectsByDate(
            startDate: Date,
            endDate: Date,
            objects: List<data_Entries>
        ): List<data_Entries> {
            var filteredDateList: List<data_Entries>
            filteredDateList = objects.filter {
                convertStringToDate(
                    it.entryDate,
                    dateFormat
                ) in startDate..endDate
            }
            return filteredDateList
        }

        //function to convert dates from string to dates
        fun convertStringToDate(dateString: String, dateFormat: String): Date {
            val formatter = SimpleDateFormat(dateFormat)
            return formatter.parse(dateString)
        }
        fun getProgress(){}
        fun removeWhitespaces(input: String): String {
        return input.replace("\\s".toRegex(), "")
        }

    //function to create an ID for every entry
    fun generateEntryId(): Int {
        var newId: Int
        newId = entList.size + 100
        return newId
    }

    fun addDataEntryToFirestore(dataEntry: data_Entries) : String {
        var response : String = "ResponseMessage"
        val db = FirebaseFirestore.getInstance()

        // Specify the collection name where you want to store the entries
        val collectionRef = db.collection("entries")

        // Create a new document with an auto-generated ID
        val documentRef = collectionRef.document()

        // Set the data of the document to the properties of the dataEntry object
        documentRef.set(dataEntry)
            .addOnSuccessListener {
                // Data entry added successfully
                response = "Success"
            }
            .addOnFailureListener { exception ->
                // Error occurred while adding the data entry
                // Handle the error or show an error message to the user
                response = "Failed"
            }
        return response
    }
    fun addDataCategoryToFirestore(dataCategory: data_Category) {
        val db = Firebase.firestore


        // Specify the collection name where you want to store the categories
         db.collection("categories")
             .add(dataCategory)

    }

    fun addTimeSheetEntryToFirestore(dataEntry: data_Entries) {

        // Specify the collection name where you want to store the categories
        db.collection("entries")
            .add(dataEntry)

    }

    fun getTimeSheetEntryToFirestore():List<data_Entries>{
        val db = Firebase.firestore

        val entryList = mutableListOf<data_Entries>()

        db.collection("entries")
            .whereEqualTo("userID", Username)
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    var entry = document.toObject(data_Entries::class.java)
                    entryList.add(entry)

                }
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents: ", exception)
            }
        entList = entryList
        return entryList
    }

    fun getDataCategoryFromFirestore():List<data_Category>{
        val db = Firebase.firestore

        val categoryList = mutableListOf<data_Category>()
        val catRef = db.collection("categories")
        val categoryNameList = mutableListOf<String>()

        db.collection("categories")
        .whereEqualTo("userId", Username)
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    var category = document.toObject(data_Category::class.java)
                    categoryList.add(category)
                    categoryNameList.add(category.category_Title)
                }
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents: ", exception)

            }
        catList = categoryList
        catTempList = categoryNameList
        return categoryList
    }



    }
