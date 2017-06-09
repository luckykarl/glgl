package com.guanshaoye.glglteacher.bean;

import android.text.TextUtils;

import com.guanshaoye.mylibrary.http.BaseUrl;

/**
 * Created by karl on 2017/5/23.
 */

public class UserInfoBean extends QXModel{

    /**
     * id : 3
     * gsy_course_class_id : 7
     * gsy_mobile : 17625899806
     * gsy_realname : 111
     * gsy_email :
     * gsy_address :
     * gsy_home_address :
     * gsy_emergency_contact_name :
     * gsy_emergency_contact_mobi :
     * gsy_gender : 0
     * gsy_birthdate :
     * gsy_native_place :
     * gsy_nation :
     * gsy_idcard :
     * gsy_is_marry : 0
     * gsy_have_children : 0
     * gsy_education_level : 0
     * gsy_graduate_school :
     * gsy_graduate_date :
     * gsy_hiredate :
     * gsy_portrait :
     * gsy_status : 1
     * gsy_note :
     * gsy_current_store_id : 1
     * gsy_current_store_name : 松江万达店
     * gsy_longitude : 0.00000000
     * gsy_latitude : 0.00000000
     * gsy_tid : 3
     * gsy_tcourse_item_class_id : 0
     * gsy_tcourse_class_id : 0
     * gsy_role_name : 暂无角色
     */

    private String id;
    private String gsy_course_class_id;
    private String gsy_mobile;
    private String gsy_realname;
    private String gsy_email;
    private String gsy_address;
    private String gsy_home_address;
    private String gsy_emergency_contact_name;
    private String gsy_emergency_contact_mobi;
    private int gsy_gender;
    private String gsy_birthdate;
    private String gsy_native_place;
    private String gsy_nation;
    private String gsy_idcard;
    private int gsy_is_marry;
    private int gsy_have_children;
    private int gsy_education_level;
    private String gsy_graduate_school;
    private String gsy_graduate_date;
    private String gsy_hiredate;
    private String gsy_portrait;
    private String gsy_status;
    private String gsy_note;
    private String gsy_current_store_id;
    private String gsy_current_store_name;
    private String gsy_longitude;
    private String gsy_latitude;
    private String gsy_tid;
    private String gsy_tcourse_item_class_id;
    private String gsy_tcourse_class_id;
    private String gsy_role_name;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGsy_course_class_id() {
        return gsy_course_class_id;
    }

    public void setGsy_course_class_id(String gsy_course_class_id) {
        this.gsy_course_class_id = gsy_course_class_id;
    }

    public String getGsy_mobile() {
        return gsy_mobile;
    }

    public void setGsy_mobile(String gsy_mobile) {
        this.gsy_mobile = gsy_mobile;
    }

    public String getGsy_realname() {
        return gsy_realname;
    }

    public void setGsy_realname(String gsy_realname) {
        this.gsy_realname = gsy_realname;
    }

    public String getGsy_email() {
        return gsy_email;
    }

    public void setGsy_email(String gsy_email) {
        this.gsy_email = gsy_email;
    }

    public String getGsy_address() {
        return gsy_address;
    }

    public void setGsy_address(String gsy_address) {
        this.gsy_address = gsy_address;
    }

    public String getGsy_home_address() {
        return gsy_home_address;
    }

    public void setGsy_home_address(String gsy_home_address) {
        this.gsy_home_address = gsy_home_address;
    }

    public String getGsy_emergency_contact_name() {
        return gsy_emergency_contact_name;
    }

    public void setGsy_emergency_contact_name(String gsy_emergency_contact_name) {
        this.gsy_emergency_contact_name = gsy_emergency_contact_name;
    }

    public String getGsy_emergency_contact_mobi() {
        return gsy_emergency_contact_mobi;
    }

    public void setGsy_emergency_contact_mobi(String gsy_emergency_contact_mobi) {
        this.gsy_emergency_contact_mobi = gsy_emergency_contact_mobi;
    }

    public String getGsy_birthdate() {
        return gsy_birthdate;
    }

    public void setGsy_birthdate(String gsy_birthdate) {
        this.gsy_birthdate = gsy_birthdate;
    }

    public String getGsy_native_place() {
        return gsy_native_place;
    }

    public void setGsy_native_place(String gsy_native_place) {
        this.gsy_native_place = gsy_native_place;
    }

    public String getGsy_nation() {
        return gsy_nation;
    }

    public void setGsy_nation(String gsy_nation) {
        this.gsy_nation = gsy_nation;
    }

    public String getGsy_idcard() {
        return gsy_idcard;
    }

    public void setGsy_idcard(String gsy_idcard) {
        this.gsy_idcard = gsy_idcard;
    }


    public String getGsy_graduate_school() {
        return gsy_graduate_school;
    }

    public void setGsy_graduate_school(String gsy_graduate_school) {
        this.gsy_graduate_school = gsy_graduate_school;
    }

    public String getGsy_graduate_date() {
        return gsy_graduate_date;
    }

    public void setGsy_graduate_date(String gsy_graduate_date) {
        this.gsy_graduate_date = gsy_graduate_date;
    }

    public String getGsy_hiredate() {
        return gsy_hiredate;
    }

    public void setGsy_hiredate(String gsy_hiredate) {
        this.gsy_hiredate = gsy_hiredate;
    }

    public String getGsy_portrait() {
        if(TextUtils.isEmpty(gsy_portrait)){
            gsy_portrait = "";
        }
        return BaseUrl.HEAD_PHOTO_OSS+gsy_portrait;
    }

    public void setGsy_portrait(String gsy_portrait) {
        this.gsy_portrait = gsy_portrait;
    }

    public String getGsy_status() {
        return gsy_status;
    }

    public void setGsy_status(String gsy_status) {
        this.gsy_status = gsy_status;
    }

    public String getGsy_note() {
        return gsy_note;
    }

    public void setGsy_note(String gsy_note) {
        this.gsy_note = gsy_note;
    }

    public String getGsy_current_store_id() {
        return gsy_current_store_id;
    }

    public void setGsy_current_store_id(String gsy_current_store_id) {
        this.gsy_current_store_id = gsy_current_store_id;
    }

    public String getGsy_current_store_name() {
        return gsy_current_store_name;
    }

    public void setGsy_current_store_name(String gsy_current_store_name) {
        this.gsy_current_store_name = gsy_current_store_name;
    }

    public String getGsy_longitude() {
        return gsy_longitude;
    }

    public void setGsy_longitude(String gsy_longitude) {
        this.gsy_longitude = gsy_longitude;
    }

    public String getGsy_latitude() {
        return gsy_latitude;
    }

    public void setGsy_latitude(String gsy_latitude) {
        this.gsy_latitude = gsy_latitude;
    }

    public String getGsy_tid() {
        return gsy_tid;
    }

    public void setGsy_tid(String gsy_tid) {
        this.gsy_tid = gsy_tid;
    }

    public String getGsy_tcourse_item_class_id() {
        return gsy_tcourse_item_class_id;
    }

    public void setGsy_tcourse_item_class_id(String gsy_tcourse_item_class_id) {
        this.gsy_tcourse_item_class_id = gsy_tcourse_item_class_id;
    }

    public String getGsy_tcourse_class_id() {
        return gsy_tcourse_class_id;
    }

    public void setGsy_tcourse_class_id(String gsy_tcourse_class_id) {
        this.gsy_tcourse_class_id = gsy_tcourse_class_id;
    }

    public String getGsy_role_name() {
        return gsy_role_name;
    }

    public void setGsy_role_name(String gsy_role_name) {
        this.gsy_role_name = gsy_role_name;
    }

    public int getGsy_gender() {
        return gsy_gender;
    }

    public void setGsy_gender(int gsy_gender) {
        this.gsy_gender = gsy_gender;
    }

    public int getGsy_is_marry() {
        return gsy_is_marry;
    }

    public void setGsy_is_marry(int gsy_is_marry) {
        this.gsy_is_marry = gsy_is_marry;
    }

    public int getGsy_have_children() {
        return gsy_have_children;
    }

    public void setGsy_have_children(int gsy_have_children) {
        this.gsy_have_children = gsy_have_children;
    }

    public int getGsy_education_level() {
        return gsy_education_level;
    }

    public void setGsy_education_level(int gsy_education_level) {
        this.gsy_education_level = gsy_education_level;
    }
}
