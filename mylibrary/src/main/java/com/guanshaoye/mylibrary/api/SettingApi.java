package com.guanshaoye.mylibrary.api;

import com.guanshaoye.mylibrary.http.BaseUrl;
import com.guanshaoye.mylibrary.http.RequestBack;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by karl on 2017/5/25.
 */

public class SettingApi extends FlpApi{

    /**
     * 获取用户设置信息
     * @param uid
     * @param requestBack
     */
    public static void getUserSetting(String uid,RequestBack requestBack) {
        Map<String, Object> params = new HashMap<>();
        params.put("tid", uid);
        requestAsync(BaseUrl.GET_USER_SETTTING, params, requestBack);
    }

    /**
     * 修改消息设置
     * @param uid
     * @param key
     * @param values
     * @param requestBack
     */
    public static void updateUserSetting(String uid,String key,int values,RequestBack requestBack) {
        Map<String, Object> params = new HashMap<>();
        params.put("tid", uid);
        params.put(key, values);
        requestAsync(BaseUrl.UPDATE_USER_SETTTING, params, requestBack);
    }


    /**
     * 意见反馈
     * @param uid
     * @param fb_content
     * @param requestBack
     */
    public static void submitFeedBack(String uid,String fb_content,RequestBack requestBack) {
        Map<String, Object> params = new HashMap<>();
        params.put("tid", uid);
        params.put("fb_content", fb_content);
        requestAsync(BaseUrl.SUBMIT_FEEDBACK, params, requestBack);
    }

    /**
     *获取版本信息接口
     * @param requestBack
     */
    public static void checkCesionCode(RequestBack requestBack) {
        Map<String, Object> params = new HashMap<>();
        params.put("gsy_type", 3);
        requestAsync(BaseUrl.CHECK_VESION_CODE, params, requestBack);
    }
}
