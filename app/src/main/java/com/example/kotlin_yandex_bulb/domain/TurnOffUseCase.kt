package com.example.kotlin_yandex_bulb.domain

interface TurnOffUseCase {
    suspend operator fun invoke(): Result<Unit>
}