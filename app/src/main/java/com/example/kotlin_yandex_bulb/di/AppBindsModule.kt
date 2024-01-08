package com.example.kotlin_yandex_bulb.di

import com.example.kotlin_yandex_bulb.data.repository.MainRepository
import com.example.kotlin_yandex_bulb.data.repository.MainRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
interface AppBindsModule {
    @Binds
    fun bindMainRepository(repository: MainRepositoryImpl): MainRepository
}