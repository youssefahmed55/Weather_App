package com.weatherrrrrr.weatherapp.ui.home

sealed class HomeIntent{
    data class GetWeather(val cityName:String) : HomeIntent()
}
