package com.guanshaoye.glglteacher.bean;

import java.util.List;

/**
 * Created by karl on 2017/5/23.
 */

public class ItemListBean extends QXModel{

    /**
     * x : 1
     * y : 1
     * grap_course_list : [{"id":"3","gsy_course_class_id":"7","gsy_course_class_name":"轮滑","gsy_store_id":"1","gsy_store_name":"松江万达店","gsy_status":"1","gsy_start_time":"09:00","gsy_end_time":"10:00","gsy_status_name":"未上","gsy_botton_color":3,"gsy_day_y":1,"gsy_hour_x":1,"gsy_day_num":"2017-05-23"}]
     * gsy_botton_color : 3
     * gsy_status : 1
     */

    private int x;
    private int y;
    private int gsy_botton_color;
    private int gsy_status = -1;
    private List<GrapCourseListBean> grap_course_list;
    private String dialog_id ;
    private String dialog_content;

    public String getDialog_id() {
        return dialog_id;
    }

    public void setDialog_id(String dialog_id) {
        this.dialog_id = dialog_id;
    }

    public String getDialog_content() {
        return dialog_content;
    }

    public void setDialog_content(String dialog_content) {
        this.dialog_content = dialog_content;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getGsy_botton_color() {
        return gsy_botton_color;
    }

    public void setGsy_botton_color(int gsy_botton_color) {
        this.gsy_botton_color = gsy_botton_color;
    }


    public List<GrapCourseListBean> getGrap_course_list() {
        return grap_course_list;
    }

    public void setGrap_course_list(List<GrapCourseListBean> grap_course_list) {
        this.grap_course_list = grap_course_list;
    }

    public int getGsy_status() {
        return gsy_status;
    }

    public void setGsy_status(int gsy_status) {
        this.gsy_status = gsy_status;
    }
}
