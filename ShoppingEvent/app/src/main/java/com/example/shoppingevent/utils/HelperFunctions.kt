package com.example.shoppingevent.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun formatDate(ms: Long?, pattern: String = "EEE MMM dd yyyy"): String? {
    if (ms == null) return null
    val formatter = SimpleDateFormat(pattern, Locale.getDefault())
    return formatter.format(Date(ms))
}