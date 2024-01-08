package com.example.kotlin_yandex_bulb.presenter.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlin_yandex_bulb.UiState
import com.example.kotlin_yandex_bulb.data.BrightnessData
import com.example.kotlin_yandex_bulb.data.ColorData
import com.example.kotlin_yandex_bulb.domain.GetBrightnessLevelsUseCase
import com.example.kotlin_yandex_bulb.domain.GetColorsUseCase
import com.example.kotlin_yandex_bulb.domain.GetCurrentBrightnessUseCase
import com.example.kotlin_yandex_bulb.domain.GetCurrentColorUseCase
import com.example.kotlin_yandex_bulb.domain.GetCurrentStateUseCase
import com.example.kotlin_yandex_bulb.domain.SetBrightnessLevelUseCase
import com.example.kotlin_yandex_bulb.domain.SetColorUseCase
import com.example.kotlin_yandex_bulb.domain.TurnOffUseCase
import com.example.kotlin_yandex_bulb.domain.TurnOnUseCase
import com.example.kotlin_yandex_bulb.toUiState
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getColorsUseCase: GetColorsUseCase,
    private val setColorUseCase: SetColorUseCase,
    private val getCurrentColorUseCase: GetCurrentColorUseCase,
    private val getBrightnessLevelsUseCase: GetBrightnessLevelsUseCase,
    private val setCurrentBrightnessUseCase: SetBrightnessLevelUseCase,
    private val getCurrentBrightnessUseCase: GetCurrentBrightnessUseCase,
    private val turnOnUseCase: TurnOnUseCase,
    private val turnOffUseCase: TurnOffUseCase,
    private val getCurrentStateUseCase: GetCurrentStateUseCase
): ViewModel() {

    private val _liveData = MutableLiveData<UiState<List<ColorData>?>>()
    val liveData: LiveData<UiState<List<ColorData>?>>
        get() = _liveData

    private val _currentColor = MutableLiveData<UiState<ColorData?>>()
    val currentColor: LiveData<UiState<ColorData?>>
        get() = _currentColor

    private val _brightnessLevels = MutableLiveData<UiState<BrightnessData?>>()
    val brightnessLevels: LiveData<UiState<BrightnessData?>>
        get() = _brightnessLevels

    private val _currentBrightness = MutableLiveData<UiState<Int?>>()
    val currentBrightness: LiveData<UiState<Int?>>
        get() = _currentBrightness

    private val _currentState = MutableLiveData<UiState<Boolean?>>()
    val currentState: LiveData<UiState<Boolean?>>
        get() = _currentState


    fun loadData() {
        viewModelScope.launch {
            val colorsResult = getColorsUseCase()
            _liveData.postValue(colorsResult.toUiState())
        }
    }

    fun setColor(color: String) {
        viewModelScope.launch {
            setColorUseCase(color)
        }
    }

    private fun loadCurrentColor() {
        viewModelScope.launch {
            val currentColorResult = getCurrentColorUseCase()
            _currentColor.postValue(currentColorResult.toUiState())
        }
    }

    private fun loadBrightnessLevels() {
        viewModelScope.launch {
            val brightnessLevelsResult = getBrightnessLevelsUseCase()
            _brightnessLevels.postValue(brightnessLevelsResult.toUiState())
        }
    }

    private fun loadCurrentBrightness() {
        viewModelScope.launch {
            val currentBrightnessResult = getCurrentBrightnessUseCase()
            _currentBrightness.postValue(currentBrightnessResult.toUiState())
        }
    }

    fun setBrightnessLevel(level: Int) {
        viewModelScope.launch {
            setCurrentBrightnessUseCase(level)
        }
    }

    private fun loadCurrentState() {
        viewModelScope.launch {
            val currentStateResult = getCurrentStateUseCase()
            _currentState.postValue(currentStateResult.toUiState())
        }
    }

    fun turnOn() {
        viewModelScope.launch {
            turnOnUseCase()
        }
    }

    fun turnOff() {
        viewModelScope.launch {
            turnOffUseCase()
        }
    }
}