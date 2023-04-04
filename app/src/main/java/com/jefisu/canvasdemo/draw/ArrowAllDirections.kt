package com.jefisu.canvasdemo.draw

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jefisu.canvasdemo.drawBackground

@Composable
fun ArrowAllDirections(
    modifier: Modifier = Modifier
) {
    Canvas(modifier = modifier) {
        drawBackground(strokeWidth = 0.2f)

        repeat(8) { i ->
            rotate(i * 45f) {
                drawArrow()
            }
        }
    }
}

fun DrawScope.drawArrow(
    color: Color = Color.White
) {
    val widthPolygon = size.width * .05f

    fun drawPolygon() {
        val path = Path().apply {
            moveTo(0f, center.y)
            lineTo(widthPolygon, size.height * .47f)
            lineTo(widthPolygon, size.height * .53f)
            close()
        }
        drawPath(
            path = path, color = color
        )
    }

    drawPolygon()
    drawLine(
        color = color, start = center, end = center.copy(x = widthPolygon)
    )
}

@Preview
@Composable
fun PreviewArrowLine() {
    ArrowAllDirections(
        modifier = Modifier
            .aspectRatio(1f)
            .padding(16.dp)
    )
}