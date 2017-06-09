package com.guanshaoye.glglteacher.bean;

import com.guanshaoye.mylibrary.http.BaseUrl;

/**
 * Created by karl on 2017/6/3.
 */

public class TeacherCommissionListBean {
  /**
 * id : 1
 * gsy_store_id : 1
 * gsy_course_class_id : 7
 * gsy_course_start_time : 1495677600
 * gsy_course_end_time : 1495681200
 * gsy_money : 122.00
 * gsy_store_name : 松江万达店
 * gsy_course_class_name : 轮滑
 * gsy_portrait : ddsdfdffddf
 * gsy_course_time : 2017-05-25 10:00-11:00
 */

    private String id;
    private String gsy_store_id;
    private String gsy_course_class_id;
    private String gsy_course_start_time;
    private String gsy_course_end_time;
    private String gsy_money;
    private String gsy_store_name;
    private String gsy_course_class_name;
    private String gsy_portrait;
    private String gsy_course_time;
    private String gsy_level;

    public String getGsy_level() {
        return gsy_level;
    }

    public void setGsy_level(String gsy_level) {
        this.gsy_level = gsy_level;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGsy_store_id() {
        return gsy_store_id;
    }

    public void setGsy_store_id(String gsy_store_id) {
        this.gsy_store_id = gsy_store_id;
    }

    public String getGsy_course_class_id() {
        return gsy_course_class_id;
    }

    public void setGsy_course_class_id(String gsy_course_class_id) {
        this.gsy_course_class_id = gsy_course_class_id;
    }

    public String getGsy_course_start_time() {
        return gsy_course_start_time;
    }

    public void setGsy_course_start_time(String gsy_course_start_time) {
        this.gsy_course_start_time = gsy_course_start_time;
    }

    public String getGsy_course_end_time() {
        return gsy_course_end_time;
    }

    public void setGsy_course_end_time(String gsy_course_end_time) {
        this.gsy_course_end_time = gsy_course_end_time;
    }

    public String getGsy_money() {
        return gsy_money;
    }

    public void setGsy_money(String gsy_money) {
        this.gsy_money = gsy_money;
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

    public String getGsy_portrait() {
        return BaseUrl.HEAD_PHOTO_OSS+gsy_portrait;
    }

    public void setGsy_portrait(String gsy_portrait) {
        this.gsy_portrait = gsy_portrait;
    }

    public String getGsy_course_time() {
        return gsy_course_time;
    }

    public void setGsy_course_time(String gsy_course_time) {
        this.gsy_course_time = gsy_course_time;
    }
}
