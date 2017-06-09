package com.guanshaoye.glglteacher.bean;

/**
 * Created by karl on 2017/6/3.
 */

public class MineBean {
    /**
     * name : 我的
     * ename : Mine
     * botton_auth_status : 1
     */

    private String name;
    private String ename;
    private int botton_auth_status;
    private int gsy_status;

    public int getGsy_status() {
        return gsy_status;
    }

    public void setGsy_status(int gsy_status) {
        this.gsy_status = gsy_status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public int getBotton_auth_status() {
        return botton_auth_status;
    }

    public void setBotton_auth_status(int botton_auth_status) {
        this.botton_auth_status = botton_auth_status;
    }
}
