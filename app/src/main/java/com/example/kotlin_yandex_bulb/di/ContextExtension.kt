package com.example.kotlin_yandex_bulb.di

import android.content.Context
import com.example.kotlin_yandex_bulb.MyApplication

val Context.appComponent: AppComponent
    get() = when(this) {
        is MyApplication -> appComponent
        else -> applicationContext.appComponent
    }