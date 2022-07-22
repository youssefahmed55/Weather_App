package com.weather.weatherapp.pojo

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.sql.Date
import java.text.SimpleDateFormat
import java.util.*

@Parcelize
@Entity(tableName = "mytable3")
data class DaysModelResponse(

	@PrimaryKey(autoGenerate = true)
	var modelId :Int? ,

	@field:SerializedName("timezone")
	val timezone: String? = null,

	@field:SerializedName("timezone_offset")
	val timezoneOffset: Int? = null,

	@field:SerializedName("daily")
	val daily: List<DailyItem?>? = null,

	@field:SerializedName("lon")
	val lon: Double? = null,

	@field:SerializedName("lat")
	val lat: Double? = null
) : Parcelable

@Parcelize
data class Temp(

	@field:SerializedName("min")
	val min: Double? = null,

	@field:SerializedName("max")
	val max: Double? = null,

	@field:SerializedName("eve")
	val eve: Double? = null,

	@field:SerializedName("night")
	val night: Double? = null,

	@field:SerializedName("day")
	val day: Double? = null,

	@field:SerializedName("morn")
	val morn: Double? = null
) : Parcelable

@Parcelize
data class FeelsLike(

	@field:SerializedName("eve")
	val eve: Double? = null,

	@field:SerializedName("night")
	val night: Double? = null,

	@field:SerializedName("day")
	val day: Double? = null,

	@field:SerializedName("morn")
	val morn: Double? = null
) : Parcelable


@Parcelize
data class DailyItem(

	@field:SerializedName("moonset")
	val moonset: Int? = null,

	@field:SerializedName("sunrise")
	val sunrise: Int? = null,

	@field:SerializedName("temp")
	val temp: Temp? = null,

	@field:SerializedName("moon_phase")
	val moonPhase: Double? = null,

	@field:SerializedName("uvi")
	val uvi: Double? = null,

	@field:SerializedName("moonrise")
	val moonrise: Int? = null,

	@field:SerializedName("pressure")
	val pressure: Int? = null,

	@field:SerializedName("clouds")
	val clouds: Int? = null,

	@field:SerializedName("feels_like")
	val feelsLike: FeelsLike? = null,

	@field:SerializedName("wind_gust")
	val windGust: Double? = null,

	@field:SerializedName("dt")
	val dt: Long? = null,

	@field:SerializedName("pop")
	val pop: Float? = null,

	@field:SerializedName("wind_deg")
	val windDeg: Int? = null,

	@field:SerializedName("dew_point")
	val dewPoint: Double? = null,

	@field:SerializedName("sunset")
	val sunset: Int? = null,

	@field:SerializedName("weather")
	val weather: List<WeatherItem?>? = null,

	@field:SerializedName("humidity")
	val humidity: Int? = null,

	@field:SerializedName("wind_speed")
	val windSpeed: Double? = null
) : Parcelable{
	fun getDayFromTimeStamp() : String {
		val date = Date(dt!!  * 1000 )
		val sfd = SimpleDateFormat("EEEE", Locale.getDefault())
		return sfd.format(date)
	}

}
