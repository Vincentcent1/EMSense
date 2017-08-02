package com.example.android.emsense3.Other;

import android.content.Context;
import android.webkit.WebView;

/**
 * Created by setia on 7/29/2017.
 */
public class GIFView extends WebView {
    public GIFView(Context context, String path) {
        super(context);
        loadUrl(path);
    }
}