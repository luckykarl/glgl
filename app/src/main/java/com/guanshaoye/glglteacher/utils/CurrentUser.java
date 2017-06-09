package com.guanshaoye.glglteacher.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.alibaba.fastjson.JSON;
import com.guanshaoye.glglteacher.bean.UserBean;

/**
 * Created by karl on 2017/5/23.
 */

public class CurrentUser {
    private static final String SP_SETTINGS = "Settings";
    private static final String KEY_CURRENT_USER = "CURRENT_USER";
    private UserBean mUser;
    private final Context mCtx;
    private static CurrentUser mCurrentUser;

    public static CurrentUser loadFromLocal(@NonNull Context context) {
        if (mCurrentUser == null) {
            mCurrentUser = new CurrentUser(context);
        }

        return mCurrentUser;
    }
    private CurrentUser(@NonNull Context context) {
        mCtx = context.getApplicationContext();

        SharedPreferences settings = mCtx.getSharedPreferences(SP_SETTINGS, Context.MODE_PRIVATE);
        String result = settings.getString(KEY_CURRENT_USER, "");
        if ("".equals(result)) {
            // 本地没有保存过用户信息
            mUser = new UserBean();
        }else {
            mUser = JSON.parseObject(result,UserBean.class);
        }
    }


    public static UserBean getUser() {
        assert (mCurrentUser != null);
        return mCurrentUser.mUser;
    }

    public static void setCurrentUser(@Nullable UserBean user) {
        assert (mCurrentUser != null);
        mCurrentUser.mUser = user;

    }
    public static void setShare(Context context,UserBean user){

        SharedPreferences pref = context.getSharedPreferences(SP_SETTINGS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(KEY_CURRENT_USER,JSON.toJSONString(user)+"");
        editor.commit();
    }

    public static void cleanUser(Context context){
        SharedPreferences pref = context.getSharedPreferences(SP_SETTINGS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(KEY_CURRENT_USER,"");
        editor.commit();
    }
}
