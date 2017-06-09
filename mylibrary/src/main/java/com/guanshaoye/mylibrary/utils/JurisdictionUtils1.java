package com.guanshaoye.mylibrary.utils;
import android.Manifest;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
/**
 * Fragment使用
 * 权限管理类
 * 作者：chenxiu  on 03 月 11 日 10:18
 * Success is getting what you want
 * happiness is wanting what you get.
 */
public class JurisdictionUtils1 {
    public static final int WRITE_EXTERNAL_STORAGE_REQUEST_CODE = 1;
    private static Fragment activity;
    final private int REQUEST_CODE_ASK_PERMISSIONS = 123;
    public JurisdictionUtils1(Fragment context) {
        activity = context;
    }

    public static JurisdictionUtils1 newInstance(Fragment context) {
        return new JurisdictionUtils1(context);
    }
    /**
     * 检查是否拥有文件读写权限
     * true  有  false 无
     *
     * @return
     */
    @TargetApi(Build.VERSION_CODES.M)
    public boolean getFilePermission() {
        return getPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, "你需要开启文件读取权限\n设置>应用>管理权限");
    }

    /**
     * 检查是否拥有相机读写权限
     * true  有  false 无
     *
     * @return
     */
    public boolean getCameraPermission() {
        return getPermission(Manifest.permission.CAMERA, "你需要开启相机权限\n设置>应用>管理权限");
    }
    /**
     * 检查是否拥有拨打电话权限
     * true  有  false 无
     *
     * @return
     */
    public boolean getPhonePermission() {
        return getPermission(Manifest.permission.CALL_PHONE, "你需要开启拨打电话权限\n设置>应用>管理权限");
    }
    /**
     * 检查是否拥有获取定位权限
     * true  有  false 无
     *
     * @return
     */
    @TargetApi(Build.VERSION_CODES.M)
    public boolean getLocationPermission() {
        return getPermission(Manifest.permission.ACCESS_FINE_LOCATION, "你需要开启定位权限\n设置>应用>管理权限");
    }
    @TargetApi(Build.VERSION_CODES.M)
    public boolean getPermission(@NonNull final String permission, String message) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int hasWriteContactsPermission = activity.getActivity().checkSelfPermission(permission);
            if (hasWriteContactsPermission != PackageManager.PERMISSION_GRANTED) {
                if (!activity.shouldShowRequestPermissionRationale(permission)) {
                    showMessageOKCancel(message,
                                new DialogInterface.OnClickListener(){
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        activity.requestPermissions(new String[]{permission}, REQUEST_CODE_ASK_PERMISSIONS);
                                    }
                                });
                    return false;
                }
                activity.requestPermissions(new String[]{permission}, WRITE_EXTERNAL_STORAGE_REQUEST_CODE);
                return  false;
            }
        }
        return true;
    }
    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(activity.getActivity())
                .setMessage(message)
                .setPositiveButton("确定", okListener)
                .setNegativeButton("取消", null)
                .create()
                .show();
    }
}