package org.tetawex.vkphotoviewer.base.bitmap

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.DisplayMetrics
import android.util.LruCache
import android.widget.ImageView
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import org.tetawex.vkphotoviewer.base.bitmap.legacy.BitmapTransformer
import java.lang.ref.WeakReference
import java.net.URL

//TODO: refactor to use Builder pattern to include more features like error placeholder drawables and stuff
object ImageLoader {
    private const val CACHE_SIZE = 32

    private val activeLoaders: MutableMap<String, Disposable> = HashMap()

    private val bitmapCache: LruCache<String, Bitmap> = LruCache(CACHE_SIZE)

    fun loadImageIntoView(imageView: ImageView, url: String, bitmapTransformer: BitmapTransformer = BitmapTransformers.DEFAULT) {
        //I've written a ton of comments there because the logic is quite obscure...

        //Avoid strong referencing imageView
        val imageViewWeakReference = WeakReference<ImageView>(imageView)

        //A key for keeping track of active loaders
        val viewKey = imageView.toString()
        //Dispose the load associated with the viewKey if there's one
        activeLoaders[viewKey]?.dispose()
        //Assign a new one
        activeLoaders[viewKey] = Single
                .fromCallable {
                    //The image exists in lru cache, just assign it
                    bitmapCache[url]?.also {
                        return@fromCallable it.copy(it.config, false)
                    }

                    //Download a new image instead
                    val urlUrl = URL(url)
                    val stream = urlUrl.openConnection().getInputStream()
                    val bitmap = BitmapFactory.decodeStream(
                            stream,
                            null,
                            BitmapFactory.Options().also { opt ->
                                opt.inDensity = DisplayMetrics.DENSITY_DEFAULT;
                                opt.inTargetDensity = DisplayMetrics.DENSITY_DEFAULT;
                                opt.inScaled = false;
                            })
                    //Save the original image to lru cache
                    bitmapCache.put(url, bitmap)
                    return@fromCallable bitmap.copy(bitmap.config, false)
                }
                //Start on IO thread
                .subscribeOn(Schedulers.io())
                //Switch back to main thread before operating on view
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally {
                    activeLoaders.remove(viewKey)
                }
                //Commented out due to weird behaviors when being called while imageView have not been initialized yet
                //Resize and transform the bitmap
                /*.map { bitmap ->
                    //Check if the imageView still exists to get its dimensions
                    val imageViewStrongReference = imageViewWeakReference.get()
                    if (imageViewStrongReference != null) {
                        val outHeight = imageViewStrongReference.height
                        val outWidth = imageViewStrongReference.width
                        if (outHeight <= 0 || outWidth <= 0)
                            throw NullPointerException("required ImageView is gone")
                        return@map bitmapTransformer.transform(bitmap)
                    }
                    //Throw an exception to finish the chain immediately
                    throw NullPointerException("required ImageView is gone")
                }*/
                .subscribe(
                        { bitmap ->
                            //TODO: MIGHT CRASH AT VERY LARGE IMAGES, DO STH
                            //Check if the image still exists, again
                            val imageViewStrongReference = imageViewWeakReference.get()
                            //Set bitmap if possible
                            imageViewStrongReference?.setImageBitmap(bitmapTransformer.transform(bitmap))
                        },
                        {
                            it.printStackTrace()
                        }
                )
    }
}