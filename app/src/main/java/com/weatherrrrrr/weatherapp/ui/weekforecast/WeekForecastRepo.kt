package com.weatherrrrrr.weatherapp.ui.weekforecast

import com.weatherrrrrr.retrofitwithcoroutines.room.DaysModelDao
import com.weatherrrrrr.weatherapp.pojo.CurrentModelResponse
import com.weatherrrrrr.weatherapp.pojo.DaysModelResponse
import com.weatherrrrrr.weatherapp.ui.restapi.ApiInterface2
import javax.inject.Inject

class WeekForecastRepo @Inject constructor(private var apiInterface2 : ApiInterface2 , private var daysModelDao: DaysModelDao) {
    //Api//

    //Get Weather Degree Of Next Week
    suspend fun getWeatherOfSevenDays (lat : Double,lon : Double,lang:String) : DaysModelResponse {
        return apiInterface2.getWeatherOfSevenDaysRequest(lat,lon,lang)
    }

    //Get Current Lat And Lon By City Name
    suspend fun getCurrentLatAndLonFromCityName(cityName : String,lang:String) : CurrentModelResponse{
        return apiInterface2.getCurrentWeatherByNameRequest(cityName,lang)
    }


    //Room//

    //Get Days Model From DataBase
    suspend fun getDataBaseDaily() : DaysModelResponse{
        return daysModelDao.getAllDays()
    }

    //Save Days Model In DataBase
    suspend fun saveDataBaseDaily(days : DaysModelResponse) {
        daysModelDao.insertAllDays(days)
    }

    //Delete All Data From Table
    suspend fun deleteDataBaseDaily() {
        daysModelDao.deleteDays()
    }





}