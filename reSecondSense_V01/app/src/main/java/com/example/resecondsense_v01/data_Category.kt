package com.example.resecondsense_v01

import java.io.Serializable
import java.util.Date

data class data_Category(
    var category_Title: String = "",
    var hoursSpent: Int = 0,
    var categoryDate: String = "",
    var UserId: String = ""
) : Serializable