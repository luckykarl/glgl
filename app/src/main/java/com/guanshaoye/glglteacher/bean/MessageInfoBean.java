package com.guanshaoye.glglteacher.bean;

/**
 * Created by karl on 2017/6/3.
 */

public class MessageInfoBean {


    /**
     * gsy_class_id : 1
     * gsy_title : 公司决定不放假了，大家一直加班吧，好好干！
     * gsy_admin_id : 10000
     * gsy_add_time : 2016-09-19 12:00
     * gsy_class_name : 课程预约
     * gsy_admin_name : 管理员
     */

    private String gsy_class_id;
    private String gsy_title;
    private String gsy_admin_id;
    private String gsy_add_time;
    private String gsy_class_name;
    private String gsy_admin_name;
    private int position;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getGsy_class_id() {
        return gsy_class_id;
    }

    public void setGsy_class_id(String gsy_class_id) {
        this.gsy_class_id = gsy_class_id;
    }

    public String getGsy_title() {
        return gsy_title;
    }

    public void setGsy_title(String gsy_title) {
        this.gsy_title = gsy_title;
    }

    public String getGsy_admin_id() {
        return gsy_admin_id;
    }

    public void setGsy_admin_id(String gsy_admin_id) {
        this.gsy_admin_id = gsy_admin_id;
    }

    public String getGsy_add_time() {
        return gsy_add_time;
    }

    public void setGsy_add_time(String gsy_add_time) {
        this.gsy_add_time = gsy_add_time;
    }

    public String getGsy_class_name() {
        return gsy_class_name;
    }

    public void setGsy_class_name(String gsy_class_name) {
        this.gsy_class_name = gsy_class_name;
    }

    public String getGsy_admin_name() {
        return gsy_admin_name;
    }

    public void setGsy_admin_name(String gsy_admin_name) {
        this.gsy_admin_name = gsy_admin_name;
    }
}
