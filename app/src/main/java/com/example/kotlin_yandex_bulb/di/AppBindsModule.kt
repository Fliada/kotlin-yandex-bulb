package com.example.kotlin_yandex_bulb.di

import com.example.kotlin_yandex_bulb.data.repository.MainRepository
import com.example.kotlin_yandex_bulb.data.repository.MainRepositoryImpl
import com.example.kotlin_yandex_bulb.domain.GetColorsUseCase
import com.example.kotlin_yandex_bulb.domain.GetColorsUseCaseImpl
import com.example.kotlin_yandex_bulb.domain.SetColorUseCase
import com.example.kotlin_yandex_bulb.domain.SetColorUseCaseImpl
import dagger.Binds
import dagger.Module

@Module
interface AppBindsModule {
    @Binds
    fun bindMainRepository(repository: MainRepositoryImpl): MainRepository

    @Binds
    fun bindColorsUseCase(useCase: GetColorsUseCaseImpl): GetColorsUseCase

    @Binds
    fun bindSetColorUseCase(useCase: SetColorUseCaseImpl): SetColorUseCase
}