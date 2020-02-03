package rbl.Assignment.weatherapp.weather.model.repository

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import rbl.Assignment.weatherapp.common.CommonUtils
import rbl.Assignment.weatherapp.common.DateUtils
import rbl.Assignment.weatherapp.weather.model.listener.IFetchCurrentWeatherListener
import rbl.Assignment.weatherapp.weather.model.listener.IFetchForecastWeatherListener
import rbl.Assignment.weatherapp.weather.model.service.IWeatherService
import rbl.Assignment.weatherapp.weather.model.service.ServiceGenerator
import rbl.Assignment.weatherapp.weather.view.ForecastAdapter

open class BaseRepository {

    companion object {

        private val TAG: String = BaseRepository::class.java.simpleName

        fun getCurrentWeatherData(listener: IFetchCurrentWeatherListener) {
            val weatherService = ServiceGenerator.createService(IWeatherService::class.java)
            weatherService.getCurrentWeather(
                CommonUtils.getLat(),
                CommonUtils.getLon(),
                CommonUtils.getWeatherTempScale(),
                CommonUtils.getWeatherAPIKey()
            )
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    listener.onCurrentWeatherFetchSuccess(
                        it.main?.temp.toString(),
                        it.name!!
                    )
                }, {
                    listener.onCurrentWeatherFetchError(it.message.toString())
                })!!
        }

        fun getForecastWeatherData(adapter: ForecastAdapter, listener: IFetchForecastWeatherListener) {
            val weatherService = ServiceGenerator.createService(IWeatherService::class.java)
            weatherService.getForecastWeather(
                CommonUtils.getLat(),
                CommonUtils.getLon(),
                CommonUtils.getWeatherTempScale(),
                CommonUtils.getWeatherAPIKey()
            )
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    it.list?.forEach { listener.onForecastWeatherFetchSuccess(it.main?.temp.toString() , DateUtils.extractdayfromdate(it.dtTxt.toString())) }
                    adapter.setData(it.list!!)
                }, {
                    listener.onForecastWeatherFetchError(it.message.toString())
                })!!
        }


    }
}