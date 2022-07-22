package com.weather.weatherapp.ui.room

import android.app.Application
import androidx.room.Room
import com.weather.retrofitwithcoroutines.room.AppDatabase
import com.weather.retrofitwithcoroutines.room.CurrentModelDao
import com.weather.retrofitwithcoroutines.room.DaysModelDao
import com.weather.retrofitwithcoroutines.room.HourlyModelDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MyDataBaseModule {

    @Provides
    @Singleton
    @Synchronized
    fun provideDatabase(application: Application?): AppDatabase {
        return Room.databaseBuilder(application!!, AppDatabase::class.java, "myDataBase")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    @Synchronized
    fun provideCurrentDao(myDatabase: AppDatabase): CurrentModelDao {
        return myDatabase.currentModelDao()
    }
    @Provides
    @Singleton
    @Synchronized
    fun provideHourlyDao(myDatabase: AppDatabase): HourlyModelDao {
        return myDatabase.hourlyModelDao()
    }

    @Provides
    @Singleton
    @Synchronized
    fun provideDaysDao(myDatabase: AppDatabase): DaysModelDao {
        return myDatabase.DaysModelDao()
    }
}