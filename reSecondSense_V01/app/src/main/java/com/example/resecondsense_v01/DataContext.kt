package com.example.resecondsense_v01

class DataContext {

    //Dummy Users
    val Users = mutableListOf<data_User>(
        data_User("Jerry","User1","Pass1"),
        data_User("Sam","User2","Pass1"),
        data_User("Fiona","User3","Pass1")
    )

    //Function to find a user
    fun findUser(UserID : String, Password : String): Boolean {

        for (i in 0..Users.size) {
            return UserID.equals(Users[i].UserID) && (Password.equals(Users[i].Password))
        }
        return false
    }

    //Function to create a user
    fun CreateUser(UserID : String, Password : String,Name: String){
        //adds to the list
        Users.add(data_User(Name,UserID,Password))
    }

}