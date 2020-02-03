package rbl.Assignment.weatherapp.weather.model.listener

interface IFetchForecastWeatherListener {
    fun onForecastWeatherFetchSuccess(temp: String, date: String)
    fun onForecastWeatherFetchError(errorMessage: String)
}