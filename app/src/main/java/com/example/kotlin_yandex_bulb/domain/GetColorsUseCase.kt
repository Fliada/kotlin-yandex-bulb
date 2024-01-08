package com.example.kotlin_yandex_bulb.domain

import com.example.kotlin_yandex_bulb.data.ColorData

interface GetColorsUseCase {
    suspend operator fun invoke(): Result<List<ColorData>?>
}
