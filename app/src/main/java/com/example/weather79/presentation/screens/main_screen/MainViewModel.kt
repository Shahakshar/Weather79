package com.example.weather79.presentation.screens.main_screen

import androidx.lifecycle.ViewModel
import com.example.weather79.data.DataOrException
import com.example.weather79.model.Weather
import com.example.weather79.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: WeatherRepository): ViewModel() {

    suspend fun getWeather(city: String, units: String) : DataOrException<Weather, Boolean, Exception> {
        return repository.getWeather(cityQuery = city, units = units)
    }

}