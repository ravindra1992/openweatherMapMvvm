package rbl.Assignment.weatherapp.weather.viewmodel

import org.junit.Test
import org.mockito.Mockito
import rbl.Assignment.weatherapp.common.BaseUnitTest
import rbl.Assignment.weatherapp.weather.model.listener.IFetchCurrentWeatherListener
import rbl.Assignment.weatherapp.weather.model.listener.IFetchForecastWeatherListener
import rbl.Assignment.weatherapp.weather.view.ForecastAdapter

class WeatherViewModelTest: BaseUnitTest() {

    @Test
    fun getCurrentTempAndLocation() {
        mCurrentWeatherListener = Mockito.mock(IFetchCurrentWeatherListener::class.java)
        mWeatherViewModel?.getCurrentTempAndLocation(mCurrentWeatherListener!!)
    }

    @Test
    fun getWeatherForecast() {
        mForecastWeatherListener = Mockito.mock(IFetchForecastWeatherListener::class.java)
        mForecastAdapter = Mockito.mock(ForecastAdapter::class.java)
        mWeatherViewModel?.getWeatherForecast(mForecastAdapter!!, mForecastWeatherListener!!)
    }
}