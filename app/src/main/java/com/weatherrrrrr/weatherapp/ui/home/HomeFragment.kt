package com.weatherrrrrr.weatherapp.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.weatherrrrrr.weatherapp.R
import com.weatherrrrrr.weatherapp.adapters.MyRecyclerAdapter1
import com.weatherrrrrr.weatherapp.databinding.FragmentHomeBinding
import com.weatherrrrrr.weatherapp.pojo.HourlyModelResponse
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class HomeFragment : Fragment() ,Animation.AnimationListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var lat: Double? = null
    private var lon: Double? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
            lat = it.getDouble("lat")
            lon = it.getDouble("lon")
        }
    }


    override fun onPause() {
        job.cancel()
        super.onPause()
    }


    private lateinit var binding: FragmentHomeBinding
    private lateinit var homeView: View
    private val myRecyclerAdapter1: MyRecyclerAdapter1 by lazy { MyRecyclerAdapter1(this.activity!!) }
    private val homeViewModel: HomeViewModel by lazy { ViewModelProvider(this)[HomeViewModel::class.java] }
    private val animFadeIn: Animation by lazy { AnimationUtils.loadAnimation(context, R.anim.fade_in) }
    private var firstTime : Boolean = true
    private lateinit var job : Job
    private val arrayList = ArrayList<String>()
    private var selectedPosition : Int = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_home, container, false)
        homeView = binding.root
        binding.lifecycleOwner = this
        binding.viewModel = homeViewModel

        observeBackStackEntry()              //Observe Back Stack Entry
        onPressBack()                        //Set On Press Back
        setOnItemSelectedListenerSpinner()   //Set On Click On Spinner
        setOnClickOnCalenderImage()          //Set On Click On Calender Image

        return homeView
    }

    override fun onResume() {
        super.onResume()
        render()
    }


    private fun editSpinner(country : String) {
        arrayList.add(country)
        resources.getStringArray(R.array.countries).forEach { it-> arrayList.add(it) }
        setSpinnerAdapter()
}

 private fun setOnItemSelectedListenerSpinner() {
 binding.spinnerHome.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        lifecycleScope.launchWhenStarted {
            selectedPosition = p2
            val s = p0?.selectedItem.toString()
            homeViewModel.intentChannel.send(HomeIntent.GetWeather(s))
        }
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
    }

}

}

private fun observeBackStackEntry() {
findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<Boolean>("key")?.observe(
    viewLifecycleOwner) { result -> if (result){
    setSpinnerAdapter()
    setAllViewsVisible()
    }
}
}

    private fun setAllViewsVisible() {
        binding.degreeLinearHome.visibility = View.VISIBLE
        binding.feelsLikeLinearHome.visibility = View.VISIBLE
        binding.humidityLinearHome.visibility = View.VISIBLE
        binding.windLinearHome.visibility = View.VISIBLE
        binding.pressureLinearHome.visibility = View.VISIBLE
        binding.recyclerHome.visibility = View.VISIBLE
    }

    private fun setSpinnerAdapter(){
     val spinnerArrayAdapter = ArrayAdapter(requireContext(),R.layout.spinner_item,arrayList)
     spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item2) //Set Drop Down View Resource To Spinner
     binding.spinnerHome.adapter = spinnerArrayAdapter //Set Spinner Adapter*/
     binding.spinnerHome.setSelection(selectedPosition)
 }



private fun setOnClickOnCalenderImage() {
binding.calenderImageHome.setOnClickListener {
    if (binding.spinnerHome.selectedItem.toString().trim().isNotEmpty()) { //If Location TextView Not Empty
        val bundle = Bundle() //Initialize Bundle
        bundle.putString("cityName", binding.spinnerHome.selectedItem.toString().trim())
        findNavController().navigate(R.id.action_homeFragment_to_weekForecastFragment, bundle) //Navigate To WeekForecastFragment With Bundle Data

    }
}
}


private fun makeAnimation() {
//Initialize animFadeIn
animFadeIn.setAnimationListener(this)

lifecycleScope.launchWhenStarted {

    delay(1000)                                   //Delay 1 sec
    binding.degreeLinearHome.visibility = View.VISIBLE     //Make Fade In Element Visible First
    binding.degreeLinearHome.startAnimation(animFadeIn)    //Start Fade In Animation
    binding.degreeLinearHome.clearAnimation()               //Clear Animation From Element
    delay(1000)                                    //Delay 1 sec

    binding.feelsLikeLinearHome.visibility = View.VISIBLE  //Make Fade In Element Visible First
    binding.feelsLikeLinearHome.startAnimation(animFadeIn) //Start Fade In Animation
    binding.feelsLikeLinearHome.clearAnimation()           //Clear Animation From Element
    delay(1000)                                    //Delay 1 sec

    binding.humidityLinearHome.visibility = View.VISIBLE   //Make Fade In Element Visible First
    binding.humidityLinearHome.startAnimation(animFadeIn)  //Start Fade In Animation
    binding.humidityLinearHome.clearAnimation()            //Clear Animation From Element
    delay(1000)                                    //Delay 1 sec

    binding.windLinearHome.visibility = View.VISIBLE        //Make Fade In Element Visible First
    binding.windLinearHome.startAnimation(animFadeIn)       //Start Fade In Animation
    binding.windLinearHome.clearAnimation()                 //Clear Animation From Element
    delay(1000)                                     //Delay 1 sec

    binding.pressureLinearHome.visibility = View.VISIBLE     //Make Fade In Element Visible First
    binding.pressureLinearHome.startAnimation(animFadeIn)    //Start Fade In Animation
    binding.pressureLinearHome.clearAnimation()              //Clear Animation From Element
    delay(1000)                                      //Delay 1 sec

    binding.recyclerHome.visibility = View.VISIBLE           //Make Fade In Element Visible First
    binding.recyclerHome.startAnimation(animFadeIn)          //Start Fade In Animation
    binding.recyclerHome.clearAnimation()                    //Clear Animation From Element
}


}




private fun render() {
job = lifecycleScope.launchWhenStarted {
    homeViewModel.states.collect {
        when (it) {
            is HomeViewStates.Idle -> lat?.let { la ->
                lon?.let { lo ->
                    homeViewModel.reduceIntentCurrent(la, lo)
                }
            }
            is HomeViewStates.ShowWeather -> {
                if (firstTime) {
                    editSpinner(it.countryName)
                    makeAnimation()   //Make Animation
                    firstTime = false
                }
                setHourlyAdapter(it.hourlyModelResponse)  //Set Adapter

            }
            is HomeViewStates.Error -> {
                if (it.error == "Unable to resolve host \"api.openweathermap.org\": No address associated with hostname") {
                    Toast.makeText(
                        this@HomeFragment.activity,
                        getString(R.string.check_internet_connection),
                        Toast.LENGTH_SHORT
                    ).show()

                } else {
                    Toast.makeText(this@HomeFragment.activity, it.error, Toast.LENGTH_SHORT).show()
                }
                Log.d(TAG, "renderError: " + it.error)
                homeViewModel.getCurrentAndHourlyDataBase() //Get Room Data

            }
        }
    }

}

}

private fun setHourlyAdapter(hourlyModelResponse: HourlyModelResponse?) {
if(hourlyModelResponse != null) {
    myRecyclerAdapter1.list = hourlyModelResponse.hourly!!  //Set List Of Recycler Adapter
    binding.recyclerHome.adapter = myRecyclerAdapter1       //Set Adapter To Recycler
}

}


private fun onPressBack() {
val callback: OnBackPressedCallback =
    object : OnBackPressedCallback(true /* enabled by default */) {
        override fun handleOnBackPressed() {
            activity!!.finish() //Finish Activity
        }
    }
requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
}


/*private fun setOnClickOnLocationTextView() {
binding.locationTextViewHome.setOnClickListener {
   //Initialize popupMenu
   var popupMenu = PopupMenu(activity, binding.locationTextViewHome)

   // Inflating popup menu from popup_menu.xml file
   popupMenu.menuInflater.inflate(R.menu.popup_menu, popupMenu.menu)

   popupMenu.setOnMenuItemClickListener { menuItem ->
      binding.locationTextViewHome.text = menuItem.title.toString()
      lifecycleScope.launchWhenStarted {
          homeViewModel.intentChannel.send(HomeIntent.GetWeather(menuItem.title.toString()))
      }
       true
   }
   // Showing the popup menu
   popupMenu.show()
}
}*/



companion object {
private const val TAG = "HomeFragment"
/**
 * Use this factory method to create a new instance of
 * this fragment using the provided parameters.
 *
 * @param param1 Parameter 1.
 * @param param2 Parameter 2.
 * @return A new instance of fragment HomeFragment.
 */
// TODO: Rename and change types and number of parameters
@JvmStatic
fun newInstance(param1: String, param2: String) =
    HomeFragment().apply {
        arguments = Bundle().apply {
            putString(ARG_PARAM1, param1)
            putString(ARG_PARAM2, param2)
        }
    }
}

override fun onAnimationStart(p0: Animation?) {

}

override fun onAnimationEnd(p0: Animation?) {

}

override fun onAnimationRepeat(p0: Animation?) {

}
}