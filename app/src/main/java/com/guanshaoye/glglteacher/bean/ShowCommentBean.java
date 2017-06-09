package com.guanshaoye.glglteacher.bean;

import java.util.List;

/**
 * Created by karl on 2017/5/30.
 */

public class ShowCommentBean {


    /**
     * comment_info : {"gsy_is_replace":2}
     * comment_pic_list : []
     * member_comment_list : [{"id":"9","gsy_pid":"100132","gsy_star_num":"5","gsy_hudong_item":"1","gsy_jishu_item":"1","gsy_jiaoxue_item":"1","gsy_xunlian_item":"1","gsy_neirong_item":"1","gsy_realname":"还是","gsy_memberportrait":"/Uploads/Public/uploads/portrait/20170525/5926338168953.png","pic_list":[{"id":"10","gsy_pic_url":"/Uploads/Public/uploads/comment/20170525/5926873c5313d.png"},{"id":"11","gsy_pic_url":"/Uploads/Public/uploads/comment/20170525/59268a53adae3.png"},{"id":"12","gsy_pic_url":"/Uploads/Public/uploads/comment/20170527/592967bcdcf17.png"}]}]
     */

    private CommentInfoBean comment_info;
    private List<CommentPicBean> comment_pic_list;
    private List<MemberCommentListBean> member_comment_list;

    public CommentInfoBean getComment_info() {
        return comment_info;
    }

    public void setComment_info(CommentInfoBean comment_info) {
        this.comment_info = comment_info;
    }

    public List<CommentPicBean> getComment_pic_list() {
        return comment_pic_list;
    }

    public void setComment_pic_list(List<CommentPicBean> comment_pic_list) {
        this.comment_pic_list = comment_pic_list;
    }

    public List<MemberCommentListBean> getMember_comment_list() {
        return member_comment_list;
    }

    public void setMember_comment_list(List<MemberCommentListBean> member_comment_list) {
        this.member_comment_list = member_comment_list;
    }

}
