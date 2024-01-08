package com.example.kotlin_yandex_bulb.data.api

import com.example.kotlin_yandex_bulb.data.BrightnessData
import com.example.kotlin_yandex_bulb.data.ColorData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface MainService {
    //Color
    @GET("color/")
    suspend fun getColors(): Response<List<ColorData>>

    @POST("color/")
    suspend fun setColor(@Query("color") color: String): Response<Unit>

    @GET("color/current")
    suspend fun getCurrentColor(): Response<ColorData>

    //Brightness
    @GET("brightness/")
    suspend fun getBrightnessLevels(): Response<BrightnessData>

    @GET("brightness/current")
    suspend fun getCurrentBrightnessLevel(): Response<Int>

    @POST("brightness/")
    suspend fun setBrightnessLevel(@Query("level") level: Int): Response<Unit>

    //State
    @POST("state/on")
    suspend fun turnOn(): Response<Unit>

    @POST("state/off")
    suspend fun turnOff(): Response<Unit>

    @GET("state/")
    suspend fun getCurrentState(): Response<Boolean>
}