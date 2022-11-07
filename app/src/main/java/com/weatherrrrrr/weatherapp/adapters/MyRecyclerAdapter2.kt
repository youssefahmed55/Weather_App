package com.weatherrrrrr.weatherapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.weatherrrrrr.weatherapp.R
import com.weatherrrrrr.weatherapp.databinding.RecyclerItem2Binding
import com.weatherrrrrr.weatherapp.pojo.DailyItem

class MyRecyclerAdapter2 constructor(var context : Context) : RecyclerView.Adapter<MyRecyclerAdapter2.Holder>() {
    lateinit var list : List<DailyItem?>


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding : RecyclerItem2Binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),R.layout.recycler_item2,parent,false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.holderBinding.item = list[position]

    }

    override fun getItemCount(): Int {
        return list.size

    }

    class Holder(binding: RecyclerItem2Binding) : RecyclerView.ViewHolder(binding.root) {
         val holderBinding : RecyclerItem2Binding = binding

    }
}