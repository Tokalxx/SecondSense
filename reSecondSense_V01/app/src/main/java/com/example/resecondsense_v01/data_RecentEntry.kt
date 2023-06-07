package com.example.resecondsense_v01

import java.io.Serializable
import java.util.Date

//the data class that serves as a blue print for creating a object of Recent entry type
data class data_RecentEntry(var imageResId: Int, var title: String, var date: String,var UserId: String) :
    Serializable {

}
