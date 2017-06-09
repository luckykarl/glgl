package com.guanshaoye.mylibrary.utils;

import android.Manifest;
import android.app.Notification;
import android.app.Notification.Builder;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v4.app.ActivityCompat;
import android.view.Gravity;
/*
 * Android 6.0		23	M
 * Android 5.1		22	LOLLIPOP_MR1
 * Android 5.0		21	LOLLIPOP
 * Android 4.4W		20	KITKAT_WATCH	KitKat for Wearables Only
 * Android 4.4		19	KITKAT
 * Android 4.3		18	JELLY_BEAN_MR2
 * Android 4.2.2	17	JELLY_BEAN_MR1
 * Android 4.2		17	JELLY_BEAN_MR1
 * Android 4.1.1	16	JELLY_BEAN
 * Android 4.1		16	JELLY_BEAN
 * Android 4.0.4	15	ICE_CREAM_SANDWICH_MR1
 * Android 4.0.3	15	ICE_CREAM_SANDWICH_MR1
 * Android 4.0.2	14	ICE_CREAM_SANDWICH
 * Android 4.0.1	14	ICE_CREAM_SANDWICH
 * Android 4.0	 	14	ICE_CREAM_SANDWICH
 * Android 3.2		13	HONEYCOMB_MR2
 * Android 3.1.x	12	HONEYCOMB_MR1
 * Android 3.0.x	11	HONEYCOMB
 * Android 2.3.4	10	GINGERBREAD_MR1
 * Android 2.3.3	10	GINGERBREAD_MR1
 * Android 2.3.2	9	GINGERBREAD
 * Android 2.3.1	9	GINGERBREAD
 * Android 2.3		9	GINGERBREAD
 * Android 2.2.x	8	FROYO
 * Android 2.1.x	7	ECLAIR_MR1
 * Android 2.0.1	6	ECLAIR_0_1
 * Android 2.0		5	ECLAIR
 * Android 1.6		4	DONUT
 * Android 1.5		3	CUPCAKE
 * Android 1.1		2	BASE_1_1
 * Android 1.0		1	BASE
 */

@SuppressWarnings("deprecation")
public final class APIHelper {
    public static final int PERMISSION_READ_PHONE_STATE = 1;
    public static final int PERMISSION_WRITE_EXTERNAL_STORAGE = 2;
    public static final int PERMISSION_ACCESS_COARSE_LOCATION = 3;
    public static final int PERMISSION_WRITE_SETTINGS = 4;
    public static final int PERMISSION_SYSTEM_ALERT_WINDOW = 5;

    public static int getPermissionID(String permission) {
        int permissionId = 0;
        if (permission.equals(Manifest.permission.READ_PHONE_STATE)) {
            permissionId = PERMISSION_READ_PHONE_STATE;
        } else if (permission.equals(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            permissionId = PERMISSION_WRITE_EXTERNAL_STORAGE;
        } else if (permission.equals(Manifest.permission.ACCESS_COARSE_LOCATION)) {
            permissionId = PERMISSION_ACCESS_COARSE_LOCATION;
        }
        return permissionId;
    }

    public static String[] getPermissions(final String permission) {
        String[] permissions = null;
        if (permission.equals(Manifest.permission.ACCESS_COARSE_LOCATION)) {
            permissions = new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION};
        } else {
            permissions = new String[]{permission};
        }
        return permissions;
    }

    public static String getPermissionRationale(String permission) {
        String rationale = null;
        if (permission.equals(Manifest.permission.READ_PHONE_STATE)) {
            rationale = "点点赚需要\n\n根据您的设备来判定您的邀请是否成功。\n\n请在接下来的提示框中选择允许。";
        } else if (permission.equals(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            rationale = "点点赚需要\n\n将问卷引擎等必要文件写入您的存储卡中。\n\n请在接下来的提示框中选择允许。";
        } else if (permission.equals(Manifest.permission.ACCESS_COARSE_LOCATION)) {
            rationale = "点点赚需要\n\n根据您的当前位置来推送特定地区的问卷。\n\n请在接下来的提示框中选择允许。";
        }
        return rationale;
    }

    public static String[] permissions() {
        String[] permissions = {
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.WRITE_SETTINGS,
                Manifest.permission.SYSTEM_ALERT_WINDOW
        };
        return permissions;
    }

    public static boolean hasPermissions(Context context, String... permissions) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    public static int GravitySTART() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            return Gravity.START;
        } else {
            return Gravity.LEFT;
        }
    }

    public static int GravityEND() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            return Gravity.END;
        } else {
            return Gravity.RIGHT;
        }
    }

    public static Drawable getDrawable(Resources res, int id) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            return res.getDrawable(id, null);
        } else {
            return res.getDrawable(id);
        }
    }

    public static Notification getNotification(Builder builder) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
            return builder.build();
        } else {
            return builder.getNotification();
        }
    }
}
