package com.example.resecondsense_v01

import java.io.Serializable
import java.util.Date

data class data_Entries(var entry_Title: String, var hoursSpent: Int, var entryDate: String,var UserID: String,var Description: String):
    Serializable {


}
