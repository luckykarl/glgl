package com.guanshaoye.mylibrary.api;

import com.guanshaoye.mylibrary.http.BaseUrl;
import com.guanshaoye.mylibrary.http.RequestBack;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by karl on 2017/6/3.
 */

public class ArticleApi extends FlpApi{

    /**
     * 文章列表
     * @param uid
     * @param class_id
     * @param requestBack
     */
    public static void getArticleList(String uid,int class_id,RequestBack requestBack) {
        Map<String, Object> params = new HashMap<>();
        params.put("tid", uid);
        params.put("class_id", class_id);
        requestAsync(BaseUrl.FEEDBACK_LIST, params, requestBack);
    }
}
