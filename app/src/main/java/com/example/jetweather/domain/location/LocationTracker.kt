package com.example.jetweather.domain.location

import android.location.Location

interface LocationTracker {
    suspend fun getCurrentLocation(): Location?
}