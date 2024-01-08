package com.example.kotlin_yandex_bulb.domain

import com.example.kotlin_yandex_bulb.data.repository.MainRepository
import javax.inject.Inject

class GetCurrentBrightnessUseCaseImpl @Inject constructor(
    private val repository: MainRepository
) : GetCurrentBrightnessUseCase {
    override suspend fun invoke(): Result<Int?> =
        repository.getCurrentBrightnessLevel()
}