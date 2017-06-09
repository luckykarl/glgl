package com.guanshaoye.mylibrary.http;

/**
 * Created by karl on 2017/5/20.
 */

public class BaseUrl {
    public static String HEAD_URL = "http://app.spgcentre.com/index.php/Teacher/";
    public static String HEAD_PHOTO = "http://app.spgcentre.com";
    public static String HEAD_PHOTO_OSS = "http://spg.oss-cn-shanghai.aliyuncs.com/";
    public static String GRADUATE_DATE = "[{\"dialog_content\":\"小学\",\"dialog_id\":\"1\"},{\"dialog_content\":\"初中\",\"dialog_id\":\"2\"},{\"dialog_content\":\"高中\",\"dialog_id\":\"3\"},{\"dialog_content\":\"专科\",\"dialog_id\":\"4\"},{\"dialog_content\":\"本科\",\"dialog_id\":\"5\"},{\"dialog_content\":\"硕士\",\"dialog_id\":\"6\"},{\"dialog_content\":\"博士\",\"dialog_id\":\"7\"}]";
    public static String SEND_SMS = HEAD_URL + "Teacher/send_sms"; //短信发送接口
    public static String USER_REGISTER = HEAD_URL + "Teacher/register";  //用户注册
    public static String USER_LOGIN = HEAD_URL + "Teacher/login"; //用户登录
    public static String HOME_SHOW = HEAD_URL + "Index/show"; //主页展示
    public static String UPDATE_USER_INFO = HEAD_URL + "Teacher/mod_common_info"; //更新用户信息
    public static String GET_USER_INFO = HEAD_URL + "Teacher/get_teacher"; //获取用户信息
    public static String GET_MINE_INFO = HEAD_URL + "Teacher/get_mine_info"; //我的接口


    public static String GET_USER_TABLE = HEAD_URL + "Grap/grap_course_tab";//我的课表接口
    public static String SHOW_GRAP_COURES = HEAD_URL + "Grap/grap_course_show"; //抢课主页
    public static String SHOW_GRAP_COURES_LIST = HEAD_URL + "Grap/grap_course_list"; //抢课列表
    public static String ADD_GRAP_COURES_LIST = HEAD_URL + "Grap/add_grap_course"; //抢课
    public static String CANCLE_GRAP_COURES_LIST = HEAD_URL + "Grap/cancel_grap_course"; //取消抢课


    public static String UPLOAD_PICTURE = HEAD_URL + "Upload/add_teacher_pic"; //图片上传

    //上课签到
    public static String GET_ATTEND_CLASS_LIST = HEAD_URL + "Comment/signin_list"; // 上课签到列表
    public static String ATTEND_CLASS_LIST = HEAD_URL + "Comment/signin_show"; // 学员签到列表
    public static String SIGN_ATTEND_CLASS = HEAD_URL + "Comment/signin"; // 添加签到
    public static String SIGN_PIC_ATTEND_CLASS = HEAD_URL + "Comment/signin_pic"; // 添加签到图片
    //培训管理
    public static String GET_TRAIN_LIST = HEAD_URL + "Train/train_list"; // 培训签到列表
    public static String GET_TRAIN_TEACHER_LIST = HEAD_URL + "Train/train_teacher_list"; //参与培训老师接口
    public static String SIGN_TRAIN = HEAD_URL + "Train/train_signin"; //培训签到
    //评论列表
    public static String GET_COMMENT_LIST = HEAD_URL + "Comment/comment_list"; // 评论列表
    public static String SHOW_COMMENT_LIST = HEAD_URL + "Comment/comment_show"; // 评论详情
    public static String ADD_COMMENT = HEAD_URL + "Comment/add_comment"; // 添加评论


    //晋级相关
    public static String ADD_PROMOTION = HEAD_URL + "Promotion/add_promotion_log"; // 添加晋级记录
    public static String GET_PROMOTION_LIST = HEAD_URL + "Promotion/promotion_list"; // 晋级记录列表



    public static String GET_USER_COMMENT_LIST = HEAD_URL + "Teacher/teacher_comment_list"; // 用户获取评论记录
    public static String GET_USER_CLASS_LIST = HEAD_URL + "Teacher/teacher_course_list"; // 用户获取上课记录





    public static String GET_USER_SETTTING = HEAD_URL + "Teacher/get_teacher_conf"; //获取消息设置接口
    public static String UPDATE_USER_SETTTING = HEAD_URL + "Teacher/mod_teacher_conf"; //修改消息设置接口


    //视频上传获取auth
    public static String GET_AUTH = HEAD_URL + "Upload/create_upload_video";

    //认证信息
    public static String GET_PRO_LIST = HEAD_URL + "Approve/get_item_class"; //获取项目分类接口
    public static String GET_CLASS_LIST = HEAD_URL + "Approve/get_course_class"; //获取课程分类接口
    public static String SUBMIT_AUTH = HEAD_URL + "Approve/teacher_approve"; //老师认证接口
    public static String GET_AUTH_INFO = HEAD_URL + "Approve/get_teacher_approve"; //获取老师认证信息


    //消息
    public static String MSG_DISPLY = HEAD_URL + "Message/message_display"; //消息主页
    public static String GET_MSG_LIST = HEAD_URL + "Message/message_list"; //消息列表

    //文章列表
    public static String FEEDBACK_LIST = HEAD_URL + "Article/article_list";


    //提交意见反馈
    public static String SUBMIT_FEEDBACK = HEAD_URL + "Feedback/teacher_feedback";
    //获取版本信息接口
    public static String CHECK_VESION_CODE = HEAD_URL + "Teacher/get_vension";

    public static String WEB_URL = "http://gl.guanshaoye.cn/index.php/Web/Article/article/id/";

    //老师佣金接口
    public static String TEACHER_SALARY = HEAD_URL + "Teacher/teacher_commission_list" ;
}
