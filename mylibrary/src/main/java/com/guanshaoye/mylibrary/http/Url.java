package com.guanshaoye.mylibrary.http;
/**
 * Created by ${张梦博} on 2017/2/25.
 *
 * Whenever,Wherever,Whatever,I believe I can handle everything
 */
   //正式环境 http://api.179001.com
   //测试环境 http://139.224.72.21:66
    public class Url {
    public static  final String  HEAD="http://api.179001.com/index.php/Home/";
    /*文章*/
    public static final String WEB_ARTICLE="http://api.179001.com/index.php/Web/Mobi/article/";
   /*用户*/
        //获取头像
       public static  final String GET_HEAD_PREFIX="http://api.179001.com";
        //获取验证码
       public static  final String GET_CHECK_CODE=HEAD+"Member/send_sms";
        //验证邀请码
       public static final String CHECK_INVITE_CODE=HEAD+"Member/invite_code";
        //登录
       public static final String LOGIN=HEAD+"Member/login";
        //验证token
       public static final String CHECK_TOKEN=HEAD+"Member/test_token";
        //注册
       public static final String REGISTER=HEAD+"Member/register";
        //获取用户信息
       public static final String GET_USER=HEAD+"Member/get_user";
        //上传图片
       public static final String UPLOAD_PIC=HEAD+"Member/upload";
        //添加修改字段
       public static final String UPDATE_KEY_WORD=HEAD+"Member/mod_member_common_info";
        //认证
       public static final String REALNAME_AUTHENTIC=HEAD+"Member/member_approve";
        //成为代理商
        public  static final String BECOME_AGENT=HEAD+"Member/upgrade_to_agent";
        //支付回调
        public static final String PAY_BACK=HEAD+"Member/return_url";
        //我的提成
        public static final String My_COMMISSION_LIST=HEAD+"Member/my_percentage";
        //余额充值
        public static final String BALANCE_ADD_VALUE=HEAD+"Member/member_recharge";
        //意见反馈
        public  static final String FEED_BACK=HEAD+"Feedback/member_feedback";
        //版本更新
        public static final String  UPDATE_VERSION=HEAD+"Member/get_vension";
       //获取认证详情
        public static final String  GET_MEMBER_APPROVE=HEAD +"Member/get_member_approve";
       //成为代理商显示
        public static final String  BECOME_AGENT_SHOW=HEAD+"Member/agent_show";
       /*卡*/
        //卡列表
        public static final String GET_CARD_LIST=HEAD+"Card/card_list";
        //卡详情
        public static final String GET_CARD_DETAIL=HEAD+"Card/card_detail";
        //查流量
        public static final String QUERY_FLOW=HEAD+"Card/get_card_flow";
        //卡套餐列表
       public static final String GET_CARD_CHANNEL_LIST=HEAD+"Card/card_channel_list";
        //充值记录
        public static final String ADD_VALUE_LOG=HEAD+"Member/member_recharge_log";
        /*订单*/
       //流量订单列表
        public static  final String FLOW_ORDER_LIST=HEAD+"Order/flow_order_list";
       //卡订单列表e
        public  static final String CARD_ORDER_LIST=HEAD+"Order/card_order_list";
       //添加流量订单
        public  static final String ADD_FLOW_ORDER=HEAD+"Order/add_flow_order";
       //添加卡订单接口
         public static final String ADD_CARD_ORDER=HEAD+"Order/add_card_order";
       //显示购卡信息
         public static final String CARD_ORDER_SHOW=HEAD+"Order/card_order_show";
     /*文章*/
        //文章列表
        public static  final String ARTICLE_LIST=HEAD+"Article/article_list";
        //文章详情
        public static  final String ARTICLE_DETAIL=HEAD+"Article/article_detail";
     /*提现*/
        //提现列表
        public static final String WITHDRAW_LIST=HEAD+"Withdraw/withdraw_list";
        //申请提现
        public static final String APPLY_WITHDRAW=HEAD+"Withdraw/apply_withdraw";
        //提现验证码
        public static final String WITHDRAW_SMS_CODE=HEAD+"Withdraw/send_tx_sms";

}
