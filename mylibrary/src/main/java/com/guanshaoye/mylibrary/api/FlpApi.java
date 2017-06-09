package com.guanshaoye.mylibrary.api;
import android.text.TextUtils;
import android.util.Log;
import com.guanshaoye.mylibrary.http.FlpException;
import com.guanshaoye.mylibrary.http.FlpHttp;
import com.guanshaoye.mylibrary.http.RequestBack;
import com.guanshaoye.mylibrary.utils.CommonUtil;
import com.guanshaoye.mylibrary.utils.Toaster;
import java.io.File;
import java.util.Map;
/**
 * Created by ${张梦博} on 2016/6/1.
 * Whenever,Wherever,Whatever,I believe I can handle everything
 */
public abstract class FlpApi {
    private static final String TAG = FlpApi.class.getName();
    protected static void requestAsync(String url, Map<String, Object> params, RequestBack requestBack) {
        if (TextUtils.isEmpty(url) || null == params || null == requestBack) {
            Log.e(TAG, "Argument error!");
            return;
         }
        if (!CommonUtil.isNetworkConnected(
                Toaster.getContext())) {
            requestBack.onFlpException(new FlpException("请检查网络链接"));
            return;
        }
		FlpHttp.requestAsync(url, params, requestBack);//调用OKHttp
       }
    protected static void upLoadFile(String url, String fileKey, File file, Map<String, Object> params,
                                     RequestBack requestBack) {
        if (TextUtils.isEmpty(url) || null == params || null == requestBack){
            Log.e(TAG, "Argument error!");
            return;
          }
        if(!CommonUtil.isNetworkConnected(Toaster.getContext())) {
            requestBack.onFlpException(new FlpException("请检查网络链接"));
            return;
          }
        FlpHttp.upLoadFile(url,fileKey,file, params, requestBack);//调用OKHttp
    }
}
