package org.tetawex.vkphotoviewer.base.bitmap

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.File
import java.io.FileOutputStream
import java.util.*

@Deprecated("Not enough time to implement")
class ImageDiskCache(val context: Context) {

    companion object {
        const val CACHE_PATH = "/image_cache"
    }

    private val urlPathMap: Map<String, String> = HashMap()

    fun getPathByUrl(url: String): String {
        return urlPathMap[url]!!
    }

    fun loadBitmapFromCache(filePath: String): Bitmap {
        val image = File(context.filesDir.path + CACHE_PATH + filePath)
        val bmOptions = BitmapFactory.Options()
        return BitmapFactory.decodeFile(image.absolutePath, bmOptions)
    }

    fun saveBitmapToCache(url: String, bitmap: Bitmap) {
        val out = FileOutputStream(CACHE_PATH)
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, out)
    }

    //fun
}