package com.example.resecondsense_v01

class data_User(val userID: String, private var min: Int, private var max: Int) {
    constructor(userID: String) : this(userID, 0, 0)

    fun getMin(): Int {
        return min
    }

    fun setMin(min: Int) {
        this.min = min
    }

    fun getMax(): Int {
        return max
    }

    fun setMax(max: Int) {
        this.max = max
    }
}

