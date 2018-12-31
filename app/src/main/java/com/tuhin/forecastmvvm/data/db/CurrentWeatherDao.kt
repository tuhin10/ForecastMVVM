package com.tuhin.forecastmvvm.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tuhin.forecastmvvm.data.db.entity.CURRENT_WEATHER_ID
import com.tuhin.forecastmvvm.data.db.entity.CurrentWeatherEntry
import com.tuhin.forecastmvvm.data.db.unitlocalized.ImperialCurrentWeatherEntry
import com.tuhin.forecastmvvm.data.db.unitlocalized.MetricCurrentWeatherEntry

@Dao
interface CurrentWeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(currentWeatherEntry : CurrentWeatherEntry)

    @Query("select * from current_weather where id = $CURRENT_WEATHER_ID")
    fun getWeatherMetric() : LiveData<MetricCurrentWeatherEntry>

    @Query("select * from current_weather where id = $CURRENT_WEATHER_ID")
    fun getWeatherImperial() : LiveData<ImperialCurrentWeatherEntry>
}