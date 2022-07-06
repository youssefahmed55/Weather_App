package com.example.retrofitwithcoroutines.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.weatherapp.ui.room.Converters
import com.example.weatherapp.pojo.CurrentModelResponse
import com.example.weatherapp.pojo.DaysModelResponse
import com.example.weatherapp.pojo.HourlyModelResponse


@Database(entities = [CurrentModelResponse::class,HourlyModelResponse::class,DaysModelResponse::class], version = 7, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun currentModelDao(): CurrentModelDao
    abstract fun hourlyModelDao(): HourlyModelDao
    abstract fun DaysModelDao(): DaysModelDao
}