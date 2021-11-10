package com.xcomp.ytemoi.view.sukien

import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.AuthFailureError
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.xcomp.ytemoi.R
import com.xcomp.ytemoi.support.BaseActivity
import com.xcomp.ytemoi.utils.Utils
import kotlinx.android.synthetic.main.activity_sukien.*
import org.bson.Document
import java.util.HashMap

class Activity_Sukien : BaseActivity() {

    // biến sử dụng
    private var idnguoidung = ""
    private var sdt = ""
    private lateinit var adapter:Adapter_sukien
    // list sukien
    private lateinit var dssukien:ArrayList<Document>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sukien)
        init_View_dssk()
    }

    private fun init_View_dssk() {
        dssukien = ArrayList()
        adapter = Adapter_sukien()
//        val linnerlayout=
//        linnerlayout.orientation = LinearLayoutManager.VERTICAL
        rc_data_item_sukien.layoutManager = LinearLayoutManager(this)
        rc_data_item_sukien.adapter = adapter
    }
    override fun onStart() {
        super.onStart()
        setbien()
        loadSukien()
    }

    private fun setbien() {
        idnguoidung = Utils.getCommonSharepreference(this)?.getString(getString(R.string.key_idnguoidung) , "").toString();
        sdt = Utils.getCommonSharepreference(this)?.getString(getString(R.string.key_sdt) , "").toString();
    }

    private fun loadSukien() {
        val queue = Volley.newRequestQueue(this@Activity_Sukien)
        val stringRequest: StringRequest = object : StringRequest(
            Method.POST,
            getString(R.string.url_load_sukien),
            Response.Listener {
                val response = TienSulyApi(it)
                val document = Document.parse(response)
                val ketqua = document["ketqua"] as String
                if(ketqua.equals("Success")){
                    val databson = document["databson"] as Document
                    dssukien = databson["dssukien"] as ArrayList<Document>
                    for( i in 0..8){
                        dssukien.add(dssukien[0])
                    }
                    view_dssukien()
                }
               }, Response.ErrorListener {
                Log.e("loadSukien", "loadSukien loi: ${it.toString()}")
            }) {
            @Throws(AuthFailureError::class)
            override fun getParams(): Map<String, String>? {
                val params: MutableMap<String, String> = HashMap()
                params["sdt"] = sdt
                params["idnguoidung"] = idnguoidung
                return params
            }
        }
        queue.cache.clear()
        queue.add(stringRequest)
    }

    private fun view_dssukien() {
        adapter.setdata(dssukien)
    }
}