package com.example.kotlin_yandex_bulb.domain

import com.example.kotlin_yandex_bulb.data.BrightnessData

interface GetBrightnessLevelsUseCase {
    suspend operator fun invoke(): Result<BrightnessData?>
}