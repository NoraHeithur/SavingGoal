package com.nora.savinggoal.util

import android.util.Log
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atStartOfDayIn
import kotlinx.datetime.todayIn
import kotlinx.datetime.until

fun calculateRemainingDays(selectedDate: kotlinx.datetime.LocalDate): Int {
    val systemTimeZone = TimeZone.UTC
    val today = Clock.System.todayIn(systemTimeZone)
    if (selectedDate.toEpochDays() <= today.toEpochDays()) {
        return 0
    }
    val instantToday = today.atStartOfDayIn(systemTimeZone)
    val remainingIn = instantToday.until(
        selectedDate.atStartOfDayIn(systemTimeZone),
        DateTimeUnit.DAY,
        systemTimeZone
    )
    return remainingIn.toInt()
}