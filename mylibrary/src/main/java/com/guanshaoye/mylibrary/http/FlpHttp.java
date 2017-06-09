package com.guanshaoye.mylibrary.http;
import android.text.TextUtils;
import android.util.Log;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.xiwen.okhttputils.OkHttpUtils;
import com.xiwen.okhttputils.builder.PostFormBuilder;
import com.xiwen.okhttputils.callback.FileCallBack;
import com.xiwen.okhttputils.callback.StringCallback;
import java.io.File;
import java.util.Map;
import okhttp3.Call;
/**
 * Created by ${张梦博} on 2016/6/1.
 * Whenever,Wherever,Whatever,I believe I can handle everything
 */
public class FlpHttp {
    public static String url;
    public static void requestAsync(final String uri, final Map<String, Object> params, final RequestBack requestBack) {
                 /* if (params.size() == 0) {
            params.put("", "");
            }*/
        PostFormBuilder post = OkHttpUtils.post();
        post.url(uri);
//        long timestamp=System.currentTimeMillis();
//        String token=Base64Util.Base64ToString(
//                MD5.MD(String.valueOf(timestamp)+"cyflp")+"_"+
//                String.valueOf(timestamp));
//                params.put("token_code",token);
        if(null!=params){
        for (Map.Entry<String, Object> entry : params.entrySet()){
            post.addParams(entry.getKey(), entry.getValue() + "");
        }
        StringBuffer sb1 = new StringBuffer();
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            sb1.append(entry.getKey()).append("=").append(entry.getValue())
                    .append("&");
           }
           sb1.deleteCharAt(sb1.length()-1);
         Log.i("Http",uri+"/"+sb1.toString());
            url=sb1.toString();
        }
        post.build().execute(new StringCallback(){
            @Override
            public void onResponse(String result){
                Log.e("FlpHttp", result);
                if(!TextUtils.isEmpty(result)){
                   try {
                    FlpBack bpztBack = JSON.parseObject(result, FlpBack.class);
                    if(!TextUtils.isEmpty(bpztBack.data)){

                        Log.i("FlpHttp", bpztBack.data+"\n"+bpztBack.status);
                      }
                    if (bpztBack.success||!bpztBack.success&&(bpztBack.errorCode==201 || bpztBack.errorCode == 202)){
                        requestBack.onComplete(bpztBack);
                        Log.i("FlpHttp",bpztBack.message+"code"+bpztBack.errorCode);
                       }
                    else{
                        if(url.indexOf("apply_activity_log")>0){
                            requestBack.onComplete(bpztBack);
                        }else{
                            requestBack.onFlpException(new FlpException(bpztBack.message));
                        }
                       }
                    } catch (JSONException e){
                      requestBack.onFlpException(new FlpException("网络请求出错，请检查网络!"));
                     }
                  }
               }
            @Override
            public void onError(Call arg0, Exception arg1){
                requestBack.onFlpException(new FlpException("网络请求出错"));
            }
         });
    }
    /**
     * 文件上传
     * @param uri
     * @param fileKey
     * @param file
     * @param params
     * @param requestBack
     */
    public static void upLoadFile(final String uri,String fileKey,File file,
                                  final Map<String, Object> params, final RequestBack requestBack) {
        if (params.size() == 0) {
            params.put("", "");
        }
//        long timestamp=System.currentTimeMillis();
//        String token=Base64Util.Base64ToString(MD5.MD(String.valueOf(timestamp)+"cyflp")+"_"+
//                String.valueOf(timestamp));
//        params.put("token_code",token);
        PostFormBuilder post = OkHttpUtils.post();
        post.url(uri);
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            post.addParams(entry.getKey(), entry.getValue() + "");
        }
        post.addFile(fileKey,file.getName(),file);
        StringBuffer sb1 = new StringBuffer();
        for (Map.Entry<String, Object> entry : params.entrySet()){
            sb1.append(entry.getKey()).append("=").append(entry.getValue())
                    .append("&");
            }
        post.build().execute(new StringCallback() {
                                 @Override
                                 public void onError(Call call, Exception e) {
                                     requestBack.onFlpException(new FlpException("网络请求出错"));
                                 }
                                 @Override
                                 public void onResponse(String result) {
                                     try {
                                         FlpBack bpBack = JSON.parseObject(result, FlpBack.class);
                                         if (bpBack.success||!bpBack.success&&bpBack.errorCode==201) {
                                             requestBack.onComplete(bpBack);
                                             Log.e("FlpHttp", bpBack.toString());
                                         } else{
                                             requestBack.onFlpException(new FlpException(
                                                     bpBack.message));
                                         }
                                     } catch (JSONException e){
                                         requestBack.onFlpException(new FlpException("网络请求出错，请检查网络"));
                                     }
                                 }
                                 @Override
                                 public void inProgress(float progress) {
                                     requestBack.inProgress(progress);
                                 }
                             }
        );
    }
    /**
     * 文件下载
     * @param uri
     * @param fileCallBack
     */
    public static void DonwLoadFile(final String uri, FileCallBack fileCallBack) {
        OkHttpUtils.get().url(uri)
                .build().execute(fileCallBack);
        new FileCallBack("", "") {
            @Override
            public void inProgress(float v) {
            }
            @Override
            public void onError(Call call, Exception e) {
            }
            @Override
            public void onResponse(File file) {
            }
        };

    }


}
