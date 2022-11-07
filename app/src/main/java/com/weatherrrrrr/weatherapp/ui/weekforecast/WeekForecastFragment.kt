package com.weatherrrrrr.weatherapp.ui.weekforecast

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.weatherrrrrr.weatherapp.R
import com.weatherrrrrr.weatherapp.adapters.MyRecyclerAdapter2
import com.weatherrrrrr.weatherapp.databinding.FragmentWeekForecastBinding
import com.weatherrrrrr.weatherapp.pojo.DaysModelResponse
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.consumeAsFlow

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [WeekForecastFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class WeekForecastFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var cityName : String? =null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
            cityName = it.getString("cityName")
        }
    }

    private lateinit var binding : FragmentWeekForecastBinding
    private lateinit var weekForecastView : View
    private val myRecyclerAdapter2: MyRecyclerAdapter2 by lazy { MyRecyclerAdapter2(this.activity!!) }
    private val weekForecastViewModel: WeekForecastViewModel by lazy { ViewModelProvider(this)[WeekForecastViewModel::class.java] }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWeekForecastBinding.inflate(inflater, container, false)
        weekForecastView = binding.root

        setTextOfCityNameOfLocation() //Set Text Of Location TextView
        onPressBackIcon()             //Set On CLick On BackIcon
        onPressBack()                 //Set On Press Back
        observeDays()                 //Observe Days

        return weekForecastView
    }

    private fun observeDays() {

        lifecycleScope.launchWhenStarted {
            weekForecastViewModel.statesDays.consumeAsFlow().buffer().collect{
                    setAdapter(it)                              //Set Adapter
            }
        }
        lifecycleScope.launchWhenStarted {
            weekForecastViewModel.statesError.collect{
                Log.d(TAG, "observeDays: $it")
                if (it == "Unable to resolve host \"api.openweathermap.org\": No address associated with hostname"){
                    Toast.makeText(this@WeekForecastFragment.activity,getString(R.string.check_internet_connection),Toast.LENGTH_SHORT).show()
                }
                weekForecastViewModel.getDailyDataBase()       //Get Days Model From Room
            }
        }
        //Get Weather Of Next Week By City Name
        weekForecastViewModel.getWeatherOfSevenDays(binding.locationTextViewWeekForecast.text.toString())
    }

    private fun setAdapter(it: DaysModelResponse?) {
        if(it != null){
            if(it.daily != null) {
                myRecyclerAdapter2.list = it.daily                        //Set List Of Adapter
                binding.recyclerWeekForecast.adapter = myRecyclerAdapter2 //Set Adapter To Recycler
            }
        }

    }


    private fun onPressBack() {
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true /* enabled by default */) {
                override fun handleOnBackPressed() {
                  findNavController().previousBackStackEntry?.savedStateHandle?.set("key", true)
                  findNavController().popBackStack()   //Back To Previous Fragment
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
    }


    private fun setTextOfCityNameOfLocation() {
        binding.locationTextViewWeekForecast.text = cityName
    }

    private fun onPressBackIcon() {
        binding.backImageWeekForecast.setOnClickListener {
            findNavController().previousBackStackEntry?.savedStateHandle?.set("key", true)
            findNavController().popBackStack()   //Back To Previous Fragment
        }
    }

    companion object {
        private const val TAG = "WeekForecastFragment"
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment WeekForecastFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            WeekForecastFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}