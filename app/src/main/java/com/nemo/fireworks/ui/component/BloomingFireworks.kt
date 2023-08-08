package com.nemo.fireworks.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Slider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment

@Composable
fun BloomingFireworks() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        var percent by remember { mutableStateOf(0.0f) }

        Fireworks(percent = percent)

        Slider(
            modifier = Modifier.padding(horizontal = 16.dp),
            value = percent,
            onValueChange = { newValue ->
                percent = newValue
            },
            valueRange = 0.0f..1.0f,
        )
    }
}

@Preview
@Composable
fun BloomingFireworksPreview() {
    BloomingFireworks()
}