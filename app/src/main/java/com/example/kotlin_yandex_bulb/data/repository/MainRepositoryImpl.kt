package com.example.kotlin_yandex_bulb.data.repository

import android.util.Log
import com.example.kotlin_yandex_bulb.data.ColorData
import com.example.kotlin_yandex_bulb.data.api.MainService
import retrofit2.HttpException
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val service: MainService,
) : MainRepository {
    override suspend fun getColors(): Result<List<ColorData>?> {
        kotlin.runCatching {
            service.getColors()
        }.fold(
            onSuccess = {
                Log.d("MainRepositoryImpl", "${it.isSuccessful}")

                return if (it.isSuccessful)
                    Result.success(it.body())
                else Result.failure(HttpException(it))
            },
            onFailure = {
                Log.d("MainRepositoryImpl", "${it.stackTrace[1]}")

                return Result.failure(it)
            }
        )
    }
}