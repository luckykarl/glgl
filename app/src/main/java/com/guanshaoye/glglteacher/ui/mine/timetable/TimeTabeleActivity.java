package com.guanshaoye.glglteacher.ui.mine.timetable;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.TextAppearanceSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.alibaba.fastjson.JSON;
import com.guanshaoye.glglteacher.R;
import com.guanshaoye.glglteacher.adapter.OnItemClickListener;
import com.guanshaoye.glglteacher.bean.AttendClassBean;
import com.guanshaoye.glglteacher.bean.CurWeekListBean;
import com.guanshaoye.glglteacher.bean.GrapCourseListBean;
import com.guanshaoye.glglteacher.bean.HourListBean;
import com.guanshaoye.glglteacher.bean.ItemListBean;
import com.guanshaoye.glglteacher.bean.TimeTableBean;
import com.guanshaoye.glglteacher.bean.UserBean;
import com.guanshaoye.glglteacher.utils.CurrentUser;
import com.guanshaoye.glglteacher.utils.LoadingDialog;
import com.guanshaoye.mylibrary.api.TableClassApi;
import com.guanshaoye.mylibrary.base.BaseActivity;
import com.guanshaoye.mylibrary.http.FlpBack;
import com.guanshaoye.mylibrary.http.FlpException;
import com.guanshaoye.mylibrary.http.RequestBack;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by karl on 2017/5/22.
 * 我的课表
 */

public class TimeTabeleActivity extends BaseActivity {
    @Bind(R.id.normal_title)
    TextView normalTitle;
    @Bind(R.id.left_img)
    ImageView leftImg;
    @Bind(R.id.right_img)
    ImageView rightImg;
    @Bind(R.id.start_time)
    TextView startTime;
    @Bind(R.id.end_time)
    TextView endTime;
    @Bind(R.id.recyclerview)
    RecyclerView recyclerview;
    @Bind(R.id.left_time_lay)
    LinearLayout leftTimeLay;
    @Bind(R.id.right_time_lay)
    LinearLayout rightTimeLay;
    @Bind(R.id.bottom_view)
    LinearLayout bottomView;

    private TimeTableAdapter timeTableAdapter;
    private List<ItemListBean> tableSelect = new ArrayList<>();
    private int change_num;

    @Override
    public void init() {
        setContentView(R.layout.timetable_layout);
        normalTitle.setText(getResources().getString(R.string.my_timetable));

        recyclerview.setLayoutManager(new GridLayoutManager(this, 7));
        timeTableAdapter = new TimeTableAdapter();
        recyclerview.setAdapter(timeTableAdapter);
        timeTableAdapter.setOnItemClickListener(onItemClickListener);

        getTimeTable();
    }

    private void loadData(TimeTableBean tableBean) {

        String sT = tableBean.getDate_arr().get(0);
        String eT = tableBean.getDate_arr().get(1);
        SpannableString styledText = new SpannableString(sT);
        styledText.setSpan(new TextAppearanceSpan(this, R.style.style_year), 0, 4, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        styledText.setSpan(new TextAppearanceSpan(this, R.style.style_monthandday), 4, sT.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        startTime.setText(styledText, TextView.BufferType.SPANNABLE);

        SpannableString styledText2 = new SpannableString(eT);
        styledText2.setSpan(new TextAppearanceSpan(this, R.style.style_year), 0, 4, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        styledText2.setSpan(new TextAppearanceSpan(this, R.style.style_monthandday), 4, eT.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        endTime.setText(styledText2, TextView.BufferType.SPANNABLE);
        List<HourListBean> hourListBeen = tableBean.getHour_list();

        leftTimeLay.removeAllViews();
        for (int i = 0; i < hourListBeen.size(); i++) {
            HourListBean hourBean = hourListBeen.get(i);
            View convertView = LayoutInflater.from(TimeTabeleActivity.this).inflate(R.layout.item_lefttime_lay, null);
            TextView left_contant = (TextView) convertView.findViewById(R.id.left_contant);
            left_contant.setText(hourBean.getStart_time() + "~" + hourBean.getEnd_time());
            leftTimeLay.addView(convertView);
        }
        List<CurWeekListBean> curWeekListBeen = tableBean.getCur_week_list();
        rightTimeLay.removeAllViews();
        for (int i = 0; i < curWeekListBeen.size(); i++) {
            CurWeekListBean curWeekBean = curWeekListBeen.get(i);
            View convertView = LayoutInflater.from(TimeTabeleActivity.this).inflate(R.layout.item_righttime_lay, null);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, 1);
            layoutParams.weight = 1;
            convertView.setLayoutParams(layoutParams);
            TextView data_time_txt = (TextView) convertView.findViewById(R.id.data_time_txt);
            TextView week_time_txt = (TextView) convertView.findViewById(R.id.week_time_txt);
            data_time_txt.setText(curWeekBean.getDay_num() + "");
            week_time_txt.setText(curWeekBean.getWeek_num());
            rightTimeLay.addView(convertView);
        }

        tableSelect.clear();
        for (int i = 0; i < hourListBeen.size() * 7; i++) {
            tableSelect.add(new ItemListBean());
        }
        timeTableAdapter.setDataList(tableSelect);


        List<ItemListBean> grapCourseListBeen = tableBean.getItem_list();
        for (int i = 0; i < grapCourseListBeen.size(); i++) {
            ItemListBean listBean = grapCourseListBeen.get(i);
            int xTime = listBean.getX(); //行
            int xDay = listBean.getY();  //列

            int position = xTime * 7 + xDay;
            tableSelect.set(position, listBean);
        }

        timeTableAdapter.setDataList(tableSelect);

    }
    private OnItemClickListener onItemClickListener = new OnItemClickListener() {

        @Override
        public void onItemClick(ItemListBean listBeann) {
            bottomView.removeAllViews();
            List<GrapCourseListBean> grapCourseList = listBeann.getGrap_course_list();
            if(grapCourseList ==null){
                return;
            }
            for (int i = 0; i < grapCourseList.size(); i++) {
                GrapCourseListBean grapCourseBean = grapCourseList.get(i);
                View convertView = LayoutInflater.from(TimeTabeleActivity.this).inflate(R.layout.item_timetable_list_lay, null);
                TextView data_time = (TextView) convertView.findViewById(R.id.data_time);
                TextView data_status = (TextView) convertView.findViewById(R.id.data_status);
                TextView data_content = (TextView) convertView.findViewById(R.id.data_content);

                data_time.setText(grapCourseBean.getGsy_start_time()+"-"+grapCourseBean.getGsy_end_time());
                data_status.setText(grapCourseBean.getGsy_status_name());
                if(grapCourseBean.getGsy_status().equals("3")){
                    String resion = grapCourseBean.getGsy_note();
                    if(!TextUtils.isEmpty(resion)){
                        data_content.setText(resion);
                    }
                }else {

                    data_content.setText(grapCourseBean.getGsy_course_class_name()+"    "+grapCourseBean.getGsy_store_name());
                }
                bottomView.addView(convertView);
            }
        }

        @Override
        public void onReviewItemClick() {

        }

        @Override
        public void onClickAttendClass(AttendClassBean attendClass) {

        }

    };
    @OnClick({R.id.left_img, R.id.right_img})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.left_img:
                change_num = change_num - 1;
                getTimeTable();
                break;
            case R.id.right_img:
                change_num = change_num + 1;
                getTimeTable();
                break;

        }
    }


    private void getTimeTable() {
        LoadingDialog.ShowLoading(TimeTabeleActivity.this);
        UserBean userBean = CurrentUser.getUser();
        if(userBean == null){
            return;
        }

        TableClassApi.getMyTable(userBean.getTid(),change_num, new RequestBack() {
            @Override
            public void onComplete(FlpBack back) {
                LoadingDialog.DissLoading(TimeTabeleActivity.this);
                if (back.errorCode == 200) {
                    TimeTableBean tableBean = JSON.parseObject(back.data, TimeTableBean.class);
                    if (tableBean != null) {
                        loadData(tableBean);
                    }
                }
            }

            @Override
            public void onFlpException(FlpException e) {
                LoadingDialog.DissLoading(TimeTabeleActivity.this);
            }
        });
    }

}
