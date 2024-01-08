package com.example.kotlin_yandex_bulb.presenter

import com.example.kotlin_yandex_bulb.data.ColorData

interface SliderSelectionListener<T> {
    fun setupColorSpinner(colors: T)
}