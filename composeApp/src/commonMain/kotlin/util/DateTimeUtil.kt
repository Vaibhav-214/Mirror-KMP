package util

import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime

object DateTimeUtil {

    fun now(): LocalDateTime {
        return Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
    }

    fun toEpochMillis(dateTime: LocalDateTime): Long {
        return dateTime.toInstant(TimeZone.currentSystemDefault()).toEpochMilliseconds()
    }

    fun generateTimestampForFilename(): String {
        val currentMoment = Clock.System.now()
        val dateTime = currentMoment.toLocalDateTime(TimeZone.currentSystemDefault())

        // Format: YYYYMMDD_HHMMSS

        // Get the year as a string
        val year = dateTime.year.toString()

        // Get the month as a two-digit string (e.g., "07" for July)
        val month = dateTime.monthNumber.toString().padStart(2, '0')

        // Get the day of the month as a two-digit string (e.g., "09" for the 9th day of the month)
        val day = dateTime.dayOfMonth.toString().padStart(2, '0')

        // Combine the above date components and separate the date and time with an underscore
        val datePart = "$year$month$day"

        // Get the hour as a two-digit string (e.g., "05" for 5 AM)
        val hour = dateTime.hour.toString().padStart(2, '0')

        // Get the minute as a two-digit string (e.g., "03" for 3 minutes past the hour)
        val minute = dateTime.minute.toString().padStart(2, '0')

        // Get the second as a two-digit string (e.g., "06" for 6 seconds past the minute)
        val second = dateTime.second.toString().padStart(2, '0')

        // Combine the time components
        val timePart = "$hour$minute$second"

        // Return the full timestamp string, combining the date and time parts
        return "$datePart$timePart"
    }

    fun formatLocalDateTime(localDateTime: LocalDateTime): String {
        val dayOfWeek = localDateTime.dayOfWeek.name
        val dayOfMonth = localDateTime.dayOfMonth
        val month = localDateTime.month.name.substring(0, 3).uppercase()
        val year = localDateTime.year.toString().substring(2)

        return "$dayOfWeek\u00B7$dayOfMonth $month $year"
    }
}

data class JournalEntry(val id: Int, val date: LocalDateTime, val content: String)

fun groupJournalsByDate(entries: List<JournalEntry>): Map<String, List<JournalEntry>> {
    val now = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())

    val groupedEntries = entries.groupBy { entry ->
        when {
            entry.date.year == now.year && entry.date.monthNumber == now.monthNumber -> "This Month"
            entry.date.year == now.year -> getMonthName(entry.date.monthNumber)
            else -> "${entry.date.year}"
        }
    }.toList()
        //sorting logic to be implemented
//        .sortedBy { (key, _) ->
//        when (key) {
//            "This Month" -> 1
//            "Earlier This Year" -> 2
//            else -> 3 // Assuming "Earlier This Year" is used for any month other than the current or previous in the current year
//        }
//    }
        .toMap()

    return groupedEntries
}

fun getMonthName(monthNumber: Int): String {
    return when(monthNumber) {
        1 -> "January"
        2 -> "February"
        3 -> "March"
        4 -> "April"
        5 -> "May"
        6 -> "June"
        7 -> "July"
        8 -> "August"
        9 -> "September"
        10 -> "October"
        11 -> "November"
        12 -> "December"
        else -> "Unknown"
    }
}
fun getMonthNumber(monthName: String): Int {
    return when(monthName) {
        "January" -> 1
        "February" -> 2
        "March" -> 3
        "April" -> 4
        "May" -> 5
        "June" -> 6
        "July" -> 7
        "August" -> 8
        "September" -> 9
        "October" -> 10
        "November" -> 11
        "December" -> 12
        else -> -1 // Indicates an unknown month name
    }
}




