package com.example.kotlin_yandex_bulb.data.api

import com.example.kotlin_yandex_bulb.data.ColorData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface MainService {
    @GET("color/")
    suspend fun getColors(): Response<List<ColorData>>

    @POST("color/")
    suspend fun setColor(@Query("color") color: String): Response<Unit>
}