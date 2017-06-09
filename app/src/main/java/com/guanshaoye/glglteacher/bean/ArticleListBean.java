package com.guanshaoye.glglteacher.bean;

import com.bigkoo.pickerview.model.IPickerViewData;

/**
 * Created by karl on 2017/6/5.
 */

public class ArticleListBean implements IPickerViewData {
    /**
     * id : 104
     * gsy_article_title : 一个故事读懂人民币暴跌真相！
     * gsy_article_pic : /Uploads/Public/uploads/article_headline/20161020/5808661749ad9.jpg
     * gsy_article_addtime : 2016-10-20
     */

    private String id;
    private String gsy_article_title;
    private String gsy_article_pic;
    private String gsy_article_addtime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGsy_article_title() {
        return gsy_article_title;
    }

    public void setGsy_article_title(String gsy_article_title) {
        this.gsy_article_title = gsy_article_title;
    }

    public String getGsy_article_pic() {
        return gsy_article_pic;
    }

    public void setGsy_article_pic(String gsy_article_pic) {
        this.gsy_article_pic = gsy_article_pic;
    }

    public String getGsy_article_addtime() {
        return gsy_article_addtime;
    }

    public void setGsy_article_addtime(String gsy_article_addtime) {
        this.gsy_article_addtime = gsy_article_addtime;
    }

    @Override
    public String getPickerViewText() {
        return getGsy_article_addtime() +" "+getGsy_article_title();
    }
}
