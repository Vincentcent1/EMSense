package com.example.android.emsense3.Other;

import android.widget.ImageView;

import com.example.android.emsense3.Activity.Album;

/**
 * Created by slzh645 on 7/22/2017.
 */

public interface ObjectItemClickListener {
    void onObjectItemClick(int pos, Album albumItem, ImageView objectView);
}
