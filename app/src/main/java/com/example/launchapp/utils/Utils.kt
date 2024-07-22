package com.example.launchapp.utils

fun Double.format(digits: Int) = "%.${digits}f".format(this).toDouble()
