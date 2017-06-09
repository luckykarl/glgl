package com.guanshaoye.glglteacher.bean;

import android.text.TextUtils;

import com.guanshaoye.mylibrary.http.BaseUrl;

/**
 * Created by karl on 2017/5/24.
 * 教师评价
 */

public class TeacherReviewBean extends QXModel{

    /**
     * gsy_course_schedule_detail_id : 6
     * gsy_course_grap_id : 5
     * gsy_course_time : 2017-05-24 15:00-16:00
     * gsy_teacher_name : 111
     * gsy_teacher_portrait : /Uploads/Public/uploads/teacher/portrait/20170529/592b7ebc5cf2f.png
     * gsy_teacher_level : 1
     * gsy_course_class_name : 轮滑
     * gsy_apply_count : 0
     * gsy_max_apply_count : 20
     * gsy_comment_status : 2
     */

    private String gsy_course_schedule_detail_id;
    private String gsy_course_grap_id;
    private String gsy_course_time;
    private String gsy_teacher_name;
    private String gsy_teacher_portrait;
    private String gsy_teacher_level;
    private String gsy_course_class_name;
    private int gsy_apply_count;
    private String gsy_max_apply_count;
    private int gsy_comment_status;
    private String gsy_store_name;

    public String getGsy_store_name() {
        return gsy_store_name;
    }

    public void setGsy_store_name(String gsy_store_name) {
        this.gsy_store_name = gsy_store_name;
    }

    public String getGsy_course_schedule_detail_id() {
        return gsy_course_schedule_detail_id;
    }

    public void setGsy_course_schedule_detail_id(String gsy_course_schedule_detail_id) {
        this.gsy_course_schedule_detail_id = gsy_course_schedule_detail_id;
    }

    public String getGsy_course_grap_id() {
        return gsy_course_grap_id;
    }

    public void setGsy_course_grap_id(String gsy_course_grap_id) {
        this.gsy_course_grap_id = gsy_course_grap_id;
    }

    public String getGsy_course_time() {
        return gsy_course_time;
    }

    public void setGsy_course_time(String gsy_course_time) {
        this.gsy_course_time = gsy_course_time;
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
        return BaseUrl.HEAD_PHOTO_OSS+gsy_teacher_portrait;
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

    public String getGsy_course_class_name() {
        return gsy_course_class_name;
    }

    public void setGsy_course_class_name(String gsy_course_class_name) {
        this.gsy_course_class_name = gsy_course_class_name;
    }

    public int getGsy_apply_count() {
        return gsy_apply_count;
    }

    public void setGsy_apply_count(int gsy_apply_count) {
        this.gsy_apply_count = gsy_apply_count;
    }

    public String getGsy_max_apply_count() {
        return gsy_max_apply_count;
    }

    public void setGsy_max_apply_count(String gsy_max_apply_count) {
        this.gsy_max_apply_count = gsy_max_apply_count;
    }

    public int getGsy_comment_status() {
        return gsy_comment_status;
    }

    public void setGsy_comment_status(int gsy_comment_status) {
        this.gsy_comment_status = gsy_comment_status;
    }
}
