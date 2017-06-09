package com.guanshaoye.glglteacher.bean;

import android.text.TextUtils;

import com.guanshaoye.mylibrary.http.BaseUrl;

/**
 * Created by karl on 2017/5/30.
 */

public class UserCommentBean {

    /**
     * id : 20
     * gsy_course_schedule_detail_id : 10
     * gsy_store_name : 松江万达店
     * gsy_course_class_name : 轮滑
     * gsy_course_class_id : 7
     * gsy_end_time : 1495771200
     * gsy_status : 5
     * gsy_course_pic :
     * gsy_course_star : 4
     */

    private String id;
    private String gsy_course_schedule_detail_id;
    private String gsy_store_name;
    private String gsy_course_class_name;
    private String gsy_course_class_id;
    private String gsy_end_time;
    private String gsy_status;
    private String gsy_course_pic;
    private int gsy_course_star;
    private String gsy_course_time;

    public String getGsy_course_time() {
        return gsy_course_time;
    }

    public void setGsy_course_time(String gsy_course_time) {
        this.gsy_course_time = gsy_course_time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGsy_course_schedule_detail_id() {
        return gsy_course_schedule_detail_id;
    }

    public void setGsy_course_schedule_detail_id(String gsy_course_schedule_detail_id) {
        this.gsy_course_schedule_detail_id = gsy_course_schedule_detail_id;
    }

    public String getGsy_store_name() {
        return gsy_store_name;
    }

    public void setGsy_store_name(String gsy_store_name) {
        this.gsy_store_name = gsy_store_name;
    }

    public String getGsy_course_class_name() {
        return gsy_course_class_name;
    }

    public void setGsy_course_class_name(String gsy_course_class_name) {
        this.gsy_course_class_name = gsy_course_class_name;
    }

    public String getGsy_course_class_id() {
        return gsy_course_class_id;
    }

    public void setGsy_course_class_id(String gsy_course_class_id) {
        this.gsy_course_class_id = gsy_course_class_id;
    }

    public String getGsy_end_time() {
        return gsy_end_time;
    }

    public void setGsy_end_time(String gsy_end_time) {
        this.gsy_end_time = gsy_end_time;
    }

    public String getGsy_status() {
        return gsy_status;
    }

    public void setGsy_status(String gsy_status) {
        this.gsy_status = gsy_status;
    }

    public String getGsy_course_pic() {
        if(TextUtils.isEmpty(gsy_course_pic)){
            gsy_course_pic = "";
        }
        return BaseUrl.HEAD_PHOTO_OSS+gsy_course_pic;
    }

    public void setGsy_course_pic(String gsy_course_pic) {
        this.gsy_course_pic = gsy_course_pic;
    }

    public int getGsy_course_star() {
        return gsy_course_star;
    }

    public void setGsy_course_star(int gsy_course_star) {
        this.gsy_course_star = gsy_course_star;
    }
}
