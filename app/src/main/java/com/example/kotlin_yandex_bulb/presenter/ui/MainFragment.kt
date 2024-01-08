package com.example.kotlin_yandex_bulb.presenter.ui

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.SeekBar
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.kotlin_yandex_bulb.ColorUtils
import com.example.kotlin_yandex_bulb.R
import com.example.kotlin_yandex_bulb.UiState
import com.example.kotlin_yandex_bulb.data.ColorData
import com.example.kotlin_yandex_bulb.databinding.FragmentMainBinding
import com.example.kotlin_yandex_bulb.di.ViewModelFactory
import com.example.kotlin_yandex_bulb.di.appComponent
import com.example.kotlin_yandex_bulb.presenter.SliderSelectionListener
import com.example.kotlin_yandex_bulb.presenter.vm.MainViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainFragment : Fragment(R.layout.fragment_main), SliderSelectionListener<List<ColorData>> {

    private val binding: FragmentMainBinding by viewBinding()

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: MainViewModel by viewModels() { viewModelFactory }

    private val colorSpinner: Spinner by lazy { binding.colorSpinner }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.liveData.observe(viewLifecycleOwner) {
            onDataReceived(it)
        }

        viewModel.backgroundColor.observe(viewLifecycleOwner) { backgroundColor ->
            backgroundColor?.let {
                binding.container.setBackgroundColor(Color.parseColor(it))
            }
        }

        binding.icBulb.setOnClickListener() {
            viewModel.toggleLight()
        }

        viewModel.loadData()
    }

    override fun setupColorSpinner(colors: List<ColorData>) {
        val colorNames = colors.map { it.color }

        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, colorNames)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        colorSpinner.adapter = adapter

        colorSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                // Обработка выбора цвета
                val selectedColor = colors[position]

                Log.d("MainFragment", "Selected color: ${selectedColor.name}")

                viewModel.setColor(selectedColor.color)

                viewModel.viewModelScope.launch {
                    delay(500)
                    viewModel.loadCurrentColor()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }

        binding.brightnessSeekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                // Обработка изменения яркости
                viewModel.setBrightnessLevel(progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                // Действия при начале отслеживания касания
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                // Действия при завершении отслеживания касания
            }
        })
    }

    private fun onDataReceived(colorsCategories: UiState<List<ColorData>?>?) {
        when(colorsCategories) {
            is UiState.Success -> {
                binding.container.visibility = View.VISIBLE
                binding.sampleProgress.visibility = View.GONE
                binding.errorImage.visibility = View.GONE
                binding.errorTitle.visibility = View.GONE
                binding.errorSubtitle.visibility = View.GONE
                colorsCategories.value?.let {colorList ->
                    setupColorSpinner(colorList)
                }
            }
            is UiState.Failure -> {
                binding.container.visibility = View.GONE
                binding.sampleProgress.visibility = View.GONE
                binding.errorImage.visibility = View.VISIBLE
                binding.errorTitle.visibility = View.VISIBLE
                binding.errorSubtitle.visibility = View.VISIBLE
                binding.errorSubtitle.text = colorsCategories.message
            }
            is UiState.Loading -> {
                binding.container.visibility = View.GONE
                binding.sampleProgress.visibility = View.VISIBLE
                binding.errorImage.visibility = View.GONE
                binding.errorTitle.visibility = View.GONE
                binding.errorSubtitle.visibility = View.GONE
            }
            else -> {}
        }
    }

    override fun onAttach(context: Context) {
        context.appComponent.inject(this)
        Log.d("MainFragment", "On attach method")
        super.onAttach(context)
    }
}