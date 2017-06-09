package com.guanshaoye.glglteacher.bean;

/**
 * Created by karl on 2017/5/24.
 */

public class ScheduleListBean {
    /**
     * id : 12
     * gsy_start_time : 21:00
     * gsy_end_time : 22:00
     * gsy_month_time : 05
     * gsy_day_time : 24
     */
    private int is_grap;  //是否已抢字端 0 1
    private String id;
    private String gsy_start_time;
    private String gsy_end_time;
    private String gsy_month_time;
    private String gsy_day_time;

    public int getIs_grap() {
        return is_grap;
    }

    public void setIs_grap(int is_grap) {
        this.is_grap = is_grap;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGsy_start_time() {
        return gsy_start_time;
    }

    public void setGsy_start_time(String gsy_start_time) {
        this.gsy_start_time = gsy_start_time;
    }

    public String getGsy_end_time() {
        return gsy_end_time;
    }

    public void setGsy_end_time(String gsy_end_time) {
        this.gsy_end_time = gsy_end_time;
    }

    public String getGsy_month_time() {
        return gsy_month_time;
    }

    public void setGsy_month_time(String gsy_month_time) {
        this.gsy_month_time = gsy_month_time;
    }

    public String getGsy_day_time() {
        return gsy_day_time;
    }

    public void setGsy_day_time(String gsy_day_time) {
        this.gsy_day_time = gsy_day_time;
    }
}
