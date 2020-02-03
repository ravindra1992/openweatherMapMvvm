package rbl.Assignment.weatherapp.weather.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import rbl.Assignment.weatherapp.weather.model.listener.IFetchCurrentWeatherListener
import rbl.Assignment.weatherapp.weather.model.listener.IFetchForecastWeatherListener
import rbl.Assignment.weatherapp.weather.model.repository.WeatherRepository
import rbl.Assignment.weatherapp.weather.view.ForecastAdapter

class WeatherViewModel(application: Application) : AndroidViewModel(application) {
    private var mWeatherRepo: WeatherRepository = WeatherRepository(application)

    fun getCurrentTempAndLocation(listener: IFetchCurrentWeatherListener) {
        mWeatherRepo.getCurrentTempAndLocation(listener)
    }

    fun getWeatherForecast(adapter: ForecastAdapter, listener: IFetchForecastWeatherListener) {
        mWeatherRepo.getWeatherForecast(adapter, listener)
    }
}