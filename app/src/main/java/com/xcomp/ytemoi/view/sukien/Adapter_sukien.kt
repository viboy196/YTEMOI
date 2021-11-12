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
//        val cv_item_sukien_status: CardView
//        val item_sukien_tieude: TextView
//        val tv_item_sukien_noidung: TextView
//        val tv_item_sukien_Khoaphong: TextView
//        val tv_item_sukien_buonggiuong: TextView
//        val tv_item_sukien_capnhat: TextView
//        init {
//            // Define click listener for the ViewHolder's View.
//            cv_item_sukien_status = view.findViewById(R.id.cv_item_sukien_status)
//            item_sukien_tieude = view.findViewById(R.id.item_sukien_tieude)
//            tv_item_sukien_noidung = view.findViewById(R.id.tv_item_sukien_noidung)
//            tv_item_sukien_Khoaphong = view.findViewById(R.id.tv_item_sukien_Khoaphong)
//            tv_item_sukien_buonggiuong = view.findViewById(R.id.tv_item_sukien_buonggiuong)
//            tv_item_sukien_capnhat = view.findViewById(R.id.tv_item_sukien_capnhat)
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

//        val document = dataSet?.get(position)
//        val data_sukiennguoidung_trangthai = document?.get("data_sukiennguoidung_trangthai") as String
//        val data_sukiennguoidung_loai = document["data_sukiennguoidung_loai"] as String
//        val data_sukiennguoidung_noidung = document["data_sukiennguoidung_noidung"] as String
//        val data_sukiennguoidung_mes = document["data_sukiennguoidung_mes"] as String
//
//        holder.item_sukien_tieude.text = data_sukiennguoidung_loai
//        holder.tv_item_sukien_noidung.text = data_sukiennguoidung_noidung
//        holder.tv_item_sukien_capnhat.text = data_sukiennguoidung_mes
    }

    
}