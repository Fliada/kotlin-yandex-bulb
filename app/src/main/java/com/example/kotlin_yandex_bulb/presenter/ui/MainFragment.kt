package com.example.kotlin_yandex_bulb.presenter.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.kotlin_yandex_bulb.R
import com.example.kotlin_yandex_bulb.UiState
import com.example.kotlin_yandex_bulb.data.ColorData
import com.example.kotlin_yandex_bulb.databinding.FragmentMainBinding
import com.example.kotlin_yandex_bulb.di.ViewModelFactory
import com.example.kotlin_yandex_bulb.di.appComponent
import com.example.kotlin_yandex_bulb.presenter.SliderSelectionListener
import com.example.kotlin_yandex_bulb.presenter.vm.MainViewModel
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
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }
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