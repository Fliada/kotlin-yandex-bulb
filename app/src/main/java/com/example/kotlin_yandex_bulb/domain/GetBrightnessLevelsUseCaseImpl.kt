package com.example.kotlin_yandex_bulb.domain

import com.example.kotlin_yandex_bulb.data.BrightnessData
import com.example.kotlin_yandex_bulb.data.repository.MainRepository
import javax.inject.Inject

class GetBrightnessLevelsUseCaseImpl @Inject constructor(
    private val repository: MainRepository,
) : GetBrightnessLevelsUseCase {
    override suspend fun invoke(): Result<BrightnessData?> =
        repository.getBrightnessLevels()
}