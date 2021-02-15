package com.test.democrud.utils

import android.graphics.Bitmap
import android.graphics.Bitmap.CompressFormat
import android.graphics.BitmapFactory
import java.io.ByteArrayOutputStream


class DbBitmapUtility {
    // convert from bitmap to byte array
  companion object{
        fun getBytes(bitmap: Bitmap): ByteArray? {
            val stream = ByteArrayOutputStream()
            bitmap.compress(CompressFormat.PNG, 0, stream)
            return stream.toByteArray()
        }

        // convert from byte array to bitmap
        fun getImage(image: ByteArray): Bitmap? {
            return BitmapFactory.decodeByteArray(image, 0, image.size)
        }
    }
}