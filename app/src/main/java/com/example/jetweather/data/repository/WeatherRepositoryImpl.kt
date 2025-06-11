package com.example.jetweather.data.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.jetweather.data.mappers.toWeatherInfo
import com.example.jetweather.data.remote.WeatherApi
import com.example.jetweather.domain.repository.WeatherRepository
import com.example.jetweather.domain.util.Resource
import com.example.jetweather.domain.weather.WeatherInfo
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val api: WeatherApi
): WeatherRepository {

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getWeatherData(
        long: Double,
        lat: Double
    ): Resource<WeatherInfo> {
        return try {
            Resource.Success(
                data = api.getWeatherData(
                    long = long,
                    lat = lat
                ).toWeatherInfo()
            )
        } catch(e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "An unknown error")
        }
    }
}