package com.guanshaoye.glglteacher.ui.message;

import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import com.alibaba.fastjson.JSON;
import com.guanshaoye.glglteacher.R;
import com.guanshaoye.glglteacher.bean.MessageInfoBean;
import com.guanshaoye.glglteacher.bean.UserBean;
import com.guanshaoye.glglteacher.utils.CurrentUser;
import com.guanshaoye.glglteacher.utils.LoadingDialog;
import com.guanshaoye.mylibrary.api.MessageApi;
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
 * Created by karl on 2017/6/3.
 */

public class ShowMsgActivity extends BaseActivity {
    @Bind(R.id.normal_title)
    TextView normalTitle;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.refreshLayout)
    TwinklingRefreshLayout refreshLayout;
    private ShowMessageAdapter adapter;
    private List<MessageInfoBean> list = new ArrayList<>();
    private int page = 1;
    private int classId ;
    @Override
    public void init() {
        setContentView(R.layout.show_msginfo_layout);
        normalTitle.setText("详情");
        initView();
    }

    private void initView() {

        classId = getIntent().getIntExtra("classId",0);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ShowMessageAdapter(getActivity());
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
                        getMsgList();
                        refreshLayout.finishRefreshing();
                    }
                }, 1500);
            }

            @Override
            public void onLoadMore(final TwinklingRefreshLayout refreshLayout) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getMsgList();
                        refreshLayout.finishLoadmore();
                    }
                }, 1500);
            }
        });

        LoadingDialog.ShowLoading(getActivity());
        getMsgList();
    }

    private void getMsgList(){
        UserBean userInfoBean = CurrentUser.getUser();
        if (userInfoBean == null){
            return;
        }

        MessageApi.getMsgList(userInfoBean.getTid(), classId, page, new RequestBack() {
            @Override
            public void onComplete(FlpBack back) {
                LoadingDialog.DissLoading(getActivity());
                if (back.errorCode == 200){
                    if (page == 1){
                        list.clear();
                    }
                    List<MessageInfoBean> messageList = JSON.parseArray(back.data,MessageInfoBean.class);
                    if (messageList != null){
                        list.addAll(messageList);
                        for (int i = 0;i< list.size();i++){
                            list.get(i).setPosition(i);
                        }

                        adapter.setDataList(list);

                        page ++;
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
