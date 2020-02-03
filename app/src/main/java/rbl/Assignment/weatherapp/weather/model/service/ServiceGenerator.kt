package rbl.Assignment.weatherapp.weather.model.service

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import rbl.Assignment.weatherapp.BuildConfig
import rbl.Assignment.weatherapp.common.CommonUtils

class ServiceGenerator {

    companion object {

        @Volatile
        private var INSTANCE: Retrofit? = null

        private val loggingInterceptor = HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY)
        private val okHttpClientBuilder = OkHttpClient.Builder()

        private fun getInstance(): Retrofit? {
            if (shouldLog()) {
                okHttpClientBuilder.addInterceptor(loggingInterceptor)
            }
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildRetrofit().also { INSTANCE = it }
            }
        }

        private fun shouldLog(): Boolean {
            return BuildConfig.DEBUG
        }

        private fun buildRetrofit() =
                Retrofit.Builder()
                        .baseUrl(CommonUtils.getWeatherBaseURL())
                        .client(okHttpClientBuilder.build())
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()

        fun <S> createService(serviceClass: Class<S>): S {
            return getInstance()!!.create(serviceClass)
        }
    }
}