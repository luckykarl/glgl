package com.guanshaoye.glglteacher.bean;

import android.text.TextUtils;

import com.guanshaoye.mylibrary.http.BaseUrl;

/**
 * Created by ${张梦博} on 2017/5/17.
 * Whenever,Wherever,Whatever,I believe I can handle everything
 */

public class TakeClassLog extends QXModel{

    /**
     * gsy_store_name : 松江万达店
     * gsy_course_class_name : 轮滑
     * gsy_end_time : 1495771200
     * gsy_status : 5
     * gsy_portrait : http://spg.oss-cn-shanghai.aliyuncs.com/drlxl1495942300.jpeg
     * gsy_course_time : 1970-01-01 08:00-12:00
     */

    private String gsy_store_name;
    private String gsy_course_class_name;
    private String gsy_end_time;
    private int gsy_status;
    private String gsy_portrait;
    private String gsy_course_time;

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

    public String getGsy_end_time() {
        return gsy_end_time;
    }

    public void setGsy_end_time(String gsy_end_time) {
        this.gsy_end_time = gsy_end_time;
    }

    public int getGsy_status() {
        return gsy_status;
    }

    public void setGsy_status(int gsy_status) {
        this.gsy_status = gsy_status;
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

    public String getGsy_course_time() {
        return gsy_course_time;
    }

    public void setGsy_course_time(String gsy_course_time) {
        this.gsy_course_time = gsy_course_time;
    }
}
