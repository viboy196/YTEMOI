package com.xcomp.ytemoi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.xcomp.ytemoi.services.CallService
import com.xcomp.ytemoi.utils.Utils
import com.xcomp.ytemoi.view.dangnhap.Activity_DangNhap
import com.xcomp.ytemoi.view.sukien.Activity_Sukien

class LaunchActivity : AppCompatActivity() {
    private var sdt = ""
    private var idnguoidung = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setbien()
        if(idnguoidung.length > 0 && sdt.length > 0)
        {
            val intent = Intent(this , Activity_Sukien::class.java)
            startActivity(intent)
        }
        else
        {
            val intent = Intent(this , Activity_DangNhap::class.java)
            startActivity(intent)
        }
        if(!CallService.isReady()){
            val intent = Intent(this@LaunchActivity, CallService::class.java)
            startService(intent)
        }
    }

    private fun setbien() {
        idnguoidung = Utils.getCommonSharepreference(this)?.getString(getString(R.string.key_idnguoidung) , "").toString();
        sdt = Utils.getCommonSharepreference(this)?.getString(getString(R.string.key_sdt) , "").toString();
    }
}