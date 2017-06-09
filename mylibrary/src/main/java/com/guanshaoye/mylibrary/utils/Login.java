package com.guanshaoye.mylibrary.utils;

import android.annotation.SuppressLint;
import android.content.Context;

public class Login {

	public static boolean needChange = true;
	public static boolean isLogin = false;
	public static int userID = 0;
	public static int type=0;
	//登陆信息
	@SuppressLint("UseValueOf") 
	public static void initLogin(Context context) {
		userID= (int) SPUtils.get(context, "userID", 0);
		type=(int) SPUtils.get(context,"type",0);
		if (userID<1) {
			isLogin = false;
		} else {
			isLogin = true;
		}
	}

	public static void setLogin(boolean flag, int id) {
		isLogin = flag;
		userID = id;
		needChange = true;
	}

}
