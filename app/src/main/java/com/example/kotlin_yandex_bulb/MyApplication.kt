package com.example.kotlin_yandex_bulb

import android.app.Application
import com.example.kotlin_yandex_bulb.di.AppComponent
import com.example.kotlin_yandex_bulb.di.DaggerAppComponent

class MyApplication : Application() {

    lateinit var appComponent: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent
            .create()
    }

}