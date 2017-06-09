package com.guanshaoye.mylibrary.http;

import com.alibaba.fastjson.JSON;

public class FlpBack {
	public int errorCode=0;
	public boolean success;
	public String message;
	public String data;
	public String status;//用户状态 -2删除-1审核未通过 0待审 1正常 2已停用
	public int  is_talk;//1表示首次约谈 2表示已经约谈过
	public int is_show_dot;//是否显示小红点


	public static FlpBack parse(String result){
		  try {
			return JSON.parseObject(result,FlpBack.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public String toString() {
		
		return JSON.toJSONString(this);
	}
	

}
