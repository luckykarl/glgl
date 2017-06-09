package com.guanshaoye.mylibrary.api;

import com.guanshaoye.mylibrary.http.BaseUrl;
import com.guanshaoye.mylibrary.http.RequestBack;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by karl on 2017/5/25.
 */

public class ManagerApi extends FlpApi{

    /**
     * 获取培训签到列表
     * @param uid
     * @param is_signin
     * @param page
     * @param requestBack
     */
    public static void getTrainSignList(String uid,int is_signin,int page,RequestBack requestBack) {
        Map<String, Object> params = new HashMap<>();
        params.put("tid", uid);
        params.put("is_signin", is_signin);
        params.put("page", page);
        requestAsync(BaseUrl.GET_TRAIN_LIST, params, requestBack);
    }

    /**
     * 参与培训老师接口
     * @param uid
     * @param gsy_train_id
     * @param requestBack
     */
    public static void getTrainTeacherList(String uid,String gsy_train_id,RequestBack requestBack) {
        Map<String, Object> params = new HashMap<>();
        params.put("tid", uid);
        params.put("gsy_train_id", gsy_train_id);
        requestAsync(BaseUrl.GET_TRAIN_TEACHER_LIST, params, requestBack);
    }

    /**
     * 培训签到接口
     * @param uid
     * @param gsy_train_id
     * @param requestBack
     */
    public static void signTrain(String uid,String gsy_train_id,RequestBack requestBack) {
        Map<String, Object> params = new HashMap<>();
        params.put("tid", uid);
        params.put("gsy_train_detail_id", gsy_train_id);
        requestAsync(BaseUrl.SIGN_TRAIN, params, requestBack);
    }
    /**
     * 获取上课签到列表
     * @param uid
     * @param is_signin
     * @param page
     * @param requestBack
     */
    public static void getTAttendClassList(String uid,int is_signin,int page,RequestBack requestBack) {
        Map<String, Object> params = new HashMap<>();
        params.put("tid", uid);
        params.put("is_signin", is_signin);
        params.put("page", page);
        requestAsync(BaseUrl.GET_ATTEND_CLASS_LIST, params, requestBack);
    }

    /**
     * 学员签到列表
     * @param uid
     * @param detail_id
     * @param requestBack
     */
    public static void AttendClassList(String uid,String detail_id,RequestBack requestBack){
        Map<String, Object> params = new HashMap<>();
        params.put("tid", uid);
        params.put("gsy_course_schedule_detail_id", detail_id);
        requestAsync(BaseUrl.ATTEND_CLASS_LIST, params, requestBack);
    }

    /**
     * 学员添加签到
     * @param uid
     * @param reserve_id
     * @param requestBack
     */
    public static void signAttendClass(String uid,String reserve_id,RequestBack requestBack){
        Map<String, Object> params = new HashMap<>();
        params.put("tid", uid);
        params.put("gsy_course_reserve_id", reserve_id);
        requestAsync(BaseUrl.SIGN_ATTEND_CLASS, params, requestBack);
    }

    /**
     * 上传签到图片接口
     * @param uid
     * @param gsy_course_schedule_detail_id
     * @param pic_url_str
     * @param requestBack
     */
    public static void signAttendPic(String uid,String gsy_course_schedule_detail_id,String pic_url_str,RequestBack requestBack){
        Map<String, Object> params = new HashMap<>();
        params.put("tid", uid);
        params.put("gsy_course_schedule_detail_id", gsy_course_schedule_detail_id);
        params.put("pic_url_str", pic_url_str);
        requestAsync(BaseUrl.SIGN_PIC_ATTEND_CLASS, params, requestBack);
    }

    /**
     * 评论列表接口
     * @param uid
     * @param is_comment
     * @param page
     * @param requestBack
     */
    public static void getCommentnList(String uid,int is_comment,int page,RequestBack requestBack) {
        Map<String, Object> params = new HashMap<>();
        params.put("tid", uid);
        params.put("is_comment", is_comment);
        params.put("page", page);
        requestAsync(BaseUrl.GET_COMMENT_LIST, params, requestBack);
    }

    /**
     * 评论详情接口
     */
    public static void showComment(String uid,String gsy_course_schedule_detail_id,String gsy_course_grap_id,int page,RequestBack requestBack) {
        Map<String, Object> params = new HashMap<>();
        params.put("tid", uid);
        params.put("gsy_course_schedule_detail_id", gsy_course_schedule_detail_id);
        params.put("gsy_course_grap_id", gsy_course_grap_id);
        params.put("page", page);
        requestAsync(BaseUrl.SHOW_COMMENT_LIST, params, requestBack);
    }

    /**
     * 添加评论
     * @param uid
     * @param gsy_course_schedule_detail_id
     * @param gsy_course_grap_id
     * @param gsy_star_num
     * @param gsy_content
     * @param gsy_is_replace
     * @param pic_url_str
     * @param requestBack
     */

    public static void addComment(String uid,String gsy_course_schedule_detail_id,String gsy_course_grap_id,int gsy_star_num,
                                  String gsy_content,int gsy_is_replace,String pic_url_str,RequestBack requestBack) {
        Map<String, Object> params = new HashMap<>();
        params.put("tid", uid);
        params.put("gsy_course_schedule_detail_id", gsy_course_schedule_detail_id);
        params.put("gsy_course_grap_id", gsy_course_grap_id);
        params.put("gsy_star_num", gsy_star_num);
        params.put("gsy_content", gsy_content);
        params.put("gsy_is_replace", gsy_is_replace);
        params.put("pic_url_str", pic_url_str);
        requestAsync(BaseUrl.ADD_COMMENT, params, requestBack);
    }




}
