package com.example.kotlin_yandex_bulb.presenter.vm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlin_yandex_bulb.ColorDataUtils
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

    private val _backgroundBrightness = MutableLiveData<Int?>()
    val backgroundBrightness: MutableLiveData<Int?>
        get() = _backgroundBrightness

    private val _currentState = MutableLiveData<UiState<Boolean?>>()
    val currentState: LiveData<UiState<Boolean?>>
        get() = _currentState

    private val _backgroundState = MutableLiveData<Boolean?>()
    val backgroundState: LiveData<Boolean?>
        get() = _backgroundState

    private val _backgroundColor = MutableLiveData<String?>()
    val backgroundColor: MutableLiveData<String?>
        get() = _backgroundColor


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

    fun loadCurrentColor() {
        viewModelScope.launch {
            try {
                val currentColorResult = getCurrentColorUseCase()
                _currentColor.postValue(currentColorResult.toUiState())

                currentColorResult.onSuccess { colorData ->
                    val backgroundColor = colorData?.id?.let { ColorDataUtils.getColorHexCode(it) }
                    Log.d("MainViewModel", backgroundColor.toString())
                    if (colorData != null) {
                        Log.d("MainViewModel", colorData.id.toString())
                    }
                    _backgroundColor.postValue(backgroundColor)
                }

            } catch (e: Exception) {
                _currentColor.postValue(UiState.Failure(e.message ?: "Unknown error"))
            }
        }
    }

    fun loadBrightnessLevels() {
        viewModelScope.launch {
            val brightnessLevelsResult = getBrightnessLevelsUseCase()
            _brightnessLevels.postValue(brightnessLevelsResult.toUiState())
        }
    }

    fun loadCurrentBrightness() {
        viewModelScope.launch {
            try {
                val currentBrightnessResult = getCurrentBrightnessUseCase()
                _currentBrightness.postValue(currentBrightnessResult.toUiState())

                currentBrightnessResult.onSuccess {
                    if (it != null) {
                        _backgroundBrightness.postValue((it / 100.0 * 255).toInt())
                        Log.d("MainViewModel", "${it / 100.0 * 255}")
                    }
                }

            } catch (e: Exception) {
                _currentBrightness.postValue(UiState.Failure(e.message ?: "Unknown error"))
            }
        }
    }

    fun setBrightnessLevel(level: Int) {
        viewModelScope.launch {
            setCurrentBrightnessUseCase(level)
            loadCurrentBrightness()
        }
    }

    fun loadCurrentState() {
        viewModelScope.launch {
            try {
                val currentStateResult = getCurrentStateUseCase()
                _currentState.postValue(currentStateResult.toUiState())

                currentStateResult.onSuccess {
                    if (it != null)
                        _backgroundState.postValue(it)
                }

            }
            catch (e: Exception) {
                _currentBrightness.postValue(UiState.Failure(e.message ?: "Unknown error"))
            }
        }
    }

    fun toggleLight() {
        viewModelScope.launch {
            try {
                if (_currentState.value == null || (_currentState.value is UiState.Success && (_currentState.value as UiState.Success<Boolean?>)?.value == true)) {
                    turnOffUseCase()
                    Log.d("MainViewModel", "Turned off")
                } else {
                    Log.d("MainViewModel", _currentState.value.toString())
                    turnOnUseCase()
                    Log.d("MainViewModel", "Turned on")
                    Log.d("MainViewModel", _currentState.value.toString())
                }

                loadCurrentState()

            } catch (e: Exception) {
                _currentState.postValue(UiState.Failure(e.message ?: "Unknown error"))
            }
        }
    }
}