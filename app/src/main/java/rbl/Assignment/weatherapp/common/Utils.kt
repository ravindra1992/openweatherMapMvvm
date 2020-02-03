package rbl.Assignment.weatherapp.common

import android.content.Context
import android.preference.PreferenceManager

internal object Utils {

    val KEY_LATTITUDE = "lattitude"
    val KEY_LOGNITUDE = "lognitude"


    fun setLattitude(context: Context, lattitude: String){
        PreferenceManager.getDefaultSharedPreferences(context)
            .edit()
            .putString(
                KEY_LATTITUDE, lattitude
            )
            .apply()
    }


    fun setLognitude(context: Context, lognitude: String){
        PreferenceManager.getDefaultSharedPreferences(context)
            .edit()
            .putString(
                KEY_LOGNITUDE, lognitude
            )
            .apply()
    }


    fun getLattitude(context: Context): String? {
        var lat:String = PreferenceManager.getDefaultSharedPreferences(context)
            .getString(KEY_LATTITUDE, "")
        if(lat.isEmpty())
        {
            return "28.6624"
        }
        return lat
    }

    fun getLognitude(context: Context): String? {
        var lon:String = PreferenceManager.getDefaultSharedPreferences(context)
            .getString(KEY_LOGNITUDE, "")
        if(lon.isEmpty())
        {
            return "77.3734"
        }
        return lon
    }


}