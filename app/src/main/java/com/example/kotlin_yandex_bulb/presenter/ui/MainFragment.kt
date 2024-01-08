package com.example.kotlin_yandex_bulb.presenter.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
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
import com.example.kotlin_yandex_bulb.presenter.vm.MainViewModel
import javax.inject.Inject

class MainFragment : Fragment(R.layout.fragment_main) {

    private val binding: FragmentMainBinding by viewBinding()

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: MainViewModel by viewModels() { viewModelFactory }

    private val adapter = MainAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
        viewModel.liveData.observe(viewLifecycleOwner) {
            onDataReceived(it)
        }
        viewModel.loadData()
    }

    private fun onDataReceived(colorsCategories: UiState<List<ColorData>?>?) {
        when(colorsCategories) {
            is UiState.Success -> {
                binding.mainRecycler.visibility = View.VISIBLE
                binding.sampleProgress.visibility = View.GONE
                binding.errorImage.visibility = View.GONE
                binding.errorTitle.visibility = View.GONE
                binding.errorSubtitle.visibility = View.GONE
                colorsCategories.value?.let { adapter.submitList(it) }
            }
            is UiState.Failure -> {
                binding.mainRecycler.visibility = View.GONE
                binding.sampleProgress.visibility = View.GONE
                binding.errorImage.visibility = View.VISIBLE
                binding.errorTitle.visibility = View.VISIBLE
                binding.errorSubtitle.visibility = View.VISIBLE
                binding.errorSubtitle.text = colorsCategories.message
            }
            is UiState.Loading -> {
                binding.mainRecycler.visibility = View.GONE
                binding.sampleProgress.visibility = View.VISIBLE
                binding.errorImage.visibility = View.GONE
                binding.errorTitle.visibility = View.GONE
                binding.errorSubtitle.visibility = View.GONE
            }
            else -> {}
        }
    }

    private fun initRecycler() = with(binding.mainRecycler) {
        layoutManager = LinearLayoutManager(requireContext())
        adapter = this@MainFragment.adapter
    }

    override fun onAttach(context: Context) {
        context.appComponent.inject(this)
        Log.d("MainFragment", "On attach method")
        super.onAttach(context)
    }
}