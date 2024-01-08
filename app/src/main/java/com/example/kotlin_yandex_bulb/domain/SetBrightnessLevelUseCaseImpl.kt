package com.example.kotlin_yandex_bulb.domain

import com.example.kotlin_yandex_bulb.data.repository.MainRepository
import javax.inject.Inject

class SetBrightnessLevelUseCaseImpl @Inject constructor(
    private val repository: MainRepository,
): SetBrightnessLevelUseCase {
    override suspend fun invoke(level: Int): Result<Unit> =
        repository.setBrightnessLevel(level)
}