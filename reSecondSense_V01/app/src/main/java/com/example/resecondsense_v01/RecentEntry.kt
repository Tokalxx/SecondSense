package com.example.resecondsense_v01

data class RecentEntry(val imageResId: Int, val title: String, val date: String) {
    constructor() : this(0, "", "")
}
