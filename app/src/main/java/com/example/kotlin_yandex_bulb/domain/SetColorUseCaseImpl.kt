package com.example.kotlin_yandex_bulb.domain

import com.example.kotlin_yandex_bulb.data.ColorData
import com.example.kotlin_yandex_bulb.data.repository.MainRepository
import javax.inject.Inject

class SetColorUseCaseImpl @Inject constructor(
    private val repository: MainRepository,
): SetColorUseCase {

    override suspend fun invoke(color: String): Result<Unit> {
        return repository.setColor(color)
    }
}