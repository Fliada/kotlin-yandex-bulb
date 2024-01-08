package com.example.kotlin_yandex_bulb.data.repository

import com.example.kotlin_yandex_bulb.data.BrightnessData
import com.example.kotlin_yandex_bulb.data.ColorData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface MainRepository {
    suspend fun getColors(): Result<List<ColorData>?>
    suspend fun setColor(color: String): Result<Unit>

    suspend fun getCurrentColor(): Result<ColorData?>

    suspend fun getBrightnessLevels(): Result<BrightnessData?>

    suspend fun getCurrentBrightnessLevel(): Result<Int?>

    suspend fun setBrightnessLevel(level: Int): Result<Unit>

    suspend fun turnOn(): Result<Unit>

    suspend fun turnOff(): Result<Unit>

    suspend fun getCurrentState(): Result<Boolean?>
}

