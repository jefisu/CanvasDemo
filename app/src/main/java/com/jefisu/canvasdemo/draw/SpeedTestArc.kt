package com.jefisu.canvasdemo.draw

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.keyframes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jefisu.canvasdemo.ui.theme.Green200
import com.jefisu.canvasdemo.ui.theme.GreenGradient
import com.jefisu.canvasdemo.ui.theme.LightColor2
import com.jefisu.canvasdemo.ui.theme.Surface
import kotlinx.coroutines.launch

@Composable
fun SpeedTestArc(
    maxAngle: Float = 240f,
    color: Color = Color.White
) {
    val animation = remember { Animatable(0f) }
    val scope = rememberCoroutineScope()

    @Composable
    fun BoxScope.SpeedIndicator() {
        Canvas(modifier = Modifier.matchParentSize()) {
            drawArcLines(
                progress = animation.value,
                maxAngle = maxAngle,
                color = color
            )
            drawArcProgress(
                progress = animation.value,
                maxAngle = maxAngle
            )
        }
    }

    @Composable
    fun MeasuredSpeedValue() {
        val speedValue = "%.1f".format(animation.value * 100)

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "DOWNLOAD",
                style = MaterialTheme.typography.body2,
                color = LightColor2
            )
            Text(
                text = speedValue,
                style = MaterialTheme.typography.h3,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
            Text(

                text = "mbps",
                style = MaterialTheme.typography.body2,
                color = LightColor2
            )
        }
    }

    @Composable
    fun BoxScope.StartButton() {
        OutlinedButton(
            enabled = !animation.isRunning,
            shape = RoundedCornerShape(24.dp),
            border = BorderStroke(
                width = 2.dp,
                color = LightColor2
            ),
            contentPadding = PaddingValues(
                horizontal = 24.dp,
                vertical = 4.dp
            ),
            colors = ButtonDefaults.outlinedButtonColors(
                backgroundColor = LightColor2.copy(0.15f),
                contentColor = Color.White,
                disabledContentColor = LightColor2
            ),
            modifier = Modifier
                .align(Alignment.BottomCenter),
            onClick = {
                scope.launch {
                    startAnimation(animation)
                }
            }
        ) {
            Text(text = "START")
        }
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .aspectRatio(1f)
            .padding(32.dp)
    ) {
        SpeedIndicator()
        MeasuredSpeedValue()
        StartButton()
    }
}

suspend fun startAnimation(animation: Animatable<Float, AnimationVector1D>) {
    animation.animateTo(
        targetValue = 0.77f,
        animationSpec = keyframes {
            durationMillis = 7000
            0f at 0 with LinearEasing
            0.79f at 1000 with LinearEasing
            0.76f at 2000 with LinearEasing
            0.83f at 3000 with LinearEasing
            0.87f at 4000 with LinearEasing
            0.72f at 5500 with LinearEasing
        }
    )
}

fun DrawScope.drawArcProgress(
    progress: Float,
    maxAngle: Float,
) {
    val startAngle = 270f - maxAngle / 2
    val sweepAngle = progress * maxAngle

    val topLeft = Offset(50f, 50f)
    val size = Size(size.width - 100f, size.height - 100f)

    fun drawBlur() {
        val maxBlurArcs = 20
        for (i in 0..maxBlurArcs) {
            /*
            * Several arcs are created,
            * with different stroke Width that
            * will vary from 480f to 80f, creating
            * the blur effect.
            */
            drawArc(
                color = Green200.copy(alpha = i / 900f),
                startAngle = startAngle,
                sweepAngle = sweepAngle,
                useCenter = false,
                topLeft = topLeft,
                size = size,
                style = Stroke(width = 80f + (maxBlurArcs - i) * 20, cap = StrokeCap.Round)
            )
        }
    }

    fun drawGradient() {
        drawArc(
            brush = GreenGradient,
            startAngle = startAngle,
            sweepAngle = sweepAngle,
            useCenter = false,
            topLeft = topLeft,
            size = size,
            style = Stroke(width = 80f, cap = StrokeCap.Round)
        )
    }

    drawBlur()
    drawGradient()
}

fun DrawScope.drawArcLines(
    progress: Float,
    maxAngle: Float,
    numberOfLines: Int = 40,
    color: Color = Color.LightGray
) {
    val oneRotation = maxAngle / numberOfLines
    val startingAngle = (180 - maxAngle) / 2
    val currentStartLine = if (progress == 0f) 0 else (progress * numberOfLines).toInt()

    for (line in currentStartLine..numberOfLines) {
        rotate(line * oneRotation + startingAngle) {
            drawLine(
                start = center.copy(
                    x = size.width / if (line % 5 == 0) 10 else 20
                ),
                end = center.copy(x = 4f),
                color = color,
                strokeWidth = 4f,
                cap = StrokeCap.Round
            )
        }
    }
}

@Preview
@Composable
fun PreviewSpeedTestArc() {
    Surface(color = Surface) {
        SpeedTestArc()
    }
}