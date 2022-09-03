package com.weatherrrrrr.weatherapp.ui.restapi
import com.weatherrrrrr.weatherapp.pojo.DaysModelResponse
import retrofit2.http.GET
import retrofit2.http.Query



interface ApiInterface2 : CommonInterface {
    companion object{
        private const val appId = "" //<-You Can Write appId Here
        private const val units = "metric"
        private const val excludeDays = "hourly,minutely,current"
    }

    //Get Weather Degree Of Next Week
    @GET("onecall?units=$units&appid=$appId&exclude=$excludeDays")
    suspend fun getWeatherOfSevenDaysRequest(@Query("lat") lat : Double , @Query("lon") lon : Double,@Query("lang") lang :String) : DaysModelResponse


}