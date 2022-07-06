package com.example.retrofitwithcoroutines.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.weatherapp.pojo.DaysModelResponse



@Dao
interface DaysModelDao {
    //Get Days Model From DataBase
    @Query("SELECT * FROM mytable3")
    suspend fun getAllDays(): DaysModelResponse
    //Insert Days Model In DataBase
    @Insert
    suspend fun insertAllDays(days: DaysModelResponse)
    //Delete All Data From Table
    @Query("DELETE FROM mytable3")
    suspend fun deleteDays()


}