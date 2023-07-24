package com.nemo.fireworks.ui.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
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
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlin.math.sqrt

@Composable
fun Fireworks(
    modifier: Modifier = Modifier,
    percent: Float,
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        FlowerPetalsRing(
            flowerPetalRectSize = Size(100f, 100f),
            numberOfFlowerPetal = 16,
            flowerPetalColor = Color.Red,
            percent = percent,
        )

        FlowerPetalsRing(
            modifier = Modifier
                .padding(32.dp),
            flowerPetalRectSize = Size(100f, 100f),
            numberOfFlowerPetal = 12,
            flowerPetalColor = Color.Blue,
            percent = percent,
        )

        FlowerPetalsRing(
            modifier = Modifier
                .padding(64.dp),
            flowerPetalRectSize = Size(100f, 100f),
            numberOfFlowerPetal = 11,
            flowerPetalColor = Color.Yellow,
            percent = percent,
        )

        FlowerPetalsRing(
            modifier = Modifier
                .padding(96.dp),
            flowerPetalRectSize = Size(100f, 100f),
            numberOfFlowerPetal = 7,
            flowerPetalColor = Color.Green,
            percent = percent,
        )

        FlowerPetalsRing(
            modifier = Modifier
                .padding(128.dp),
            flowerPetalRectSize = Size(100f, 100f),
            numberOfFlowerPetal = 3,
            flowerPetalColor = Color.Cyan,
            percent = percent,
        )
    }
}

@Composable
private fun FlowerPetalsRing(
    modifier: Modifier = Modifier,
    flowerPetalRectSize: Size,
    numberOfFlowerPetal: Int,
    flowerPetalColor: Color,
    percent: Float = 1.0f,
) {
    BoxWithConstraints(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(1f)
    ) {
        val pieceLength = with(LocalDensity.current) { constraints.maxWidth.toDp() }.value
        val padding = (sqrt(2f) - 1) / 2 * pieceLength

        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding.dp)
        ) {
            for (i in 0 until numberOfFlowerPetal) {
                val angle = (360 / numberOfFlowerPetal * i).toFloat()
                drawFlowerPetal(
                    size = flowerPetalRectSize,
                    color = flowerPetalColor,
                    angle = angle,
                    displayRatio = percent,
                )
            }
        }
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
    Column {
        Fireworks(
            modifier = Modifier
                .wrapContentHeight()
                .background(Color.Black),
            percent = 0.5f,
        )

        Fireworks(
            modifier = Modifier
                .wrapContentHeight()
                .background(Color.Black),
            percent = 0.8f,
        )
    }
}

@Preview
@Composable
fun FlowerPetalsRingPreview() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .background(Color.Black),
        contentAlignment = Alignment.Center,
    ) {
        FlowerPetalsRing(
            flowerPetalRectSize = Size(100f, 100f),
            numberOfFlowerPetal = 12,
            flowerPetalColor = Color.Red,
            percent = 0.6f
        )
    }
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
                displayRatio = 0.5f,
            )
        }
    }
}