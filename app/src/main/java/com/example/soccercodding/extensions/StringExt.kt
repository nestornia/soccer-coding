package com.example.soccercodding.extensions

import java.text.SimpleDateFormat
import java.util.*

// extension function for the format of date using a String as base
fun String.getDateWithServerTimeStamp(returnPattern: String) =
    // specifing the desired date format and parsing the passed string, checking if its null
    SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH).parse(this)?.let {
        // applying the format to the passed String in English language
        SimpleDateFormat(returnPattern, Locale.ENGLISH).format(it)
    }