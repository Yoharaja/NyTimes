package com.nytimes.android.nytimesapp.app;

import android.app.Application;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.nytimes.android.nytimesapp.utils.LruBitmapCache;

public class NyTimesApp extends Application {

    public static final String TAG = NyTimesApp.class.getSimpleName();

    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;
//Singleton class
    private static NyTimesApp ourInstance = new NyTimesApp();

    @Override
    public void onCreate() {
        super.onCreate();
        ourInstance = this;
    }

    public static synchronized NyTimesApp getInstance() {
        return ourInstance;
    }


    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return mRequestQueue;
    }

    public ImageLoader getImageLoader() {
        getRequestQueue();
        if (mImageLoader == null) {
            mImageLoader = new ImageLoader(this.mRequestQueue,
                    new LruBitmapCache());
        }
        return this.mImageLoader;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }
}
