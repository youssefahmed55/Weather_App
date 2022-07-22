package com.weather.weatherapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.weather.weatherapp.R
import com.weather.weatherapp.databinding.RecyclerItem1Binding
import com.weather.weatherapp.pojo.HourlyItem

class MyRecyclerAdapter1 constructor(var context : Context) : RecyclerView.Adapter<MyRecyclerAdapter1.Holder>() {
    lateinit var list : List<HourlyItem?>


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding : RecyclerItem1Binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),R.layout.recycler_item1,parent,false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.holderBinding.item = list[position]
        Glide.with(context).load("https://openweathermap.org/img/w/" + list[position]?.weather?.get(0)?.icon.toString() + ".png").into(holder.holderBinding.imageRecyclerList) //Load Image From Link
    }

    override fun getItemCount(): Int {
        return list.size

    }

    class Holder(binding: RecyclerItem1Binding) : RecyclerView.ViewHolder(binding.root) {
         val holderBinding : RecyclerItem1Binding = binding

    }
}