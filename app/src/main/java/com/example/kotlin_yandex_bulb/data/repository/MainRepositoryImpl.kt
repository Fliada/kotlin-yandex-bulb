package com.example.kotlin_yandex_bulb.data.repository

import com.example.kotlin_yandex_bulb.data.ColorData
import com.example.kotlin_yandex_bulb.data.api.MainService
import retrofit2.HttpException
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val service: MainService,
) : MainRepository {
    override suspend fun getJokesCategories(): Result<List<ColorData>?> {
        kotlin.runCatching {
            service.getJokesCategories()
        }.fold(
            onSuccess = {
                return if (it.isSuccessful)
                    Result.success(it.body())
                else Result.failure(HttpException(it))
            },
            onFailure = {
                return Result.failure(it)
            }
        )
    }
}