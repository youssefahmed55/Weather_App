package com.weatherrrrrr.weatherapp.ui.restapi

import com.weatherrrrrr.weatherapp.BuildConfig
import com.weatherrrrrr.weatherapp.pojo.CurrentModelResponse
import com.weatherrrrrr.weatherapp.ui.Constants.units
import retrofit2.http.GET
import retrofit2.http.Query

interface CommonInterface {


    //Get Current Weather By City Name
    @GET("weather?units=$units&appid=${BuildConfig.API_KEY}")
    suspend fun getCurrentWeatherByNameRequest(@Query("q") cityName : String,@Query("lang") lang :String) : CurrentModelResponse

}