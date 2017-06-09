package com.guanshaoye.mylibrary.utils;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.guanshaoye.mylibrary.R;

public class ImageUtils {

    public static void loadImg(Activity activity, String url,
                               ImageView imageView) {
        Glide.with(activity).load(url).into(imageView);
    }
    public static void loadFragmentHead(Fragment fragment, String url,
                                        ImageView imageView) {
        Glide.with(fragment).load(url).placeholder(R.drawable.default_head_icon).into(imageView);
    }

    public static void loadImg(FragmentActivity fragmentActivity, String url,
                               ImageView imageView) {
        if(imageView!=null){
            Glide.with(fragmentActivity).load(url).
                    placeholder(R.drawable.default_head_icon).into(imageView);
         }
    }
    public static void loadHeadImg(FragmentActivity fragmentActivity, String url,
                                   ImageView imageView) {
        if(imageView!=null){
            Glide.with(fragmentActivity).load(url).
                    placeholder(R.drawable.default_head_icon).into(imageView);
        }
    }
    public static void loadImg(Fragment fragment, String url,
                               ImageView imageView) {
        Glide.with(fragment).load(url).into(imageView);
    }

    public static void loadDefaultHeadImg(Context context, String url, ImageView imageView){
        Glide.with(context).load(url).placeholder(R.drawable.default_head_icon).into(imageView);
    }
    public static void loadImg(String url,ImageView imageView){
        Context context=imageView.getContext();
        if(context!=null) {
            Glide.with(context).load(url)
                    .placeholder(R.drawable.default_head_icon)
                    .into(imageView);
        }
       }
   public static void loadImgCir(String url, ImageView imageView) {
        Context context=imageView.getContext();
        if(context!=null) {
            Glide.with(context).load(url).placeholder(R.drawable.default_head_icon)
                .transform(new GlideCircleTransform(context))
                .into(imageView);

        }

    }


}
