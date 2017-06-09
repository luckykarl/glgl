package com.guanshaoye.glglteacher.ui.mine.tactless;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.guanshaoye.glglteacher.R;
import com.guanshaoye.glglteacher.adapter.ClassRankAdapter;
import com.guanshaoye.glglteacher.adapter.StoreNameAdapter;
import com.guanshaoye.glglteacher.bean.GsyLevelListBean;
import com.guanshaoye.glglteacher.bean.RobClassBean;
import com.guanshaoye.glglteacher.bean.ScheduleListBean;
import com.guanshaoye.glglteacher.bean.StoreListBean;
import com.guanshaoye.glglteacher.bean.UserBean;
import com.guanshaoye.glglteacher.utils.CurrentUser;
import com.guanshaoye.glglteacher.utils.LoadingDialog;
import com.guanshaoye.glglteacher.widget.DialogUtils;
import com.guanshaoye.mylibrary.api.RobClassApi;
import com.guanshaoye.mylibrary.base.BaseActivity;
import com.guanshaoye.mylibrary.http.FlpBack;
import com.guanshaoye.mylibrary.http.FlpException;
import com.guanshaoye.mylibrary.http.RequestBack;
import com.guanshaoye.mylibrary.utils.Toaster;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.Bind;

/**
 * Created by ${张梦博} on 2017/5/15.
 * Whenever,Wherever,Whatever,I believe I can handle everything
 */
public class RobClassActivity extends BaseActivity {
    @Bind(R.id.normal_title)
    TextView normalTitle;
    @Bind(R.id.store_recyclerView)
    RecyclerView storeRecyclerView;
    @Bind(R.id.rank_recyclerView)
    RecyclerView rankRecyclerView;
    @Bind(R.id.calendarView)
    MaterialCalendarView calendarView;
    @Bind(R.id.add_bottom_lay)
    LinearLayout addBottomLay;
    private ClassRankAdapter mClassRankAdapter;
    private StoreNameAdapter mStoreNameAdapter;
    private UserBean userBean;
    private List<ScheduleListBean> scheduleList = new ArrayList<>();
    private String gsy_store_id = "";
    private String gsy_teacher_level = "";
    private String date_str = "";

    @Override
    public void init() {
        setContentView(R.layout.activity_rob_class);
        normalTitle.setText("抢课");

        mClassRankAdapter = new ClassRankAdapter(this);
        mStoreNameAdapter = new StoreNameAdapter(this);
        LinearLayoutManager storeNameManager = new LinearLayoutManager(this);
        storeNameManager.setOrientation(OrientationHelper.HORIZONTAL);
        storeRecyclerView.setLayoutManager(storeNameManager);
        storeRecyclerView.setAdapter(mStoreNameAdapter);
        LinearLayoutManager rankManager = new LinearLayoutManager(this);
        rankManager.setOrientation(OrientationHelper.HORIZONTAL);
        rankRecyclerView.setLayoutManager(rankManager);
        rankRecyclerView.setAdapter(mClassRankAdapter);
        mStoreNameAdapter.setOnItemClickListener(new StoreNameAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, StoreListBean data) {
                if (data != null) {
                    gsy_store_id = data.getId();
                    showGrapCpuresList();
                }
            }
        });
        mClassRankAdapter.setOnItemClickListener(new ClassRankAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, GsyLevelListBean data) {
                if (data != null) {
                    gsy_teacher_level = data.getLevel();
                    showGrapCpuresList();
                }
            }
        });
        Calendar calendar = Calendar.getInstance();
        calendarView.setSelectedDate(calendar.getTime());

        Calendar instance1 = Calendar.getInstance();
        instance1.set(instance1.get(Calendar.YEAR), Calendar.JANUARY, 1);

        Calendar instance2 = Calendar.getInstance();
        instance2.set(instance2.get(Calendar.YEAR) + 2, Calendar.OCTOBER, 31);
        calendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                String dateString = formatter.format(date.getDate());
                date_str = dateString;
                showGrapCpuresList();
            }
        });
        calendarView.state().edit()
                .setMinimumDate(instance1.getTime())
                .setMaximumDate(instance2.getTime())
                .commit();

        userBean = CurrentUser.getUser();

        showGrapCpures();
    }


    private void initDate(RobClassBean robClass) {

        List<StoreListBean> store_list = robClass.getStore_list();
        if (store_list == null) {
            return;
        }
        if (store_list.size() > 0) {
            gsy_store_id = store_list.get(0).getId();
        }
        mStoreNameAdapter.storeList.addAll(store_list);
        mStoreNameAdapter.notifyDataSetChanged();
        List<GsyLevelListBean> gsy_level_list = robClass.getGsy_level_list();
        if (gsy_level_list == null) {
            return;
        }
        if (gsy_level_list.size() > 0) {
            gsy_teacher_level = gsy_level_list.get(0).getLevel();
        }

        mClassRankAdapter.levelList.addAll(gsy_level_list);
        mClassRankAdapter.notifyDataSetChanged();

        List<ScheduleListBean> schedule_list = robClass.getSchedule_list();
        if (schedule_list == null) {
            return;
        }
        initBottonDate(schedule_list,false);

    }

    private void initBottonDate(List<ScheduleListBean> schedule_list,boolean flag) {
        if(!flag){
            scheduleList.clear();
            scheduleList.addAll(schedule_list);
        }

        addBottomLay.removeAllViews();
        for (int i = 0; i < schedule_list.size(); i++) {
            final ScheduleListBean scheduleBean = schedule_list.get(i);
            final int position = i;
            View convertView = LayoutInflater.from(RobClassActivity.this).inflate(R.layout.item_rob_class, null);
            TextView tv_day = (TextView) convertView.findViewById(R.id.tv_day);
            TextView tv_month = (TextView) convertView.findViewById(R.id.tv_month);
            TextView tv_time = (TextView) convertView.findViewById(R.id.tv_time);
            TextView btn_rob_class = (TextView) convertView.findViewById(R.id.btn_rob_class);
            ImageView status_img = (ImageView) convertView.findViewById(R.id.status_img);
            LinearLayout click_lay = (LinearLayout) convertView.findViewById(R.id.click_lay);
            tv_day.setText(scheduleBean.getGsy_day_time());
            tv_month.setText(scheduleBean.getGsy_month_time());
            tv_time.setText(scheduleBean.getGsy_start_time() + "~" + scheduleBean.getGsy_end_time());

            String dialogTitle = "";
            if (scheduleBean.getIs_grap() == 0){
                btn_rob_class.setVisibility(View.VISIBLE);
                status_img.setVisibility(View.GONE);
                dialogTitle = getResources().getString(R.string.grap_dialog_sure);
            }else if(scheduleBean.getIs_grap() == 1){
                status_img.setVisibility(View.VISIBLE);
                btn_rob_class.setVisibility(View.GONE);
                dialogTitle = getResources().getString(R.string.grap_dialog_cancle);
            }
            final String finalDialogTitle = dialogTitle;
            click_lay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DialogUtils.appointCourseDialog(RobClassActivity.this, finalDialogTitle, scheduleBean, new DialogUtils.DialogClickListener() {
                        @Override
                        public void clicksure(ScheduleListBean scheduleBean) {

                            if (scheduleBean == null) {
                                return;
                            }
                            if (scheduleBean.getIs_grap() == 0) {
                                addGrapCpures(position,scheduleBean);
                            } else if (scheduleBean.getIs_grap() == 1) {
                                cancleGrapCpures(position,scheduleBean);
                            }
                        }
                    }).show();
                }
            });
            addBottomLay.addView(convertView);
        }
    }

    /**
     * 抢课主页
     */
    private void showGrapCpures() {
        LoadingDialog.ShowLoading(RobClassActivity.this);
        if (userBean == null) {
            return;
        }
        RobClassApi.showGrapCpures(userBean.getTid(), new RequestBack() {
            @Override
            public void onComplete(FlpBack back) {
                LoadingDialog.DissLoading(RobClassActivity.this);
                if (back.errorCode == 200) {
                    RobClassBean robClass = JSON.parseObject(back.data, RobClassBean.class);
                    if (robClass != null)
                        initDate(robClass);
                }
                Toaster.showToast(back.message);
            }

            @Override
            public void onFlpException(FlpException e) {
                LoadingDialog.DissLoading(RobClassActivity.this);
            }
        });
    }

    /**
     * 抢课列表
     */
    private void showGrapCpuresList() {
        LoadingDialog.ShowLoading(RobClassActivity.this);
        if (userBean == null) {
            return;
        }
        RobClassApi.showGrapCpuresList(userBean.getTid(), date_str, gsy_store_id, gsy_teacher_level, new RequestBack() {
            @Override
            public void onComplete(FlpBack back) {
                LoadingDialog.DissLoading(RobClassActivity.this);
                if (back.errorCode == 200) {
                    List<ScheduleListBean> scheduleList = JSON.parseArray(back.data, ScheduleListBean.class);
                    if (scheduleList == null) {
                        return;
                    }
                    initBottonDate(scheduleList,false);
                } else if (back.errorCode == 202) {
                    addBottomLay.removeAllViews();
                    Toaster.showToast(back.message);
                }

            }

            @Override
            public void onFlpException(FlpException e) {
                LoadingDialog.DissLoading(RobClassActivity.this);
            }
        });
    }

    /**
     * 抢课
     *
     * @param scheduleBean
     */
    private void addGrapCpures(final int posiiton, ScheduleListBean scheduleBean) {
        LoadingDialog.ShowLoading(RobClassActivity.this);
        if (userBean == null) {
            return;
        }
        RobClassApi.addGrapCpures(userBean.getTid(), scheduleBean.getId(), gsy_store_id, new RequestBack() {
            @Override
            public void onComplete(FlpBack back) {
                LoadingDialog.DissLoading(RobClassActivity.this);
                Toaster.showToast(back.message);
                if(back.errorCode == 200){
                    scheduleList.get(posiiton).setIs_grap(1);
                    initBottonDate(scheduleList,true);
                }
            }

            @Override
            public void onFlpException(FlpException e) {
                LoadingDialog.DissLoading(RobClassActivity.this);
            }
        });
    }

    /**
     * 取消抢课
     *
     * @param scheduleBean
     */
    private void cancleGrapCpures(final int posiiton ,ScheduleListBean scheduleBean) {
        LoadingDialog.ShowLoading(RobClassActivity.this);
        if (userBean == null) {
            return;
        }
        RobClassApi.cancleGrapCpures(userBean.getTid(), scheduleBean.getId(), new RequestBack() {
            @Override
            public void onComplete(FlpBack back) {
                LoadingDialog.DissLoading(RobClassActivity.this);
                Toaster.showToast(back.message);
                if(back.errorCode == 200){
                    scheduleList.get(posiiton).setIs_grap(0);
                    initBottonDate(scheduleList,true);
                }
            }

            @Override
            public void onFlpException(FlpException e) {
                LoadingDialog.DissLoading(RobClassActivity.this);
            }
        });
    }

    private void changeView(int position){
        View v = addBottomLay.getChildAt(position);
    }

}
