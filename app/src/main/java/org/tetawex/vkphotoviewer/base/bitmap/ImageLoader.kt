package org.tetawex.vkphotoviewer.base.bitmap

import android.graphics.BitmapFactory
import android.support.v4.util.LruCache
import android.widget.ImageView
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import org.tetawex.vkphotoviewer.base.bitmap.legacy.BitmapTransformer
import org.tetawex.vkphotoviewer.base.bitmap.legacy.BitmapTransformers
import java.io.InputStream
import java.lang.ref.WeakReference
import java.net.URL

//TODO: refactor to use Builder pattern to include more features like error placeholder drawables and stuff
object ImageLoader {
    const val CACHE_SIZE = 64

    private val activeLoaders: MutableMap<String, Disposable> = HashMap()

    //Stores input streams instead of raw bitmaps to avoid OOM while decoding very large images (>2k)
    private val bitmapCache: LruCache<String, InputStream> = LruCache(CACHE_SIZE)

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
                        return@fromCallable it
                    }

                    //Download a new image instead
                    val urlUrl = URL(url)
                    val stream = urlUrl.openConnection().getInputStream()

                    //Put the image stream in cache
                    bitmapCache.put(url, stream)

                    return@fromCallable stream
                }
                //Convert the stream to the resized bitmap, then apply a transformer to it
                .map {
                    val options = BitmapFactory.Options().apply {
                        //Check if the image still exists
                        val imageViewStrongReference = imageViewWeakReference.get()
                        if (imageViewStrongReference != null) {
                            //Resize the decoded image
                            outHeight = imageViewStrongReference.height
                            outWidth = imageViewStrongReference.width
                        }
                    }
                    return@map BitmapFactory.decodeStream(it, null, options)
                }
                //Start on IO thread
                .subscribeOn(Schedulers.io())
                //Switch back to main thread before operating on view
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            //Check if the image still exists, again
                            val imageViewStrongReference = imageViewWeakReference.get()
                            //Set bitmap if possible
                            imageViewStrongReference?.setImageBitmap(it)
                        },
                        {
                            //Set placeholder or sth, I'll do it later, have no time rn
                        }
                )
    }
}