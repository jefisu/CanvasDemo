package com.jefisu.canvasdemo.draw

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jefisu.canvasdemo.drawBackground

@Composable
fun DottedEllipse(
    maxAngle: Int,
    modifier: Modifier = Modifier
) {

    Canvas(
        modifier = modifier
    ) {
        drawBackground(strokeWidth = 0.2f)

        val pointerA = center
        val pointerB = Offset(0f, center.y)
        (0..maxAngle).forEach { angle ->
            rotate(angle.toFloat()) {
                drawLine(
                    brush = Brush.linearGradient(
                        colors = listOf(Color.Red, Color.Blue, Color.Green)
                    ),
                    start = pointerA,
                    end = pointerB
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewDottedEllipse() {
    DottedEllipse(
        maxAngle = 360,
        modifier = Modifier
            .aspectRatio(1f)
            .padding(16.dp)
    )
}