package com.weatherrrrrr.weatherapp.ui.home


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.weatherrrrrr.weatherapp.pojo.CurrentModelResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import java.util.*
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(private var homeRepo: HomeRepo) : ViewModel(){


    var intentChannel = Channel<HomeIntent>(Channel.UNLIMITED)


    private val _mutableStateFlow = MutableStateFlow<HomeViewStates>(HomeViewStates.Idle)
    val states : StateFlow<HomeViewStates> get() = _mutableStateFlow

    private val _mutableLiveDataCurrent = MutableLiveData<CurrentModelResponse>()
    val currentModel : LiveData<CurrentModelResponse> get() = _mutableLiveDataCurrent

    private val handler = CoroutineExceptionHandler() { _, throwable ->_mutableStateFlow.value = HomeViewStates.Error(throwable.message!!)}

    init {
        processIntent()
    }
    fun processIntent(){

        viewModelScope.launch {
            intentChannel.consumeAsFlow().collect{
                when(it){
                    is HomeIntent.GetWeather -> reduceIntentCityName(it.cityName)
                }
            }
        }
    }

    fun reduceIntentCurrent(lat:Double , lon:Double){

         viewModelScope.launch(handler) {

             val current = async { homeRepo.getCurrentCityNameAndWeather(lat,lon,Locale.getDefault().language) }
             val hourly = async { homeRepo.getWeatherHourly(lat,lon,Locale.getDefault().language) }

             homeRepo.saveDataBaseCurrent(current.await())
             homeRepo.saveDataBaseHourly(hourly.await())


             withContext(Dispatchers.Main){ _mutableLiveDataCurrent.value = current.await() }


             _mutableStateFlow.value=HomeViewStates.ShowWeather(current.await().name ?: "", hourly.await())

            }

        }

    fun reduceIntentCityName(cityName : String){
        viewModelScope.launch(handler) {

                val current = async {homeRepo.getCurrentWeatherByName(cityName, Locale.getDefault().language)}
                homeRepo.saveDataBaseCurrent(current.await())


                val hourly = async { homeRepo.getWeatherHourly(current.await().coord?.lat!!,current.await().coord?.lon!!,Locale.getDefault().language) }
                homeRepo.saveDataBaseHourly(hourly.await())


              withContext(Dispatchers.Main){ _mutableLiveDataCurrent.value = current.await() }
              _mutableStateFlow.value=HomeViewStates.ShowWeather(current.await().name ?: "", hourly.await())
        }
    }


    fun getCurrentAndHourlyDataBase()  {
        viewModelScope.launch(handler) {
            withContext(Dispatchers.Main){ _mutableLiveDataCurrent.value = homeRepo.getDataBaseCurrent() }
            _mutableStateFlow.value=HomeViewStates.ShowWeather(homeRepo.getDataBaseCurrent().name ?:"", homeRepo.getDataBaseHourly())
        }
    }




}