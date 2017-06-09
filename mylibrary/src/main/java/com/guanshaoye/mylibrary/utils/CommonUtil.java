package com.guanshaoye.mylibrary.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.IBinder;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

@SuppressLint("SimpleDateFormat")
public class CommonUtil {

	/**
	 * 屏幕宽度
	 */
	@SuppressWarnings("deprecation")
	public static int getScreenWidth(Context context) {
		int with = 200;
		if (context != null) {
			WindowManager wm = (WindowManager) context
					.getSystemService(Context.WINDOW_SERVICE);
			Display display = wm.getDefaultDisplay();
			with = display.getWidth();
		}
		return with;
	}

	/**
	 * 屏幕高度
	 */
	@SuppressWarnings("deprecation")
	public static int getScreenHeight(Context context) {
		int with = 200;
		if (context != null) {
			WindowManager wm = (WindowManager) context
					.getSystemService(Context.WINDOW_SERVICE);
			Display display = wm.getDefaultDisplay();
			with = display.getHeight();
		}
		return with;
	}
	public static boolean isWeixinAvilible(Context context) {
		final PackageManager packageManager = context.getPackageManager();// 获取packagemanager
		List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);// 获取所有已安装程序的包信息
		if (pinfo != null) {
			for (int i = 0; i < pinfo.size(); i++) {
				String pn = pinfo.get(i).packageName;
				if (pn.equals("com.tencent.mm")) {
					return true;
				}
			}
		}

		return false;
	}

	/**
	 * 收起软键盘
	 */
	public static void hideSoftInput(View view) {
		if (view != null) {
			Context context = view.getContext();
			IBinder windowToken = view.getWindowToken();
			InputMethodManager inputMethodManager = (InputMethodManager) context
					.getSystemService(Context.INPUT_METHOD_SERVICE);
			inputMethodManager.hideSoftInputFromWindow(windowToken, 0);

		}
	}
	/**
	 * 收起软键盘
	 */
	public static void hideSoftInput(Activity context) {
		if (context != null) {
			View view = context.getWindow().peekDecorView();
			InputMethodManager inputMethodManager = (InputMethodManager) context
					.getSystemService(Context.INPUT_METHOD_SERVICE);
			inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);

		}
	}

	/**
	 * 重启软键盘
	 */
	public static void restart(View view) {
		if (view != null) {
			Context context = view.getContext();
			IBinder windowToken = view.getWindowToken();
			InputMethodManager inputMethodManager = (InputMethodManager) context
					.getSystemService(Context.INPUT_METHOD_SERVICE);
			inputMethodManager.restartInput(view);
			inputMethodManager.toggleSoftInputFromWindow(windowToken, 0,
					InputMethodManager.SHOW_FORCED);
		}
	}
	/**
	 * 重启软键盘
	 */
	public static void restartDelay(final View view) {
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				restart(view);
			}
		}, 100);

	}

	/**
	 * 是否有可用的网络连接
	 */
	public static boolean isNetworkConnected(Context context) {
		if (context != null) {
			ConnectivityManager mConnectivityManager = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo mNetworkInfo = mConnectivityManager
					.getActiveNetworkInfo();
			if (mNetworkInfo != null) {
				return mNetworkInfo.isAvailable();
			}
		}
		return false;
	}
}
