package com.example.weather79.repository

import com.example.weather79.data.DataOrException
import com.example.weather79.model.Weather
import com.example.weather79.data.network.WeatherApi
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val api: WeatherApi) {

    suspend fun getWeather(cityQuery: String, units: String)
            : DataOrException<Weather, Boolean, Exception> {
        val response = try {
            api.getWeather(cityQuery, units)
        } catch (e: Exception) {
            return DataOrException(e = e)
        }
        return DataOrException(data = response)
    }

}