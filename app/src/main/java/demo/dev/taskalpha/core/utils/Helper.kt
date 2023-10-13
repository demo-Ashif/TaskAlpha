package demo.dev.taskalpha.core.utils

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object Helper {

    fun toReadableDate(dateInput: String): String {
        return try {
            val dateFormat: DateFormat =
                SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
            val date: Date =
                dateFormat.parse(dateInput) as Date
            val formatter: DateFormat =
                SimpleDateFormat(
                    "MMMM dd, yyyy",
                    Locale.getDefault()
                )
            formatter.format(date)
        } catch (e: java.lang.Exception) {
            dateInput
        }
    }

}