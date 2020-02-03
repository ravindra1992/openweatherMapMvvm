package rbl.Assignment.weatherapp.common

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class DateUtils {

    companion object {
        fun extractdayfromdate(datetime: String): String {

            val format = SimpleDateFormat("yyyy-MM-dd")
            val date: Date = format.parse(datetime)
            val format2: DateFormat = SimpleDateFormat("EEEE")
            val dayName = format2.format(date)

            return dayName

        }
    }
}