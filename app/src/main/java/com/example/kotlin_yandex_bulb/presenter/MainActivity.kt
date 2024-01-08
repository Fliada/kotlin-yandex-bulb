package com.example.kotlin_yandex_bulb.presenter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.kotlin_yandex_bulb.R
import com.example.kotlin_yandex_bulb.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val binding: ActivityMainBinding by viewBinding()

}