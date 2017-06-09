package com.guanshaoye.glglteacher.bean;

import java.util.List;

/**
 * Created by karl on 2017/5/24.
 */

public class RobClassBean {

    private int gsy_teacher_level;
    private List<StoreListBean> store_list;
    private List<GsyLevelListBean> gsy_level_list;
    private List<ScheduleListBean> schedule_list;

    public int getGsy_teacher_level() {
        return gsy_teacher_level;
    }

    public void setGsy_teacher_level(int gsy_teacher_level) {
        this.gsy_teacher_level = gsy_teacher_level;
    }

    public List<StoreListBean> getStore_list() {
        return store_list;
    }

    public void setStore_list(List<StoreListBean> store_list) {
        this.store_list = store_list;
    }

    public List<GsyLevelListBean> getGsy_level_list() {
        return gsy_level_list;
    }

    public void setGsy_level_list(List<GsyLevelListBean> gsy_level_list) {
        this.gsy_level_list = gsy_level_list;
    }

    public List<ScheduleListBean> getSchedule_list() {
        return schedule_list;
    }

    public void setSchedule_list(List<ScheduleListBean> schedule_list) {
        this.schedule_list = schedule_list;
    }
}
