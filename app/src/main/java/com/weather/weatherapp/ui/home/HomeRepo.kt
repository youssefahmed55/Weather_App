package com.weather.weatherapp.ui.home

import com.weather.retrofitwithcoroutines.room.CurrentModelDao
import com.weather.retrofitwithcoroutines.room.HourlyModelDao
import com.weather.weatherapp.pojo.CurrentModelResponse
import com.weather.weatherapp.pojo.HourlyModelResponse
import com.weather.weatherapp.ui.restapi.ApiInterface
import javax.inject.Inject

class HomeRepo @Inject constructor(private var apiInterface : ApiInterface , private var currentModelDao: CurrentModelDao , private var hourlyModelDao: HourlyModelDao) {
    //Api//

    //Get Current City Name And Weather
    suspend fun getCurrentCityNameAndWeather (lat : Double,lon : Double,lang:String) : CurrentModelResponse{
        return apiInterface.getCurrentCityNameAndWeatherRequest(lat,lon,lang)
    }

    //Get Current Weather By Name
    suspend fun getCurrentWeatherByName(cityName : String,lang:String) : CurrentModelResponse{
        return apiInterface.getCurrentWeatherByNameRequest(cityName,lang)
    }


    //Get Weather Hourly
    suspend fun getWeatherHourly(lat : Double,lon : Double,lang:String) : HourlyModelResponse{
        return apiInterface.getWeatherHourlyRequest(lat,lon,lang)
    }

    //Room//

    //Get Current Model From DataBase
    suspend fun getDataBaseCurrent() : CurrentModelResponse{
        return currentModelDao.getAllCurrent()
    }
    //Save Current Model In DataBase
    suspend fun saveDataBaseCurrent(current : CurrentModelResponse) {
        currentModelDao.insertAllCurrent(current)
    }
    //Delete All Data In table
    suspend fun deleteDataBaseCurrent() {
        currentModelDao.deleteAllCurrent()
    }

    //Get Hourly Model From DataBase
    suspend fun getDataBaseHourly() : HourlyModelResponse{
        return hourlyModelDao.getAllHourly()
    }

    //Save Hourly Model In DataBase
    suspend fun saveDataBaseHourly(hourly: HourlyModelResponse) {
        hourlyModelDao.insertAllHourly(hourly)
    }

    //Delete All Data In table
    suspend fun deleteDataBaseHourly() {
        hourlyModelDao.deleteHourly()
    }





}