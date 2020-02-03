package rbl.Assignment.weatherapp.common

import android.content.Context


class CommonUtils {

    companion object {

        private lateinit var context: Context

        fun setContext(con: Context) {
            context=con
        }

        fun getWeatherAPIKey(): String = WeatherAPIKey.WEATHER_APP_ID

        fun getWeatherBaseURL(): String = APIConstants.WEATHER_BASE_URL

        fun getWeatherTempScale(): String = APIConstants.TEMP_SCALE

        fun getLat():String= Utils.getLattitude(context).toString()
        fun getLon():String=  Utils.getLognitude(context).toString()


    }
}