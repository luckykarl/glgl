package com.guanshaoye.glglteacher.bean;

/**
 * Created by karl on 2017/5/23.
 */

public class HourListBean extends QXModel{
    /**
     * start_time : 08:00
     * end_time : 09:00
     */

    private String start_time;
    private String end_time;

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }
}
