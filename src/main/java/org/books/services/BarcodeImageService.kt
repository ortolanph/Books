package org.books.services

import com.google.zxing.BarcodeFormat
import com.google.zxing.WriterException
import com.google.zxing.client.j2se.MatrixToImageWriter
import com.google.zxing.common.BitMatrix
import com.google.zxing.oned.EAN13Writer
import com.google.zxing.qrcode.QRCodeWriter
import org.springframework.stereotype.Service

import javax.imageio.ImageIO
import java.awt.image.BufferedImage
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.InputStream

@Service
class BarcodeImageService {

    fun generateQRCode(text: String, width: Int, height: Int): InputStream {
        val qrCodeWriter = QRCodeWriter()
        var bitMatrix: BitMatrix? = null
        try {
            bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height)
        } catch (e: WriterException) {
            e.printStackTrace()
        }

        val image = MatrixToImageWriter.toBufferedImage(bitMatrix!!)

        val baos = ByteArrayOutputStream()
        try {
            ImageIO.write(image, "png", baos)
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return ByteArrayInputStream(baos.toByteArray())
    }

    fun generateEAN13Barcode(text: String, width: Int, height: Int): InputStream {
        val ean13Writer = EAN13Writer()
        var bitMatrix: BitMatrix? = null
        try {
            bitMatrix = ean13Writer.encode(text, BarcodeFormat.EAN_13, width, height)
        } catch (e: WriterException) {
            e.printStackTrace()
        }

        val image = MatrixToImageWriter.toBufferedImage(bitMatrix!!)

        val baos = ByteArrayOutputStream()
        try {
            ImageIO.write(image, "png", baos)
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return ByteArrayInputStream(baos.toByteArray())
    }

}
