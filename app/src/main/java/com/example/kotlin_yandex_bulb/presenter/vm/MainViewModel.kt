package com.example.kotlin_yandex_bulb.presenter.vm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlin_yandex_bulb.ColorUtils
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

                // Дополнительно обновляем цвет фона контейнера при каждом изменении цвета
                currentColorResult.onSuccess { colorData ->
                    val backgroundColor = colorData?.id?.let { ColorUtils.getColorHexCode(it) }
                    Log.d("MainViewModel", backgroundColor.toString())
                    if (colorData != null) {
                        Log.d("MainViewModel", colorData.id.toString())
                    }
                    _backgroundColor.postValue(backgroundColor)
                }

            } catch (e: Exception) {
                // Обработка ошибок
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
            val currentBrightnessResult = getCurrentBrightnessUseCase()
            _currentBrightness.postValue(currentBrightnessResult.toUiState())
        }
    }

    fun setBrightnessLevel(level: Int) {
        viewModelScope.launch {
            setCurrentBrightnessUseCase(level)
        }
    }

    fun loadCurrentState() {
        viewModelScope.launch {
            val currentStateResult = getCurrentStateUseCase()
            _currentState.postValue(currentStateResult.toUiState())
        }
    }

    fun toggleLight() {
        viewModelScope.launch {
            try {
                // Выполняем запрос на сервер для переключения состояния лампы
                if (_currentState.value is UiState.Success && (_currentState.value as UiState.Success<Boolean?>)?.value == true) {
                    // Если лампа включена, выключаем
                    turnOffUseCase()
                } else {
                    // Если лампа выключена или состояние не определено, включаем
                    turnOnUseCase()
                }

                loadCurrentColor()

            } catch (e: Exception) {
                // Обработка ошибок
                _currentState.postValue(UiState.Failure(e.message ?: "Unknown error"))
            }
        }
    }
}