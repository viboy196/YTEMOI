package com.xcomp.ytemoi.view.sukien

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.xcomp.ytemoi.R
import org.bson.Document

class Adapter_sukien() : RecyclerView.Adapter<Adapter_sukien.ViewHolder>() {
    var dataSet : ArrayList<Document>? = null
    
    @SuppressLint("NotifyDataSetChanged")
    fun setdata(lsData: ArrayList<Document>){
        dataSet = lsData
        notifyDataSetChanged()
    }
    class ViewHolder(view:View) : RecyclerView.ViewHolder(view){

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_sukien , parent , false)
        return ViewHolder(view)
    }
    
    override fun getItemCount(): Int {
        return if(dataSet == null)
            0
        else
            dataSet!!.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    }

    
}