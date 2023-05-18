package com.example.resecondsense_v01
//the data class that serves as a blue print for creating a object of Recent entry type
data class data_RecentEntry(val imageResId: Int, val title: String, val date: String) {
    constructor() : this(0, "", "")
}
