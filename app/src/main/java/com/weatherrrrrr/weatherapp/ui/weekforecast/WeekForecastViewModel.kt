package com.weatherrrrrr.weatherapp.ui.weekforecast


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.weatherrrrrr.weatherapp.pojo.DaysModelResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class WeekForecastViewModel @Inject constructor(private var weekForecastRepo: WeekForecastRepo) : ViewModel() {

    private val _statusDaysChannel = Channel<DaysModelResponse>(Channel.UNLIMITED)
    val statesDays : Channel<DaysModelResponse> get() = _statusDaysChannel

    private val _mutableStateFlowError = MutableStateFlow<String>("")
    val statesError : StateFlow<String> get() = _mutableStateFlowError

    private val handler = CoroutineExceptionHandler() { _ , throwable -> _mutableStateFlowError.value = throwable.message!!}

     fun getWeatherOfSevenDays(cityName : String){
        viewModelScope.launch(handler) {
                val daysResult = weekForecastRepo.getWeatherOfSevenDays(cityName)
                weekForecastRepo.saveDataBaseDaily(daysResult)
                statesDays.send(daysResult)
         }
    }



    fun getDailyDataBase(){
        viewModelScope.launch(handler) {
            statesDays.send(weekForecastRepo.getDataBaseDaily())
        }
    }



}