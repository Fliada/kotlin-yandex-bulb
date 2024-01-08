package com.example.kotlin_yandex_bulb.domain

import com.example.kotlin_yandex_bulb.data.repository.MainRepository
import javax.inject.Inject

class TurnOnUseCaseImpl @Inject constructor(
    private val repository: MainRepository,
): TurnOnUseCase {
    override suspend fun invoke(): Result<Unit> =
        repository.turnOn()
}