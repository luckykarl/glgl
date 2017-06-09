package com.guanshaoye.glglteacher.bean;

import java.util.List;

/**
 * Created by karl on 2017/5/22.
 */

public class TimeTableBean extends QXModel{

    private List<String> date_arr;
    private List<HourListBean> hour_list;
    private List<CurWeekListBean> cur_week_list;
    private List<ItemListBean> item_list;

    public List<String> getDate_arr() {
        return date_arr;
    }

    public void setDate_arr(List<String> date_arr) {
        this.date_arr = date_arr;
    }

    public List<HourListBean> getHour_list() {
        return hour_list;
    }

    public void setHour_list(List<HourListBean> hour_list) {
        this.hour_list = hour_list;
    }

    public List<CurWeekListBean> getCur_week_list() {
        return cur_week_list;
    }

    public void setCur_week_list(List<CurWeekListBean> cur_week_list) {
        this.cur_week_list = cur_week_list;
    }

    public List<ItemListBean> getItem_list() {
        return item_list;
    }

    public void setItem_list(List<ItemListBean> item_list) {
        this.item_list = item_list;
    }
}
