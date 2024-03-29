package com.example.kotlin_yandex_bulb.di

import dagger.Module

@Module(
    includes = [
        ViewModelModule::class,
        NetworkModule::class,
        AppBindsModule::class,
    ]
)
class AppModule