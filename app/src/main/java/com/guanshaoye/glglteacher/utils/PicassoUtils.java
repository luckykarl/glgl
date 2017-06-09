package com.guanshaoye.glglteacher.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;
import com.guanshaoye.glglteacher.R;
import com.squareup.picasso.Picasso;

import static com.squareup.picasso.MemoryPolicy.NO_CACHE;
import static com.squareup.picasso.MemoryPolicy.NO_STORE;

/**
 * Created by karl on 2017/5/25.
 *
 */

public class PicassoUtils {
    public static void showPersionImg(Context context, String url, ImageView imageView){
        Picasso.with(context).load(url).config(Bitmap.Config.RGB_565).placeholder(R.drawable.head_icon).error(R.drawable.head_icon).memoryPolicy(NO_CACHE, NO_STORE).into(imageView);
    }


    public static void showPhoto(Context context, String url, ImageView imageView){
        Picasso.with(context).load(url).placeholder(R.mipmap.photo_f_icon).error(R.mipmap.photo_f_icon).memoryPolicy(NO_CACHE, NO_STORE).into(imageView);
    }
}
