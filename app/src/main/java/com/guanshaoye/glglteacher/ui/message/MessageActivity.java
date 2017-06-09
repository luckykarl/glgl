package com.guanshaoye.glglteacher.ui.message;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.guanshaoye.glglteacher.R;
import com.guanshaoye.glglteacher.bean.MessageBean;
import com.guanshaoye.glglteacher.bean.TakeClassLog;
import com.guanshaoye.glglteacher.bean.UserBean;
import com.guanshaoye.glglteacher.listener.OnClickItemListener;
import com.guanshaoye.glglteacher.utils.CurrentUser;
import com.guanshaoye.glglteacher.utils.LoadingDialog;
import com.guanshaoye.mylibrary.api.MessageApi;
import com.guanshaoye.mylibrary.base.BaseActivity;
import com.guanshaoye.mylibrary.http.FlpBack;
import com.guanshaoye.mylibrary.http.FlpException;
import com.guanshaoye.mylibrary.http.RequestBack;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by karl on 2017/6/3.
 */

public class MessageActivity extends BaseActivity implements OnClickItemListener{
    @Bind(R.id.normal_title)
    TextView normalTitle;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    private MessageAdapter adapter;
    private List<MessageBean> list = new ArrayList<>();

    @Override
    public void init() {
        setContentView(R.layout.message_lay);
        normalTitle.setText("消息");
        initView();
    }

    private void initView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MessageAdapter(getActivity(),this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getMsgDisply();
    }

    private void getMsgDisply(){
        UserBean user = CurrentUser.getUser();
        if (user == null){
            return;
        }
        LoadingDialog.ShowLoading(getActivity());
        MessageApi.getMsgDisply(user.getTid(), new RequestBack() {
            @Override
            public void onComplete(FlpBack back) {
                LoadingDialog.DissLoading(getActivity());
                if (back.errorCode == 200){
                    List<MessageBean> ms = JSON.parseArray(back.data,MessageBean.class);
                    if (ms != null){
                        list.clear();
                        list.addAll(ms);
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
    public void onItemClick(int gsy_class_id) {
        Intent intent = new Intent(getActivity(),ShowMsgActivity.class);
        intent.putExtra("classId",gsy_class_id);
        startActivityForNoResult(intent);

    }
}
