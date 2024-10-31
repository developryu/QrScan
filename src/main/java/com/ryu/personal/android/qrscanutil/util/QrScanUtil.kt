package com.ryu.personal.android.qrscanutil.util

import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.common.InputImage
import com.ryu.personal.android.camerautil.model.ImageProcessData
import timber.log.Timber

object QrScanUtil {

    fun processImageProxyQrCode(
        image: ImageProcessData,
        onQrDetected: (qrCode: String) -> Unit,
    ) {
        val inputImage = InputImage.fromByteArray(
            image.nv21ImageData,
            image.width,
            image.height,
            image.orientation,
            InputImage.IMAGE_FORMAT_NV21
        )
        val options = BarcodeScannerOptions.Builder()
            .setBarcodeFormats(Barcode.FORMAT_QR_CODE)
            .build()

        val scanner = BarcodeScanning.getClient(options)
        scanner.process(inputImage)
            .addOnSuccessListener { barcodes ->
                for (barcode in barcodes) {
                    if (barcode.rawValue.isNullOrEmpty().not()) {
                        onQrDetected(barcode.rawValue!!)
                    }
                }
            }
            .addOnFailureListener {
                Timber.e(it, " MLKit QR 코드 인식 실패")
            }
            .addOnCompleteListener {
                scanner.close()
            }
    }
}