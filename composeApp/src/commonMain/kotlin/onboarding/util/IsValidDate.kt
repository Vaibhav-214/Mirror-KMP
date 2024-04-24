package onboarding.util

fun isValidDate(date: String): Boolean {
    val dateRegex = Regex("^\\d{2}/\\d{2}/\\d{4}$")

    if (!date.matches(dateRegex)) {
        return false
    }

    val parts = date.split("/").map { it.toInt() }
    val day = parts[0]
    val month = parts[1]
    val year = parts[2]

    if (month !in 1..12) return false

    val daysInMonth = when (month) {
        1, 3, 5, 7, 8, 10, 12 -> 31
        4, 6, 9, 11 -> 30
        2 -> if (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)) 29 else 28
        else -> return false
    }

    return day in 1..daysInMonth
}
