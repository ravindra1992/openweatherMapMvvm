package rbl.Assignment.weatherapp.weather.model.repository

import android.app.Application
import rbl.Assignment.weatherapp.weather.model.listener.IFetchCurrentWeatherListener
import rbl.Assignment.weatherapp.weather.model.listener.IFetchForecastWeatherListener
import rbl.Assignment.weatherapp.weather.view.ForecastAdapter

class WeatherRepository(application: Application) : BaseRepository() {

    fun getCurrentTempAndLocation(listener: IFetchCurrentWeatherListener) = getCurrentWeatherData(listener)

    fun getWeatherForecast(adapter: ForecastAdapter, listener: IFetchForecastWeatherListener) =
        getForecastWeatherData(adapter, listener)

    companion object {
        val TAG: String = WeatherRepository::class.java.simpleName
    }
}