package com.example.jetweather.di

import com.example.jetweather.data.DefaultLocationTracker
import com.example.jetweather.domain.location.LocationTracker
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class LocationModule {

    @Binds
    @Singleton
    abstract fun bindsLocationTracker(defaultLocationTracker: DefaultLocationTracker): LocationTracker
}