package com.guanshaoye.mylibrary.api;

import com.guanshaoye.mylibrary.http.BaseUrl;
import com.guanshaoye.mylibrary.http.RequestBack;
import com.guanshaoye.mylibrary.http.Url;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by karl on 2017/5/23.
 */

public class EditUserInfoApi extends FlpApi {


    /**
     * 获取用户信息
     * @param tid
     * @param requestBack
     */
    public static void getUserInfo(String tid, RequestBack requestBack){
        Map<String,Object> params=new HashMap<>();
        params.put("tid",tid);
        requestAsync(BaseUrl.GET_USER_INFO, params, requestBack);
    }
    /**
     * 获取我的接口
     * @param tid
     * @param requestBack
     */
    public static void getMineInfo(String tid, RequestBack requestBack){
        Map<String,Object> params=new HashMap<>();
        params.put("tid",tid);
        requestAsync(BaseUrl.GET_MINE_INFO, params, requestBack);
    }
    /**
     * 更新用户信息接口
     * @param params
     * @param requestBack
     */
    public static void updateUserInfo(Map<String, Object> params, RequestBack requestBack){

        requestAsync(BaseUrl.UPDATE_USER_INFO, params, requestBack);
    }

    /**
     * 获取用户评论记录
     * @param tid
     * @param page
     * @param requestBack
     */
    public static void getCommentList(String tid,int page, RequestBack requestBack){
        Map<String,Object> params=new HashMap<>();
        params.put("tid",tid);
        params.put("page",page);
        requestAsync(BaseUrl.GET_USER_COMMENT_LIST, params, requestBack);
    }

    /**
     * 获取用户上课记录
     * @param tid
     * @param page
     * @param requestBack
     */
    public static void getClassList(String tid,int page, RequestBack requestBack){
        Map<String,Object> params=new HashMap<>();
        params.put("tid",tid);
        params.put("page",page);
        requestAsync(BaseUrl.GET_USER_CLASS_LIST, params, requestBack);
    }
    //**************晋级相关**********

    /**
     *
     * @param tid
     * @param requestBack
     */
    public static void addPromotion(String tid, RequestBack requestBack){
        Map<String,Object> params=new HashMap<>();
        params.put("tid",tid);
        requestAsync(BaseUrl.ADD_PROMOTION, params, requestBack);
    }

    /**
     * 获取晋级记录列表
     * @param tid
     * @param page
     * @param requestBack
     */
    public static void getPromotionList(String tid,int page, RequestBack requestBack){
        Map<String,Object> params=new HashMap<>();
        params.put("tid",tid);
        params.put("page",page);
        requestAsync(BaseUrl.GET_PROMOTION_LIST, params, requestBack);
    }

}
