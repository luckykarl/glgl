package com.guanshaoye.glglteacher.ui.authentication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.guanshaoye.glglteacher.R;
import com.guanshaoye.glglteacher.bean.AuthInfoBean;
import com.guanshaoye.glglteacher.ui.mine.EditUserInfoActivity;
import com.guanshaoye.glglteacher.utils.CurrentUser;
import com.guanshaoye.glglteacher.utils.LoadingDialog;
import com.guanshaoye.mylibrary.api.AuthApi;
import com.guanshaoye.mylibrary.base.BaseActivity;
import com.guanshaoye.mylibrary.http.FlpBack;
import com.guanshaoye.mylibrary.http.FlpException;
import com.guanshaoye.mylibrary.http.RequestBack;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by karl on 2017/6/5.
 */

public class ShowAuthStatusActivity extends BaseActivity {
    @Bind(R.id.normal_title)
    TextView normalTitle;
    @Bind(R.id.tv_project)
    TextView tvProject;
    @Bind(R.id.tv_course)
    TextView tvCourse;
    @Bind(R.id.img_audit)
    ImageView imgAudit;
    @Bind(R.id.tv_info)
    TextView tvInfo;
    @Bind(R.id.submit_btn)
    TextView submitBtn;

    @Override
    public void init() {
        setContentView(R.layout.show_authstatus_layout);
        normalTitle.setText("认证信息");
        getAuthInfo();
    }

    private void initView( AuthInfoBean authInfo) {
        tvProject.setText(authInfo.getGsy_item_class_name());
        tvCourse.setText(authInfo.getGsy_course_class_name());
        int userStatus = authInfo.getGsy_status();
         if (userStatus == 1){
             imgAudit.setBackgroundResource(R.mipmap.auth_sucess);
        }else if (userStatus == 2){
            imgAudit.setBackgroundResource(R.drawable.auditing_icon);
        }else if (userStatus == -1){
             imgAudit.setBackgroundResource(R.mipmap.auth_fail);
             submitBtn.setVisibility(View.VISIBLE);
        }
        tvInfo.setText(authInfo.getGsy_note());
    }
    @OnClick(R.id.submit_btn)
    public void subMit(){
        startActivityForNoResult(new Intent(getActivity(),AuthenticationAvtivity.class));
        finish();
    }

    private void getAuthInfo(){
        LoadingDialog.ShowLoading(getActivity());
        AuthApi.getAuthInfo(CurrentUser.getUser().getTid(), new RequestBack() {
            @Override
            public void onComplete(FlpBack back) {
                LoadingDialog.DissLoading(getActivity());
                if (back.errorCode == 200){
                    AuthInfoBean authInfo = JSON.parseObject(back.data,AuthInfoBean.class);
                    if (authInfo != null)
                        initView(authInfo);
                }
            }

            @Override
            public void onFlpException(FlpException e) {
                LoadingDialog.DissLoading(getActivity());
            }
        });
    }
}
