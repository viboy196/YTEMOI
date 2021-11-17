package com.xcomp.ytemoi.services

import android.app.Service
import android.content.Intent
import android.os.IBinder
import org.linphone.core.Core
import org.linphone.core.CoreListenerStub
import org.linphone.core.Factory

class CallService : Service() {

    private  var mCore: Core? = null



    private lateinit var mCoreListener: CoreListenerStub
    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }

    override fun onCreate() {
        super.onCreate()
        val factory: Factory = Factory.instance()
        factory.setDebugMode(true, "Hello Linphone")
        mCore = factory.createCore(null, null, this)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)
        if(sInstance != null)
            return START_STICKY
        sInstance = this
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        mCore?.stop();
        mCore = null;
        sInstance = null
    }

    override fun onTaskRemoved(rootIntent: Intent?) {
        super.onTaskRemoved(rootIntent)
        stopSelf()
    }
    public fun getCore(): Core? {
        return sInstance!!.mCore
    }

    companion object{
        private var sInstance: CallService? = null
        var checklogin = false
        fun getInstance(): CallService? {
            return sInstance
        }
        fun isReady(): Boolean {
            return sInstance != null
        }
    }
}