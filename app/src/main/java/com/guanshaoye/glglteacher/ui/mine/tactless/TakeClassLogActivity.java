package com.guanshaoye.glglteacher.ui.mine.tactless;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.guanshaoye.glglteacher.R;
import com.guanshaoye.glglteacher.bean.TakeClassLog;
import com.guanshaoye.glglteacher.bean.UserBean;
import com.guanshaoye.glglteacher.bean.UserCommentBean;
import com.guanshaoye.glglteacher.utils.CurrentUser;
import com.guanshaoye.glglteacher.utils.LoadingDialog;
import com.guanshaoye.mylibrary.api.EditUserInfoApi;
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

public class TakeClassLogActivity extends BaseActivity {

    @Bind(R.id.normal_title)
    TextView normalTitle;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.refreshLayout)
    TwinklingRefreshLayout refreshLayout;
    private TaskLogAdapter mTakeClassLogAdapter;
    private List<TakeClassLog> takeClassList = new ArrayList<>();
    private int page = 1;

    @Override
    public void init() {
        setContentView(R.layout.activity_promotion_log);
        normalTitle.setText("上课记录");

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mTakeClassLogAdapter = new TaskLogAdapter(getActivity());
        recyclerView.setAdapter(mTakeClassLogAdapter);


        ProgressLayout header = new ProgressLayout(this);
        refreshLayout.setHeaderView(header);
        refreshLayout.setFloatRefresh(true);
        refreshLayout.setEnableOverScroll(false);
        refreshLayout.setHeaderHeight(140);
        refreshLayout.setMaxHeadHeight(240);
        refreshLayout.setTargetView(recyclerView);

        refreshLayout.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(final TwinklingRefreshLayout refreshLayout) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        page = 1;
                        getClassList();
                        refreshLayout.finishRefreshing();
                    }
                }, 15000);
            }

            @Override
            public void onLoadMore(final TwinklingRefreshLayout refreshLayout) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getClassList();
                        refreshLayout.finishLoadmore();
                    }
                }, 15000);
            }
        });
        getClassList();
    }
    private void getClassList() {
        UserBean userBean = CurrentUser.getUser();
        if (userBean == null) {
            return;
        }
        LoadingDialog.ShowLoading(getActivity());
        EditUserInfoApi.getClassList(CurrentUser.getUser().getTid(), page, new RequestBack() {
            @Override
            public void onComplete(FlpBack back) {
                LoadingDialog.DissLoading(getActivity());
                if (back.errorCode == 200){
                    if(page == 1){
                        takeClassList.clear();
                    }
                    List<TakeClassLog> list = JSON.parseArray(back.data,TakeClassLog.class);
                    if(list != null){
                        takeClassList.addAll(list);
                        mTakeClassLogAdapter.setDataList(takeClassList);
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
