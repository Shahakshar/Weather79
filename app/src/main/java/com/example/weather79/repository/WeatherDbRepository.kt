package com.example.weather79.repository

import com.example.weather79.data.local.WeatherDao
import com.example.weather79.model.Favorite
import com.example.weather79.model.Unit
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WeatherDbRepository @Inject constructor(
    private val weatherDao: WeatherDao
) {

    fun getFavorites(): Flow<List<Favorite>> {
        return weatherDao.getFavorites()
    }

    suspend fun insertFavorite(favorite: Favorite) {
        return weatherDao.insertFavorite(favorite)
    }

    suspend fun updateFavorite(favorite: Favorite) {
        return weatherDao.updateFavorite(favorite)
    }

    suspend fun deleteAllFavorites() = weatherDao.deleteAllFavorites()

    suspend fun deleteFavorite(favorite: Favorite) = weatherDao.deleteFavorite(favorite)

    suspend fun getFavById(city: String) = weatherDao.getFavById(city)

    fun getUnits(): Flow<List<Unit>> {
        return weatherDao.getUnits()
    }

    suspend fun insertUnit(unit: Unit) {
        return weatherDao.insertUnit(unit)
    }

    suspend fun updateUnit(unit: Unit) {
        return weatherDao.updateUnit(unit)
    }

    suspend fun deleteUnit(unit: Unit) {
        return weatherDao.deleteUnit(unit)
    }

    suspend fun deleteAllUnits() {
        return weatherDao.deleteAllUnits()
    }
}