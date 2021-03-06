package org.tetawex.vkphotoviewer.base.bitmap.legacy;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import android.widget.ImageView;

import org.tetawex.vkphotoviewer.base.bitmap.BitmapTransformer;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tetawex on 27.02.2018.
 * Cancels obsolete downloads to reduce latency
 */
@Deprecated
public class ImageLoadManager {
    private Map<String, AsyncImageLoader> activeLoaders;
    private LruCache<String, Bitmap> bitmapCache;


    public ImageLoadManager() {
        activeLoaders = new HashMap<>();
    }

    public void loadImageIntoImageView(final ImageView view,
                                       BitmapTransformer bitmapTransformer,
                                       String url) {
        if (activeLoaders.containsKey(view.toString())) {
            activeLoaders.get(view.toString()).cancel(true);
        }
        AsyncImageLoader loader =
                new AsyncImageLoader(view, bitmapTransformer, new AsyncImageLoader.ManagerCallback() {
                    @Override
                    public void doneJob() {
                        activeLoaders.remove(view.toString());
                    }
                });
        activeLoaders.put(view.toString(), loader);
        loader.execute(url);
    }

    public void clear() {
        for (Map.Entry<String, AsyncImageLoader> entry : activeLoaders.entrySet()) {
            entry.getValue().cancel(true);
        }
        activeLoaders.clear();
    }
}
