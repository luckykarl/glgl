package com.guanshaoye.glglteacher.ui.mine.feedback;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.guanshaoye.glglteacher.R;
import com.guanshaoye.glglteacher.bean.FeedBackBean;
import com.guanshaoye.glglteacher.bean.UserBean;
import com.guanshaoye.glglteacher.listener.OnClickItemListener;
import com.guanshaoye.glglteacher.ui.IntentKey;
import com.guanshaoye.glglteacher.utils.CurrentUser;
import com.guanshaoye.glglteacher.utils.LoadingDialog;
import com.guanshaoye.mylibrary.api.ArticleApi;
import com.guanshaoye.mylibrary.base.BaseActivity;
import com.guanshaoye.mylibrary.http.BaseUrl;
import com.guanshaoye.mylibrary.http.FlpBack;
import com.guanshaoye.mylibrary.http.FlpException;
import com.guanshaoye.mylibrary.http.RequestBack;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by karl on 2017/6/3.
 */

public class FeedBackActivity extends BaseActivity implements OnClickItemListener {
    @Bind(R.id.normal_title)
    TextView normalTitle;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.click_btn)
    TextView clickBtn;
    private FeedBackAdapter adapter;

    private int key;
    @Override
    public void init() {
        setContentView(R.layout.feedback_layout);
        normalTitle.setText("帮助和反馈");
        initView();
    }

    private void initView() {
        key = getIntent().getIntExtra("IntentKey",0);

        if (key == IntentKey.FeedBack){
            normalTitle.setText("帮助和反馈");
            clickBtn.setVisibility(View.VISIBLE);
        }else if (key == IntentKey.Raiders){
            normalTitle.setText("攻略");
            clickBtn.setVisibility(View.GONE);
        }


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new FeedBackAdapter(this);
        recyclerView.setAdapter(adapter);

        getArticleList();
    }

    private void getArticleList() {
        UserBean userBean = CurrentUser.getUser();
        if (userBean == null) {
            return;
        }
        LoadingDialog.ShowLoading(getActivity());
        ArticleApi.getArticleList(userBean.getTid(), key, new RequestBack() {
            @Override
            public void onComplete(FlpBack back) {
                LoadingDialog.DissLoading(getActivity());
                if (back.errorCode == 200) {
                    List<FeedBackBean> beanList = JSON.parseArray(back.data, FeedBackBean.class);
                    if (beanList != null) {
                        adapter.setDataList(beanList);
                    }
                }
            }

            @Override
            public void onFlpException(FlpException e) {
                LoadingDialog.DissLoading(getActivity());
            }
        });
    }

    @Override
    public void onItemClick(int id) {
        startActivityForNoResult(new Intent(getActivity(),WebViewActivity.class).putExtra("WEB_URL", BaseUrl.WEB_URL+id));
    }

    @OnClick(R.id.click_btn)
    public void submitBtn(){
        startActivityForNoResult(new Intent(getActivity(),FeedSubmitActivity.class));
    }


}
