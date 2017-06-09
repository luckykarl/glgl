package com.guanshaoye.glglteacher.ui.mine;

import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.guanshaoye.glglteacher.R;
import com.guanshaoye.glglteacher.adapter.UserCommentAdapter;
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

/**
 * Created by karl on 2017/5/30.'
 * 用户评论记录
 */

public class UserCommentListActitvity extends BaseActivity{

    @Bind(R.id.normal_title)
    TextView normalTitle;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.refreshLayout)
    TwinklingRefreshLayout refreshLayout;
    private UserCommentAdapter adapter;
    private List<UserCommentBean> mPromoteLogList = new ArrayList<>();
    private int page = 1;

    @Override
    public void init() {
        setContentView(R.layout.activity_promotion_log);
        normalTitle.setText("评论记录");
        initview();
    }

    private void initview() {

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new UserCommentAdapter(getActivity());
        recyclerView.setAdapter(adapter);

        ProgressLayout header = new ProgressLayout(getActivity());
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
                        getCommentList();
                        refreshLayout.finishRefreshing();
                    }
                }, 1500);
            }

            @Override
            public void onLoadMore(final TwinklingRefreshLayout refreshLayout) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getCommentList();
                        refreshLayout.finishLoadmore();
                    }
                }, 1500);
            }
        });
        getCommentList();
    }

    private void getCommentList() {
        UserBean userBean = CurrentUser.getUser();
        if (userBean == null) {
            return;
        }
        LoadingDialog.ShowLoading(getActivity());
        EditUserInfoApi.getCommentList(CurrentUser.getUser().getTid(), page, new RequestBack() {
            @Override
            public void onComplete(FlpBack back) {
                LoadingDialog.DissLoading(getActivity());
                if (back.errorCode == 200){
                    if(page == 1){
                        mPromoteLogList.clear();
                    }
                    List<UserCommentBean> list = JSON.parseArray(back.data,UserCommentBean.class);
                    if(list != null){
                        mPromoteLogList.addAll(list);
                        adapter.setDataList(mPromoteLogList);
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
