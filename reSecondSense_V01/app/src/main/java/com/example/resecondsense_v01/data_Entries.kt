package com.example.resecondsense_v01

import android.net.Uri
import java.io.Serializable
import java.util.Date

 class data_Entries( var entryId:Int, var entry_Title: String, var hoursSpent: Int, var entryDate: String,
                        var UserID: String,var Description : String,var CategoryTitle: String , var imageData: String?): Serializable


