package com.example.kotlin_yandex_bulb.domain

import com.example.kotlin_yandex_bulb.data.ColorData
import com.example.kotlin_yandex_bulb.data.repository.MainRepository
import javax.inject.Inject

class GetColorsUseCaseImpl @Inject constructor(
    private val repository: MainRepository,
): GetColorsUseCase {
    override suspend fun invoke(): Result<List<ColorData>?> =
        repository.getColors()

}