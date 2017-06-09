package com.guanshaoye.mylibrary.api;

import com.guanshaoye.mylibrary.http.BaseUrl;
import com.guanshaoye.mylibrary.http.RequestBack;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by karl on 2017/6/3.
 */

public class SalaryApi extends FlpApi{

    /**
     * 老师佣金接口
     * @param uid
     * @param page
     * @param requestBack
     */
    public static void getTeacherSalary(String uid,int page,int change_num,RequestBack requestBack) {
        Map<String, Object> params = new HashMap<>();
        params.put("tid", uid);
        params.put("page", page);
        params.put("change_num", change_num);
        requestAsync(BaseUrl.TEACHER_SALARY, params, requestBack);
    }
}
