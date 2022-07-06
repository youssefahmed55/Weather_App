package com.example.retrofitwithcoroutines.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.weatherapp.pojo.HourlyModelResponse


@Dao
interface HourlyModelDao {
    //Get Days Model From DataBase
    @Query("SELECT * FROM mytable2")
    suspend fun getAllHourly(): HourlyModelResponse
    //Insert Days Model In DataBase
    @Insert
    suspend fun insertAllHourly(hourly: HourlyModelResponse)
    //Delete All Data From Table
    @Query("DELETE FROM mytable2")
    suspend fun deleteHourly()


}