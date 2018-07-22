package org.tetawex.vkphotoviewer.base.bitmap;

import android.graphics.Bitmap;

import org.tetawex.vkphotoviewer.base.bitmap.legacy.BitmapTransformer;

/**
 * Created by tetawex on 27.02.2018.
 */

public class DefaultBitmapTransformer implements BitmapTransformer {
    @Override
    public Bitmap transform(Bitmap bitmap) {
        return bitmap;
    }
}
