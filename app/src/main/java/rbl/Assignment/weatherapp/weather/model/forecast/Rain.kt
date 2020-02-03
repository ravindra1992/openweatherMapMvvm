package rbl.Assignment.weatherapp.weather.model.forecast

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Rain {

    @SerializedName("3h")
    @Expose
    var rain:Double? = null

}
