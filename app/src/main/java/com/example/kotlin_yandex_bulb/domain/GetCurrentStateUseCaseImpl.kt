package com.example.kotlin_yandex_bulb.domain

import com.example.kotlin_yandex_bulb.data.repository.MainRepository
import javax.inject.Inject

class GetCurrentStateUseCaseImpl @Inject constructor(
    private val repository: MainRepository,
) : GetCurrentStateUseCase {
    override suspend fun invoke(): Result<Boolean?> =
        repository.getCurrentState()
}