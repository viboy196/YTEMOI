package com.xcomp.ytemoi.view.qr
import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.xcomp.ytemoi.R
import android.widget.Toast
import com.budiyev.android.codescanner.*
import com.gun0912.tedpermission.normal.TedPermission
import com.gun0912.tedpermission.PermissionListener
import android.util.Log
import com.budiyev.android.codescanner.DecodeCallback
import com.budiyev.android.codescanner.CodeScanner
import com.budiyev.android.codescanner.CodeScannerView

class Activity_Scan_Qr : AppCompatActivity() {
    private var mCodeScanner: CodeScanner? = null
    private var scannerView: CodeScannerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scan_qr)
        scannerView = findViewById(R.id.scanner_view)
        requestPermissionsCamera()
    }


    // khởi chạy Scanner
    private fun onScanner() {
        mCodeScanner = CodeScanner(this, scannerView!!)
        mCodeScanner!!.decodeCallback = DecodeCallback { result ->
            runOnUiThread(Runnable {
                Log.e("anhdan", "run: result$result")
                val qrcode = result.toString()
//                if (qrcode.contains("giuong-")) {
//                    val intent = Intent(this@QrscanerActivity, ActivateActivity::class.java)
//                    intent.putExtra("qrcode", qrcode)
//                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
//                    startActivity(intent)
//                } else {
//                    return@Runnable
//                }
            })
        }
        scannerView!!.setOnClickListener {
            mCodeScanner!!.startPreview()
        }
    }

    override fun onResume() {
        super.onResume()
        if (mCodeScanner != null) mCodeScanner!!.startPreview()
    }

    override fun onPause() {
        if (mCodeScanner != null) mCodeScanner!!.releaseResources()
        super.onPause()
    }


    private fun requestPermissionsCamera() {
        val pm: PermissionListener = object : PermissionListener {
            override fun onPermissionGranted() {
                // Khởi Động Scanner
                onScanner()
            }

            override fun onPermissionDenied(deniedPermissions: List<String>) {
                Toast.makeText(
                    this@Activity_Scan_Qr,
                    "Permission Denied\n$deniedPermissions",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        TedPermission.create()
            .setPermissionListener(pm)
            .setDeniedMessage("If you reject permission,you can not use this service\n\nPlease turn on permissions at [Setting] > [Permission]")
            .setPermissions(Manifest.permission.CAMERA)
            .check()
    }
}