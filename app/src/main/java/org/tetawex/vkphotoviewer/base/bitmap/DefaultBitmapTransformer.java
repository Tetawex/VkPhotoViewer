package org.tetawex.vkphotoviewer.base.bitmap;

import android.graphics.Bitmap;

/**
 * Created by tetawex on 27.02.2018.
 */

public class DefaultBitmapTransformer implements BitmapTransformer {
    @Override
    public Bitmap transform(Bitmap bitmap) {
        return bitmap;
    }
}
