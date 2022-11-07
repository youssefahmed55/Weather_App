package com.weatherrrrrr.weatherapp.ui.restapi
import com.weatherrrrrr.weatherapp.BuildConfig
import com.weatherrrrrr.weatherapp.pojo.DaysModelResponse
import com.weatherrrrrr.weatherapp.ui.Constants.excludeDays
import com.weatherrrrrr.weatherapp.ui.Constants.units
import retrofit2.http.GET
import retrofit2.http.Query



interface ApiInterface2 : CommonInterface {


    //Get Weather Degree Of Next Week
    @GET("onecall?units=$units&appid=${BuildConfig.API_KEY}&exclude=$excludeDays")
    suspend fun getWeatherOfSevenDaysRequest(@Query("lat") lat : Double , @Query("lon") lon : Double,@Query("lang") lang :String) : DaysModelResponse


}