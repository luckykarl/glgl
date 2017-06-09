package com.guanshaoye.glglteacher.ui.mine.bonus;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.guanshaoye.glglteacher.R;
import com.guanshaoye.glglteacher.bean.SalaryLogBean;
import com.guanshaoye.glglteacher.bean.TeacherCommissionListBean;
import com.guanshaoye.glglteacher.ui.mine.bonus.adapter.SalaryLogAdapter;
import com.guanshaoye.glglteacher.utils.CurrentUser;
import com.guanshaoye.glglteacher.utils.LoadingDialog;
import com.guanshaoye.mylibrary.api.SalaryApi;
import com.guanshaoye.mylibrary.base.BaseActivity;
import com.guanshaoye.mylibrary.http.FlpBack;
import com.guanshaoye.mylibrary.http.FlpException;
import com.guanshaoye.mylibrary.http.RequestBack;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.header.progresslayout.ProgressLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ${张梦博} on 2017/5/17.
 * Whenever,Wherever,Whatever,I believe I can handle everything
 */

public class SalaryLogActivity extends BaseActivity {
    @Bind(R.id.normal_title)
    TextView normalTitle;
    @Bind(R.id.refreshLayout)
    TwinklingRefreshLayout refreshLayout;
    SalaryLogAdapter salaryLogAdapter;
    TextView tvTotal;
    private int page = 1;
    private int change_num;
    private List<TeacherCommissionListBean> salaryLogBeanList = new ArrayList<>();
    @Bind(R.id.recyclerview)
    RecyclerView recyclerview;
    View headerView;
    private LinearLayout left_lay,right_lay;
    private TextView tv_center;

    @Override
    public void init() {
        setContentView(R.layout.salarylog_layout);
        normalTitle.setText("工资记录");

        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        salaryLogAdapter = new SalaryLogAdapter(getActivity());
        recyclerview.setAdapter(salaryLogAdapter);

        headerView =  LayoutInflater.from(getActivity()).inflate(R.layout.salary_head_lay, null);
        tvTotal = (TextView) headerView.findViewById(R.id.tv_total);
        left_lay = (LinearLayout) headerView.findViewById(R.id.left_lay);
        right_lay = (LinearLayout) headerView.findViewById(R.id.right_lay);
        tv_center = (TextView) headerView.findViewById(R.id.tv_center);
        left_lay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                change_num = change_num - 1;
                page = 1;
                LoadingDialog.ShowLoading(getActivity());
                getTeacherSalary();
            }
        });
        right_lay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                change_num = change_num + 1;
                page = 1;
                LoadingDialog.ShowLoading(getActivity());
                getTeacherSalary();
            }
        });
        salaryLogAdapter.setHeadHolder(headerView);

        ProgressLayout header = new ProgressLayout(getActivity());
        refreshLayout.setHeaderView(header);
        refreshLayout.setFloatRefresh(true);
        refreshLayout.setEnableOverScroll(false);
        refreshLayout.setHeaderHeight(140);
        refreshLayout.setMaxHeadHeight(240);
        refreshLayout.setTargetView(recyclerview);

        refreshLayout.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(final TwinklingRefreshLayout refreshLayout) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        page = 1;
                        getTeacherSalary();
                        refreshLayout.finishRefreshing();
                    }
                }, 1500);
            }

            @Override
            public void onLoadMore(final TwinklingRefreshLayout refreshLayout) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getTeacherSalary();
                        refreshLayout.finishLoadmore();
                    }
                }, 1500);
            }
        });

        LoadingDialog.ShowLoading(getActivity());
        getTeacherSalary();
    }


    private void getTeacherSalary() {
        SalaryApi.getTeacherSalary(CurrentUser.getUser().getTid(), page, change_num, new RequestBack() {
            @Override
            public void onComplete(FlpBack back) {
                LoadingDialog.DissLoading(getActivity());
                if (back.errorCode == 200) {
                    if (page == 1) {
                        salaryLogBeanList.clear();
                    }
                    SalaryLogBean salaryLogBean = JSON.parseObject(back.data, SalaryLogBean.class);
                    if (salaryLogBean != null) {
                        tvTotal.setText(salaryLogBean.getCommission_total_count());
                        tv_center.setText(salaryLogBean.getCommission_date());
                        salaryLogBeanList.addAll(salaryLogBean.getTeacher_commission_list());
                        salaryLogAdapter.setDataList(salaryLogBeanList);
                        page++;
                    }
                }
            }

            @Override
            public void onFlpException(FlpException e) {
                LoadingDialog.DissLoading(getActivity());
            }
        });
    }

}
