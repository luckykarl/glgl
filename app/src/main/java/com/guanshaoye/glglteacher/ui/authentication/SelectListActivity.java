package com.guanshaoye.glglteacher.ui.authentication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.guanshaoye.glglteacher.R;
import com.guanshaoye.glglteacher.bean.ContentBean;
import com.guanshaoye.glglteacher.listener.SelectListener;
import com.guanshaoye.glglteacher.ui.mine.feedback.FeedBackAdapter;
import com.guanshaoye.glglteacher.utils.CurrentUser;
import com.guanshaoye.glglteacher.utils.LoadingDialog;
import com.guanshaoye.mylibrary.api.AuthApi;
import com.guanshaoye.mylibrary.base.BaseActivity;
import com.guanshaoye.mylibrary.http.FlpBack;
import com.guanshaoye.mylibrary.http.FlpException;
import com.guanshaoye.mylibrary.http.RequestBack;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by karl on 2017/6/4.
 */

public class SelectListActivity extends BaseActivity implements SelectListener {
    @Bind(R.id.normal_title)
    TextView normalTitle;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;

    private SelectListAdapter adapter;
    private int code;
    private String selectId = "";
    @Override
    public void init() {
        setContentView(R.layout.select_list_layout);

        initView();
    }

    private void initView() {

        code = getIntent().getIntExtra("CODE",0);
        selectId = getIntent().getStringExtra("SELECT_ID");
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new SelectListAdapter(this);
        recyclerView.setAdapter(adapter);
        if(code == AuthenticationAvtivity.PRODECT_CODE){
            normalTitle.setText("项目");
            getProgectList();
        }else if(code == AuthenticationAvtivity.CLASS_CODE){
            normalTitle.setText("课程");
            getClassList();
        }


    }

    //获取项目分类接口
    private void getProgectList(){
        LoadingDialog.ShowLoading(getActivity());
        AuthApi.getProgectList(CurrentUser.getUser().getTid(), new RequestBack() {
            @Override
            public void onComplete(FlpBack back) {
                LoadingDialog.DissLoading(getActivity());
                if (back.errorCode == 200){
                    List<ContentBean> list = JSON.parseArray(back.data,ContentBean.class);
                    if(list != null){
                        adapter.setDataList(list);
                    }
                }
            }

            @Override
            public void onFlpException(FlpException e) {
                LoadingDialog.DissLoading(getActivity());
            }
        });
    }

    //获取课程分类接口
    private void getClassList(){
        LoadingDialog.ShowLoading(getActivity());
        AuthApi.getClassList(CurrentUser.getUser().getTid(), selectId, new RequestBack() {
            @Override
            public void onComplete(FlpBack back) {
                LoadingDialog.DissLoading(getActivity());
                if (back.errorCode == 200){
                    List<ContentBean> list = JSON.parseArray(back.data,ContentBean.class);
                    if(list != null){
                        adapter.setDataList(list);
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
    public void onItemClick(ContentBean bean) {
        Intent intent = new Intent();
        Bundle b = new Bundle();
        b.putString("Gsy_name",bean.getGsy_name());
        b.putString("Gsy_id",bean.getId());
        intent.putExtras(b);
        setResult(code, intent);
        finish();
    }
}
