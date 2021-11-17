package com.xcomp.ytemoi.view.dichvu

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.view.View
import android.widget.Toast
import com.xcomp.ytemoi.R
import com.xcomp.ytemoi.services.CallService
import com.xcomp.ytemoi.support.BaseActivity
import com.xcomp.ytemoi.utils.Utils
import kotlinx.android.synthetic.main.activity_goi_yta.*
import org.linphone.core.*

class Activity_GoiYta : BaseActivity() {
    //gọi
    private val coreListener = object: CoreListenerStub() {
        override fun onAccountRegistrationStateChanged(core: Core, account: Account, state: RegistrationState?, message: String) {
            //findViewById<TextView>(org.linphone.core.R.id.registration_status).text = message

            if (state == RegistrationState.Failed) {
                call_layout.visibility = View.GONE
                Toast.makeText(
                    this@Activity_GoiYta,
                    "Đăng ký VOIP thất bại!",
                    Toast.LENGTH_LONG
                ).show()
            } else if (state == RegistrationState.Ok) {
                CallService.checklogin = true
                //call_layout.visibility = View.VISIBLE
            }
        }

        override fun onAudioDeviceChanged(core: Core, audioDevice: AudioDevice) {
        }

        override fun onAudioDevicesListUpdated(core: Core) {
            // This callback will be triggered when the available devices list has changed,
            // for example after a bluetooth headset has been connected/disconnected.
        }

        override fun onCallStateChanged(
            core: Core,
            call: Call,
            state: Call.State?,
            message: String
        ) {


            // When a call is received
            when (state) {
                Call.State.IncomingReceived -> {
                    call_layout.visibility = View.VISIBLE
                    noti_layout.visibility = View.GONE
                }
                Call.State.Connected -> {
                    //mute_mic.isEnabled = true
                    toggle_speaker.isEnabled = true
                    startClock()
                }
                Call.State.Released -> {
                    answer.visibility = View.VISIBLE
                    hang_up.visibility = View.VISIBLE
                    hang_up_up.visibility = View.GONE
                    call_layout.visibility = View.GONE
                    noti_layout.visibility = View.VISIBLE
                    chElapsedTime.visibility = View.GONE

                }
            }
        }
    }
    // gửi thông báo
    private val urlactivateNumber = "https://ytemoi.com/api/ncb"
    private var data: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goi_yta)

        //lấy dữ liệu đã lưu trong cache
        //data = Utils.getCommonSharepreference(this)?.getString("ketqua", "")

        //set thong so ban dau
        noti_layout.visibility = View.VISIBLE
        call_layout.visibility =  View.GONE
        chElapsedTime.visibility = View.GONE
        hang_up_up.visibility = View.GONE

        toggle_speaker.isEnabled = false
        login()
    }
    override fun onStart() {
        super.onStart()
        hang_up.setOnClickListener {
            // Terminates the call, whether it is ringing or running
            CallService.getInstance()?.getCore()!!.currentCall?.terminate()

            answer.visibility = View.VISIBLE
            hang_up.visibility = View.VISIBLE
            hang_up_up.visibility = View.GONE
            call_layout.visibility = View.GONE
            noti_layout.visibility = View.VISIBLE
            chElapsedTime.visibility = View.GONE
        }
        hang_up_up.setOnClickListener{
            CallService.getInstance()?.getCore()!!.currentCall?.terminate()

            answer.visibility = View.VISIBLE
            hang_up.visibility = View.VISIBLE
            hang_up_up.visibility = View.GONE
            call_layout.visibility = View.GONE
            noti_layout.visibility = View.VISIBLE
            chElapsedTime.visibility = View.GONE
        }
        answer.setOnClickListener {
            CallService.getInstance()?.getCore()!!.currentCall?.accept()
            answer.visibility = View.GONE
            hang_up.visibility = View.GONE
            hang_up_up.visibility = View.VISIBLE
        }
        toggle_speaker.setOnClickListener {
            toggleSpeaker()
        }

        ll_back.setOnClickListener {
            finish()
        }
    }
    private fun toggleSpeaker() {
        // Get the currently used audio device
        val currentAudioDevice = CallService.getInstance()?.getCore()!!.currentCall?.outputAudioDevice
        val speakerEnabled = currentAudioDevice?.type == AudioDevice.Type.Speaker

        // We can get a list of all available audio devices using
        // Note that on tablets for example, there may be no Earpiece device
        for (audioDevice in CallService.getInstance()?.getCore()!!.audioDevices) {
            if (speakerEnabled && audioDevice.type == AudioDevice.Type.Earpiece) {
                CallService.getInstance()?.getCore()!!.currentCall?.outputAudioDevice = audioDevice
                cvSpeaker.setCardBackgroundColor(Color.parseColor("#FFFFFF"))
                ivSpeaker.setColorFilter(Color.parseColor("#5887a1")) // #335A70
                return
            } else if (!speakerEnabled && audioDevice.type == AudioDevice.Type.Speaker) {
                cvSpeaker.setCardBackgroundColor(Color.parseColor("#335A70"))
                ivSpeaker.setColorFilter(Color.parseColor("#FFFFFF"))
                CallService.getInstance()?.getCore()!!.currentCall?.outputAudioDevice = audioDevice
                return
            }/* If we wanted to route the audio to a bluetooth headset
                else if (audioDevice.type == AudioDevice.Type.Bluetooth) {
                    core.currentCall?.outputAudioDevice = audioDevice
                }*/
        }
    }

    private fun login() {
        if(!CallService.checklogin) {
            val username = "800"
            val password = username.plus("ytm")
            val domain = "171.244.133.171"

            val transportType = TransportType.Udp

            val authInfo = Factory.instance()
                .createAuthInfo(username, null, password, null, null, domain, null)

            val params = CallService.getInstance()?.getCore()!!.createAccountParams()
            val identity = Factory.instance().createAddress("sip:$username@$domain")
            params.identityAddress = identity

            val address = Factory.instance().createAddress("sip:$domain")
            address?.transport = transportType
            params.serverAddress = address
            params.registerEnabled = true
            params.pushNotificationAllowed = true

            val account = CallService.getInstance()?.getCore()!!.createAccount(params)

            CallService.getInstance()?.getCore()!!.addAuthInfo(authInfo)
            CallService.getInstance()?.getCore()!!.addAccount(account)

            CallService.getInstance()?.getCore()!!.defaultAccount = account
            CallService.getInstance()?.getCore()!!.addListener(coreListener)
            CallService.getInstance()?.getCore()!!.start()
            if (packageManager.checkPermission(
                    Manifest.permission.RECORD_AUDIO,
                    packageName
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                requestPermissions(arrayOf(Manifest.permission.RECORD_AUDIO), 0)
                return
            }
        }
    }

    private fun startClock() {
        chElapsedTime.visibility = View.VISIBLE
        chElapsedTime.base = SystemClock.elapsedRealtime()
        chElapsedTime.start();
    }
}