package com.guanshaoye.glglteacher.utils;



import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesUtils {
	public static String TABLE_NAME = "glgl_teacher";
	public static String LOGIN_UserMessage="usermessage";
	public static String LOGIN_STYPE="LOGIN_STYPE"; //0 普通登录  1： 微信登录
	public static String ISFIRST="ISFIRST"; //
	public static void putInt(Context context,String key,int value){
		SharedPreferences sp = context.getSharedPreferences(TABLE_NAME, Context.MODE_PRIVATE);
		sp.edit().putInt(key, value).commit();
	}
	
	public static int getInt(Context context, String key){
		SharedPreferences sp = context.getSharedPreferences(TABLE_NAME, Context.MODE_PRIVATE);
		return sp.getInt(key, 0);
	}
	
	public static void putBoolean(Context context,String key,boolean value){
		SharedPreferences sp = context.getSharedPreferences(TABLE_NAME, Context.MODE_PRIVATE);
		sp.edit().putBoolean(key, value).commit();
	}
	
	public static boolean getBoolean(Context context, String key){
		SharedPreferences sp = context.getSharedPreferences(TABLE_NAME, Context.MODE_PRIVATE);
		return sp.getBoolean(key, false);
	}
	
	
	/***
	 * 传入数据
	 * @param context
	 * @param key 密钥
	 * @param value 值
	 */
	public static void putValue(Context context, String key, String value) {
		// TODO Auto-generated method stub
		SharedPreferences sp = context.getSharedPreferences(TABLE_NAME, Context.MODE_PRIVATE);
	      SharedPreferences.Editor editor = sp.edit();  
	      editor.putString(key, value);  
	      editor.commit(); 
	}
	
	/***
	 * 取出数据
	 * @param context
	 * @param key 密钥
	 * @return 返回一串字符串
	 */
	public static String getValue(Context context, String key) {
		// TODO Auto-generated method stub
		if(context == null){
			return "";
		}
		SharedPreferences sp = context.getSharedPreferences(TABLE_NAME, Context.MODE_PRIVATE);
		return sp.getString(key, "");
	}
	public static void putLong(Context context, String key, long value) {
		// TODO Auto-generated method stub
		SharedPreferences sp = context.getSharedPreferences(TABLE_NAME, Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sp.edit();
		editor.putLong(key, value);
		editor.commit();
	}

	/***
	 * 取出数据
	 * @param context
	 * @param key 密钥
	 * @return 返回一串字符串
	 */
	public static long getLong(Context context, String key) {
		// TODO Auto-generated method stub
		if(context == null){
			return 0;
		}
		SharedPreferences sp = context.getSharedPreferences(TABLE_NAME, Context.MODE_PRIVATE);
		return sp.getLong(key, 0);
	}
}
