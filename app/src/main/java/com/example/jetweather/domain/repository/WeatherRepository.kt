package com.example.jetweather.domain.repository

import com.example.jetweather.domain.util.Resource
import com.example.jetweather.domain.weather.WeatherData
import com.example.jetweather.domain.weather.WeatherInfo

interface WeatherRepository {
    suspend fun getWeatherData(long: Double, lat: Double): Resource<WeatherInfo>
}