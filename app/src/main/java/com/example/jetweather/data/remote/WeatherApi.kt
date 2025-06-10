package com.example.jetweather.data.remote

interface WeatherApi {
    @GET()
    suspend fun getWeatherData(
        @Query(value = "longitude") long: Double,
        @Query(value = "latitude") lat: Double
    )
}