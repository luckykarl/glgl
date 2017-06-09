package com.guanshaoye.glglteacher.bean;

import android.text.TextUtils;

import com.guanshaoye.mylibrary.http.BaseUrl;

/**
 * Created by karl on 2017/5/24.
 * 培训管理
 */

public class TrainBean extends QXModel{

    /**
     * gsy_train_id : 1
     * gsy_course_pic :
     * gsy_train_level_name : 初级培训
     * gsy_train_time : 2017-05-28 10:00
     * gsy_train_address : 松江万达店
     * gsy_train_status : 2
     * gsy_train_count : 20
     * gsy_signin_count : 2
     */

    private String gsy_train_id;
    private String gsy_course_pic;
    private String gsy_train_level_name;
    private String gsy_train_time;
    private String gsy_train_address;
    private String gsy_train_status;
    private String gsy_train_count;
    private String gsy_signin_count;
    private String gsy_course_name;
    private String gsy_train_detail_id;
    private String gsy_teacher_name;
    private String gsy_teacher_portrait;
    private String gsy_teacher_level;
    private int gsy_signin_status; //是否签到 0否 1是

    public String getGsy_train_detail_id() {
        return gsy_train_detail_id;
    }

    public void setGsy_train_detail_id(String gsy_train_detail_id) {
        this.gsy_train_detail_id = gsy_train_detail_id;
    }

    public String getGsy_teacher_name() {
        return gsy_teacher_name;
    }

    public void setGsy_teacher_name(String gsy_teacher_name) {
        this.gsy_teacher_name = gsy_teacher_name;
    }

    public String getGsy_teacher_portrait() {
        if (TextUtils.isEmpty(gsy_teacher_portrait)){
            gsy_teacher_portrait = "";
        }
        return BaseUrl.HEAD_PHOTO+gsy_teacher_portrait;
    }

    public void setGsy_teacher_portrait(String gsy_teacher_portrait) {
        this.gsy_teacher_portrait = gsy_teacher_portrait;
    }

    public String getGsy_teacher_level() {
        return gsy_teacher_level;
    }

    public void setGsy_teacher_level(String gsy_teacher_level) {
        this.gsy_teacher_level = gsy_teacher_level;
    }

    public int getGsy_signin_status() {
        return gsy_signin_status;
    }

    public void setGsy_signin_status(int gsy_signin_status) {
        this.gsy_signin_status = gsy_signin_status;
    }

    public String getGsy_course_name() {
        return gsy_course_name;
    }

    public void setGsy_course_name(String gsy_course_name) {
        this.gsy_course_name = gsy_course_name;
    }

    public String getGsy_train_id() {
        return gsy_train_id;
    }

    public void setGsy_train_id(String gsy_train_id) {
        this.gsy_train_id = gsy_train_id;
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

    public String getGsy_train_level_name() {
        return gsy_train_level_name;
    }

    public void setGsy_train_level_name(String gsy_train_level_name) {
        this.gsy_train_level_name = gsy_train_level_name;
    }

    public String getGsy_train_time() {
        return gsy_train_time;
    }

    public void setGsy_train_time(String gsy_train_time) {
        this.gsy_train_time = gsy_train_time;
    }

    public String getGsy_train_address() {
        return gsy_train_address;
    }

    public void setGsy_train_address(String gsy_train_address) {
        this.gsy_train_address = gsy_train_address;
    }

    public String getGsy_train_status() {
        return gsy_train_status;
    }

    public void setGsy_train_status(String gsy_train_status) {
        this.gsy_train_status = gsy_train_status;
    }

    public String getGsy_train_count() {
        return gsy_train_count;
    }

    public void setGsy_train_count(String gsy_train_count) {
        this.gsy_train_count = gsy_train_count;
    }

    public String getGsy_signin_count() {
        return gsy_signin_count;
    }

    public void setGsy_signin_count(String gsy_signin_count) {
        this.gsy_signin_count = gsy_signin_count;
    }
}
