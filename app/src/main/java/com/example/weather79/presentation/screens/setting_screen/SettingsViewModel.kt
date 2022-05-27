package com.example.weather79.presentation.screens.setting_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather79.model.Unit
import com.example.weather79.repository.WeatherDbRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val repository: WeatherDbRepository
) : ViewModel() {


    private val _unitList = MutableStateFlow<List<Unit>>(emptyList())
    val unitList: StateFlow<List<Unit>> = _unitList.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getUnits().distinctUntilChanged().collect { listOfUnit ->
                if (listOfUnit.isNullOrEmpty()) {
                    _unitList.value = emptyList()
                } else {
                    _unitList.value = listOfUnit
                }
            }
        }
    }

    fun insertUnit(unit: Unit) = viewModelScope.launch {
        repository.insertUnit(unit)
    }

    fun updateUnit(unit: Unit) = viewModelScope.launch {
        repository.updateUnit(unit)
    }

    fun deleteUnit(unit: Unit) = viewModelScope.launch {
        repository.deleteUnit(unit)
    }

    fun deleteAllUnits() = viewModelScope.launch {
        repository.deleteAllUnits()
    }


}