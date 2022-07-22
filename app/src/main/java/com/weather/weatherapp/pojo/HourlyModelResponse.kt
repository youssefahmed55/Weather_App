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
@Entity(tableName = "mytable2")
data class HourlyModelResponse(

	@PrimaryKey(autoGenerate = true)
	var modelId :Int? ,

	@field:SerializedName("timezone")
	val timezone: String? = null,

	@field:SerializedName("timezone_offset")
	val timezoneOffset: Long? = null,

	@field:SerializedName("lon")
	val lon: Double? = null,

	@field:SerializedName("hourly")
	val hourly: List<HourlyItem?>? = null,

	@field:SerializedName("lat")
	val lat: Double? = null
) : Parcelable


@Parcelize
data class HourlyItem(

	@field:SerializedName("temp")
	val temp: Double? = null,

	@field:SerializedName("visibility")
	val visibility: Int? = null,

	@field:SerializedName("uvi")
	val uvi: Double? = null,

	@field:SerializedName("pressure")
	val pressure: Int? = null,

	@field:SerializedName("clouds")
	val clouds: Int? = null,

	@field:SerializedName("feels_like")
	val feelsLike: Double? = null,

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

	@field:SerializedName("weather")
	val weather: List<WeatherItem?>? = null,

	@field:SerializedName("humidity")
	val humidity: Int? = null,

	@field:SerializedName("wind_speed")
	val windSpeed: Double? = null



) : Parcelable{
	fun getDateFormatAfterSplit() : List<String>{
		val date = Date(dt!!  * 1000 )
		val sfd = SimpleDateFormat("EEE, dd MMM-HH:mm aa", Locale.getDefault())
		val array = sfd.format(date).split("-")
		return array

	}

	fun getDateFromTimeStamp() : String {
		return getDateFormatAfterSplit()[0]
	}
	fun getTimeFromTimeStamp() : String {
		return getDateFormatAfterSplit()[1]
	}
}
