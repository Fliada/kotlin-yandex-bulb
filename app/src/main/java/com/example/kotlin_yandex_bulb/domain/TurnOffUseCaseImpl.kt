package com.example.kotlin_yandex_bulb.domain


import com.example.kotlin_yandex_bulb.data.repository.MainRepository
import javax.inject.Inject

class TurnOffUseCaseImpl @Inject constructor(
    private val repository: MainRepository
) : TurnOffUseCase {
    override suspend fun invoke(): Result<Unit> =
        repository.turnOff()
}