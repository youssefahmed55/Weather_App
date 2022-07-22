package com.weatherrrrrr.weatherapp.ui.room

import androidx.room.TypeConverter
import com.weatherrrrrr.weatherapp.pojo.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    @TypeConverter
    fun fromMainToString(value: Main?): String? {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun fromStringToMain(s: String?): Main? {
        return Gson().fromJson<Main>(s, object : TypeToken<Main?>() {}.type
        )
    }

    @TypeConverter
    fun fromCloudsToString(value: Clouds?): String? {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun fromStringToClouds(s: String?): Clouds? {
        return Gson().fromJson<Clouds>(s, object : TypeToken<Clouds?>() {}.type
        )
    }

    @TypeConverter
    fun fromSysToString(value: Sys?): String? {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun fromStringToSys(s: String?): Sys? {
        return Gson().fromJson<Sys>(s, object : TypeToken<Sys?>() {}.type
        )
    }

    @TypeConverter
    fun fromCoordToString(value: Coord?): String? {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun fromStringToCoord(s: String?): Coord? {
        return Gson().fromJson<Coord>(s, object : TypeToken<Coord?>() {}.type
        )
    }

    //
    @TypeConverter
    fun fromListWeatherItemToString(value: List<WeatherItem?>?): String? {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun fromStringToListWeatherItem(s: String?): List<WeatherItem?>? {
        return Gson().fromJson<List<WeatherItem?>>(s, object : TypeToken<List<WeatherItem?>?>() {}.type
        )
    }
    //
    @TypeConverter
    fun fromWindToString(value: Wind?): String? {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun fromStringToWind(s: String?): Wind? {
        return Gson().fromJson<Wind>(s, object : TypeToken<Wind?>() {}.type
        )
    }
    //
    @TypeConverter
    fun fromListHourlyItemToString(value: List<HourlyItem?>?): String? {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun fromStringToListHourlyItem(s: String?): List<HourlyItem?>? {
        return Gson().fromJson<List<HourlyItem?>>(s, object : TypeToken<List<HourlyItem?>?>() {}.type
        )
    }
   //
   @TypeConverter
   fun fromListDailyItemToString(value: List<DailyItem?>?): String? {
       return Gson().toJson(value)
   }

    @TypeConverter
    fun fromStringToListDailyItem(s: String?): List<DailyItem?>? {
        return Gson().fromJson<List<DailyItem?>>(s, object : TypeToken<List<DailyItem?>?>() {}.type
        )
    }
    //
    @TypeConverter
    fun fromTempToString(value:Temp?): String? {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun fromStringToTemp(s: String?): Temp? {
        return Gson().fromJson<Temp>(s, object : TypeToken<Temp?>() {}.type
        )
    }

    //
    @TypeConverter
    fun fromFeelsLikeToString(value:FeelsLike?): String? {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun fromStringToFeelsLike(s: String?): FeelsLike? {
        return Gson().fromJson<FeelsLike>(s, object : TypeToken<FeelsLike?>() {}.type
        )
    }
}