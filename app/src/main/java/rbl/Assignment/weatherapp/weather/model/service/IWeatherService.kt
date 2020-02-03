package rbl.Assignment.weatherapp.weather.model.service

import io.reactivex.Flowable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import rbl.Assignment.weatherapp.weather.model.current.WeatherCurrentResponse
import rbl.Assignment.weatherapp.weather.model.forecast.WeatherForecastResponse

interface IWeatherService {

    @GET("weather")
    fun getCurrentWeather(
        @Query("lat") lat: String?,
        @Query("lon") lon: String?,
        @Query("units") units: String,
        @Query("APPID") app_id: String
    ): Single<WeatherCurrentResponse>

    @GET("forecast")
    fun getForecastWeather(
        @Query("lat") lat: String?,
        @Query("lon") lon: String?,
        @Query("units") units: String,
        @Query("APPID") app_id: String
    ): Flowable<WeatherForecastResponse>



}