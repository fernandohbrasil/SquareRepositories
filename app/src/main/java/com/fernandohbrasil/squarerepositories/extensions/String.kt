package com.fernandohbrasil.squarerepositories.extensions

import java.text.SimpleDateFormat
import java.util.*

fun String.textOrNA(): String {
    if (this.isNullOrEmpty()) {
        return "N/A"
    }
    return this
}

fun String?.toStringDateTime(): String {
    return try {
        val date = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").parse(this)
        val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.UK)
        sdf.format(date!!)
    } catch (e: Exception) {
        "N/A"
    }
}