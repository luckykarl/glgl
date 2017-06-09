package com.guanshaoye.glglteacher.ui.manager.attendclass;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.fastjson.JSON;
import com.guanshaoye.glglteacher.R;
import com.guanshaoye.glglteacher.adapter.OnItemClickListener;
import com.guanshaoye.glglteacher.bean.AttendClassBean;
import com.guanshaoye.glglteacher.bean.ItemListBean;
import com.guanshaoye.glglteacher.bean.UserBean;
import com.guanshaoye.glglteacher.ui.BaseFragment;
import com.guanshaoye.glglteacher.utils.CurrentUser;
import com.guanshaoye.mylibrary.api.ManagerApi;
import com.guanshaoye.mylibrary.http.FlpBack;
import com.guanshaoye.mylibrary.http.FlpException;
import com.guanshaoye.mylibrary.http.RequestBack;
import com.guanshaoye.mylibrary.utils.Toaster;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.header.progresslayout.ProgressLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by karl on 2017/5/23.
 * 已评价
 */

public class ClassNoSignFragment extends BaseFragment implements OnItemClickListener {
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.refreshLayout)
    TwinklingRefreshLayout refreshLayout;
    private AttendClassAdapter adapter;
    private List<AttendClassBean> list = new ArrayList<>();
    private UserBean userBean;
    private int page = 1;

    @Override
    protected Activity getSelfActivity() {
        return getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        setContentView(R.layout.t_review_lay);
        initView();
        return getContentView();
    }

    private void initView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getSelfActivity()));
        adapter = new AttendClassAdapter(getSelfActivity(),false);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(this);


        ProgressLayout header = new ProgressLayout(getSelfActivity());
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
                        getSingList();
                        refreshLayout.finishRefreshing();
                    }
                }, 1500);
            }

            @Override
            public void onLoadMore(final TwinklingRefreshLayout refreshLayout) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getSingList();
                        refreshLayout.finishLoadmore();
                    }
                }, 1500);
            }
        });

        userBean = CurrentUser.getUser();
        if (userBean == null) {
            return;
        }

    }


    /**
     * 获取签到列表
     */
    private void getSingList() {
        ManagerApi.getTAttendClassList(CurrentUser.getUser().getTid(), 2, page, new RequestBack() {
            @Override
            public void onComplete(FlpBack back) {

                Toaster.showToast(back.message);
                if (back.errorCode == 200){
                    if(page == 1){
                        list.clear();
                    }
                    List<AttendClassBean> beanList = JSON.parseArray(back.data,AttendClassBean.class);
                    if(beanList != null){
                        list.addAll(beanList);
                        adapter.setDataList(list);
                        page++;
                    }

                }
            }

            @Override
            public void onFlpException(FlpException e) {

            }
        });
    }

    @Override
    public void onItemClick(ItemListBean listBean) {

    }

    @Override
    public void onReviewItemClick() {

    }

    @Override
    public void onClickAttendClass(AttendClassBean attendClass) {
        if(attendClass == null){
            return;
        }
        Intent intent = new Intent(getSelfActivity(),ShowAttendClassActivity.class);
        intent.putExtra("showFlag",false);
        Bundle mBundle = new Bundle();
        mBundle.putSerializable("model",attendClass);
        intent.putExtras(mBundle);
        startActivity(intent);

    }
    @Override
    public void onResume() {
        super.onResume();
        page = 1;
        getSingList();
    }

}