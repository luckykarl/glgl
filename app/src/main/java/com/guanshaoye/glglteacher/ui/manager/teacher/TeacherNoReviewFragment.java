package com.guanshaoye.glglteacher.ui.manager.teacher;

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
import com.guanshaoye.glglteacher.adapter.OnCommentClickListener;
import com.guanshaoye.glglteacher.adapter.OnItemClickListener;
import com.guanshaoye.glglteacher.bean.AttendClassBean;
import com.guanshaoye.glglteacher.bean.ItemListBean;
import com.guanshaoye.glglteacher.bean.TeacherReviewBean;
import com.guanshaoye.glglteacher.ui.BaseFragment;
import com.guanshaoye.glglteacher.ui.manager.attendclass.ShowAttendClassActivity;
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
import butterknife.ButterKnife;

/**
 *
 * Created by karl on 2017/5/23.
 *
 */

public class TeacherNoReviewFragment extends BaseFragment implements OnCommentClickListener {
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.refreshLayout)
    TwinklingRefreshLayout refreshLayout;
    private TeacherReviewAdapter adapter;
    private List<TeacherReviewBean> list = new ArrayList<>();
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
        adapter = new TeacherReviewAdapter(getSelfActivity(),false);
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


    }

    private void getCommentList() {
        ManagerApi.getCommentnList(CurrentUser.getUser().getTid(), 2, page, new RequestBack() {
            @Override
            public void onComplete(FlpBack back) {

                Toaster.showToast(back.message);
                if (back.errorCode == 200) {
                    if (page == 1) {
                        list.clear();
                    }
                    List<TeacherReviewBean> beanList = JSON.parseArray(back.data, TeacherReviewBean.class);
                    if (beanList != null) {
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
    public void onItemClick(TeacherReviewBean bean) {
        if(bean == null){
            return;
        }
        Intent intent = new Intent(getSelfActivity(),ShowCommentActivity.class);
        intent.putExtra("showFlag",false);
        Bundle mBundle = new Bundle();
        mBundle.putSerializable("model",bean);
        intent.putExtras(mBundle);
        startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();
        page = 1;
        getCommentList();
    }
}
