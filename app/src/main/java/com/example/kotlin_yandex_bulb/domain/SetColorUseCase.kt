package com.example.kotlin_yandex_bulb.domain

interface SetColorUseCase {
    suspend operator fun invoke(color: String): Result<Unit>
}
