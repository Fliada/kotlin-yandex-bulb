package com.example.kotlin_yandex_bulb.data.repository

import android.util.Log
import com.example.kotlin_yandex_bulb.data.BrightnessData
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

    override suspend fun setColor(color: String): Result<Unit> {
        kotlin.runCatching {
            Log.d("MainRepositoryImpl", color)
            service.setColor(color)
        }.fold(
            onSuccess = {
                Log.d("MainRepositoryImpl", "${it.isSuccessful}")

                return if (it.isSuccessful)
                    Result.success(Unit)
                else Result.failure(HttpException(it))
            },
            onFailure = {
                Log.d("MainRepositoryImpl", "${it.stackTrace[1]}")

                return Result.failure(it)
            }
        )
    }

    override suspend fun getCurrentColor(): Result<ColorData?> {
        kotlin.runCatching {
            service.getCurrentColor()
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

    override suspend fun getBrightnessLevels(): Result<BrightnessData?> {
        kotlin.runCatching {
            service.getBrightnessLevels()
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

    override suspend fun getCurrentBrightnessLevel(): Result<Int?> {
        kotlin.runCatching {
            service.getCurrentBrightnessLevel()
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

    override suspend fun setBrightnessLevel(level: Int): Result<Unit> {
        kotlin.runCatching {
            service.setBrightnessLevel(level)
        }.fold(
            onSuccess = {
                return if (it.isSuccessful)
                    Result.success(Unit)
                else Result.failure(HttpException(it))
            },
            onFailure = {
                return Result.failure(it)
            }
        )
    }

    override suspend fun turnOn(): Result<Unit> {
        kotlin.runCatching {
            service.turnOn()
        }.fold(
            onSuccess = {
                Log.d("Lag", "Success")
                return if (it.isSuccessful)
                    Result.success(Unit)
                else Result.failure(HttpException(it))
            },
            onFailure = {
                Log.d("Lag", "Failure")
                return Result.failure(it)
            }
        )
    }

    override suspend fun turnOff(): Result<Unit> {
        kotlin.runCatching {
            service.turnOff()
        }.fold(
            onSuccess = {
                return if (it.isSuccessful)
                    Result.success(Unit)
                else Result.failure(HttpException(it))
            },
            onFailure = {
                return Result.failure(it)
            }
        )
    }

    override suspend fun getCurrentState(): Result<Boolean?> {
        kotlin.runCatching {
            service.getCurrentState()
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