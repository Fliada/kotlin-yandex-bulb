package com.example.kotlin_yandex_bulb.data

import java.io.Serializable

data class BrightnessData(
    val max : Int,
    val min : Int,
    val precision : Int
) : Serializable
