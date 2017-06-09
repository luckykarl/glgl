package com.guanshaoye.glglteacher.ui;

import android.app.Application;
import com.guanshaoye.glglteacher.utils.CurrentUser;
import com.guanshaoye.mylibrary.utils.Toaster;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by ${张梦博} on 2017/5/16.
 * Whenever,Wherever,Whatever,I believe I can handle everything
 */

public class GlGlApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Toaster.init(this);
        CurrentUser.loadFromLocal(getApplicationContext());
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
    }
}
