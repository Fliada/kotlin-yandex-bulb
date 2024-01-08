package com.example.kotlin_yandex_bulb.di

import com.example.kotlin_yandex_bulb.data.api.MainService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class NetworkModule {

    @Provides
    fun provideMainService(): MainService =
        Retrofit.Builder()
            .baseUrl("http://195.54.14.121:8086/swagger-ui/index.html/")
            .build()
            .create(MainService::class.java)

}