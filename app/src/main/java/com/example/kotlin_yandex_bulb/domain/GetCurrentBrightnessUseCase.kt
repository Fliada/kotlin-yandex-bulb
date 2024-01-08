package com.example.kotlin_yandex_bulb.domain

interface GetCurrentBrightnessUseCase {
    suspend operator fun invoke(): Result<Int?>
}