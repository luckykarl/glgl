package com.guanshaoye.mylibrary.api;

import com.guanshaoye.mylibrary.http.BaseUrl;
import com.guanshaoye.mylibrary.http.RequestBack;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by karl on 2017/5/23.
 * 课表相关
 */

public class TableClassApi extends FlpApi{

    /**
     * 我的课表接口
     * @param tid
     * @param requestBack
     */
    public static void getMyTable(String tid,int change_num,RequestBack requestBack) {
        Map<String, Object> params = new HashMap<>();
        params.put("tid", tid);
        params.put("change_num",change_num);
        requestAsync(BaseUrl.GET_USER_TABLE, params, requestBack);
    }
}
