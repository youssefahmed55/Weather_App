package com.example.weatherapp.ui.home


import com.example.weatherapp.pojo.CurrentModelResponse
import com.example.weatherapp.pojo.HourlyModelResponse

sealed class HomeViewStates{
    object Idle : HomeViewStates()
    data class ShowWeather(val currentModelResponse: CurrentModelResponse,val hourlyModelResponse: HourlyModelResponse) : HomeViewStates()
    data class Error(val error : String) : HomeViewStates()

}
