package com.ryu.personal.android.qrscanutil.ui

import com.ryu.personal.android.qrscanutil.R
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ryu.personal.android.camerautil.constant.CameraAngle
import com.ryu.personal.android.camerautil.constant.CameraDirection
import com.ryu.personal.android.camerautil.constant.CameraFlash
import com.ryu.personal.android.camerautil.constant.CameraRatio
import com.ryu.personal.android.camerautil.ui.CameraScreen
import com.ryu.personal.android.camerautil.ui.CameraViewModel
import com.ryu.personal.android.qrscanutil.util.QrScanUtil

@Composable
fun QrScanViewScreen(
    modifier: Modifier = Modifier,
    onQrDetected: (String) -> Unit
) {
    val context = LocalContext.current

    val cameraViewModel: CameraViewModel =  viewModel(
        factory = CameraViewModel.Factory(
            context = context,
            cameraDirection = CameraDirection.BACK,
            cameraAngle = CameraAngle.NORMAL,
            cameraRatio = CameraRatio.RATIO_1_1,
            cameraFlash = CameraFlash.OFF,
            onProcessImage = {
                QrScanUtil.processImageProxyQrCode(
                    image = it,
                    onQrDetected = onQrDetected
                )
            }
        )
    )
    val cameraDirection by cameraViewModel.cameraDirection.collectAsState()
    val cameraAngle by cameraViewModel.cameraAngle.collectAsState()
    val cameraFlash by cameraViewModel.cameraFlash.collectAsState()

    Surface {
        Box(
            modifier = modifier.fillMaxSize()
        ) {
            CameraScreen(
                viewModel = cameraViewModel,
                modifier = Modifier.fillMaxSize()
            )
            QrScanLineAnimation(modifier = Modifier.fillMaxSize())
            QrScanGreenCorners(modifier = Modifier.fillMaxSize())

            Row(
                modifier = Modifier
                    .padding(end = 10.dp)
                    .align(Alignment.BottomEnd),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                if (cameraDirection == CameraDirection.BACK) {
                    CameraOptionButton(
                        onClick = {
                            cameraViewModel.changeFlash(if (cameraFlash == CameraFlash.OFF) CameraFlash.TORCH else CameraFlash.OFF)
                        },
                        icon = R.drawable.ic_camera_flash
                    )
                }
                CameraOptionButton(
                    onClick = {
                        cameraViewModel.swapAngle()
                    },
                    icon = R.drawable.ic_camera_angle
                )
                CameraOptionButton(
                    onClick = {
                        cameraViewModel.swapDirection()
                    },
                    icon = R.drawable.ic_camera_direction
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun QrScanViewScreenPreview() {
    QrScanViewScreen {}
}

@Composable
private fun CameraOptionButton(
    onClick: () -> Unit,
    title: String? = null,
    @DrawableRes icon: Int
) {
    Box(
        modifier = Modifier
            .fillMaxWidth(0.15f)
            .aspectRatio(1f)
            .clickable(
                onClick = onClick,
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ),
        contentAlignment = Alignment.Center

    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier.size(20.dp),
                painter = painterResource(id = icon),
                contentDescription = null
            )
            title?.let {
                Text(
                    text = it,
                    color = Color.White,
                    fontSize = 10.sp,
                    maxLines = 1
                )
            }
        }
    }
}
