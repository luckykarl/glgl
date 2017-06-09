package com.guanshaoye.glglteacher.bean;

import android.text.TextUtils;

import com.guanshaoye.mylibrary.http.BaseUrl;

/**
 * Created by karl on 2017/5/27.
 */

public class TeacherInfoBean {
    /**
     * id : 3
     * gsy_portrait : /Uploads/Public/uploads/teacher/portrait/20170525/59265b5f93dff.png
     * gsy_realname : 111
     * gsy_status : 1
     * gsy_role : 1
     * gsy_course_class_id : 7
     * gsy_status_name : 未认证
     * gsy_complete_scale : 70
     * gsy_role_name : 外部跑课老师
     * gsy_course_class_name : 轮滑
     */

    private String id;
    private String gsy_portrait;
    private String gsy_realname;
    private int gsy_status;
    private String gsy_role;
    private String gsy_course_class_id;
    private String gsy_status_name;
    private int gsy_complete_scale;
    private String gsy_role_name;
    private String gsy_course_class_name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGsy_portrait() {
        if(TextUtils.isEmpty(gsy_portrait)){
            gsy_portrait =  "";
        }
        return BaseUrl.HEAD_PHOTO_OSS + gsy_portrait;
    }

    public void setGsy_portrait(String gsy_portrait) {
        this.gsy_portrait = gsy_portrait;
    }

    public String getGsy_realname() {
        return gsy_realname;
    }

    public void setGsy_realname(String gsy_realname) {
        this.gsy_realname = gsy_realname;
    }

    public int getGsy_status() {
        return gsy_status;
    }

    public void setGsy_status(int gsy_status) {
        this.gsy_status = gsy_status;
    }

    public String getGsy_role() {
        return gsy_role;
    }

    public void setGsy_role(String gsy_role) {
        this.gsy_role = gsy_role;
    }

    public String getGsy_course_class_id() {
        return gsy_course_class_id;
    }

    public void setGsy_course_class_id(String gsy_course_class_id) {
        this.gsy_course_class_id = gsy_course_class_id;
    }

    public String getGsy_status_name() {
        return gsy_status_name;
    }

    public void setGsy_status_name(String gsy_status_name) {
        this.gsy_status_name = gsy_status_name;
    }

    public int getGsy_complete_scale() {
        return gsy_complete_scale;
    }

    public void setGsy_complete_scale(int gsy_complete_scale) {
        this.gsy_complete_scale = gsy_complete_scale;
    }

    public String getGsy_role_name() {
        return gsy_role_name;
    }

    public void setGsy_role_name(String gsy_role_name) {
        this.gsy_role_name = gsy_role_name;
    }

    public String getGsy_course_class_name() {
        return gsy_course_class_name;
    }

    public void setGsy_course_class_name(String gsy_course_class_name) {
        this.gsy_course_class_name = gsy_course_class_name;
    }
}
