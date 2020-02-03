package rbl.Assignment.weatherapp.weather.model.listener

interface IFetchCurrentWeatherListener {
    fun onCurrentWeatherFetchSuccess(currentTemp: String, currentCity: String)
    fun onCurrentWeatherFetchError(errorMessage: String)
}