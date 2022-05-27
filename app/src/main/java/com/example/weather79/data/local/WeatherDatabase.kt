package com.example.weather79.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.weather79.model.Favorite
import com.example.weather79.model.Unit

@Database(entities = [Favorite::class, Unit::class], version = 2, exportSchema = false)
abstract class WeatherDatabase : RoomDatabase() {
    abstract fun weatherDao(): WeatherDao
}