package dev.robaldo.viaggiatreno

import kotlinx.datetime.TimeZone
import kotlinx.datetime.offsetAt
import kotlinx.datetime.toLocalDateTime
import kotlin.math.abs
import kotlin.time.ExperimentalTime
import kotlin.time.Instant


@OptIn(ExperimentalTime::class)
fun Instant.getNowTimeString(): String {
    val timeZone = TimeZone.currentSystemDefault()
    val localDateTime = toLocalDateTime(timeZone)

    val day = when (localDateTime.dayOfWeek.name) {
        "MONDAY" -> "Mon"
        "TUESDAY" -> "Tue"
        "WEDNESDAY" -> "Wed"
        "THURSDAY" -> "Thu"
        "FRIDAY" -> "Fri"
        "SATURDAY" -> "Sat"
        "SUNDAY" -> "Sun"
        else -> error("Unsupported day of week: ${localDateTime.dayOfWeek}")
    }

    val month = when (localDateTime.month.name) {
        "JANUARY" -> "Jan"
        "FEBRUARY" -> "Feb"
        "MARCH" -> "Mar"
        "APRIL" -> "Apr"
        "MAY" -> "May"
        "JUNE" -> "Jun"
        "JULY" -> "Jul"
        "AUGUST" -> "Aug"
        "SEPTEMBER" -> "Sep"
        "OCTOBER" -> "Oct"
        "NOVEMBER" -> "Nov"
        "DECEMBER" -> "Dec"
        else -> error("Unsupported month: ${localDateTime.month}")
    }

    val offsetSeconds = timeZone.offsetAt(this).totalSeconds
    val absoluteOffsetMinutes = abs(offsetSeconds) / 60
    val offsetSign = if (offsetSeconds < 0) "-" else "+"
    val offsetHours = (absoluteOffsetMinutes / 60).toString().padStart(2, '0')
    val offsetMinutes = (absoluteOffsetMinutes % 60).toString().padStart(2, '0')
    val gmtOffset = "GMT$offsetSign$offsetHours$offsetMinutes"

    val dayTwoDigits = localDateTime.day.toString().padStart(2, '0')
    val yearFourDigits = localDateTime.year.toString().padStart(4, '0')
    val hourTwoDigits = localDateTime.hour.toString().padStart(2, '0')
    val minuteTwoDigits = localDateTime.minute.toString().padStart(2, '0')
    val secondTwoDigits = localDateTime.second.toString().padStart(2, '0')

    return "$day $month $dayTwoDigits $yearFourDigits " +
        "$hourTwoDigits:$minuteTwoDigits:$secondTwoDigits $gmtOffset"
}

