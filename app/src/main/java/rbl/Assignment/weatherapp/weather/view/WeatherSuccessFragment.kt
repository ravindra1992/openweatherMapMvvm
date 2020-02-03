package rbl.Assignment.weatherapp.weather.view


import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.fragment_weather_success.*
import kotlinx.android.synthetic.main.fragment_weather_success.view.*
import rbl.Assignment.weatherapp.R
import rbl.Assignment.weatherapp.weather.model.forecast.ListWeather
import rbl.Assignment.weatherapp.weather.model.listener.IFetchCurrentWeatherListener
import rbl.Assignment.weatherapp.weather.model.listener.IFetchForecastWeatherListener
import rbl.Assignment.weatherapp.weather.viewmodel.WeatherViewModel

class WeatherSuccessFragment : Fragment(), IFetchCurrentWeatherListener, IFetchForecastWeatherListener {

    private var mContext: Context? = null
    private var mWeatherViewModel: WeatherViewModel? = null
    private var mForecastAdapter: ForecastAdapter? = null
    private var mForecastDays: MutableList<ListWeather> = mutableListOf()

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        this.mContext = context
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_weather_success_container, container, false)
        initViews(view)
        initWeatherViewModel()
        makeRequestForCurrentWeather()
        setAdapter(view)
        makeRequestForForecastWeather(view)
        return view
    }

    private fun initViews(v: View) {
        val linearLayoutManager = LinearLayoutManager(v.context)
        v.recyclerView_weather_forecast.addItemDecoration(
            DividerItemDecoration(
                v.context,
                DividerItemDecoration.VERTICAL
            )
        )
        v.recyclerView_weather_forecast.layoutManager = linearLayoutManager
    }

    private fun initWeatherViewModel() {
        mWeatherViewModel = ViewModelProviders.of(this@WeatherSuccessFragment).get(WeatherViewModel::class.java)
    }

    private fun makeRequestForCurrentWeather() {
        mWeatherViewModel!!.getCurrentTempAndLocation(this)
    }

    private fun makeRequestForForecastWeather(v: View) {
        showProgress(v)
        mWeatherViewModel!!.getWeatherForecast(mForecastAdapter!!, this)
    }


    private fun setAdapter(v: View) {
        mForecastAdapter = ForecastAdapter(mContext!!, mForecastDays)
        v.recyclerView_weather_forecast.adapter = mForecastAdapter
    }

    private fun showProgress(v: View) {
        v.imageView_loading.visibility = View.VISIBLE
        animateProgressImage(v)
    }

    private fun hideProgress(v: View) {
        clearProgressImageAnimation(v)
        v.imageView_loading.visibility = View.GONE
        startSlideUpAnimation(v)
    }

    private fun animateProgressImage(v: View) {
        val rotateClockWise = AnimationUtils.loadAnimation(v.imageView_loading.context, R.anim.rotate_clockwise)
        v.imageView_loading.startAnimation(rotateClockWise)
    }

    private fun clearProgressImageAnimation(v: View) {
        v.imageView_loading.clearAnimation()
    }

    private fun startSlideUpAnimation(v: View) {
        val slideUpAnimation = AnimationUtils.loadAnimation(
            v.context,
            R.anim.slide_up
        )
        v.recyclerView_weather_forecast.startAnimation(slideUpAnimation)
    }

    override fun onCurrentWeatherFetchSuccess(currentTemp: String, currentCity: String) {
        Log.i(TAG, "current:: temp:: $currentTemp")
        Log.i(TAG, "current:: city:: $currentCity")
        if (currentTemp.isNullOrEmpty() || currentCity.isNullOrEmpty()) {
            //Should be handled based on the requirements
        } else {
             view?.textView_current_temp?.text=currentTemp.plus(getString(R.string.degree))
             view?.textView_current_city?.text=currentCity
        }
        if (view != null) {
            hideProgress(view!!)
        }
    }

    override fun onCurrentWeatherFetchError(errorMessage: String) {
        Log.i(TAG, "current: errorMessage:: $errorMessage")
        if (view != null) {
            hideProgress(view!!)
        }
    }

    override fun onForecastWeatherFetchSuccess(temp: String, date: String) {
        Log.i(TAG, "forecast:: temp:: $temp")
        Log.i(TAG, "forecast:: city:: $date")
        if (view != null) {
            hideProgress(view!!)
        }
    }

    override fun onForecastWeatherFetchError(errorMessage: String) {
        Log.i(TAG, "forecast: errorMessage:: $errorMessage")
        if (view != null) {
            hideProgress(view!!)
        }
    }

    companion object {
        val TAG: String = WeatherSuccessFragment::class.java.simpleName
    }

}
