package com.example.weatherapp.ui.restapi
import com.example.weatherapp.pojo.DaysModelResponse
import retrofit2.http.GET
import retrofit2.http.Query



interface ApiInterface2 : CommonInterface {
    companion object{
        private const val appId = "bb9c8b4fcbcef448a15c0a10d751b0b3"
        private const val units = "metric"
        private const val excludeDays = "hourly,minutely,current"
    }

    //Get Weather Degree Of Next Week
    @GET("onecall?units=$units&appid=$appId&exclude=$excludeDays")
    suspend fun getWeatherOfSevenDaysRequest(@Query("lat") lat : Double , @Query("lon") lon : Double,@Query("lang") lang :String) : DaysModelResponse


}