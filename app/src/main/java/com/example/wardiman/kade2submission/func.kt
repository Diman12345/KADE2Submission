package com.example.wardiman.kade2submission

import android.annotation.SuppressLint
import java.text.Format
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Pattern

@SuppressLint("SimpleDateFormat")
fun strToDate(strDate: String?, pattern: String = "yyyy-MM-dd"): Date{
    val format = SimpleDateFormat(pattern)
    val date = format.parse(strDate)

    return date
}

@SuppressLint("SimpleDateFormat")
fun FormatDate(date: Date?): String? = with(date ?: Date()){
    SimpleDateFormat("EEE, dd MMM yyy").format(this)
}