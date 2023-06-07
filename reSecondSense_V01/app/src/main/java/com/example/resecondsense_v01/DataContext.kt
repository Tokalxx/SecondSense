package com.example.resecondsense_v01

import java.util.Date

object DataContext {
    //Dummy Category Data
    val currentDate: Date = Date()
    var Username : String = ""

    var TimeSheetEntries = mutableListOf<data_Entries>(
        data_Entries("Math", 2, currentDate.toString(),"User1","Description",null),
        data_Entries("Science", 2, currentDate.toString(),"User2","Description",null),
        data_Entries("English", 2, currentDate.toString(),"User1","Description",null),
        data_Entries("Math", 2, currentDate.toString(),"User3","Description",null),
        data_Entries("Science", 2, currentDate.toString(),"User1","Description",null),
        data_Entries("English", 3, currentDate.toString(),"User2","Description",null),
        data_Entries("Math", 2, currentDate.toString(),"User2","Description",null),
        data_Entries("Science", 3, currentDate.toString(),"User3","Description",null),
        data_Entries("English", 3, currentDate.toString(),"User3","Description",null),
        data_Entries("Math", 2, currentDate.toString(),"User1","Description",null),
        data_Entries("Science", 2, currentDate.toString(),"User1","Description",null),
        data_Entries("English", 2, currentDate.toString(),"User2","Description",null),
        data_Entries("English", 2, currentDate.toString(),"User1","Description",null),
        // Add more items as needed
    )

    //This is dummy data to test the view
    var data = mutableListOf<data_RecentEntry>(
        data_RecentEntry(R.drawable.the_goat, "Title 1", currentDate.toString(),"User1"),
        data_RecentEntry(R.drawable.the_cow, "Title 2",  currentDate.toString(),"User1"),
        data_RecentEntry(R.drawable.the_other_goat, "Title 3",  currentDate.toString(),"User2"),
        data_RecentEntry(R.drawable.the_cow, "Title 2",  currentDate.toString(),"User2"),
        data_RecentEntry(R.drawable.the_goat, "Title 1",  currentDate.toString(),"User3"),
        data_RecentEntry(R.drawable.the_cow, "Title 2",  currentDate.toString(),"User3"),
        // Add more items as needed
    )


    //Dummy Users
    val Users = mutableListOf<data_User>(
        data_User("Jerry","User1","Pass1"),
        data_User("Sam","User2","Pass1"),
        data_User("Fiona","User3","Pass1")
    )



    var Cat = mutableListOf<data_Category>(
        data_Category("Math User 1", 2, currentDate.toString(),"User1"),
        data_Category("Science User3", 2, currentDate.toString(),"User3"),
        data_Category("English", 2, currentDate.toString(),"User1"),
        data_Category("Biology", 2, currentDate.toString(),"User3"),
        data_Category("Math", 3, currentDate.toString(),"User1"),
        data_Category("Science", 4, currentDate.toString(),"User3"),
        data_Category("English", 6, currentDate.toString(),"User1"),
        data_Category("Biology User2", 8, currentDate.toString(),"User2"),
        data_Category("Math", 2, currentDate.toString(),"User3"),
        data_Category("Science", 5, currentDate.toString(),"User2"),
        data_Category("English", 6, currentDate.toString(),"User3"),
        data_Category("Biology", 8, currentDate.toString(),"User2"),
        data_Category("Biology", 133, currentDate.toString(),"User3"),
        data_Category("Biology", 1323, currentDate.toString(),"User2")
    )


    //Function to find a user
    fun findUser(UserID : String, Password : String): Boolean {

        for (i in 0..Users.size) {
            if ( UserID.equals(Users[i].UserID) && (Password.equals(Users[i].Password))  ){
                Username = UserID
                return true
            }

        }
        return false
    }

    //Function to create a user
    fun CreateUser(UserID : String, Password : String,Name: String){
        //adds to the list
        Users.add(data_User(Name,UserID,Password))
    }

    fun CreateCategory(catName: String){
        Cat.add(data_Category(catName, 0, currentDate.toString(), Username))
    }

    fun getEntries(): List<data_Entries> {
        TimeSheetEntries = TimeSheetEntries.filter { it.UserID == Username }.toMutableList()
        return TimeSheetEntries
    }
    fun getCategory(): List<data_Category> {
        Cat = Cat.filter { it.UserId == Username }.toMutableList()
        return Cat
    }

    fun getRecentEntry(): List<data_RecentEntry> {
        data = data.filter { it.UserId == Username }.toMutableList()
        return data
    }


    fun createEntry(dataEntries: data_Entries){

        TimeSheetEntries.add(
            dataEntries
        )
    }

}