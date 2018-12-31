package com.tuhin.forecastmvvm.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.tuhin.forecastmvvm.data.db.entity.CurrentWeatherEntry

@Database(
    entities = [CurrentWeatherEntry::class],
    version = 1
)
abstract class ForecastWeatherDatabase :RoomDatabase() {

    abstract fun currentWeatherDao() : CurrentWeatherDao

    companion object {
        @Volatile private var instance : ForecastWeatherDatabase? = null
        private val LOCK = Any()

        operator fun invoke (context : Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context : Context) =
                Room.databaseBuilder(context.applicationContext,
                    ForecastWeatherDatabase::class.java, "forecast.db")
                    .build()
    }
}