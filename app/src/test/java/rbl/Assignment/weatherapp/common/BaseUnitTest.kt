package rbl.Assignment.weatherapp.common

import android.content.Context
import org.mockito.Mock
import org.mockito.Mockito
import rbl.Assignment.weatherapp.weather.model.listener.IFetchCurrentWeatherListener
import rbl.Assignment.weatherapp.weather.model.listener.IFetchForecastWeatherListener
import rbl.Assignment.weatherapp.weather.view.ForecastAdapter
import rbl.Assignment.weatherapp.weather.viewmodel.WeatherViewModel

open class BaseUnitTest {
    @Mock
    var mContext: Context = Mockito.mock(Context::class.java)
    val mWeatherViewModel: WeatherViewModel? = null
    var mCurrentWeatherListener: IFetchCurrentWeatherListener? = null
    var mForecastWeatherListener: IFetchForecastWeatherListener? = null
    var mForecastAdapter: ForecastAdapter?= null
}