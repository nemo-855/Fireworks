package com.nemo.fireworks.ui.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
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
    percent: Float,
    flowerPetalRectSize: Size,
) {
    BoxWithConstraints(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        val fireworksFrameSize = minOf(
            maxWidth,
            maxHeight,
        )

        FlowerPetalsRing(
            modifier = Modifier
                .size(fireworksFrameSize * 0.7f),
            flowerPetalRectSize = flowerPetalRectSize,
            numberOfFlowerPetal = 20,
            flowerPetalColor = Color.Magenta,
            percent = percent,
        )

        FlowerPetalsRing(
            modifier = Modifier
                .size(fireworksFrameSize * 0.6f),
            flowerPetalRectSize = flowerPetalRectSize,
            numberOfFlowerPetal = 16,
            flowerPetalColor = Color.Red,
            percent = percent,
        )

        FlowerPetalsRing(
            modifier = Modifier
                .size(fireworksFrameSize * 0.5f),
            flowerPetalRectSize = flowerPetalRectSize,
            numberOfFlowerPetal = 12,
            flowerPetalColor = Color.Blue,
            percent = percent,
        )

        FlowerPetalsRing(
            modifier = Modifier
                .size(fireworksFrameSize * 0.4f),
            flowerPetalRectSize = flowerPetalRectSize,
            numberOfFlowerPetal = 11,
            flowerPetalColor = Color.Yellow,
            percent = percent,
        )

        FlowerPetalsRing(
            modifier = Modifier
                .size(fireworksFrameSize * 0.3f),
            flowerPetalRectSize = flowerPetalRectSize,
            numberOfFlowerPetal = 7,
            flowerPetalColor = Color.Green,
            percent = percent,
        )

        FlowerPetalsRing(
            modifier = Modifier
                .size(fireworksFrameSize * 0.2f),
            flowerPetalRectSize = flowerPetalRectSize,
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
    Canvas(
        modifier = modifier
            .fillMaxSize()
            .aspectRatio(1f)
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
            .wrapContentSize()
            .background(Color.Black),
        percent = 0.5f,
        flowerPetalRectSize = Size(20f, 20f),
    )
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