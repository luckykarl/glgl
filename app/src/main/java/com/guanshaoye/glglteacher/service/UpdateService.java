package com.guanshaoye.glglteacher.service;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.Notification.Builder;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.widget.RemoteViews;


import com.guanshaoye.glglteacher.R;
import com.guanshaoye.mylibrary.utils.APIHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
/**
 * <b>PackageName:</b> com.Nk.cn.util<br>
 * <b>FileName:</b> UpdateService.java<br>
 * <b>ClassName:</b> UpdateService<br>
 * @Description  apk 升级
 * @author       nk0000098	
 * @date         2015年6月9日 下午2:02:26
 */
public class UpdateService extends Service{
	// 标题
	String url;
	// 文件存储
	private File updateDir = null;
	private File updateFile = null;
	// 下载状态
	private final static int DOWNLOAD_COMPLETE = 0;
	private final static int DOWNLOAD_FAIL = 1;

	// 这样的下载代码很多，我就不做过多的说明
	int downloadCount = 0;
	int currentSize = 0;
	long totalSize = 0;
	int updateTotalSize = 0;
	// 在onStartCommand()方法中准备相关的下载工作：
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// 获取传值
		url = intent.getStringExtra("url");
		// 创建文件
		if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
			updateDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
			final String filename = url.substring(url.lastIndexOf("/"), url.length());
			updateFile = new File(updateDir.getAbsolutePath() + File.separator + filename);
		}
		Builder b = new Builder(this)
				.setContentTitle("咕噜咕噜")
				.setContentText("0%")
				.setTicker("开始下载咕噜咕噜")
				.setSmallIcon(R.mipmap.app_luncher);
		Notification n = APIHelper.getNotification(b);
		NotificationManager nm = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
		nm.notify(0, n);
		
		// 开启一个新的线程下载，如果使用Service同步下载，会导致ANR问题，Service本身也会阻塞
		new Thread(new updateRunnable()).start();// 这个是下载的重点，是下载的过程
		return super.onStartCommand(intent, flags, startId);
	}
	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}

	private Handler updateHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case DOWNLOAD_COMPLETE:
				// 点击安装PendingIntent
				Uri uri = Uri.fromFile(updateFile);
				Intent installIntent = new Intent(Intent.ACTION_VIEW);
				installIntent.setDataAndType(uri,"application/vnd.android.package-archive");
				PendingIntent pi = PendingIntent.getActivity(UpdateService.this, 0, installIntent, 0);
				Builder b = new Builder(UpdateService.this)
						.setContentTitle("咕噜咕噜")
						.setContentText("咕噜咕噜下载完成,点击安装。")
						.setDefaults(Notification.DEFAULT_SOUND)
						.setSmallIcon(R.mipmap.app_luncher)
						.setContentIntent(pi);
				Notification n = APIHelper.getNotification(b);
				NotificationManager nm = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
				nm.notify(0, n);
				// 停止服务
				stopSelf();
				break;
			case DOWNLOAD_FAIL:
				// 下载失败
				b = new Builder(UpdateService.this)
						.setContentTitle("咕噜咕噜")
						.setContentText("咕噜咕噜下载失败!")
						.setSmallIcon(R.mipmap.app_luncher);
				n = APIHelper.getNotification(b);
				nm = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
				nm.notify(0, n);
				stopSelf();
				break;
			default:
				stopSelf();
			}
		}
	};
	public long downloadUpdateFile(String downloadUrl, File saveFile)throws Exception {

		HttpURLConnection httpConnection = null;
		InputStream is = null;
		FileOutputStream fos = null;
		try {
			URL url = new URL(downloadUrl);
			httpConnection = (HttpURLConnection) url.openConnection();
			httpConnection.setRequestProperty("User-Agent", "PacificHttpClient");
			if (currentSize > 0) {
				httpConnection.setRequestProperty("RANGE", "bytes="+ currentSize + "-");
			}
			httpConnection.setConnectTimeout(10000);
			httpConnection.setReadTimeout(20000);
			updateTotalSize = httpConnection.getContentLength();
			if (httpConnection.getResponseCode() == 404) {
				throw new Exception("fail!");
			}
			is = httpConnection.getInputStream();
			fos = new FileOutputStream(saveFile, false);
			byte buffer[] = new byte[4096];
			int readsize = 0;
			while ((readsize = is.read(buffer)) > 0) {
				fos.write(buffer, 0, readsize);
				totalSize += readsize;
				// 为了防止频繁的通知导致应用吃紧，百分比增加10才通知一次
				if ((downloadCount == 0)|| (int) (totalSize * 100 / updateTotalSize) - 10 > downloadCount) {
					downloadCount += 10;
					/***
					 * 在这里我们用自定的view来显示Notification
					 */
					RemoteViews rv = new RemoteViews(getPackageName(), R.layout.notification_item);
					rv.setTextViewText(R.id.notificationTitle, "正在下载咕噜咕噜");
					rv.setProgressBar(R.id.notificationProgress, 100, downloadCount, false);
					
					Builder b = new Builder(this)
							.setContentTitle("正在下载咕噜咕噜")
							.setContentText((int) totalSize * 100 / updateTotalSize+ "%")
							.setSmallIcon(R.mipmap.app_luncher)
							.setContent(rv);
					Notification n = APIHelper.getNotification(b);
					NotificationManager nm = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
					nm.notify(0, n);
				}
			}
		} finally {
			if (httpConnection != null) {
				httpConnection.disconnect();
			   }
			if (is != null) {
				is.close();
			}
			if (fos != null) {
				fos.close();
			}
		}
		return totalSize;
	}

	class updateRunnable implements Runnable {
		Message message = updateHandler.obtainMessage();

		public void run() {
			message.what = DOWNLOAD_COMPLETE;			
			try {
				if (!updateDir.exists()) {
					updateDir.mkdirs();
				}
				if (!updateFile.exists()) {
					updateFile.createNewFile();
				}
				long downloadSize = downloadUpdateFile(url,updateFile);
				if (downloadSize > 0) {
					// 下载成功
					updateHandler.sendMessage(message);
				} 
			} catch (Exception ex) {
				ex.printStackTrace();
				message.what = DOWNLOAD_FAIL;
				// 下载失败
				updateHandler.sendMessage(message);
			}
		}
	}
}
