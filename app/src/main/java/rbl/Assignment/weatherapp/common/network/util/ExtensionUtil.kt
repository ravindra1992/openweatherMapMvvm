package rbl.Assignment.weatherapp.common.network.util

import android.content.Context
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT

fun Context.displayToast(message: String, duration: Int = LENGTH_SHORT){
    Toast.makeText(this, message, duration).show()
}