package org.tetawex.vkphotoviewer.base.bitmaps;

import android.util.Log;
import android.widget.ImageView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tetawex on 27.02.2018.
 * Cancels obsolete downloads to reduce latency
 */
public class ImageLoadManager {
    private Map<String, AsyncImageLoader> activeLoaders;

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
                new AsyncImageLoader(view, bitmapTransformer, new AsyncImageLoader.MangerCallback() {
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
