package com.example.kotlin_yandex_bulb.data.repository

import com.example.kotlin_yandex_bulb.data.ColorData

interface MainRepository {
    suspend fun getJokesCategories(): Result<List<ColorData>?>
}

