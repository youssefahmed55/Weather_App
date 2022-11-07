package com.weatherrrrrr.weatherapp.ui.home


import com.weatherrrrrr.weatherapp.pojo.CurrentModelResponse
import com.weatherrrrrr.weatherapp.pojo.HourlyModelResponse
import com.weatherrrrrr.weatherapp.ui.restapi.ApiInterface
import com.weatherrrrrr.weatherapp.ui.room.CurrentModelDao
import com.weatherrrrrr.weatherapp.ui.room.HourlyModelDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class HomeRepo @Inject constructor(private var apiInterface : ApiInterface, private var currentModelDao: CurrentModelDao, private var hourlyModelDao: HourlyModelDao) {
    //Api//

    //Get Current City Name And Weather
    suspend fun getCurrentCityNameAndWeather (lat : Double,lon : Double,lang:String) : CurrentModelResponse = withContext(Dispatchers.IO){
        return@withContext apiInterface.getCurrentCityNameAndWeatherRequest(lat,lon,lang)
    }

    //Get Current Weather By Name
    suspend fun getCurrentWeatherByName(cityName : String,lang:String) : CurrentModelResponse = withContext(Dispatchers.IO){
        return@withContext apiInterface.getCurrentWeatherByNameRequest(cityName,lang)
    }


    //Get Weather Hourly
    suspend fun getWeatherHourly(lat : Double,lon : Double,lang:String) : HourlyModelResponse= withContext(Dispatchers.IO){
        return@withContext apiInterface.getWeatherHourlyRequest(lat,lon,lang)
    }

    //Room//

    //Get Current Model From DataBase
    suspend fun getDataBaseCurrent() : CurrentModelResponse = withContext(Dispatchers.IO){
        return@withContext currentModelDao.getAllCurrent()
    }
    //Save Current Model In DataBase
    suspend fun saveDataBaseCurrent(current : CurrentModelResponse) {
        withContext(Dispatchers.IO){
            launch { currentModelDao.deleteAllCurrent() }.join()
            launch { currentModelDao.insertAllCurrent(current) }
        }
    }

    //Get Hourly Model From DataBase
    suspend fun getDataBaseHourly() : HourlyModelResponse = withContext(Dispatchers.IO){
        return@withContext hourlyModelDao.getAllHourly()
    }

    //Save Hourly Model In DataBase
    suspend fun saveDataBaseHourly(hourly: HourlyModelResponse) {
        withContext(Dispatchers.IO){
            launch { hourlyModelDao.deleteHourly() }.join()
            launch { hourlyModelDao.insertAllHourly(hourly) }
        }

    }






}