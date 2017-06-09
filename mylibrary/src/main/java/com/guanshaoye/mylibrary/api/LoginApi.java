package com.guanshaoye.mylibrary.api;

import com.guanshaoye.mylibrary.http.BaseUrl;
import com.guanshaoye.mylibrary.http.RequestBack;
import com.guanshaoye.mylibrary.http.Url;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by karl on 2017/5/20.
 */

public class LoginApi extends FlpApi{
    /**
     * 发送验证码
     * @param mobile
     * @param type
     * @param requestBack
     */
    public static void sendSms(String mobile, int type,RequestBack requestBack) {
        Map<String, Object> params = new HashMap<>();
        params.put("mobile", mobile);
        params.put("type", type);
        requestAsync(BaseUrl.SEND_SMS, params, requestBack);
    }

    /**
     * 用户注册
     * @param mobile
     * @param code
     * @param requestBack
     */
    public static void userRegister(String mobile, String code,RequestBack requestBack) {
        Map<String, Object> params = new HashMap<>();
        params.put("mobile", mobile);
        params.put("code", code);
        requestAsync(BaseUrl.USER_REGISTER, params, requestBack);
    }

    /**
     * 用户登录
     * @param mobile
     * @param code
     * @param requestBack
     */
    public static void userLogin(String mobile, String code,RequestBack requestBack) {
        Map<String, Object> params = new HashMap<>();
        params.put("mobile", mobile);
        params.put("code", code);
        requestAsync(BaseUrl.USER_LOGIN, params, requestBack);
    }
}
