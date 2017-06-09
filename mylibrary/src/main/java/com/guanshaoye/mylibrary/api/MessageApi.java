package com.guanshaoye.mylibrary.api;

import com.guanshaoye.mylibrary.http.BaseUrl;
import com.guanshaoye.mylibrary.http.RequestBack;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by karl on 2017/6/3.
 */

public class MessageApi extends FlpApi{

    /**
     * 消息主页接口
     * @param tid
     * @param requestBack
     */
    public static void getMsgDisply(String tid,RequestBack requestBack) {
        Map<String, Object> params = new HashMap<>();
        params.put("tid", tid);
        requestAsync(BaseUrl.MSG_DISPLY, params, requestBack);
    }

    /**
     * 消息列表
     * @param tid
     * @param gsy_class_id
     * @param page
     * @param requestBack
     */

    public static void getMsgList(String tid,int gsy_class_id,int page,RequestBack requestBack) {
        Map<String, Object> params = new HashMap<>();
        params.put("tid", tid);
        params.put("page", page);
        params.put("gsy_class_id", gsy_class_id);
        requestAsync(BaseUrl.GET_MSG_LIST, params, requestBack);
    }
}
