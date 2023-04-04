package com.jefisu.canvasdemo

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

fun DrawScope.drawBackground(
    lines: Int = 4,
    color: Color = Color.LightGray,
    strokeWidth: Float = Stroke.HairlineWidth
) {
    for(line in 0..lines) {
        // Rows
        val yAxis = (size.height / lines) * line
        drawLine(
            color = color,
            start = Offset.Zero.copy(y = yAxis),
            end = Offset(size.width, yAxis),
            strokeWidth = strokeWidth
        )

        // Columns
        val xAxis = (size.width / lines) * line
        drawLine(
            color = color,
            start = Offset.Zero.copy(x = xAxis),
            end = Offset(xAxis, size.height),
            strokeWidth = strokeWidth
        )
    }
}

@Preview
@Composable
fun PreviewDrawBackground() {
   Canvas(modifier = Modifier.size(200.dp)) {
       drawBackground()
   }
}