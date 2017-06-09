package com.guanshaoye.glglteacher.bean;

import android.text.TextUtils;

import com.guanshaoye.mylibrary.http.BaseUrl;

/**
 * Created by karl on 2017/5/30.
 */

public class CommentPicBean {

    /**
     * id : 21
     * gsy_course_schedule_detail_id : 10
     * gsy_comment_type : 2
     * gsy_pid : 5
     * gsy_pic_url : drlxl1496136188.jpeg
     * gsy_status : 1
     * gsy_add_time : 1496136209
     */

    private String id;
    private String gsy_course_schedule_detail_id;
    private String gsy_comment_type;
    private String gsy_pid;
    private String gsy_pic_url;
    private String gsy_status;
    private String gsy_add_time;

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

    public String getGsy_comment_type() {
        return gsy_comment_type;
    }

    public void setGsy_comment_type(String gsy_comment_type) {
        this.gsy_comment_type = gsy_comment_type;
    }

    public String getGsy_pid() {
        return gsy_pid;
    }

    public void setGsy_pid(String gsy_pid) {
        this.gsy_pid = gsy_pid;
    }

    public String getGsy_pic_url() {
        if(TextUtils.isEmpty(gsy_pic_url)){
            gsy_pic_url = "";
        }
        return BaseUrl.HEAD_PHOTO_OSS+gsy_pic_url;
    }

    public void setGsy_pic_url(String gsy_pic_url) {
        this.gsy_pic_url = gsy_pic_url;
    }

    public String getGsy_status() {
        return gsy_status;
    }

    public void setGsy_status(String gsy_status) {
        this.gsy_status = gsy_status;
    }

    public String getGsy_add_time() {
        return gsy_add_time;
    }

    public void setGsy_add_time(String gsy_add_time) {
        this.gsy_add_time = gsy_add_time;
    }
}
