package com.weather.weatherapp.ui.home


import com.weather.weatherapp.pojo.CurrentModelResponse
import com.weather.weatherapp.pojo.HourlyModelResponse

sealed class HomeViewStates{
    object Idle : HomeViewStates()
    data class ShowWeather(val currentModelResponse: CurrentModelResponse,val hourlyModelResponse: HourlyModelResponse) : HomeViewStates()
    data class Error(val error : String) : HomeViewStates()

}
