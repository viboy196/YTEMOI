package com.xcomp.ytemoi.view.sukien

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Switch
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.xcomp.ytemoi.R
import com.xcomp.ytemoi.view.dichvu.Activity_GoiYta
import org.bson.Document

class Adapter_sukien() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
     var dataSet: ArrayList<Document>? = null
     var mContext: Context? = null
    @SuppressLint("NotifyDataSetChanged")
    fun setdata(context: Context , lsData: ArrayList<Document>) {
        mContext = context
        dataSet = lsData
        val doc = Document()
        dataSet!!.add(doc)
        dataSet!!.add(doc)
        dataSet!!.add(doc)


        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val cv_item_sukien_chitiet: CardView

        //        val item_sukien_tieude: TextView
//        val tv_item_sukien_noidung: TextView
//        val tv_item_sukien_Khoaphong: TextView
//        val tv_item_sukien_buonggiuong: TextView
//        val tv_item_sukien_capnhat: TextView
        init {
            cv_item_sukien_chitiet = view.findViewById(R.id.cv_item_sukien_chitiet)
//            item_sukien_tieude = view.findViewById(R.id.item_sukien_tieude)
//            tv_item_sukien_noidung = view.findViewById(R.id.tv_item_sukien_noidung)
//            tv_item_sukien_Khoaphong = view.findViewById(R.id.tv_item_sukien_Khoaphong)
//            tv_item_sukien_buonggiuong = view.findViewById(R.id.tv_item_sukien_buonggiuong)
//            tv_item_sukien_capnhat = view.findViewById(R.id.tv_item_sukien_capnhat)
        }


    }

    class ViewHolder_Create(view: View) : RecyclerView.ViewHolder(view) {
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

    class ViewHolder_Tinhhuong(view: View) : RecyclerView.ViewHolder(view) {
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

    class ViewHolder_foodter(view: View) : RecyclerView.ViewHolder(view) {
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            1 -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_sukien_taosukien, parent, false)
                ViewHolder_Create(view)
            }
            2 -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_sukien_taotinhhuong, parent, false)
                ViewHolder_Tinhhuong(view)
            }
            3 -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_sukien_footer, parent, false)
                ViewHolder_foodter(view)
            }

            else -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_sukien, parent, false)
                ViewHolder(view)
            }
        }
    }

    override fun getItemCount(): Int {
        return if (dataSet == null)
            0
        else
            dataSet!!.size
    }

    override fun getItemViewType(position: Int): Int {
        if (position < itemCount - 3)
            return 0
        else if (position == itemCount - 3)
            return 1
        else if (position == itemCount - 2)
            return 2
        else
            return 3
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder.itemViewType){
            0 -> {
                val doc = dataSet?.get(position)

                val viewHolder0 = holder as ViewHolder
                viewHolder0.cv_item_sukien_chitiet.setOnClickListener {
                    val intent = Intent(mContext , Activity_GoiYta::class.java)
                    mContext?.startActivity(intent)
                }
            }
        }
    }


}