package com.tuhin.forecastmvvm.data.network.response

import com.google.gson.annotations.SerializedName
import com.tuhin.forecastmvvm.data.db.entity.CurrentWeatherEntry
import com.tuhin.forecastmvvm.data.db.entity.Location

data class CurrentWeatherResponse(
    @SerializedName("current")
    val currentWeatherEntry: CurrentWeatherEntry,
    val location: Location
)