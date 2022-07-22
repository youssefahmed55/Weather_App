package com.weather.weatherapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.weather.weatherapp.R
import com.weather.weatherapp.databinding.RecyclerItem2Binding
import com.weather.weatherapp.pojo.DailyItem

class MyRecyclerAdapter2 constructor(var context : Context) : RecyclerView.Adapter<MyRecyclerAdapter2.Holder>() {
    lateinit var list : List<DailyItem?>


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding : RecyclerItem2Binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),R.layout.recycler_item2,parent,false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        Glide.with(context).load("https://openweathermap.org/img/w/" + list[position]?.weather?.get(0)?.icon.toString() + ".png").into(holder.holderBinding.imageRecyclerList2) //Load Image From Link
        holder.holderBinding.item = list.get(position)

    }

    override fun getItemCount(): Int {
        return list.size

    }

    class Holder(binding: RecyclerItem2Binding) : RecyclerView.ViewHolder(binding.root) {
         val holderBinding : RecyclerItem2Binding = binding

    }
}