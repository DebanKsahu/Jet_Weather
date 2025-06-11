package com.example.jetweather.di

import com.example.jetweather.data.DefaultLocationTracker
import com.example.jetweather.data.repository.WeatherRepositoryImpl
import com.example.jetweather.domain.location.LocationTracker
import com.example.jetweather.domain.repository.WeatherRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindsWeatherRepository(weatherRepository: WeatherRepositoryImpl): WeatherRepository
}