package com.example.kotlin_yandex_bulb.presenter.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class MainViewModel @Inject constructor(

): ViewModel() {

    private val _liveData = MutableLiveData<List<String>>()
    val liveData: LiveData<List<String>>
        get() = _liveData

    fun loadData() {
        val data = (0..20).map { "Item #$it" }
        _liveData.postValue(data)
    }

}