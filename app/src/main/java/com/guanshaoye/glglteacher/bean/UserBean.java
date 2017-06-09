package com.guanshaoye.glglteacher.bean;

import android.text.TextUtils;

import com.guanshaoye.mylibrary.http.BaseUrl;

/**
 * Created by karl on 2017/5/25.
 */

public class UserBean {

    /**
     * mobile : 17625899806
     * is_reg : 1
     * tid : 3
     * store_id : 1
     * store_name : 松江万达店
     * status : 1
     * gender : 1
     * memberportrait : /Uploads/Public/uploads/teacher/portrait/20170525/59265b5f93dff.png
     * realname : 111
     * token : ea14650e3779d30e9786617d80c4e5d6
     */

    private String mobile;
    private int is_reg;
    private String tid;
    private String store_id;
    private String store_name;
    private String status;
    private String gender;
    private String memberportrait;
    private String realname;
    private String token;

    private UserInfoBean userInfo;
    /**
     * teacher_info : {"id":"3","gsy_portrait":"/Uploads/Public/uploads/teacher/portrait/20170525/59265b5f93dff.png","gsy_realname":"111","gsy_status":"1","gsy_role":"1","gsy_course_class_id":"7","gsy_status_name":"未认证","gsy_complete_scale":70,"gsy_role_name":"外部跑课老师","gsy_course_class_name":"轮滑"}
     * teacher_level : {"max_level":12,"cur_level":0}
     * teach_course : {"max_count":10,"cur_count":0}
     * good_comment_scale : 80
     * train_course : {"max_count":10,"cur_count":0}
     * upgrade_botton_status : 1
     * new_comment_count : 5
     * total_commission : 1000
     * total_bonus : 1000
     */

    private TeacherInfoBean teacher_info;
    private TeacherLevelBean teacher_level;
    private TeachCourseBean teach_course;
    private int good_comment_scale;
    private TrainCourseBean train_course;
    private int upgrade_botton_status;//晋级按钮状态 1可点击 2不可点击 注：根据状态判断显示颜色不一样
    private int new_comment_count;
    private String total_commission;
    private String total_bonus;



    public UserInfoBean getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfoBean userInfo) {
        this.userInfo = userInfo;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public int getIs_reg() {
        return is_reg;
    }

    public void setIs_reg(int is_reg) {
        this.is_reg = is_reg;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getStore_id() {
        return store_id;
    }

    public void setStore_id(String store_id) {
        this.store_id = store_id;
    }

    public String getStore_name() {
        return store_name;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMemberportrait() {
        if(TextUtils.isEmpty(memberportrait)){
            memberportrait = "";
        }
        return BaseUrl.HEAD_PHOTO+memberportrait;
    }

    public void setMemberportrait(String memberportrait) {
        this.memberportrait = memberportrait;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public TeacherInfoBean getTeacher_info() {
        return teacher_info;
    }

    public void setTeacher_info(TeacherInfoBean teacher_info) {
        this.teacher_info = teacher_info;
    }

    public TeacherLevelBean getTeacher_level() {
        return teacher_level;
    }

    public void setTeacher_level(TeacherLevelBean teacher_level) {
        this.teacher_level = teacher_level;
    }

    public TeachCourseBean getTeach_course() {
        return teach_course;
    }

    public void setTeach_course(TeachCourseBean teach_course) {
        this.teach_course = teach_course;
    }

    public int getGood_comment_scale() {
        return good_comment_scale;
    }

    public void setGood_comment_scale(int good_comment_scale) {
        this.good_comment_scale = good_comment_scale;
    }

    public TrainCourseBean getTrain_course() {
        return train_course;
    }

    public void setTrain_course(TrainCourseBean train_course) {
        this.train_course = train_course;
    }

    public int getUpgrade_botton_status() {
        return upgrade_botton_status;
    }

    public void setUpgrade_botton_status(int upgrade_botton_status) {
        this.upgrade_botton_status = upgrade_botton_status;
    }

    public int getNew_comment_count() {
        return new_comment_count;
    }

    public void setNew_comment_count(int new_comment_count) {
        this.new_comment_count = new_comment_count;
    }

    public String getTotal_commission() {
        return total_commission;
    }

    public void setTotal_commission(String total_commission) {
        this.total_commission = total_commission;
    }

    public String getTotal_bonus() {
        return total_bonus;
    }

    public void setTotal_bonus(String total_bonus) {
        this.total_bonus = total_bonus;
    }
}
