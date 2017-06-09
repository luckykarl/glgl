package com.guanshaoye.mylibrary.utils;

import android.content.Context;
import android.widget.Toast;

public class Toaster extends Toast {
    private static Context mContext;
    static Toast mToast;

    private Toaster(Context context) {
        super(context);
    }
    public static void init(Context context){    
    		 mContext=context;
    }

    public static void showToast(String str) {
            if (mToast!=null){
            	mToast.cancel();
            }
            mToast =Toast.makeText(mContext, str, Toast.LENGTH_SHORT);
            mToast .show();

    }
    public static void showToast(int str) {
        if (mToast!=null){
        	mToast.cancel();
        }
        mToast =Toast.makeText(mContext, str, Toast.LENGTH_SHORT);
        mToast .show();

    }

    public static void cancelToast(){
          if(mToast!=null){
              mToast.cancel();
          }
    }
    
	public static Context getContext() {
		return mContext;
	}

}
