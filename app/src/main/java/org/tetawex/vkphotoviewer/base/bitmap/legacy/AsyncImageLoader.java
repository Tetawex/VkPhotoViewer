package org.tetawex.vkphotoviewer.base.bitmap.legacy;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.net.URL;

//Got this from StackOverflow and heavily modified
public class AsyncImageLoader extends AsyncTask<String, Void, Bitmap> {
    private final WeakReference<ImageView> imageViewReference;
    private BitmapTransformer bitmapTransformer;
    private ManagerCallback managerCallback;

    public interface ManagerCallback {
        void doneJob();
    }


    public AsyncImageLoader(ImageView imageView,
                            BitmapTransformer bitmapTransformer,
                            ManagerCallback managerCallback) {
        // Use a WeakReference to ensure the ImageView can be garbage collected
        imageViewReference = new WeakReference<ImageView>(imageView);
        this.bitmapTransformer = bitmapTransformer;
        this.managerCallback = managerCallback;
    }

    // Decode image in background.
    @Override
    public Bitmap doInBackground(String... params) {
        if (isCancelled()) {
            return null;
        }
        try {
            URL url = new URL(params[0]);

            BitmapFactory.Options options = new BitmapFactory.Options();
            //The patches are huge (3k:3k) so they are resized to match view's dimensions
            options.outHeight = imageViewReference.get().getHeight();
            options.outWidth = imageViewReference.get().getWidth();//decrease decoded image
            Bitmap bitmap = BitmapFactory
                    .decodeStream(url.openConnection().getInputStream(), null, options);
            return bitmapTransformer.transform(bitmap);
        } catch (IOException|OutOfMemoryError err) {
            err.printStackTrace();
            return null;
        }

    }

    // Once complete, see if ImageView is still around and set bitmap.
    @Override
    public void onPostExecute(Bitmap bitmap) {
        managerCallback.doneJob();
        if (imageViewReference != null && bitmap != null) {
            final ImageView imageView = imageViewReference.get();
            if (imageView != null) {
                imageView.setImageBitmap(bitmap);
            }
        }
    }

}