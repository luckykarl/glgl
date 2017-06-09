package com.guanshaoye.mylibrary.api;

import com.guanshaoye.mylibrary.http.BaseUrl;
import com.guanshaoye.mylibrary.http.RequestBack;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by karl on 2017/5/21.
 */

public class HomeApi extends FlpApi{
    /**
     *主页展示页面
     * @param uid
     * @param requestBack
     */
    public static void getShow(String uid,RequestBack requestBack) {
        Map<String, Object> params = new HashMap<>();
        params.put("tid", uid);
        requestAsync(BaseUrl.HOME_SHOW, params, requestBack);
    }
}
