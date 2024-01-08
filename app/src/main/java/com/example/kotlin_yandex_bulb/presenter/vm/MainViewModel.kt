package com.example.kotlin_yandex_bulb.presenter.vm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlin_yandex_bulb.UiState
import com.example.kotlin_yandex_bulb.data.ColorData
import com.example.kotlin_yandex_bulb.domain.GetColorsUseCase
import com.example.kotlin_yandex_bulb.toUiState
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getColorsUseCase: GetColorsUseCase,
): ViewModel() {

    private val _liveData = MutableLiveData<UiState<List<ColorData>?>>()
    val liveData: LiveData<UiState<List<ColorData>?>>
        get() = _liveData

    fun loadData() {
        viewModelScope.launch {
            val colorsResult = getColorsUseCase()
            _liveData.postValue(colorsResult.toUiState())
        }
    }
}