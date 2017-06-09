package com.guanshaoye.glglteacher.bean;

/**
 * Created by karl on 2017/6/3.
 */

public class ManagementBean {
    /**
     * name : 管理
     * ename : Management
     * botton_auth_status : 1
     */

    private String name;
    private String ename;
    private int botton_auth_status;

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
