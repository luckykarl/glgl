package com.guanshaoye.mylibrary.utils;

import android.content.Context;
import android.content.res.TypedArray;

public class ResourcesHelper {
	static Context mContext;
	
	public static void init(Context context){

		mContext=context;
	}
	
	public static String[] getStringArray(int resourceID){
		return mContext.getResources().getStringArray(resourceID);
	}
	
	public static TypedArray getTypedArray(int resourceID){
		return mContext.getResources().obtainTypedArray(resourceID);

	}
}
