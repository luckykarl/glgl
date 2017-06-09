package com.guanshaoye.glglteacher.bean;

import java.util.List;

/**
 * Created by karl on 2017/5/22.
 * 佣金
 */

public class SalaryLogBean extends QXModel{

    /**
     * commission_total_count : 244.00
     * teacher_commission_list : [{"id":"1","gsy_store_id":"1","gsy_course_class_id":"7","gsy_course_start_time":"1495677600","gsy_course_end_time":"1495681200","gsy_money":"122.00","gsy_store_name":"松江万达店","gsy_course_class_name":"轮滑","gsy_portrait":"ddsdfdffddf","gsy_course_time":"2017-05-25 10:00-11:00"},{"id":"2","gsy_store_id":"1","gsy_course_class_id":"7","gsy_course_start_time":"1495695600","gsy_course_end_time":"1495699200","gsy_money":"122.00","gsy_store_name":"松江万达店","gsy_course_class_name":"轮滑","gsy_portrait":"ddsdfdffddf","gsy_course_time":"2017-05-25 15:00-16:00"}]
     */
    private String commission_date;
    private String commission_total_count;
    private List<TeacherCommissionListBean> teacher_commission_list;

    public String getCommission_date() {
        return commission_date;
    }

    public void setCommission_date(String commission_date) {
        this.commission_date = commission_date;
    }

    public String getCommission_total_count() {
        return commission_total_count;
    }

    public void setCommission_total_count(String commission_total_count) {
        this.commission_total_count = commission_total_count;
    }

    public List<TeacherCommissionListBean> getTeacher_commission_list() {
        return teacher_commission_list;
    }

    public void setTeacher_commission_list(List<TeacherCommissionListBean> teacher_commission_list) {
        this.teacher_commission_list = teacher_commission_list;
    }


}
