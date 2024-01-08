package com.example.kotlin_yandex_bulb.domain

interface SetBrightnessLevelUseCase {
    suspend operator fun invoke(level: Int): Result<Unit>
}