package com.example.kotlin_yandex_bulb.di

import com.example.kotlin_yandex_bulb.presenter.ui.MainFragment
import dagger.Component

@Component(
    modules = [ AppModule::class ]
)
interface AppComponent {
    fun inject(fragment: MainFragment)
}