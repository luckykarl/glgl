package com.guanshaoye.glglteacher.bean;

import android.text.TextUtils;

import com.guanshaoye.mylibrary.http.BaseUrl;

import java.util.List;

/**
 * Created by karl on 2017/5/30.
 */

public class MemberCommentListBean {
    /**
     * id : 9
     * gsy_pid : 100132
     * gsy_star_num : 5
     * gsy_hudong_item : 1
     * gsy_jishu_item : 1
     * gsy_jiaoxue_item : 1
     * gsy_xunlian_item : 1
     * gsy_neirong_item : 1
     * gsy_realname : 还是
     * gsy_memberportrait : /Uploads/Public/uploads/portrait/20170525/5926338168953.png
     * pic_list : [{"id":"10","gsy_pic_url":"/Uploads/Public/uploads/comment/20170525/5926873c5313d.png"},{"id":"11","gsy_pic_url":"/Uploads/Public/uploads/comment/20170525/59268a53adae3.png"},{"id":"12","gsy_pic_url":"/Uploads/Public/uploads/comment/20170527/592967bcdcf17.png"}]
     */

    private String id;
    private String gsy_pid;
    private int gsy_star_num;
    private int gsy_hudong_item;
    private int gsy_jishu_item;
    private int gsy_jiaoxue_item;
    private int gsy_xunlian_item;
    private int gsy_neirong_item;
    private String gsy_realname;
    private String gsy_memberportrait;
    private List<CommentPicBean> pic_list;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGsy_pid() {
        return gsy_pid;
    }

    public void setGsy_pid(String gsy_pid) {
        this.gsy_pid = gsy_pid;
    }

    public int getGsy_star_num() {
        return gsy_star_num;
    }

    public void setGsy_star_num(int gsy_star_num) {
        this.gsy_star_num = gsy_star_num;
    }

    public int getGsy_hudong_item() {
        return gsy_hudong_item;
    }

    public void setGsy_hudong_item(int gsy_hudong_item) {
        this.gsy_hudong_item = gsy_hudong_item;
    }

    public int getGsy_jishu_item() {
        return gsy_jishu_item;
    }

    public void setGsy_jishu_item(int gsy_jishu_item) {
        this.gsy_jishu_item = gsy_jishu_item;
    }

    public int getGsy_jiaoxue_item() {
        return gsy_jiaoxue_item;
    }

    public void setGsy_jiaoxue_item(int gsy_jiaoxue_item) {
        this.gsy_jiaoxue_item = gsy_jiaoxue_item;
    }

    public int getGsy_xunlian_item() {
        return gsy_xunlian_item;
    }

    public void setGsy_xunlian_item(int gsy_xunlian_item) {
        this.gsy_xunlian_item = gsy_xunlian_item;
    }

    public int getGsy_neirong_item() {
        return gsy_neirong_item;
    }

    public void setGsy_neirong_item(int gsy_neirong_item) {
        this.gsy_neirong_item = gsy_neirong_item;
    }

    public String getGsy_realname() {
        return gsy_realname;
    }

    public void setGsy_realname(String gsy_realname) {
        this.gsy_realname = gsy_realname;
    }

    public String getGsy_memberportrait() {
        if (TextUtils.isEmpty(gsy_memberportrait)){
            gsy_memberportrait = "";
        }
        return BaseUrl.HEAD_PHOTO+gsy_memberportrait;
    }

    public void setGsy_memberportrait(String gsy_memberportrait) {
        this.gsy_memberportrait = gsy_memberportrait;
    }

    public List<CommentPicBean> getPic_list() {
        return pic_list;
    }

    public void setPic_list(List<CommentPicBean> pic_list) {
        this.pic_list = pic_list;
    }
}
