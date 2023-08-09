package com.nemo.fireworks.ui.component

import androidx.annotation.IntRange
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun Fireworks(
    modifier: Modifier = Modifier,
    uiModel: FireworksUiModel = FireworksUiModel.DEFAULT,
) {
    BoxWithConstraints(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        for (index in 0 until uiModel.numberOfRings) {
            val displayMagnification = uiModel.getFireworksRingDisplayMagnification(index)
            Canvas(
                modifier = Modifier
                    .size(minOf(maxWidth, maxHeight) * displayMagnification)
            ) {
                val numberOfFlowerPetals = uiModel.getNumberOfFlowerPetals(index)
                for (i in 0 until numberOfFlowerPetals) {
                    val angle = (360 / numberOfFlowerPetals * i).toFloat()
                    drawFlowerPetal(
                        size = uiModel.flowerPetalRectSize,
                        color = uiModel.getColor(index),
                        angle = angle,
                        displayRatio = uiModel.flowerPetalsDisplayRatio,
                    )
                }
            }
        }
    }
}

data class FireworksUiModel(
    @IntRange(from = 2, to = 12)
    val numberOfRings: Int,
    val flowerPetalRectSize: Size,
    val flowerPetalsDisplayRatio: Float,
) {
    companion object {
        val DEFAULT = FireworksUiModel(
            numberOfRings = 6,
            flowerPetalRectSize = Size(100f, 100f),
            flowerPetalsDisplayRatio = 1.0f,
        )
        private val colors = listOf(
            Color.Cyan,
            Color.Green,
            Color.Yellow,
            Color.Blue,
            Color.Red,
            Color.Magenta,
        )
        private const val primaryNumberOfFlowerPetal = 3
        private const val primaryRingDisplayMagnification = 0.2f
        private const val maxRingDisplayMagnification = 0.7f
    }

    fun getColor(index: Int): Color {
        return colors[index % colors.size]
    }

    fun getNumberOfFlowerPetals(index: Int): Int {
        return primaryNumberOfFlowerPetal + index * 2
    }

    fun getFireworksRingDisplayMagnification(index: Int): Float {
        return (maxRingDisplayMagnification - primaryRingDisplayMagnification) * (index.toFloat() / (numberOfRings - 1)) + primaryRingDisplayMagnification
    }
}

private fun DrawScope.drawFlowerPetal(
    size: Size,
    color: Color,
    angle: Float = 0f,
    displayRatio: Float = 1.0f,
) {
    rotate(degrees = - angle) {
        val path = Path().apply {
            moveTo(0f, 0f)
            quadraticBezierTo(
                 size.width * 5 / 6,
                size.height * 1 / 6,
                size.width,
                size.height,
            )
            moveTo(size.width, size.height)
            quadraticBezierTo(
                size.width * 1 / 6,
                size.height * 5 / 6,
                0f,
                0f,
            )
        }
        clipPath(path = path) {
            val triangleWidth = size.width - (2 * size.width * displayRatio)
            val triangleHeight = size.height - (2 * size.height * displayRatio)

            val trianglePath = Path().apply {
                moveTo(size.width, triangleHeight)
                lineTo(size.width, size.height)
                lineTo(triangleWidth, size.height)
                close()
            }

            drawPath(
                path = trianglePath,
                color = color,
                style = Fill
            )
        }
    }
}

@Preview
@Composable
fun FireworksPreview() {
    Fireworks(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        uiModel = FireworksUiModel(
            numberOfRings = 6,
            flowerPetalRectSize = Size(100f, 100f),
            flowerPetalsDisplayRatio = 0.5f,
        ),
    )
}

@Preview
@Composable
fun FlowerPetalPreview() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .background(Color.Black),
        contentAlignment = Alignment.Center,
    ) {
        Canvas(
            modifier = Modifier
        ) {
            drawFlowerPetal(
                size = Size(160f, 160f),
                color = Color.Red,
            )
        }
    }
}