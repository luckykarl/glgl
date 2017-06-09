package com.guanshaoye.mylibrary.api;

import com.guanshaoye.mylibrary.http.BaseUrl;
import com.guanshaoye.mylibrary.http.RequestBack;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by karl on 2017/5/24.
 * 抢课
 */

public class RobClassApi extends FlpApi{

    /**
     * 抢课主页面
     * @param uid
     * @param requestBack
     */
    public static void showGrapCpures(String uid,RequestBack requestBack) {
        Map<String, Object> params = new HashMap<>();
        params.put("tid", uid);
        requestAsync(BaseUrl.SHOW_GRAP_COURES, params, requestBack);
    }

    /**
     * 抢课列表
     * @param uid
     * @param date_str
     * @param gsy_store_id
     * @param gsy_teacher_level
     * @param requestBack
     */
    public static void showGrapCpuresList(String uid,String date_str,String gsy_store_id,String gsy_teacher_level,RequestBack requestBack) {
        Map<String, Object> params = new HashMap<>();
        params.put("tid", uid);
        params.put("date_str", date_str);
        params.put("gsy_store_id", gsy_store_id);
        params.put("gsy_teacher_level", gsy_teacher_level);
        requestAsync(BaseUrl.SHOW_GRAP_COURES_LIST, params, requestBack);
    }

    /**
     * 抢课
     * @param uid
     * @param gsy_schedule_course_id
     * @param requestBack
     */
    public static void addGrapCpures(String uid,String gsy_schedule_course_id ,String gsy_store_id,RequestBack requestBack) {
        Map<String, Object> params = new HashMap<>();
        params.put("tid", uid);
        params.put("gsy_store_id", gsy_store_id);
        params.put("gsy_schedule_course_id", gsy_schedule_course_id);
        requestAsync(BaseUrl.ADD_GRAP_COURES_LIST, params, requestBack);
    }

    /**
     * 取消抢课
     * @param uid
     * @param gsy_schedule_course_id
     * @param requestBack
     */
    public static void cancleGrapCpures(String uid,String gsy_schedule_course_id ,RequestBack requestBack) {
        Map<String, Object> params = new HashMap<>();
        params.put("tid", uid);
        params.put("gsy_schedule_course_id", gsy_schedule_course_id);
        requestAsync(BaseUrl.CANCLE_GRAP_COURES_LIST, params, requestBack);
    }
}
