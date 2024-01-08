package com.example.kotlin_yandex_bulb.presenter.ui

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.kotlin_yandex_bulb.databinding.FragmentMainBinding
import com.example.kotlin_yandex_bulb.di.ViewModelFactory
import com.example.kotlin_yandex_bulb.di.appComponent
import com.example.kotlin_yandex_bulb.presenter.vm.MainViewModel
import javax.inject.Inject

class MainFragment : Fragment() {

    private val binding: FragmentMainBinding by viewBinding()

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: MainViewModel by viewModels() { viewModelFactory }

    override fun onAttach(context: Context) {
        context.appComponent.inject(this)
        super.onAttach(context)
    }
}