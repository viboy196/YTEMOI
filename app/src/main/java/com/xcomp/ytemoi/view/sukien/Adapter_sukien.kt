package com.xcomp.ytemoi.view.sukien

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Switch
import android.widget.TextView
import androidx.cardview.widget.CardView
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
//        val cv_item_sukien: CardView
//        val img_item_sukien: ImageView
//        val tv_item_sukien: TextView
//        val switch_item_sukien: Switch
//
//        init {
//            // Define click listener for the ViewHolder's View.
//            cv_item_sukien = view.findViewById(R.id.cv_item_sukien)
//            img_item_sukien = view.findViewById(R.id.img_item_sukien)
//            tv_item_sukien = view.findViewById(R.id.tv_item_sukien)
//            switch_item_sukien = view.findViewById(R.id.switch_item_sukien)
//        }


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

//        if(position == 2)
//            holder.img_item_sukien.setImageResource(R.drawable.ic_item_sukien_check)
    }

    
}