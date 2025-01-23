package com.example.weatherapp.network

import com.example.weatherapp.data.CurrentWeather
import com.example.weatherapp.data.ForecastWeather
import retrofit2.http.GET
import retrofit2.http.Url


interface WeatherApiService {
    @GET()
    suspend fun getCurrentWeather(@Url endUrl: String): CurrentWeather

    @GET()
    suspend fun getForecastWeather(@Url endUrl: String): ForecastWeather
}


object WeatherApi {
    val retrofitService: WeatherApiService by lazy {
        retrofit.create(WeatherApiService::class.java)
    }
}