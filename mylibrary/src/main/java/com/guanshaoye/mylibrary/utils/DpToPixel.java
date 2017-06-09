package com.guanshaoye.mylibrary.utils;

import android.content.Context;

/**
 * Created by ${张梦博} on 2016/11/29.
 * Whenever,Wherever,Whatever,I believe I can handle everything
 */
public class DpToPixel {
    public static int convertDpToPixel(Context context, int dp) {
        float density = context.getResources().getDisplayMetrics().density;
        return Math.round((float) dp * density);
    }
}
