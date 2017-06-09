package com.guanshaoye.glglteacher.bean;

import android.text.TextUtils;

import com.guanshaoye.mylibrary.http.BaseUrl;

/**
 * Created by ${张梦博} on 2017/5/16.
 * Whenever,Wherever,Whatever,I believe I can handle everything
 */

public class PromoteLog extends QXModel{
    private String gsy_portrait;
    private int gsy_before_level;
    private int gsy_after_level;
    private int gsy_status;
    private String gsy_add_time;
    private String gsy_theory_score;
    private String gsy_skill_score;

    public String getGsy_portrait() {
        if(TextUtils.isEmpty(gsy_portrait)){
            gsy_portrait = "";
        }
        return BaseUrl.HEAD_PHOTO_OSS+gsy_portrait;
    }

    public void setGsy_portrait(String gsy_portrait) {
        this.gsy_portrait = gsy_portrait;
    }

    public int getGsy_before_level() {
        return gsy_before_level;
    }

    public void setGsy_before_level(int gsy_before_level) {
        this.gsy_before_level = gsy_before_level;
    }

    public int getGsy_after_level() {
        return gsy_after_level;
    }

    public void setGsy_after_level(int gsy_after_level) {
        this.gsy_after_level = gsy_after_level;
    }

    public int getGsy_status() {
        return gsy_status;
    }

    public void setGsy_status(int gsy_status) {
        this.gsy_status = gsy_status;
    }

    public String getGsy_add_time() {
        return gsy_add_time;
    }

    public void setGsy_add_time(String gsy_add_time) {
        this.gsy_add_time = gsy_add_time;
    }

    public String getGsy_theory_score() {
        return gsy_theory_score;
    }

    public void setGsy_theory_score(String gsy_theory_score) {
        this.gsy_theory_score = gsy_theory_score;
    }

    public String getGsy_skill_score() {
        return gsy_skill_score;
    }

    public void setGsy_skill_score(String gsy_skill_score) {
        this.gsy_skill_score = gsy_skill_score;
    }
}
