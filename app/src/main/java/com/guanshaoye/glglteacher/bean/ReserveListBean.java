package com.guanshaoye.glglteacher.bean;

import android.text.TextUtils;

import com.guanshaoye.mylibrary.http.BaseUrl;

/**
 * Created by karl on 2017/5/26.
 */

public class ReserveListBean {
    /**
     * gsy_course_reserve_id : 15
     * gsy_realname :
     * gsy_memberportrait :
     * gsy_level : null
     * gsy_signin_status : 2
     * gsy_insurance_no :
     */

    private String gsy_course_reserve_id;
    private String gsy_realname;
    private String gsy_memberportrait;
    private Object gsy_level;
    private int gsy_signin_status;
    private String gsy_insurance_no;
    private String gsy_course_class_name;

    public String getGsy_course_class_name() {
        return gsy_course_class_name;
    }

    public void setGsy_course_class_name(String gsy_course_class_name) {
        this.gsy_course_class_name = gsy_course_class_name;
    }

    public String getGsy_course_reserve_id() {
        return gsy_course_reserve_id;
    }

    public void setGsy_course_reserve_id(String gsy_course_reserve_id) {
        this.gsy_course_reserve_id = gsy_course_reserve_id;
    }

    public String getGsy_realname() {
        return gsy_realname;
    }

    public void setGsy_realname(String gsy_realname) {
        this.gsy_realname = gsy_realname;
    }

    public String getGsy_memberportrait() {
        if (TextUtils.isEmpty(gsy_memberportrait)){
            gsy_memberportrait = "";
        }
        return BaseUrl.HEAD_PHOTO+gsy_memberportrait;
    }

    public void setGsy_memberportrait(String gsy_memberportrait) {
        this.gsy_memberportrait = gsy_memberportrait;
    }

    public Object getGsy_level() {
        return gsy_level;
    }

    public void setGsy_level(Object gsy_level) {
        this.gsy_level = gsy_level;
    }

    public int getGsy_signin_status() {
        return gsy_signin_status;
    }

    public void setGsy_signin_status(int gsy_signin_status) {
        this.gsy_signin_status = gsy_signin_status;
    }

    public String getGsy_insurance_no() {
        return gsy_insurance_no;
    }

    public void setGsy_insurance_no(String gsy_insurance_no) {
        this.gsy_insurance_no = gsy_insurance_no;
    }
}
