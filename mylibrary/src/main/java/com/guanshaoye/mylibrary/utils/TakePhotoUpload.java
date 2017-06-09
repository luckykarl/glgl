package com.guanshaoye.mylibrary.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
public class TakePhotoUpload {
	public static AlertDialog alert=null;
	public static final int CompanyInfoActivity_PHOTO_REQUEST_CAMERA = 1;// 拍照
	public static final int CompanyInfoActivity_PHOTO_REQUEST_GALLERY = 2;// 从相册中选择
	public static final int CompanyInfoActivity_PHOTO_REQUEST_CUT = 3;// 相册裁剪结果
	public static final int CompanyInfoActivity_PHOTO_REQUEST_CUT2 = 4;// 相机裁剪结果
	public static Uri imageUri=Uri.parse("file:///sdcard/business_license.png");
	public static Uri imageUri2 = Uri.parse("file:///sdcard/temp.png");
	public static Uri imageUri3= Uri.parse("file:///sdcard/temp2.png");
	public static final int UPDATECOMPANY_SUCCESS=0;
	public static final int UPDATECOMPANY_FAIL=1;
	/*
     * 从相册获取
     */
	public static void gallery(Activity activity) {
		// 激活系统图库，选择一张图片
		Intent intent = new Intent(Intent.ACTION_PICK);
		intent.setType("image/*");
		activity.startActivityForResult(intent, CompanyInfoActivity_PHOTO_REQUEST_GALLERY);
	   }
	/*
     * 从相机获取
     */
	public static void camera(Activity activity) {
		Intent takePhotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		takePhotoIntent .putExtra(MediaStore.EXTRA_OUTPUT, imageUri2);
		activity.startActivityForResult(takePhotoIntent, CompanyInfoActivity_PHOTO_REQUEST_CAMERA);

	}
	/**
	 * 相册选取后剪切图片
	 *
	 * @function:
	 * @author:Jerry
	 * @param uri
	 */
	public static void crop(Uri uri,Activity activity,int aspectX,int aspectY,int outputX,int outputY) {
		// 裁剪图片意图
		Intent intent = new Intent("com.android.camera.action.CROP");
		intent.setDataAndType(uri, "image/*");
		intent.putExtra("crop", "true");
		//裁剪框的比例
		intent.putExtra("aspectX", aspectX);
		intent.putExtra("aspectY", aspectY);
		// 裁剪后输出图片的尺寸大小
		intent.putExtra("outputX", outputX);
		intent.putExtra("outputY", outputY);
		intent.putExtra("scale",true);
		intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
		//图片格式
		intent.putExtra("outputFormat", Bitmap.CompressFormat.PNG.toString());
		intent.putExtra("noFaceDetection", true);// 取消人脸识别
		intent.putExtra("return-data", false);// true:不返回uri，false：返回uri
		activity.startActivityForResult(intent, CompanyInfoActivity_PHOTO_REQUEST_CUT);
	}

	/**
	 * 相机选取后剪切图片
	 *
	 * @function:
	 * @author:Jerry
	 * @param uri
	 */
	public static void cropImageUri(Uri uri,Activity activity,int aspectX,int aspectY,int outputX,int outputY){

		Intent intent = new Intent("com.android.camera.action.CROP");
		intent.setDataAndType(uri, "image/*");
		intent.putExtra("crop", "true");//可裁剪
		intent.putExtra("aspectX", aspectX);
		intent.putExtra("aspectY", aspectY);
		// 裁剪后输出图片的尺寸大小
		intent.putExtra("outputX", outputX);
		intent.putExtra("outputY", outputY);
		intent.putExtra("scale", true);
		intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri3);
		intent.putExtra("return-data", false);//若为false则表示不返回数据
		intent.putExtra("outputFormat", Bitmap.CompressFormat.PNG.toString());
		intent.putExtra("noFaceDetection", true);
		activity.startActivityForResult(intent, CompanyInfoActivity_PHOTO_REQUEST_CUT2);
	}
	public static boolean hasSdcard() {
		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
			return true;
		} else {
			return false;
		}
	}
	//压缩成png格式的并伴随内存回收
	public static byte[] bmptoByteArray(final Bitmap map, final boolean neesrec) {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		map.compress(Bitmap.CompressFormat.PNG, 100, out);
		if (neesrec) {
			map.recycle();
		}
		byte[] res = out.toByteArray();
		try {
			out.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}

	public static Bitmap getBitmap(Uri uri,Activity activity){
		Bitmap bm=null;
		try {
			bm= BitmapFactory.decodeStream(activity.getContentResolver().openInputStream(uri));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		return bm;
	}

}
