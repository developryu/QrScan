package com.ryu.personal.android.qrscanutil.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import kotlin.math.min

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun QrScanGreenCorners(
    color: Color = Color(0xFF76D344),
    modifier: Modifier = Modifier
) {
    BoxWithConstraints(
        modifier = modifier
            .fillMaxSize(),
    ) {
        val width = constraints.maxWidth.toFloat()
        val height = constraints.maxHeight.toFloat()
        val baseSize = min(constraints.maxHeight.toFloat(), constraints.maxWidth.toFloat())
        val cornerLength = baseSize / 3
        val strokeWidth = baseSize / 50

        Canvas(modifier = Modifier.fillMaxSize()
            .alpha(0.8f)) {
            drawLine(
                color = color,
                start = Offset(0f, 0f),
                end = Offset(cornerLength, 0f),
                strokeWidth = strokeWidth
            )
            drawLine(
                color = color,
                start = Offset(0f, 0f),
                end = Offset(0f, cornerLength),
                strokeWidth = strokeWidth
            )

            drawLine(
                color = color,
                start = Offset(width - cornerLength, 0f),
                end = Offset(width, 0f),
                strokeWidth = strokeWidth
            )
            drawLine(
                color = color,
                start = Offset(width, 0f),
                end = Offset(width, cornerLength),
                strokeWidth = strokeWidth
            )

            drawLine(
                color = color,
                start = Offset(0f, height - cornerLength),
                end = Offset(0f, height),
                strokeWidth = strokeWidth
            )
            drawLine(
                color = color,
                start = Offset(0f, height),
                end = Offset(cornerLength, height),
                strokeWidth = strokeWidth
            )

            drawLine(
                color = color,
                start = Offset(width, height - cornerLength),
                end = Offset(width, height),
                strokeWidth = strokeWidth
            )
            drawLine(
                color = color,
                start = Offset(width - cornerLength, height),
                end = Offset(width, height),
                strokeWidth = strokeWidth
            )
        }
    }
}