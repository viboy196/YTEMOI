package com.xcomp.ytemoi.view.dangnhap

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import com.xcomp.ytemoi.R
import kotlinx.android.synthetic.main.activity_dang_nhap.*

import android.widget.Toast
import com.android.volley.*
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.xcomp.ytemoi.support.BaseActivity
import com.xcomp.ytemoi.utils.Utils
import com.xcomp.ytemoi.view.sukien.Activity_Sukien
import org.bson.Document
import java.lang.Exception


// ultis
import java.util.HashMap


class Activity_DangNhap : BaseActivity() {
    lateinit var sdt: String
    var apptoken: String = "a"
    var typedevice: String = "a"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dang_nhap)

    }

    override fun onStart() {
        super.onStart()
        btndangnhap.setOnClickListener {
            sdt = et_sdt.text.toString()
            if (validatePhone(sdt))
                DangNhap(sdt, apptoken, typedevice)
        }
        btnxacthuc.setOnClickListener {
            val otp = et_otp.text.toString()
            if (validateOTP(otp))
                XacThuc(sdt, otp)
        }

    }

    private fun validateOTP(otp: String): Boolean {
        if (otp.length == 0) {
            Toast.makeText(this, "Nhập mã xác thực", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    private fun XacThuc(sdt: String, otp: String) {
        showProgressDialogWithText("Xác Thực...")
        val queue = Volley.newRequestQueue(this@Activity_DangNhap)
        val stringRequest: StringRequest = object : StringRequest(
            Method.POST, "https://ytemoi.com/api/app/app_xacthuc",
            Response.Listener {
                hideProgressDialog()
                var strResponse = TienSulyApi(it)
                try {
                    val document = Document.parse(strResponse)
                    val ketqua = document["ketqua"] as String
                    if (ketqua.equals("Success")) {
                        val idnguoidung = document["idnguoidung"] as String
                        Utils.setCommonSharepreference(this, getString(R.string.key_idnguoidung), idnguoidung)
                        Utils.setCommonSharepreference(this, getString(R.string.key_sdt), sdt)
                        Toast.makeText(this, "Xác thực thành công", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this , Activity_Sukien::class.java)
                        startActivity(intent)
                    } else {
                        val mes = document["mes"] as String
                        Toast.makeText(this, mes, Toast.LENGTH_SHORT).show()
                    }
                } catch (e: Exception) {
                    Log.e("xacthuc", "xacthuc loi: ${e.toString()}")
                }


            }, Response.ErrorListener {
                Log.e("xacthuc", "xacthuc loi: ${it.toString()}")
            }) {
            @Throws(AuthFailureError::class)
            override fun getParams(): Map<String, String>? {
                val params: MutableMap<String, String> = HashMap()
                params["sdt"] = sdt
                params["otp"] = otp
                return params
            }
        }
        queue.cache.clear()
        queue.add(stringRequest)
    }


    private fun validatePhone(sdt: String): Boolean {
        if (sdt.length == 0) {
            Toast.makeText(this, "Nhập số điện thoại", Toast.LENGTH_SHORT).show()
            return false
        }
        if (!Utils.ChecPhoneknumber(sdt)) {
            Toast.makeText(this, "Nhập sai số điện thoại", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    private fun DangNhap(phoneNumber: String, token: String, typeDevice: String) {
        showProgressDialogWithText("Đăng nhập ...")
        val queue = Volley.newRequestQueue(this@Activity_DangNhap)
        val stringRequest: StringRequest = object : StringRequest(
            Method.POST, "https://ytemoi.com/api/app/app_dangky",
            Response.Listener {
                hideProgressDialog()
                var strResponse = TienSulyApi(it)
                try {
                    val document = Document.parse(strResponse)
                    val ketqua = document["ketqua"] as String
                    if (ketqua.equals("Success")) {
                        lldangnhap.visibility = View.GONE
                        llotp.visibility = View.VISIBLE
                        Toast.makeText(this, "Nhập mã OTP được gửi về sđt", Toast.LENGTH_SHORT)
                            .show()
                    }
                } catch (e: Exception) {
                    Log.e("dangnhap", "DangNhap loi: ${e.toString()}")
                }


            }, Response.ErrorListener {
                Log.e("dangnhap", "DangNhap loi: ${it.toString()}")
            }) {
            @Throws(AuthFailureError::class)
            override fun getParams(): Map<String, String>? {
                val params: MutableMap<String, String> = HashMap()
                params["sdt"] = sdt
                params["apptoken"] = token
                params["typedevice"] = typeDevice
                return params
            }
        }
        queue.cache.clear()
        queue.add(stringRequest)
    }

}