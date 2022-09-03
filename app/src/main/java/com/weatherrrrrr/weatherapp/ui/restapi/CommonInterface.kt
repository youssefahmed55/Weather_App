package com.weatherrrrrr.weatherapp.ui.restapi

import com.weatherrrrrr.weatherapp.pojo.CurrentModelResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CommonInterface {
    companion object{
        private const val appId = "" //<-You Can Write appId Here
        private const val units = "metric"
    }

    //Get Current Weather By City Name
    @GET("weather?units=$units&appid=$appId")
    suspend fun getCurrentWeatherByNameRequest(@Query("q") cityName : String,@Query("lang") lang :String) : CurrentModelResponse

}