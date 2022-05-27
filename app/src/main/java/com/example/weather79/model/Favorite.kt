package com.example.weather79.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "fav_table")
data class Favorite(
    @NonNull
    @PrimaryKey(autoGenerate = false)
    val city: String,
    val country: String,

)
