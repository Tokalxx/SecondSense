package com.example.resecondsense_v01

import java.util.Date

object DataContext {
    //Dummy Category Data
    val currentDate: Date = Date()
    var Username : String = ""
    var clickedCategory : String = ""

    var TimeSheetEntries = mutableListOf<data_Entries>(
        data_Entries(1,"Math", 2, currentDate.toString(),"User1","Description","Math User 1",null),
        data_Entries(2,"Science", 2, currentDate.toString(),"User2","Description","",null),
        data_Entries(3,"English", 2, currentDate.toString(),"User1","Description","Math User 1",null),
        data_Entries(4,"Math", 2, currentDate.toString(),"User1","Description","Math User 1",null),
        data_Entries(5,"Science", 2, currentDate.toString(),"User3","Description","Science User3",null),
        data_Entries(6,"English", 2, currentDate.toString(),"User2","Description","",null),
        data_Entries(7,"English", 2, currentDate.toString(),"User1","Description","Math User 1",null),
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
        var tempTimesheet : List<data_Entries>
        tempTimesheet = TimeSheetEntries.filter { it.UserID == Username }.toMutableList()
        return tempTimesheet
    }
    fun getCategory(): List<data_Category> {
        var tempCategories : List<data_Category>
        tempCategories = Cat.filter { it.UserId == Username }.toMutableList()
        return tempCategories
    }

    fun getRecentEntry(): List<data_RecentEntry> {
        var tempRecent : List<data_RecentEntry>
        tempRecent = data.filter { it.UserId == Username }.toMutableList()
        return tempRecent
    }


    fun createEntry(dataEntries: data_Entries){

        TimeSheetEntries.add(
            dataEntries
        )
        var reCategory : data_Category
        Cat.filter { it.UserId == Username && it.category_Title == dataEntries.CategoryTitle }.first().hoursSpent+=dataEntries.hoursSpent

    }
fun calavulateent():Int
{var tempTimesheetEntires : List<data_Entries>
    tempTimesheetEntires= TimeSheetEntries.filter { it.UserID== Username }.toMutableList()
    val total = tempTimesheetEntires.sumOf { it.hoursSpent }
    return total
}

    fun calavulateCat():Int
    {
        var tempCatCalculate : List<data_Category>
        tempCatCalculate= Cat.filter { it.UserId== Username }.toMutableList()
        var total = tempCatCalculate.sumOf { it.hoursSpent }
        return total
    }
    fun generateEntryId():Int{
        var newId : Int
        newId = TimeSheetEntries[TimeSheetEntries.size-1].entryId+1
        return newId
    }

    fun getTimeSheetEntry(EntryId: Int):data_Entries{
        var data_Entries = TimeSheetEntries.get(EntryId - 1)
        return data_Entries
    }

//    fun getACategory(): data_Category{
//
//    }
//
//    fun deleteCategory(){
//
//    }
}