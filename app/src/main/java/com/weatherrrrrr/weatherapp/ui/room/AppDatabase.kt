package com.weatherrrrrr.weatherapp.ui.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters


import com.weatherrrrrr.weatherapp.pojo.CurrentModelResponse
import com.weatherrrrrr.weatherapp.pojo.DaysModelResponse
import com.weatherrrrrr.weatherapp.pojo.HourlyModelResponse


@Database(entities = [CurrentModelResponse::class,HourlyModelResponse::class,DaysModelResponse::class], version = 7, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun currentModelDao(): CurrentModelDao
    abstract fun hourlyModelDao(): HourlyModelDao
    abstract fun DaysModelDao(): DaysModelDao
}