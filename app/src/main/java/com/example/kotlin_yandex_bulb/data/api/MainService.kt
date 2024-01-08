package com.example.kotlin_yandex_bulb.data.api

import com.example.kotlin_yandex_bulb.data.ColorData
import retrofit2.Response
import retrofit2.http.GET

interface MainService {
    @GET("color/")
    suspend fun getColors(): Response<List<ColorData>>
}