package com.example.kotlin_yandex_bulb.di

import com.example.kotlin_yandex_bulb.data.repository.MainRepository
import com.example.kotlin_yandex_bulb.data.repository.MainRepositoryImpl
import com.example.kotlin_yandex_bulb.domain.GetBrightnessLevelsUseCase
import com.example.kotlin_yandex_bulb.domain.GetBrightnessLevelsUseCaseImpl
import com.example.kotlin_yandex_bulb.domain.GetColorsUseCase
import com.example.kotlin_yandex_bulb.domain.GetColorsUseCaseImpl
import com.example.kotlin_yandex_bulb.domain.GetCurrentBrightnessUseCase
import com.example.kotlin_yandex_bulb.domain.GetCurrentBrightnessUseCaseImpl
import com.example.kotlin_yandex_bulb.domain.GetCurrentColorUseCase
import com.example.kotlin_yandex_bulb.domain.GetCurrentColorUseCaseImpl
import com.example.kotlin_yandex_bulb.domain.GetCurrentStateUseCase
import com.example.kotlin_yandex_bulb.domain.GetCurrentStateUseCaseImpl
import com.example.kotlin_yandex_bulb.domain.SetBrightnessLevelUseCase
import com.example.kotlin_yandex_bulb.domain.SetBrightnessLevelUseCaseImpl
import com.example.kotlin_yandex_bulb.domain.SetColorUseCase
import com.example.kotlin_yandex_bulb.domain.SetColorUseCaseImpl
import com.example.kotlin_yandex_bulb.domain.TurnOffUseCase
import com.example.kotlin_yandex_bulb.domain.TurnOffUseCaseImpl
import com.example.kotlin_yandex_bulb.domain.TurnOnUseCase
import com.example.kotlin_yandex_bulb.domain.TurnOnUseCaseImpl
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

    @Binds
    fun bindGetCurrentColorUseCase(useCase: GetCurrentColorUseCaseImpl): GetCurrentColorUseCase

    @Binds
    fun bindGetBrightnessLevelsUseCase(useCase: GetBrightnessLevelsUseCaseImpl): GetBrightnessLevelsUseCase

    @Binds
    fun bindSetBrightnessLevelUseCase(useCase: SetBrightnessLevelUseCaseImpl): SetBrightnessLevelUseCase

    @Binds
    fun bindGetCurrentBrightnessLevelUseCase(useCase: GetCurrentBrightnessUseCaseImpl): GetCurrentBrightnessUseCase

    @Binds
    fun bindTurnOnUseCase(useCase: TurnOnUseCaseImpl): TurnOnUseCase

    @Binds
    fun bindTurnOffUseCase(useCase: TurnOffUseCaseImpl): TurnOffUseCase

    @Binds
    fun bindGetCurrentStateUseCase(useCase: GetCurrentStateUseCaseImpl): GetCurrentStateUseCase
}