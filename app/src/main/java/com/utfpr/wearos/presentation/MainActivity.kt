/* While this template provides a good starting point for using Wear Compose, you can always
 * take a look at https://github.com/android/wear-os-samples/tree/main/ComposeStarter to find the
 * most up to date changes to the libraries and their usages.
 */

package com.utfpr.wearos.presentation

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.utfpr.wearos.presentation.screens.HomeScreen
import com.utfpr.wearos.viewModel.SensorsViewModel

class MainActivity : ComponentActivity() {
    private lateinit var sensorManager: SensorManager

    private var pressureSensor: Sensor? = null
    private var temperatureSensor: Sensor? = null
    private var humiditySensor: Sensor? = null

    private var pressureSensorListener: SensorEventListener? = null
    private var temperatureSensorListener: SensorEventListener? = null
    private var humiditySensorListener: SensorEventListener? = null

    private val sensorsViewModel = SensorsViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()

        super.onCreate(savedInstanceState)

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        pressureSensor = sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE)
        humiditySensor = sensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY)
        temperatureSensor = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE)

        setTheme(android.R.style.Theme_DeviceDefault)

        setContent {
            val temperatureState by remember { sensorsViewModel.temperature }
            val pressureState by remember { sensorsViewModel.pressure }
            val humidityState by remember { sensorsViewModel.humidity }

            LaunchedEffect(Unit) {
                val temperatureListener = object : SensorEventListener {
                    override fun onAccuracyChanged(
                        sensor: Sensor?,
                        accuracy: Int
                    ) {
                    }

                    override fun onSensorChanged(event: SensorEvent?) {
                        event?.values?.firstOrNull()?.let { value ->
                            sensorsViewModel.updateTemperature(value.toInt())
                        }
                    }
                }

                val pressureListener = object : SensorEventListener {
                    override fun onAccuracyChanged(
                        sensor: Sensor?,
                        accuracy: Int
                    ) {
                    }

                    override fun onSensorChanged(event: SensorEvent?) {
                        event?.values?.firstOrNull()?.let { value ->
                            sensorsViewModel.updatePressure(value.toInt())
                        }
                    }
                }

                val humidityListener = object : SensorEventListener {
                    override fun onAccuracyChanged(
                        sensor: Sensor?,
                        accuracy: Int
                    ) {
                    }

                    override fun onSensorChanged(event: SensorEvent?) {
                        event?.values?.firstOrNull()?.let { value ->
                            sensorsViewModel.updateHumidity(value.toInt())
                        }
                    }
                }

                pressureSensorListener = pressureListener
                temperatureSensorListener = temperatureListener
                humiditySensorListener = humidityListener

                pressureSensor?.let {
                    sensorManager.registerListener(
                        pressureSensorListener,
                        it,
                        SensorManager.SENSOR_DELAY_NORMAL
                    )
                }

                temperatureSensor?.let {
                    sensorManager.registerListener(
                        temperatureSensorListener,
                        it,
                        SensorManager.SENSOR_DELAY_NORMAL
                    )
                }

                humiditySensor?.let {
                    sensorManager.registerListener(
                        humiditySensorListener,
                        it,
                        SensorManager.SENSOR_DELAY_NORMAL
                    )
                }


            }

            DisposableEffect(Unit) {
                onDispose {
                    humiditySensorListener?.let {
                        sensorManager.unregisterListener(it)
                    }
                    temperatureSensorListener?.let {
                        sensorManager.unregisterListener(it)
                    }
                    pressureSensorListener?.let {
                        sensorManager.unregisterListener(it)
                    }
                }

            }


            HomeScreen(
                humidityState.toString(),
                temperatureState.toString(),
                pressureState.toString()
            )
        }
    }
}
