package com.weather.weatherapp.ui.restapi

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Provides
    @Singleton
    fun myApiInterface() : ApiInterface{
        return Retrofit.Builder().baseUrl("https://api.openweathermap.org/data/2.5/")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ApiInterface::class.java)
    }

    @Provides
    @Singleton
    fun myApiInterface2() : ApiInterface2{
        return Retrofit.Builder().baseUrl("https://api.openweathermap.org/data/2.5/")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ApiInterface2::class.java)
    }
}