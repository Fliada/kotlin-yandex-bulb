package com.example.kotlin_yandex_bulb.data

import java.io.Serializable

data class ColorData(
    val id : Int,
    val name : String,
    val type : String,
    val color : String,
) : Serializable
