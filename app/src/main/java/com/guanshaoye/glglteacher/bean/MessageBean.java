package com.guanshaoye.glglteacher.bean;

import android.text.TextUtils;

import com.guanshaoye.mylibrary.http.BaseUrl;

/**
 * Created by karl on 2017/6/3.
 */

public class MessageBean {

    /**
     * message_info : null
     * message_class_name : 课程预约
     * message_class_pic_url : /Uploads/Public/uploads/teacher/message/20170524/m1.png
     * no_scan_num : 0
     * gsy_class_id : 1
     */

    private MessageInfoBean message_info;
    private String message_class_name;
    private String message_class_pic_url;
    private int no_scan_num;
    private int gsy_class_id;
    private String gsy_add_time;

    public String getGsy_add_time() {
        return gsy_add_time;
    }

    public void setGsy_add_time(String gsy_add_time) {
        this.gsy_add_time = gsy_add_time;
    }

    public MessageInfoBean getMessage_info() {
        return message_info;
    }

    public void setMessage_info(MessageInfoBean message_info) {
        this.message_info = message_info;
    }

    public String getMessage_class_name() {
        return message_class_name;
    }

    public void setMessage_class_name(String message_class_name) {
        this.message_class_name = message_class_name;
    }

    public String getMessage_class_pic_url() {
        return BaseUrl.HEAD_PHOTO+message_class_pic_url;
    }

    public void setMessage_class_pic_url(String message_class_pic_url) {
        this.message_class_pic_url = message_class_pic_url;
    }

    public int getNo_scan_num() {
        return no_scan_num;
    }

    public void setNo_scan_num(int no_scan_num) {
        this.no_scan_num = no_scan_num;
    }

    public int getGsy_class_id() {
        return gsy_class_id;
    }

    public void setGsy_class_id(int gsy_class_id) {
        this.gsy_class_id = gsy_class_id;
    }
}
