package com.example.weatherapp.ui.weekforecast


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.pojo.DaysModelResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import java.util.*
import javax.inject.Inject

@HiltViewModel
class WeekForecastViewModel @Inject constructor(private var weekForecastRepo: WeekForecastRepo) : ViewModel() {

    private val _statusDaysChannel = Channel<DaysModelResponse>(Channel.UNLIMITED)
    val statesDays : Channel<DaysModelResponse> get() = _statusDaysChannel

    private val _MutableStateFlowError = MutableStateFlow<String>("")
    val statesError : MutableStateFlow<String> get() = _MutableStateFlowError

    val handler = CoroutineExceptionHandler() { coroutinesContexts , throwable -> _MutableStateFlowError.value = throwable.message!!}

     fun getWeatherOfSevenDays(cityName : String){
        viewModelScope.launch(handler + Dispatchers.IO) {
                val current = async { weekForecastRepo.getCurrentLatAndLonFromCityName(cityName,Locale.getDefault().language) }
                val days = async() { current.await().coord?.lon?.let { current.await().coord?.lat?.let { it1 -> weekForecastRepo.getWeatherOfSevenDays(it1, it,Locale.getDefault().language) } } }
                val daysResult = days.await()
                statesDays.send(daysResult!!)
         }
    }


    fun deleteAndInsertD(daysModelResponse: DaysModelResponse?){
        viewModelScope.launch(handler + Dispatchers.IO) {
            if (daysModelResponse != null) {
                launch { weekForecastRepo.deleteDataBaseDaily() }.join()
                launch { weekForecastRepo.saveDataBaseDaily(daysModelResponse) }
            }
        }
    }

    fun getDailyDataBase(){
        viewModelScope.launch(handler + Dispatchers.IO) {
            val data = weekForecastRepo.getDataBaseDaily()
            statesDays.send(data)
        }
    }



}