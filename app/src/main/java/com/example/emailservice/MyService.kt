package com.example.emailservice

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import androidx.localbroadcastmanager.content.LocalBroadcastManager

class MyService : Service() {
    private var newList = ArrayList<Email>()
    val ACTION = "com.example.emailservice.MyService"

    override fun onCreate() {
        Log.i("Create", "Service onCreate")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.i("Start", "Service onStartCommand $startId")
        val list = intent?.getParcelableArrayListExtra<Email>("list")
        newList = list?.let { removeEmail(it) } as ArrayList<Email>
        val i = Intent(ACTION)
        i.putExtra("newList", newList)
        LocalBroadcastManager.getInstance(this).sendBroadcast(i)
        return START_STICKY
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onDestroy() {
    }

    private fun removeEmail(emailList: List<Email>): List<Email>? {
        val newList: MutableList<Email> = ArrayList()
        for (i in emailList.indices) {
            if (newList.isEmpty()) {
                newList.add(emailList[i])
            } else {
                var count = 0
                for (f in newList) {
                    if (emailList[i].title == f.title) {
                        count++
                    }
                }
                if (count == 0) {
                    newList.add(emailList[i])
                }
            }
        }
        return newList
    }
}