package com.utfpr.wearos.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.wear.compose.foundation.lazy.AutoCenteringParams
import androidx.wear.compose.foundation.lazy.ScalingLazyColumn
import androidx.wear.compose.material.Chip
import androidx.wear.compose.material.ChipDefaults
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.Scaffold
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.TimeText
import com.utfpr.wearos.presentation.theme.TrabalhoWearOSTheme
import androidx.compose.ui.res.painterResource
import com.utfpr.wearos.R

@Composable
fun HomeScreen(
    humidity: String,
    temperature: String,
    pression: String
) {
    TrabalhoWearOSTheme {
        Scaffold(
            timeText = { TimeText() }
        ) {
            ScalingLazyColumn(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp),
                autoCentering = AutoCenteringParams(itemIndex = 0)
            ) {
                item {
                    Chip(
                        modifier = Modifier.fillMaxWidth(0.9f),
                        onClick = { },
                        label = { Text("Umidade") },
                        secondaryLabel = { Text("$humidity %") },
                        icon = {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_water_drop),
                                contentDescription = "Umidade"
                            )
                        },
                        colors = ChipDefaults.primaryChipColors()
                    )
                }

                item {
                    Chip(
                        modifier = Modifier.fillMaxWidth(0.9f),
                        onClick = { },
                        label = { Text("Temperatura") },
                        secondaryLabel = { Text("$temperature °C") },
                        icon = {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_thermostat),
                                contentDescription = "Temperatura"
                            )
                        },
                        colors = ChipDefaults.primaryChipColors()
                    )
                }

                item {
                    Chip(
                        modifier = Modifier.fillMaxWidth(0.9f),
                        onClick = { },
                        label = { Text("Pressão") },
                        secondaryLabel = { Text("$pression hPa") },
                        icon = {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_speed),
                                contentDescription = "Pressão"
                            )
                        },
                        colors = ChipDefaults.primaryChipColors()
                    )
                }
            }
        }
    }
}

@Preview(
    device = Devices.WEAR_OS_LARGE_ROUND,
    showSystemUi = true
)
@Composable
fun HomeScreenPreview() {
    HomeScreen(
        humidity = "6",
        temperature = "5",
        pression = "5"
    )
}

