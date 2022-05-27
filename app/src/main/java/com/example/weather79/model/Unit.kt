package com.example.weather79.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "settings_table")
data class Unit(
    @NonNull
    @PrimaryKey(autoGenerate = false)
    val unit: String
)
