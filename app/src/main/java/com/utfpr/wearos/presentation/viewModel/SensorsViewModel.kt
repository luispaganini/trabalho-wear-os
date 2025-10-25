
package com.utfpr.wearos.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class SensorsViewModel : ViewModel(){
    private val temperatureViewModel = mutableStateOf(0)
    private val pressureViewModel = mutableStateOf(0)
    private val humidityViewModel = mutableStateOf(0)

    val temperature: State<Int> get() = temperatureViewModel
    val pressure: State<Int> get() = pressureViewModel
    val humidity: State<Int> get() = humidityViewModel

    private var lastUpdateTemperature = 0L
    private var lastUpdatePressure = 0L
    private var lastUpdateHumidity = 0L

    fun updateTemperature(newTemperature:Int){
        val now = System.currentTimeMillis()

        if(now - lastUpdateTemperature > 1000){
            temperatureViewModel.value = newTemperature
            lastUpdateTemperature = now
        }
    }

     fun updatePressure(newPressure:Int){
        val now = System.currentTimeMillis()

        if(now - lastUpdatePressure > 1000){
            pressureViewModel.value = newPressure
            lastUpdatePressure = now
        }
    }

     fun updateHumidity(newHumidity:Int){
        val now = System.currentTimeMillis()

        if(now - lastUpdateHumidity > 1000){
            humidityViewModel.value = newHumidity
            lastUpdateHumidity = now
        }
    }

}