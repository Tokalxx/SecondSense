package com.example.resecondsense_v01

import android.net.Uri
import java.io.Serializable
import java.util.Date

 data class data_Entries(
     var entryId:Int = 0,
     var entry_Title: String = "",
     var hoursSpent: Int = 0,
     var entryDate: String = "",
     var UserID: String = "",
     var Description : String = "",
     var CategoryTitle: String = "",
     var imageData: String? = ""): Serializable


