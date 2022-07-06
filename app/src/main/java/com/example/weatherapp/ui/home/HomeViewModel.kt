package com.example.weatherapp.ui.home


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.pojo.CurrentModelResponse
import com.example.weatherapp.pojo.HourlyModelResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import java.util.*
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(private var homeRepo: HomeRepo) : ViewModel(){

    private val _statusCurrentChannel = Channel<CurrentModelResponse>(Channel.UNLIMITED)
    val statesCurrent : Channel<CurrentModelResponse>  get() = _statusCurrentChannel

    private val _statusHourlyChannel = Channel<HourlyModelResponse>(Channel.UNLIMITED)
    val statesHourly : Channel<HourlyModelResponse>  get() = _statusHourlyChannel


    var intentChannel = Channel<HomeIntent>(Channel.UNLIMITED)

    private val _mutableLiveDataCurrent  = MutableLiveData<CurrentModelResponse>()
    val mutableCurrent : MutableLiveData<CurrentModelResponse> get() = _mutableLiveDataCurrent

    private val _mutableStateFlow = MutableStateFlow<HomeViewStates>(HomeViewStates.Idle)
    val states : MutableStateFlow<HomeViewStates> get() = _mutableStateFlow

    val handler = CoroutineExceptionHandler() { coroutineContext, throwable ->_mutableStateFlow.value = HomeViewStates.Error(throwable.message!!)}

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

         viewModelScope.launch( Dispatchers.IO + handler ) {
                     val current = async { homeRepo.getCurrentCityNameAndWeather(lat,lon,Locale.getDefault().language) }
                     val hourly = async { homeRepo.getWeatherHourly(lat,lon,Locale.getDefault().language) }

                     val  currentt = current.await()
                     val  hourlyy = hourly.await()
                     withContext(Dispatchers.Main) {
                         _mutableLiveDataCurrent.value = currentt
                     }
                      launch { homeRepo.deleteDataBaseCurrent() }.join()
                      launch { homeRepo.deleteDataBaseCurrent() }.join()
                      launch { homeRepo.deleteDataBaseHourly() }.join()
                      launch { homeRepo.saveDataBaseHourly(hourlyy) }

                      _mutableStateFlow.value=HomeViewStates.ShowWeather(currentt, hourlyy)


            }

        }



    fun reduceIntentCityName(cityName : String){
        viewModelScope.launch(handler+ Dispatchers.IO) {
                    val current = async {homeRepo.getCurrentWeatherByName(cityName, Locale.getDefault().language)}
                    val hourly = async { homeRepo.getWeatherHourly(current.await().coord?.lat!!,current.await().coord?.lon!!,Locale.getDefault().language) }

                    val  currentt = current.await()
                    val  hourlyy = hourly.await()
                    withContext(Dispatchers.Main) {
                        _mutableLiveDataCurrent.value = currentt
                    }
                    _mutableStateFlow.value=HomeViewStates.ShowWeather(currentt, hourlyy)
        }
    }

    fun deleteAndInsertCAndH(current :CurrentModelResponse,hourly :HourlyModelResponse){
        viewModelScope.launch(handler + Dispatchers.IO) {
            launch { homeRepo.deleteDataBaseCurrent() }.join()
            launch { homeRepo.saveDataBaseCurrent(current) }.join()
            launch { homeRepo.deleteDataBaseHourly() }.join()
            launch { homeRepo.saveDataBaseHourly(hourly) }.join()
        }
    }

    fun getCurrentDataBase()  {
        viewModelScope.launch(handler+ Dispatchers.IO) {
            val data = homeRepo.getDataBaseCurrent()
            withContext(Dispatchers.Main) {
                _mutableLiveDataCurrent.value = data
            }
            _statusCurrentChannel.send(data)

        }
    }
    fun getHourlyDataBase() {
        viewModelScope.launch(handler+ Dispatchers.IO) {
            val data = homeRepo.getDataBaseHourly()
                _statusHourlyChannel.send(data)
        }
    }



}