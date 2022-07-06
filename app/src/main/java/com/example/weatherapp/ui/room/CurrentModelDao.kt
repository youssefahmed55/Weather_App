package com.example.retrofitwithcoroutines.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.weatherapp.pojo.CurrentModelResponse


@Dao
interface CurrentModelDao {
    //Get Current Model From DataBase
    @Query("SELECT * FROM mytable1")
    suspend fun getAllCurrent(): CurrentModelResponse

    //Insert Current Model In DataBase
    @Insert
    suspend fun insertAllCurrent(current : CurrentModelResponse)

    //Delete All Data From Table
    @Query("DELETE FROM mytable1")
    suspend fun deleteAllCurrent()



}