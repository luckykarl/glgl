package com.guanshaoye.mylibrary.api;

import com.guanshaoye.mylibrary.http.BaseUrl;
import com.guanshaoye.mylibrary.http.RequestBack;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by karl on 2017/6/2.
 */

public class AuthApi extends FlpApi{

    /**
     * 获取视频上传凭证接口
     * @param uid
     * @param video_name
     * @param requestBack
     */
    public static void getAuth(String uid,String video_name,String video_title,RequestBack requestBack) {
        Map<String, Object> params = new HashMap<>();
        params.put("tid", uid);
        params.put("video_name", video_name);
        params.put("video_title", video_title);
        requestAsync(BaseUrl.GET_AUTH, params, requestBack);
    }

    /**
     * 老师认证
     * @param uid
     * @param item_class_id
     * @param course_class_id
     * @param video_url_str
     * @param competition_pic_url_str
     * @param approve_pic_url_str
     * @param requestBack
     */
    public static void submitAuth(String uid,String item_class_id,String course_class_id,
                                  String video_url_str,String competition_pic_url_str,String approve_pic_url_str,RequestBack requestBack) {
        Map<String, Object> params = new HashMap<>();
        params.put("tid", uid);
        params.put("item_class_id", item_class_id);
        params.put("course_class_id", course_class_id);
        params.put("video_url_str", video_url_str);
        params.put("competition_pic_url_str", competition_pic_url_str);
        params.put("approve_pic_url_str", approve_pic_url_str);
        requestAsync(BaseUrl.SUBMIT_AUTH, params, requestBack);
    }

    /**
     * 获取项目分类接口
     * @param uid
     * @param requestBack
     */
    public static void getProgectList(String uid,RequestBack requestBack) {
        Map<String, Object> params = new HashMap<>();
        params.put("tid", uid);
        requestAsync(BaseUrl.GET_PRO_LIST, params, requestBack);
    }

    /**
     * 获取课程分类接口
     * @param uid
     * @param item_class_id
     * @param requestBack
     */
    public static void getClassList(String uid,String item_class_id,RequestBack requestBack) {
        Map<String, Object> params = new HashMap<>();
        params.put("tid", uid);
        params.put("item_class_id", item_class_id);
        requestAsync(BaseUrl.GET_CLASS_LIST, params, requestBack);
    }

    /**
     * 获取老师认证信息
     * @param uid
     * @param requestBack
     */
    public static void getAuthInfo(String uid,RequestBack requestBack) {
        Map<String, Object> params = new HashMap<>();
        params.put("tid", uid);
        requestAsync(BaseUrl.GET_AUTH_INFO, params, requestBack);
    }
}
