package com.example.resecondsense_v01

import android.widget.TextView
import java.security.KeyStore.Entry
import java.util.Date

class DataContext {

    //Dummy Users
    val Users = mutableListOf<data_User>(
        data_User("Jerry", "User1", "Pass1"),
        data_User("Sam", "User2", "Pass1"),
        data_User("Fiona", "User3", "Pass1")
    )

    //Dummy Category Data
    val currentDate: Date = Date()

    val Cat = mutableListOf<data_Category>(
        data_Category("Math", 2, currentDate),
        data_Category("Science", 2, currentDate),
        data_Category("English", 2, currentDate),
        data_Category("Biology", 2, currentDate),
        data_Category("Math", 3, currentDate),
        data_Category("Science", 4, currentDate),
        data_Category("English", 6, currentDate),
        data_Category("Biology", 8, currentDate),
        data_Category("Math", 2, currentDate),
        data_Category("Science", 5, currentDate),
        data_Category("English", 6, currentDate),
        data_Category("Biology", 8, currentDate),
        data_Category("Biology", 133, currentDate),
        data_Category("Biology", 1323, currentDate)
    )

    //Function to find a user
    fun findUser(UserID: String, Password: String): Boolean {

        for (i in 0..Users.size) {
            return UserID.equals(Users[i].UserID) && (Password.equals(Users[i].Password))
        }
        return false
    }

    //Function to create a user
    fun CreateUser(UserID: String, Password: String, Name: String) {
        //adds to the list
        Users.add(data_User(Name, UserID, Password))
    }

    fun CreateCategory(catName: String) {
        Cat.add(data_Category(catName, 0, currentDate))
    }


    fun getCategory(): MutableList<data_Category> {
        return Cat
    }


    val Entry = mutableListOf<EntryStorage>(

    )

    // create entry method which holds the value passed from the text views
    fun createEntry(
        Title: String,
        entryDate: String,
        startTime: String,
        endTime: String,
        category: String,
        description: String
    ) {
        Entry.add(EntryStorage(Title, entryDate, startTime, endTime, category, description))
    }

    // method to extract the entry title from the list
    fun extractDetails(Titles: String) {
        Entry.find {
            it.Title == Titles

        }

        fun extractCat(Car: String) {
            Cat.find {
                it.category_Title == Car

            }
        }
    }
}
