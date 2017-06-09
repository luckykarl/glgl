package com.guanshaoye.glglteacher.bean;

/**
 * Created by karl on 2017/5/21.
 */

public class SmsBean extends QXModel{

    /**
     * mobile : 17625899805
     * is_reg : 1
     * code : 245376
     */

    private String mobile;
    private int is_reg;
    private int code;

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

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
