package com.guanshaoye.glglteacher.bean;

/**
 * Created by karl on 2017/5/26.
 */

public class SettingBean {

    /**
     * id : 22
     * gsy_tid : 3
     * gsy_sound_warn : 1
     * gsy_is_warn : 1
     * gsy_importance_warn : 1
     */

    private String id;
    private String gsy_tid;
    private int gsy_sound_warn;
    private int gsy_is_warn;
    private int gsy_importance_warn;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGsy_tid() {
        return gsy_tid;
    }

    public void setGsy_tid(String gsy_tid) {
        this.gsy_tid = gsy_tid;
    }

    public int getGsy_sound_warn() {
        return gsy_sound_warn;
    }

    public void setGsy_sound_warn(int gsy_sound_warn) {
        this.gsy_sound_warn = gsy_sound_warn;
    }

    public int getGsy_is_warn() {
        return gsy_is_warn;
    }

    public void setGsy_is_warn(int gsy_is_warn) {
        this.gsy_is_warn = gsy_is_warn;
    }

    public int getGsy_importance_warn() {
        return gsy_importance_warn;
    }

    public void setGsy_importance_warn(int gsy_importance_warn) {
        this.gsy_importance_warn = gsy_importance_warn;
    }
}
