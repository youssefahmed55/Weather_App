package com.weatherrrrrr.weatherapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.weatherrrrrr.weatherapp.R
import com.weatherrrrrr.weatherapp.databinding.RecyclerItem1Binding
import com.weatherrrrrr.weatherapp.pojo.HourlyItem

class MyRecyclerAdapter1 constructor(var context : Context) : RecyclerView.Adapter<MyRecyclerAdapter1.Holder>() {
    lateinit var list : List<HourlyItem?>


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding : RecyclerItem1Binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),R.layout.recycler_item1,parent,false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.holderBinding.item = list[position]
    }

    override fun getItemCount(): Int {
        return list.size

    }

    class Holder(binding: RecyclerItem1Binding) : RecyclerView.ViewHolder(binding.root) {
         val holderBinding : RecyclerItem1Binding = binding

    }
}