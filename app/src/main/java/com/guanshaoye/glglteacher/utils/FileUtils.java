package com.guanshaoye.glglteacher.utils;

import android.annotation.SuppressLint;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.UUID;

/**
 * Created by karl on 2017/5/29.
 */

public class FileUtils {
    public final static String FILE_PREFIX = "file://";

    public final static String ROOT_PATH = Environment.getExternalStorageDirectory()+"/hub/";
    //下载目录
    public final static String DOWNLOAD_PATH = ROOT_PATH
            + "download/";

    // Universal image loader 缓存目录
    private final static String CACHE_PATH = ROOT_PATH
            + "cache_images/";

    private final static String THUMBNAIL_PATH = ROOT_PATH
            + "thumbnail/";

    //视频/照片 处理暂存目录,app启动时会清空
    private final static String TEMP_PATH = ROOT_PATH
            + "tmp/";

    @NonNull
    public static String getDownloadPath() {
        File fileDir = new File(DOWNLOAD_PATH);
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }
        return DOWNLOAD_PATH;
    }

    public static String getThumbnailPath(){
        File fileDir = new File(THUMBNAIL_PATH);
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }
        return THUMBNAIL_PATH;
    }

    @NonNull
    public static String getCachePath() {
        File fileDir = new File(CACHE_PATH);
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }
        return CACHE_PATH;
    }

    @NonNull
    public static String getTempPath() {
        File fileDir = new File(TEMP_PATH);
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }
        return TEMP_PATH;
    }

    public static void saveBitmap(@NonNull Bitmap bm, String picName) {
        try {
            if (!isFileExist("")) {
                File tempf = createSDDir("");
            }
            File f = new File(TEMP_PATH, picName + ".jpg");
            if (f.exists()) {
                f.delete();
            }
            FileOutputStream out = new FileOutputStream(f);
            bm.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String saveBitmap(@NonNull String dirPath,String path,String picName) {
        try {
            File dir = new File(dirPath);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            File f = new File(dirPath,picName + ".jpg");
            if (f.exists()) {
                return dirPath + picName + ".jpg";
            }
            Bitmap bm = createVideoThumbnail(path,100,100);
            FileOutputStream out = new FileOutputStream(f);
            bm.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return dirPath + picName + ".jpg";
    }

    public static Bitmap createVideoThumbnail(String url, int width, int height) {
        Bitmap bitmap;
        int kind = MediaStore.Video.Thumbnails.FULL_SCREEN_KIND;
        bitmap = ThumbnailUtils.createVideoThumbnail(url, kind);
        bitmap = ThumbnailUtils.extractThumbnail(bitmap, width, height, ThumbnailUtils.OPTIONS_RECYCLE_INPUT);

        return bitmap;
    }


    @NonNull
    public static File createSDDir(String dirName) {
        File dir = new File(TEMP_PATH + dirName);
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {

            System.out.println("createSDDir:" + dir.getAbsolutePath());
            System.out.println("createSDDir:" + dir.mkdir());
        }
        return dir;
    }

    public static boolean isFileExist(String fileName) {
        File file = new File(TEMP_PATH + fileName);
        file.isFile();
        return file.exists();
    }

    /**
     * 描述：获取src中的图片资源.
     *
     * @param url 图片的src路径，如（“image/arrow.png”）
     * @return Bitmap 图片
     */
    public static Bitmap getBitmapFormSrc(@NonNull String url){
        try {
            FileInputStream fis = new FileInputStream(url);
            return BitmapFactory.decodeStream(fis);  ///把流转化为Bitmap图片

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Nullable
    public static File createFileObjFromPath(@Nullable final String path, final boolean mustCanRead) {
        if (path != null) {
            try {
                File file = new File(path);
                if (mustCanRead) {
                    file.setReadable(true);
                    if (!file.canRead()) {
                        return null;
                    }
                }
                return file.getAbsoluteFile();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }
    public static String queryAbsolutePath(@NonNull final Context context, @NonNull final Uri uri) {
        final String[] projection = { MediaStore.MediaColumns.DATA };
        Cursor cursor = null;
        try {
            cursor = context.getContentResolver().query(uri, projection, null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
                final int index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
                return cursor.getString(index);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            if (cursor != null) {
                cursor.close();
            }
        }
        return null;
    }

    public static void deleteDir()
    {
        File dir = new File(getTempPath());
        if (dir == null || !dir.exists() || !dir.isDirectory())
            return;

        for (File file : dir.listFiles())
        {
            if (file.isFile())
                file.delete(); // 删除所有文件
            else if (file.isDirectory())
                deleteDir(); // 递规的方式删除文件夹
        }
//		dir.delete();// 删除目录本身
    }


    @Nullable
    public static File getFilesFromUris(final Context context, final Uri uri) {
        return getFileFromUri(context, uri);
    }
    @Nullable
    @SuppressLint("NewApi")
    public static File getFileFromUri(final Context context, @Nullable final Uri uri) {
        if (uri == null) {
            return null;
        }
        // 判斷是否為Android 4.4之後的版本
        final boolean after44 = Build.VERSION.SDK_INT >= 19;
        if (after44 && DocumentsContract.isDocumentUri(context, uri)) {
            // 如果是Android 4.4之後的版本，而且屬於文件URI
            // 判斷Authority是否為本地端檔案所使用的
            // 圖片、影音檔案
            final String docId = DocumentsContract.getDocumentId(uri);
            final String[] divide = docId.split(":");
            final String type = divide[0];
            Uri mediaUri;
            if ("image".equals(type)) {
                mediaUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
            } else if ("video".equals(type)) {
                mediaUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
            } else if ("audio".equals(type)) {
                mediaUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
            } else {
                return null;
            }
            mediaUri = ContentUris.withAppendedId(mediaUri, Long.parseLong(divide[1]));
            String path = FileUtils.queryAbsolutePath(context, mediaUri);
            return FileUtils.createFileObjFromPath(path, true);
        } else {
            // 如果是一般的URI
            final String scheme = uri.getScheme();
            String path = null;
            if ("content".equals(scheme)) {
                // 內容URI
                path = FileUtils.queryAbsolutePath(context, uri);
            } else if ("file".equals(scheme)) {
                // 檔案URI
                path = uri.getPath();
            }
            return FileUtils.createFileObjFromPath(path, true);
        }
    }


    public static String genUUID()
    {
        return UUID.randomUUID().toString().replace("-", "");
    }
    public static String ShowVideoInfo(Context context,Intent data) {
        Uri selectedUri = data.getData();
        String videoPath = "";
        Cursor cursor = null;

        try {
            cursor = context.getContentResolver().query(selectedUri, null, null, null, null);
            // 应该有2种形式:
            // (1)以content:// 开始
            // (2)以file:// 开始

            if (selectedUri != null && selectedUri.toString().substring(0, 10).equals("content://") && cursor != null && cursor.moveToFirst()) {
                videoPath = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA));



                // debug video info

            } else if (selectedUri != null
                    && selectedUri.toString().substring(0, 7).equals("file://")
                    && cursor == null) {
                // 简单的方式判断。 常用的也就几个 MP4, AVI, 3GPP, WMV
                videoPath = selectedUri.toString();


            } else {

                return "";
            }

        } catch (Exception exp) {
            exp.printStackTrace();
            return "";
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        return videoPath;
    }


    public static Bitmap getMMR( String url) {
        Bitmap bitmap = null;
        MediaMetadataRetriever mmr = new MediaMetadataRetriever();
        try {
            if (url != null) {
                File file = new File(url);
                mmr.setDataSource(file.getAbsolutePath());
                bitmap = mmr.getFrameAtTime();
            }
        } catch (Exception ignored) {
            Log.e("------",ignored.toString());
        }
        return bitmap;
    }


    public static String selectImage(Context context,Intent data){
        Uri selectedImage = data.getData();
//      Log.e("=e=", selectedImage.toString());
        if(selectedImage!=null){
            String uriStr=selectedImage.toString();
            String path=uriStr.substring(10,uriStr.length());
            if(path.startsWith("com.sec.android.gallery3d")){
                Log.e("=selectImage=", "It's auto backup pic path:"+selectedImage.toString());
                return null;
            }
        }
        String[] filePathColumn = { MediaStore.Images.Media.DATA };
        Cursor cursor = context.getContentResolver().query(selectedImage,filePathColumn, null, null, null);
        cursor.moveToFirst();
        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
        String picturePath = cursor.getString(columnIndex);
        cursor.close();
        return picturePath;
    }

    public static String getPath(final Context context, final Uri uri) {

        final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;

        // DocumentProvider
        if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
            // ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }

                // TODO handle non-primary volumes
            }
            // DownloadsProvider
            else if (isDownloadsDocument(uri)) {

                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));

                return getDataColumn(context, contentUri, null, null);
            }
            // MediaProvider
            else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }

                final String selection = "_id=?";
                final String[] selectionArgs = new String[] {
                        split[1]
                };

                return getDataColumn(context, contentUri, selection, selectionArgs);
            }
        }
        // MediaStore (and general)
        else if ("content".equalsIgnoreCase(uri.getScheme())) {

            // Return the remote address
            if (isGooglePhotosUri(uri))
                return uri.getLastPathSegment();

            return getDataColumn(context, uri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }

        return null;
    }
    public static Bitmap createVideoThumbnail(String filePath) {
        // MediaMetadataRetriever is available on API Level 8  
        // but is hidden until API Level 10  
        Class<?> clazz = null;
        Object instance = null;
        try {
            clazz = Class.forName("android.media.MediaMetadataRetriever");
            instance = clazz.newInstance();

            Method method = clazz.getMethod("setDataSource", String.class);
            method.invoke(instance, filePath);

            // The method name changes between API Level 9 and 10.  
            if (Build.VERSION.SDK_INT <= 9) {
                return (Bitmap) clazz.getMethod("captureFrame").invoke(instance);
            } else {
                byte[] data = (byte[]) clazz.getMethod("getEmbeddedPicture").invoke(instance);
                if (data != null) {
                    Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                    if (bitmap != null) return bitmap;
                }
                return (Bitmap) clazz.getMethod("getFrameAtTime").invoke(instance);
            }
        } catch (IllegalArgumentException ex) {
            // Assume this is a corrupt video file  
        } catch (RuntimeException ex) {
            // Assume this is a corrupt video file.  
        } catch (InstantiationException e) {
            Log.e("=e=", "createVideoThumbnail", e);
        } catch (InvocationTargetException e) {
            Log.e("=e=", "createVideoThumbnail", e);
        } catch (ClassNotFoundException e) {
            Log.e("=e=", "createVideoThumbnail", e);
        } catch (NoSuchMethodException e) {
            Log.e("=e=", "createVideoThumbnail", e);
        } catch (IllegalAccessException e) {
            Log.e("=e=", "createVideoThumbnail", e);
        } finally {
            try {
                if (instance != null) {
                    clazz.getMethod("release").invoke(instance);
                }
            } catch (Exception ignored) {
            }
        }
        return null;
    }
    /**
     * Get the value of the data column for this Uri. This is useful for
     * MediaStore Uris, and other file-based ContentProviders.
     *
     * @param context The context.
     * @param uri The Uri to query.
     * @param selection (Optional) Filter used in the query.
     * @param selectionArgs (Optional) Selection arguments used in the query.
     * @return The value of the _data column, which is typically a file path.
     */
    public static String getDataColumn(Context context, Uri uri, String selection,
                                       String[] selectionArgs) {

        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {
                column
        };

        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
                    null);
            if (cursor != null && cursor.moveToFirst()) {
                final int index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }


    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is ExternalStorageProvider.
     */
    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is Google Photos.
     */
    public static boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }

    /**
     * sd卡的根目录
     */
    private static String mSdRootPath = Environment.getExternalStorageDirectory().getPath();
    /**
     * 手机的缓存根目录
     */
    private static String mDataRootPath = null;
    /**
     * 保存Image的目录名
     */
    private final static String FOLDER_NAME = "/ccclubsdk";


    public FileUtils(Context context){
        mDataRootPath = context.getCacheDir().getPath();
    }


    /**
     * 获取储存Image的目录
     * @return
     */
    public static String getStorageDirectory(){
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED) ?
                mSdRootPath + FOLDER_NAME : mDataRootPath + FOLDER_NAME;
    }
    /**
     * 判断文件是否存在
     * @param fileName
     * @return
     */
    public boolean isFileExists(String fileName){
        return new File(getStorageDirectory() + File.separator + fileName).exists();
    }

    /**
     * 获取文件的大小
     * @param fileName
     * @return
     */
    public long getFileSize(String fileName) {
        return new File(getStorageDirectory() + File.separator + fileName).length();
    }

    /**
     *  判断给定图片的大小，并进行缩放
     *  @param uri 图片路径
     *  @param width  目标高度
     *  @param  height  目标宽度
     *  @param   config 配色方案
     */
    public static Bitmap GetBitMapFromUrlAndSize1(String uri,int width,int height,int config){
        BitmapFactory.Options options=new BitmapFactory.Options();
        options.inJustDecodeBounds=true;
        BitmapFactory.decodeFile(uri,options);
        int mwidth=options.outWidth;
        int mheight=options.outHeight;
        options.inSampleSize =1;
        //缩略图为 120*160
        options.inSampleSize= mwidth/width < mheight/height? mwidth/width:mheight/height;
        switch (config) {
            case 1:
                options.inPreferredConfig= Bitmap.Config.ALPHA_8;
                break;

            case 2:
                options.inPreferredConfig= Bitmap.Config.RGB_565;
                break;

            case 3:
                options.inPreferredConfig= Bitmap.Config.ARGB_8888;
                break;

            default:
                options.inPreferredConfig= Bitmap.Config.RGB_565;
                break;
        }

        options.inJustDecodeBounds=false;

        return BitmapFactory.decodeFile(uri,options);
    }



    /**
     * 删除SD卡或者手机的缓存图片和目录
     */
    public static void  deleteFile() {
        File dirFile = new File(getStorageDirectory());
        if(! dirFile.exists()){
            return;
        }
        if (dirFile.isDirectory()) {
            String[] children = dirFile.list();
            for (int i = 0; i < children.length; i++) {
                new File(dirFile, children[i]).delete();
            }
        }

        dirFile.delete();
    }


}

