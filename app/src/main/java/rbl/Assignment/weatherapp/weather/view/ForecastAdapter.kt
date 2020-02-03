package rbl.Assignment.weatherapp.weather.view

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.list_forecastday.view.*
import rbl.Assignment.weatherapp.R
import rbl.Assignment.weatherapp.common.APIConstants.Companion.CELCIUS
import rbl.Assignment.weatherapp.common.DateUtils
import rbl.Assignment.weatherapp.weather.model.forecast.ListWeather
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*


open class ForecastAdapter(private val context: Context, private val forecastDays: MutableList<ListWeather>) :
    RecyclerView.Adapter<ForecastAdapter.ForecastViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ForecastViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.list_forecastday, parent, false)
        return ForecastViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return forecastDays.size
    }

    override fun onBindViewHolder(ForecastViewHolder: ForecastViewHolder, position: Int) {
        val forecastDays = forecastDays[position]
        ForecastViewHolder.bindData(forecastDays)

    }

    fun setData(forecastDaysList: List<ListWeather>) {
        for (i in 0..forecastDaysList.size) {

                forecastDays.add(i, forecastDaysList[i + 1])
                notifyDataSetChanged()

            }

    }
    inner class ForecastViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindData(forecastdays: ListWeather?) {
            forecastdays?.let {
                itemView.textView_forecast_temp.text = "Min/Max: "+it.main?.tempMin.toString().substringBefore(".")+" C"+"/"+ it.main?.tempMax.toString().substringBefore(".")

                itemView.textView_forecast_day_name.text = DateUtils.extractdayfromdate(it.dtTxt.toString())
                itemView.textView_forecast_temp_scale.text = CELCIUS
            }
        }
    }

    fun extractdayfromdate(datetime: String): String{

        val format = SimpleDateFormat("yyyy-MM-dd")
        val date: Date = format.parse(datetime)
        val format2: DateFormat = SimpleDateFormat("EEEE")
        val dayName = format2.format(date)

        return  dayName

    }

    companion object {
        val TAG: String = ForecastAdapter::class.java.simpleName
    }
}