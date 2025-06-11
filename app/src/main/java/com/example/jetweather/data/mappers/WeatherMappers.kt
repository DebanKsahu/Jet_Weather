package com.example.jetweather.data.mappers

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.jetweather.data.remote.WeatherDataDto
import com.example.jetweather.data.remote.WeatherDto
import com.example.jetweather.domain.weather.WeatherData
import com.example.jetweather.domain.weather.WeatherInfo
import com.example.jetweather.domain.weather.WeatherType
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

private data class IndexWeatherData(
    val index: Int,
    val data: WeatherData
)

@RequiresApi(Build.VERSION_CODES.O)
fun WeatherDataDto.toWeatherData(): Map<Int, List<WeatherData>> {
    return time.mapIndexed { index, time ->
        val temperature = temperatures[index]
        val pressure = pressures[index]
        val windSpeed = windSpeeds[index]
        val humidity = humidities[index]
        val weatherCode = weatherCodes[index]
        IndexWeatherData(
            index = index,
            data =  WeatherData(
                time = LocalDateTime.parse(time, DateTimeFormatter.ISO_DATE_TIME),
                temperature = temperature,
                pressure = pressure,
                windSpeed = windSpeed,
                humidity = humidity,
                weatherType = WeatherType.fromWMO(weatherCode)
            )
        )
    }.groupBy { it ->
        it.index / 24
    }.mapValues { it ->
        it.value.map {it -> it.data}
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun WeatherDto.toWeatherInfo(): WeatherInfo {
    val weatherDataMap = weatherData.toWeatherData()
    val now = LocalDateTime.now()
    val currentWeatherData = weatherDataMap[0]?.find { it ->
        val hour = if (now.minute<30) now.hour else now.hour+1
        it.time.hour==hour
    }
    return WeatherInfo(
        weatherDataPerDay = weatherDataMap,
        currentWeatherData = currentWeatherData
    )
}