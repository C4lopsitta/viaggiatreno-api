package dev.robaldo.viaggiatreno

import java.time.DayOfWeek
import java.time.Instant
import java.time.Month
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale


/**
 * Automatically format the TimeString to be compatible with ViaggiaTreno for the current timezone, or from a given instant.
 */
fun Instant.getNowTimeString(): String {
    val currentZone = ZoneId.systemDefault()

    val zoneTime = this.atZone(currentZone)

    val dayOfWeek = zoneTime.dayOfWeek
    val monthEnum = zoneTime.month

    val day = when(dayOfWeek) {
        DayOfWeek.MONDAY -> "Mon"
        DayOfWeek.TUESDAY -> "Tue"
        DayOfWeek.WEDNESDAY -> "Wed"
        DayOfWeek.THURSDAY -> "Thu"
        DayOfWeek.FRIDAY -> "Fri"
        DayOfWeek.SATURDAY -> "Sat"
        DayOfWeek.SUNDAY -> "Sun"
    }

    val month = when(monthEnum) {
        Month.JANUARY -> "Jan"
        Month.FEBRUARY -> "Feb"
        Month.MARCH -> "Mar"
        Month.APRIL -> "Apr"
        Month.MAY -> "May"
        Month.JUNE -> "Jun"
        Month.JULY -> "Jul"
        Month.AUGUST -> "Aug"
        Month.SEPTEMBER -> "Sep"
        Month.OCTOBER -> "Oct"
        Month.NOVEMBER -> "Nov"
        Month.DECEMBER -> "Dec"
    }

    val gmtOffsetStr: String = zoneTime.format(DateTimeFormatter.ofPattern("'GMT'Z", Locale.US))
    val dayTwoDigits: String = zoneTime.dayOfMonth.toString().padStart(2, '0')
    val yearFourDigits: String = zoneTime.year.toString().padStart(4, '0')
    val hour24TwoDigits: String = zoneTime.hour.toString().padStart(2, '0')
    val minTwoDigits: String = zoneTime.minute.toString().padStart(2, '0')
    val secTwoDigits: String = zoneTime.second.toString().padStart(2, '0')

    return "$day $month $dayTwoDigits $yearFourDigits $hour24TwoDigits:$minTwoDigits:$secTwoDigits $gmtOffsetStr"
}
