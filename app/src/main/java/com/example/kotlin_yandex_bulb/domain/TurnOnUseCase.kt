package com.example.kotlin_yandex_bulb.domain

interface TurnOnUseCase {
    suspend operator fun invoke(): Result<Unit>
}