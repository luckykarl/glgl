package com.guanshaoye.glglteacher.bean;

import android.text.TextUtils;

import com.guanshaoye.mylibrary.http.BaseUrl;
import com.guanshaoye.mylibrary.utils.TextUtil;

/**
 * Created by karl on 2017/5/24.
 * 上课签到
 */

public class AttendClassBean extends QXModel{


    /**
     * gsy_course_schedule_detail_id : 11
     * gsy_course_pic :
     * gsy_course_level : 0
     * course_time : 2017-05-26 06:00-07:00
     * start_course_time : 2017-05-26 06:00
     * gsy_max_apply_count : 20
     * gsy_apply_count : 2
     * max_signin_count : 2
     * gsy_signin_count : 0
     * gsy_course_class_name : 轮滑
     * gsy_store_name : 五角场万达店
     */

    private String gsy_course_schedule_detail_id;
    private String gsy_course_pic;
    private int gsy_course_level;
    private String course_time;
    private String start_course_time;
    private String gsy_max_apply_count;
    private String gsy_apply_count;
    private String max_signin_count;
    private String gsy_signin_count;
    private String gsy_course_class_name;
    private String gsy_store_name;
    private String gsy_teacher_level;
    private String gsy_teacher_portrait;
    private String gsy_teacher_name;

    public String getGsy_teacher_name() {
        return gsy_teacher_name;
    }

    public void setGsy_teacher_name(String gsy_teacher_name) {
        this.gsy_teacher_name = gsy_teacher_name;
    }

    public String getGsy_teacher_level() {
        return gsy_teacher_level;
    }

    public void setGsy_teacher_level(String gsy_teacher_level) {
        this.gsy_teacher_level = gsy_teacher_level;
    }

    public String getGsy_teacher_portrait() {
        if(TextUtils.isEmpty(gsy_teacher_portrait)){
            gsy_teacher_portrait = "";
        }
        return BaseUrl.HEAD_PHOTO_OSS+gsy_teacher_portrait;
    }

    public void setGsy_teacher_portrait(String gsy_teacher_portrait) {
        this.gsy_teacher_portrait = gsy_teacher_portrait;
    }

    public String getGsy_course_schedule_detail_id() {
        return gsy_course_schedule_detail_id;
    }

    public void setGsy_course_schedule_detail_id(String gsy_course_schedule_detail_id) {
        this.gsy_course_schedule_detail_id = gsy_course_schedule_detail_id;
    }

    public String getGsy_course_pic() {
        return gsy_course_pic;
    }

    public void setGsy_course_pic(String gsy_course_pic) {
        this.gsy_course_pic = gsy_course_pic;
    }

    public int getGsy_course_level() {
        return gsy_course_level;
    }

    public void setGsy_course_level(int gsy_course_level) {
        this.gsy_course_level = gsy_course_level;
    }

    public String getCourse_time() {
        return course_time;
    }

    public void setCourse_time(String course_time) {
        this.course_time = course_time;
    }

    public String getStart_course_time() {
        return start_course_time;
    }

    public void setStart_course_time(String start_course_time) {
        this.start_course_time = start_course_time;
    }

    public String getGsy_max_apply_count() {
        return gsy_max_apply_count;
    }

    public void setGsy_max_apply_count(String gsy_max_apply_count) {
        this.gsy_max_apply_count = gsy_max_apply_count;
    }

    public String getGsy_apply_count() {
        return gsy_apply_count;
    }

    public void setGsy_apply_count(String gsy_apply_count) {
        this.gsy_apply_count = gsy_apply_count;
    }

    public String getMax_signin_count() {
        return max_signin_count;
    }

    public void setMax_signin_count(String max_signin_count) {
        this.max_signin_count = max_signin_count;
    }

    public String getGsy_signin_count() {
        return gsy_signin_count;
    }

    public void setGsy_signin_count(String gsy_signin_count) {
        this.gsy_signin_count = gsy_signin_count;
    }

    public String getGsy_course_class_name() {
        return gsy_course_class_name;
    }

    public void setGsy_course_class_name(String gsy_course_class_name) {
        this.gsy_course_class_name = gsy_course_class_name;
    }

    public String getGsy_store_name() {
        return gsy_store_name;
    }

    public void setGsy_store_name(String gsy_store_name) {
        this.gsy_store_name = gsy_store_name;
    }
}
