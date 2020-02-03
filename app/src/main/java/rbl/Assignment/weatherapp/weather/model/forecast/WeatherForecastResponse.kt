package rbl.Assignment.weatherapp.weather.model.forecast

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class WeatherForecastResponse {

    @SerializedName("cod")
    @Expose
    var cod: String? = null
    @SerializedName("message")
    @Expose
    var message: Double? = null
    @SerializedName("cnt")
    @Expose
    var cnt: Int? = null
    @SerializedName("list")
    @Expose
    var list: List<ListWeather>? = null
    @SerializedName("city")
    @Expose
    var city: City? = null

}
