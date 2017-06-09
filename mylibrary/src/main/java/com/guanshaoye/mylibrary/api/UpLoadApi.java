package com.guanshaoye.mylibrary.api;

import com.guanshaoye.mylibrary.http.BaseUrl;
import com.guanshaoye.mylibrary.http.RequestBack;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by karl on 2017/5/24.
 */

public class UpLoadApi extends FlpApi{

    /**
     * 上传图片
     * @param tid
     * @param requestBack
     */
    public static void upLoadPicture(String tid, String pic_url, RequestBack requestBack) {
        Map<String, Object> params = new HashMap<>();
        params.put("tid", tid);
        params.put("pic_url",pic_url);
        params.put("mid",1);
        requestAsync(BaseUrl.UPLOAD_PICTURE, params, requestBack);
    }
}
