package com.example.weatherapp.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun formatDateFromInt(
    dt: Number,
    pattern: String = "yyyy-MM-dd",
): String {
    return SimpleDateFormat(pattern, Locale.getDefault())
        .format(Date(dt.toLong() * 1000))
}


fun getIconUrl(icon: String): String {
    return "https://openweathermap.org/img/wn/${icon}@2x.png"
}

