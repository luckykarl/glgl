package com.guanshaoye.glglteacher.adapter;


import com.guanshaoye.glglteacher.bean.AttendClassBean;
import com.guanshaoye.glglteacher.bean.ItemListBean;

public interface OnItemClickListener {

    void onItemClick(ItemListBean listBean);
    void onReviewItemClick();
    void onClickAttendClass(AttendClassBean attendClass);
}