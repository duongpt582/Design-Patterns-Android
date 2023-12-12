package com.duongpt207595.designpatterns

import android.app.Application
import com.duongpt207595.designpatterns.singleton.room.AppContainer
import com.duongpt207595.designpatterns.singleton.room.AppDataContainer

class MyApplication: Application() {

    /**
     * AppContainer instance used by the rest of classes to obtain dependencies
     */
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}