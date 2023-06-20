package com.example.resecondsense_v01

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object DataContext {
    //Dummy Category Data
    val currentDate: Date = Date()
    var Username: String = ""
    var clickedCategory: String = ""

    var TimeSheetEntries = mutableListOf<data_Entries>(
        data_Entries(
            1,
            "Math",
            2,
            currentDate.toString(),
            "User1",
            "Description",
            "Math User 1",
            null
        ),
        data_Entries(2, "Science", 2, currentDate.toString(), "User2", "Description", "", null),
        data_Entries(
            3,
            "English",
            2,
            currentDate.toString(),
            "User1",
            "Description",
            "Math User 1",
            null
        ),
        data_Entries(
            4,
            "Math",
            2,
            currentDate.toString(),
            "User1",
            "Description",
            "Math User 1",
            null
        ),
        data_Entries(
            5,
            "Science",
            2,
            currentDate.toString(),
            "User3",
            "Description",
            "Science User3",
            null
        ),
        data_Entries(6, "English", 2, currentDate.toString(), "User2", "Description", "", null),
        data_Entries(
            7,
            "English",
            2,
            currentDate.toString(),
            "User1",
            "Description",
            "Math User 1",
            null
        ),
        // Add more items as needed
    )




    //Dummy Users
    val Users = mutableListOf<data_User>(
        data_User("Jerry", "User1", "Pass1"),
        data_User("Sam", "User2", "Pass1"),
        data_User("Fiona", "User3", "Pass1")
    )


    var Cat = mutableListOf<data_Category>(
        data_Category("Math User 1", calavulateCat(getEntriesCategory()), currentDate.toString(), "User1"),
        data_Category("Science User3", 2, currentDate.toString(), "User3"),
        data_Category("English", 2, currentDate.toString(), "User1"),
        data_Category("Biology", 133, currentDate.toString(), "User3"),
        data_Category("Biology", 1323, currentDate.toString(), "User2")
    )


    //Function to find a user
    fun findUser(UserID: String, Password: String): Boolean {

        for (i in 0..Users.size) {
            if (UserID.equals(Users[i].UserID) && (Password.equals(Users[i].Password))) {
                Username = UserID
                return true
            }

        }
        return false
    }


    //Function to create a user
    fun createUser(UserID: String, Password: String, Name: String) {
        //adds to the list
        Users.add(data_User(Name, UserID, Password))
    }

    //function to add a new category
    fun createCategory(catName: String) {
        Cat.add(data_Category(catName, 0, currentDate.toString(), Username))
    }

    //function to get all entries that belong to a specific user
    fun getEntries(): List<data_Entries> {
        var tempTimesheet: List<data_Entries>
        tempTimesheet = TimeSheetEntries.filter { it.UserID == Username }.toMutableList()
        return tempTimesheet
    }

    //function to get all categories that belong to a specific user
    fun getCategory(): List<data_Category> {
        var tempCategories: List<data_Category>
        tempCategories = Cat.filter { it.UserId == Username }.toMutableList()
        return tempCategories
    }

    //functions to get all recent entries that belong to a specific user
    fun getRecentEntry(): List<data_Entries> {
        var tempRecent: List<data_Entries>

        tempRecent = TimeSheetEntries.filter { it.UserID == Username }.toMutableList()
        tempRecent = sortBydate2(tempRecent)
        if (tempRecent.size < 7) {
            return tempRecent
        } else {
            return tempRecent.take(7)
        }
    }

    //function to create a new entry
    fun createEntry(dataEntries: data_Entries) {

        TimeSheetEntries.add(
            dataEntries
        )
        var reCategory: data_Category
        Cat.filter { it.UserId == Username && it.category_Title == dataEntries.CategoryTitle }
            .first().hoursSpent += dataEntries.hoursSpent

    }

    //function to calculate the total hours for all entries
    fun calavulateent(): Int {
        var tempTimesheetEntires: List<data_Entries>
        tempTimesheetEntires = TimeSheetEntries.filter { it.UserID == Username }.toMutableList()
        val total = tempTimesheetEntires.sumOf { it.hoursSpent }
        return total
    }

    fun calavulateCat(): Int {
        var tempCatCalculate: List<data_Category>
        tempCatCalculate = Cat.filter { it.UserId == Username }.toMutableList()
        var total = tempCatCalculate.sumOf { it.hoursSpent }
        return total
    }

    //function to create an ID for every entry
    fun generateEntryId(): Int {
        var newId: Int
        newId = TimeSheetEntries[TimeSheetEntries.size - 1].entryId + 1
        return newId
    }

    //function to get a specific entry and the details
    fun getTimeSheetEntry(EntryId: Int): data_Entries {
        var data_Entries = TimeSheetEntries.get(EntryId - 1)
        return data_Entries
    }

    //function to check if duplicate categories exist
    fun checkDuplicatCategory(categoryTitle: String) {}

    //function to sort the entry list by date
    fun sortByDate(list: List<data_Entries>): List<data_Entries> {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

        return list.sortedByDescending { dateFormat.parse(it.entryDate) }
    }

    fun sortBydate2(entries: List<data_Entries>): List<data_Entries> {

        val dateFormat = SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.getDefault())
        return entries.sortedBy{ entry ->
            try {
                dateFormat.parse(entry.entryDate)
            } catch (e: Exception) {
                null
            }

        }

    }

    fun getEntriesCategory(categoryName : String) :  List<data_Entries> {
        var tempentries : List<data_Entries> =  getEntries()
        tempentries = tempentries.filter { it.CategoryTitle == categoryName }
        return tempentries
    }

    fun calavulateCat(cetainList : List<data_Entries>): Int {
        var total=0
        total = cetainList.sumOf { it.hoursSpent }
        return total
    }
}