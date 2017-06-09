package com.guanshaoye.glglteacher.bean;

import java.util.List;

/**
 * Created by karl on 2017/5/26.
 */

public class ShowAttendClassBean {


    private List<PicBean> pic_list;
    private List<ReserveListBean> reserve_list;

    public List<PicBean> getPic_list() {
        return pic_list;
    }

    public void setPic_list(List<PicBean> pic_list) {
        this.pic_list = pic_list;
    }

    public List<ReserveListBean> getReserve_list() {
        return reserve_list;
    }

    public void setReserve_list(List<ReserveListBean> reserve_list) {
        this.reserve_list = reserve_list;
    }

}
