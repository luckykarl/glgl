package com.guanshaoye.glglteacher.utils;

import android.Manifest;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.guanshaoye.glglteacher.ui.mine.EditUserInfoActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by karl on 2017/6/4.
 */

public class TakePhotoUtils {
    public final static int TAKEPHOTO = 100;
    public final static int CHECKPHOTO = 200;
    public final static int CROPPHOTO = 300;

    public final static int TAKEPHOTO7 = 1007;
    public final static int CHECKPHOTO7 = 2007;
    public final static int CROPPHOTO7 = 3007;
    /**
     * 拍照的图片路径
     */
    public static String mPath;
    private static Uri imageUri;
    public static String cachPath;
    public static File cacheFile;
    private static String fileprovider = "com.guanshaoye.glglteacher.fileprovider";


    public static void requestOpenPicture(final Activity activity){
        String[] permissions =
                { Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE };
        requestRuntimePermission(activity,permissions, new PermissionListener() {
            @Override public void onGranted() {
                openAlbum(activity);
            }

            @Override public void onDenied(List<String> deniedPermission) {
                //没有获取到权限，什么也不执行，看你心情
            }
        });
    }
    //andrpoid 6.0 需要写运行时权限
    public static void requestRuntimePermission(Activity activities,String[] permissions, PermissionListener listener) {

        List<String> permissionList = new ArrayList<>();
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(activities, permission)
                    != PackageManager.PERMISSION_GRANTED) {
                permissionList.add(permission);
            }
        }
        if (!permissionList.isEmpty()) {
            ActivityCompat.requestPermissions(activities,
                    permissionList.toArray(new String[permissionList.size()]), 1);
        } else {
            listener.onGranted();
        }
    }

    public static void openAlbum(Activity activity) {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        activity.startActivityForResult(intent, CHECKPHOTO7); // 打开相册
    }

    public static void handleImageOnKitKat(Activity activity,Intent data) {
        if(data == null){
            return;
        }
        Uri uri = data.getData();
        Log.d("TAG", "handleImageOnKitKat: uri is " + uri);
        String imagePath = uriToPath(activity,uri);
        //        displayImage(imagePath); // 根据图片路径显示图片

        Log.i("TAG", "file://" + imagePath + "选择图片的URI" + uri);
        startPhotoZoom(activity,new File(imagePath), 350);
    }


    private static String uriToPath(Activity activity,Uri uri) {
        String path = null;
        if (DocumentsContract.isDocumentUri(activity, uri)) {
            // 如果是document类型的Uri，则通过document id处理
            String docId = DocumentsContract.getDocumentId(uri);
            if ("com.android.providers.media.documents".equals(uri.getAuthority())) {
                String id = docId.split(":")[1]; // 解析出数字格式的id
                String selection = MediaStore.Images.Media._ID + "=" + id;
                path = getImagePath(activity,MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
            } else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
                Uri contentUri =
                        ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"),
                                Long.valueOf(docId));
                path = getImagePath(activity,contentUri, null);
            }
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            // 如果是content类型的Uri，则使用普通方式处理
            path = getImagePath(activity,uri, null);
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            // 如果是file类型的Uri，直接获取图片路径即可
            path = uri.getPath();
        }
        return path;
    }
    private static String getImagePath(Activity activity,Uri uri, String selection) {
        String path = null;
        // 通过Uri和selection来获取真实的图片路径
        Cursor cursor = activity.getContentResolver().query(uri, null, selection, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }
    /**
     * 剪裁图片
     */
    public static void startPhotoZoom(Activity activity,File file, int size) {
        //Log.i("TAG", getImageContentUri(this, file) + "裁剪照片的真实地址");
        try {
            cachPath = FileUtils.getStorageDirectory() + UUID.randomUUID().toString()+ ".jpg";
            cacheFile = new File(cachPath);
            Intent intent = new Intent("com.android.camera.action.CROP");
            intent.setDataAndType(getImageContentUri(activity, file), "image/*");//自己使用Content Uri替换File Uri
            intent.putExtra("crop", "true");
            intent.putExtra("aspectX", 1);
            intent.putExtra("aspectY", 1);
            intent.putExtra("outputX", size);
            intent.putExtra("outputY", size);
            intent.putExtra("scale", true);
            intent.putExtra("return-data", false);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(cacheFile));//定义输出的File Uri
            intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
            intent.putExtra("noFaceDetection", true);
            activity.startActivityForResult(intent, CROPPHOTO7);
        } catch (ActivityNotFoundException e) {
            String errorMessage = "Your device doesn't support the crop action!";
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static Uri getImageContentUri(Context context, File imageFile) {
        String filePath = imageFile.getAbsolutePath();
        Cursor cursor = context.getContentResolver()
                .query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        new String[] { MediaStore.Images.Media._ID }, MediaStore.Images.Media.DATA + "=? ",
                        new String[] { filePath }, null);

        if (cursor != null && cursor.moveToFirst()) {
            int id = cursor.getInt(cursor.getColumnIndex(MediaStore.MediaColumns._ID));
            Uri baseUri = Uri.parse("content://media/external/images/media");
            return Uri.withAppendedPath(baseUri, "" + id);
        } else {
            if (imageFile.exists()) {
                ContentValues values = new ContentValues();
                values.put(MediaStore.Images.Media.DATA, filePath);
                return context.getContentResolver()
                        .insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
            } else {
                return null;
            }
        }
    }

    public static void handleImageBeforeKitKat(Activity activity,Intent data) {
        if (data == null){
            return;
        }
        Uri uri = data.getData();
        String imagePath = getImagePath(activity,uri, null);
        //        displayImage(imagePath);
        Log.i("TAG", "file://" + imagePath + "选择图片的URI" + uri);
        startPhotoZoom(activity,new File(imagePath), 350);
    }

    /** 拍照7.0 */
    public static void takePhoto(final Activity activity) {
        String[] permissions = {
                Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        };
        requestRuntimePermission(activity,permissions, new PermissionListener() {
            @Override public void onGranted() {
                myTakePhotoFor7(activity);
            }

            @Override public void onDenied(List<String> deniedPermission) {
                //有权限被拒绝，什么也不做好了，看你心情
            }
        });
    }
    private static void myTakePhotoFor7(Activity activity){
        String mUUID = UUID.randomUUID().toString();
        mPath = FileUtils.getStorageDirectory() + mUUID;
        File cameraFile = new File(mPath + ".jpg");
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            imageUri = Uri.fromFile(cameraFile);
        } else {
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            imageUri = FileProvider.getUriForFile(activity, fileprovider, cameraFile);
        }
        // 启动相机程序
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        activity.startActivityForResult(intent, TAKEPHOTO7);
    }
    public interface PermissionListener {
        /**
         * 成功获取权限
         */
        void onGranted();

        /**
         * 为获取权限
         */
        void onDenied(List<String> deniedPermission);
    }
}
