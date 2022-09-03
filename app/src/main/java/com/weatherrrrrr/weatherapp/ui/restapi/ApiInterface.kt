package com.weatherrrrrr.weatherapp.ui.restapi
import com.weatherrrrrr.weatherapp.pojo.CurrentModelResponse
import com.weatherrrrrr.weatherapp.pojo.HourlyModelResponse
import retrofit2.http.GET
import retrofit2.http.Query



interface ApiInterface : CommonInterface {
    companion object{
        private const val appId = "" //<-You Can Write appId Here
        private const val units = "metric"
        private const val excludeHourly = "daily,minutely,current,alerts"
    }



    //Get Current City Name And Weather
    @GET("weather?units=$units&appid=$appId")
    suspend fun getCurrentCityNameAndWeatherRequest(@Query("lat") lat : Double, @Query("lon") lon : Double, @Query("lang") lang :String) : CurrentModelResponse

    //Get Weather Degree Hourly
    @GET("onecall?units=$units&appid=$appId&exclude=$excludeHourly")
    suspend fun getWeatherHourlyRequest(@Query("lat") lat : Double , @Query("lon") lon : Double,@Query("lang") lang :String) : HourlyModelResponse

}