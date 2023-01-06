package com.example.kmmdemo

import kotlinx.datetime.*

fun daysUntilNewYear() : Int {
    val today = Clock.System.todayIn(kotlinx.datetime.TimeZone.currentSystemDefault())
    val closestNewYear = LocalDate(today.year + 1,1 ,1)
    return today.daysUntil(closestNewYear)
}