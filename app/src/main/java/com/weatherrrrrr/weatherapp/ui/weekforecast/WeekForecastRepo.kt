package com.weatherrrrrr.weatherapp.ui.weekforecast


import com.weatherrrrrr.weatherapp.pojo.DaysModelResponse
import com.weatherrrrrr.weatherapp.ui.restapi.ApiInterface2
import com.weatherrrrrr.weatherapp.ui.room.DaysModelDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*
import javax.inject.Inject

class WeekForecastRepo @Inject constructor(private var apiInterface2 : ApiInterface2 , private var daysModelDao: DaysModelDao) {
    //Api//

    //Get Current Lat And Lon By City Name
    suspend fun getWeatherOfSevenDays(cityName : String) : DaysModelResponse = withContext(Dispatchers.IO){

        val current = async { apiInterface2.getCurrentWeatherByNameRequest(cityName,Locale.getDefault().language) }
        val lat = current.await().coord?.lat
        val lon = current.await().coord?.lon
        return@withContext apiInterface2.getWeatherOfSevenDaysRequest(lat!!,lon!!, Locale.getDefault().language)

    }


    //Room//

    //Get Days Model From DataBase
    suspend fun getDataBaseDaily() : DaysModelResponse = withContext(Dispatchers.IO){
        return@withContext daysModelDao.getAllDays()
    }

    //Save Days Model In DataBase
    suspend fun saveDataBaseDaily(days : DaysModelResponse) = withContext(Dispatchers.IO){
        launch { daysModelDao.deleteDays() }.join()
        launch { daysModelDao.insertAllDays(days) }
    }






}