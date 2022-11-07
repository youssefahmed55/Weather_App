package com.weatherrrrrr.weatherapp.ui.restapi
import com.weatherrrrrr.weatherapp.BuildConfig
import com.weatherrrrrr.weatherapp.pojo.CurrentModelResponse
import com.weatherrrrrr.weatherapp.pojo.HourlyModelResponse
import com.weatherrrrrr.weatherapp.ui.Constants.excludeHourly
import com.weatherrrrrr.weatherapp.ui.Constants.units
import retrofit2.http.GET
import retrofit2.http.Query



interface ApiInterface : CommonInterface {

    //Get Current City Name And Weather
    @GET("weather?units=$units&appid=${BuildConfig.API_KEY}")
    suspend fun getCurrentCityNameAndWeatherRequest(@Query("lat") lat : Double, @Query("lon") lon : Double, @Query("lang") lang :String) : CurrentModelResponse

    //Get Weather Degree Hourly
    @GET("onecall?units=$units&appid=${BuildConfig.API_KEY}&exclude=$excludeHourly")
    suspend fun getWeatherHourlyRequest(@Query("lat") lat : Double , @Query("lon") lon : Double,@Query("lang") lang :String) : HourlyModelResponse

}