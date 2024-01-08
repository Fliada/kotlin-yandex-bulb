package com.example.kotlin_yandex_bulb.domain

import com.example.kotlin_yandex_bulb.data.ColorData


interface GetCurrentColorUseCase {
    suspend operator fun invoke(): Result<ColorData?>
}