package com.example.kotlin_yandex_bulb.domain

interface GetCurrentStateUseCase {
    suspend operator fun invoke(): Result<Boolean?>
}