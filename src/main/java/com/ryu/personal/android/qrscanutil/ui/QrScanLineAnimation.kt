package com.ryu.personal.android.qrscanutil.ui

import android.annotation.SuppressLint
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun QrScanLineAnimation(
    color: Color = Color.Red,
    durationMillis: Int = 1000,
    modifier: Modifier = Modifier,
) {
    BoxWithConstraints(
        modifier = modifier
            .fillMaxSize()
    ) {
        val height = constraints.maxHeight.toFloat()

        val transition = rememberInfiniteTransition(label = "")
        val animatedY by transition.animateFloat(
            initialValue = 0f,
            targetValue = height,
            animationSpec = infiniteRepeatable(
                animation = tween(durationMillis, easing = LinearEasing),
                repeatMode = RepeatMode.Reverse
            ), label = ""
        )

        Canvas(modifier = Modifier.fillMaxSize()) {
            drawLine(
                color = color,
                start = Offset(0f, animatedY),
                end = Offset(size.width, animatedY),
                strokeWidth = 4f
            )
        }
    }
}