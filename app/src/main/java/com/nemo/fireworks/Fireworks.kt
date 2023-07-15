package com.nemo.fireworks

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlin.math.sqrt

@Composable
fun Fireworks(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        FlowerPetalsRing(
            flowerPetalRectSize = Size(100f, 100f),
            numberOfFlowerPetal = 16,
            flowerPetalColor = Color.Red,
        )

        FlowerPetalsRing(
            modifier = Modifier
                .padding(32.dp),
            flowerPetalRectSize = Size(100f, 100f),
            numberOfFlowerPetal = 12,
            flowerPetalColor = Color.Blue,
        )

        FlowerPetalsRing(
            modifier = Modifier
                .padding(64.dp),
            flowerPetalRectSize = Size(100f, 100f),
            numberOfFlowerPetal = 11,
            flowerPetalColor = Color.Yellow,
        )

        FlowerPetalsRing(
            modifier = Modifier
                .padding(96.dp),
            flowerPetalRectSize = Size(100f, 100f),
            numberOfFlowerPetal = 7,
            flowerPetalColor = Color.Green,
        )

        FlowerPetalsRing(
            modifier = Modifier
                .padding(128.dp),
            flowerPetalRectSize = Size(100f, 100f),
            numberOfFlowerPetal = 3,
            flowerPetalColor = Color.Cyan,
        )
    }
}

@Composable
private fun FlowerPetalsRing(
    modifier: Modifier = Modifier,
    flowerPetalRectSize: Size,
    numberOfFlowerPetal: Int,
    flowerPetalColor: Color,
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
                    angle = angle
                )
            }
        }
    }
}

private fun DrawScope.drawFlowerPetal(
    size: Size,
    color: Color,
    angle: Float = 0f
) {
    rotate(degrees = angle) {
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
        drawPath(
            path = path,
            color = color,
            style = Stroke(width = 4f),
        )
    }
}

@Preview
@Composable
fun FirewoRrksPreview() {
    Fireworks(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    )
}