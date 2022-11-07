package com.weatherrrrrr.weatherapp.ui.home



import com.weatherrrrrr.weatherapp.pojo.HourlyModelResponse

sealed class HomeViewStates{
    object Idle : HomeViewStates()
    data class ShowWeather(val countryName: String,val hourlyModelResponse: HourlyModelResponse) : HomeViewStates()
    data class Error(val error : String) : HomeViewStates()

}
