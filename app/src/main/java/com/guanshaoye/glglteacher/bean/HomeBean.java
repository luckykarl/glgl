package com.guanshaoye.glglteacher.bean;

import java.util.List;

/**
 * Created by karl on 2017/6/3.
 */

public class HomeBean {

    /**
     * article_list : [{"id":"104","gsy_article_title":"一个故事读懂人民币暴跌真相！","gsy_article_pic":"/Uploads/Public/uploads/article_headline/20161020/5808661749ad9.jpg","gsy_article_addtime":"2016-10-20"},{"id":"97","gsy_article_title":"一个杜蕾斯成本多少？一起来了解各行各业的成本构成吧","gsy_article_pic":"/Uploads/Public/uploads/article_headline/20161019/58072fee18d17.png","gsy_article_addtime":"2016-10-19"},{"id":"96","gsy_article_title":"腾讯五虎将、新东方\u201c中国合伙人\u201d、携程四君子...合伙人抱团取暖才是王道","gsy_article_pic":"/Uploads/Public/uploads/article_headline/20161019/58072ef33767a.jpg","gsy_article_addtime":"2016-10-19"}]
     * banner_list : [{"id":"17","gsy_cate":"2","gsy_banner_name":"资本驱动下，互联网教育新路径","gsy_banner_type":"0","gsy_banner_url":"http://www.huodongxing.com/event/2349292106500?utm_source=","gsy_banner_pho_url":"/Uploads/Public/uploads/banner/20160905/57ccebff6f9a5.jpg","gsy_article_id":"3","gsy_status":"1","gsy_sort":"0","gsy_add_time":"1473047551"},{"id":"16","gsy_cate":"2","gsy_banner_name":"互联网新时代连锁餐饮的商业变局","gsy_banner_type":"0","gsy_banner_url":"http://www.huodongxing.com/event/3350446189900?utm_source=","gsy_banner_pho_url":"/Uploads/Public/uploads/banner/20160905/57cce6e4290d8.jpg","gsy_article_id":"2","gsy_status":"1","gsy_sort":"0","gsy_add_time":"1473045636"},{"id":"15","gsy_cate":"2","gsy_banner_name":"2016第八届中国连锁酒店发展大会","gsy_banner_type":"0","gsy_banner_url":"http://www.huodongxing.com/event/7345843864500?utm_source=","gsy_banner_pho_url":"/Uploads/Public/uploads/banner/20160905/57cce3533ecec.jpg","gsy_article_id":"1","gsy_status":"1","gsy_sort":"0","gsy_add_time":"1473045331"}]
     * takelessons : {"name":"抢课","ename":"Take lessons","botton_auth_status":1}
     * work : {"name":"工作","ename":"Work","botton_auth_status":1}
     * management : {"name":"管理","ename":"Management","botton_auth_status":1}
     * mine : {"name":"我的","ename":"Mine","botton_auth_status":1}
     * courseware : {"name":"课件","ename":"Courseware","botton_auth_status":1}
     */

    private TakelessonsBean takelessons;
    private WorkBean work;
    private ManagementBean management;
    private MineBean mine;
    private CoursewareBean courseware;
    private List<ArticleListBean> article_list;
    private List<BannerBean> banner_list;

    public TakelessonsBean getTakelessons() {
        return takelessons;
    }

    public void setTakelessons(TakelessonsBean takelessons) {
        this.takelessons = takelessons;
    }

    public WorkBean getWork() {
        return work;
    }

    public void setWork(WorkBean work) {
        this.work = work;
    }

    public ManagementBean getManagement() {
        return management;
    }

    public void setManagement(ManagementBean management) {
        this.management = management;
    }

    public MineBean getMine() {
        return mine;
    }

    public void setMine(MineBean mine) {
        this.mine = mine;
    }

    public CoursewareBean getCourseware() {
        return courseware;
    }

    public void setCourseware(CoursewareBean courseware) {
        this.courseware = courseware;
    }

    public List<ArticleListBean> getArticle_list() {
        return article_list;
    }

    public void setArticle_list(List<ArticleListBean> article_list) {
        this.article_list = article_list;
    }

    public List<BannerBean> getBanner_list() {
        return banner_list;
    }

    public void setBanner_list(List<BannerBean> banner_list) {
        this.banner_list = banner_list;
    }




}
