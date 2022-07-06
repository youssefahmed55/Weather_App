package com.example.weatherapp.ui.restapi

import com.example.weatherapp.pojo.CurrentModelResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CommonInterface {
    companion object{
        private const val appId = "bb9c8b4fcbcef448a15c0a10d751b0b3"
        private const val units = "metric"
    }

    //Get Current Weather By City Name
    @GET("weather?units=$units&appid=$appId")
    suspend fun getCurrentWeatherByNameRequest(@Query("q") cityName : String,@Query("lang") lang :String) : CurrentModelResponse

}