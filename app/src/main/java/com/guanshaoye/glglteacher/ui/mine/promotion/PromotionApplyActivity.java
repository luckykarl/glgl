package com.guanshaoye.glglteacher.ui.mine.promotion;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.guanshaoye.glglteacher.R;
import com.guanshaoye.glglteacher.bean.UserBean;
import com.guanshaoye.glglteacher.utils.CurrentUser;
import com.guanshaoye.glglteacher.utils.LoadingDialog;
import com.guanshaoye.mylibrary.api.EditUserInfoApi;
import com.guanshaoye.mylibrary.base.BaseActivity;
import com.guanshaoye.mylibrary.http.FlpBack;
import com.guanshaoye.mylibrary.http.FlpException;
import com.guanshaoye.mylibrary.http.RequestBack;
import com.guanshaoye.mylibrary.utils.Toaster;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by ${张梦博} on 2017/5/16.
 * Whenever,Wherever,Whatever,I believe I can handle everything
 */

public class PromotionApplyActivity extends BaseActivity {
    @Bind(R.id.normal_title)
    TextView normalTitle;
    @Bind(R.id.btn_promote_log)
    TextView btnPromoteLog;
    @Bind(R.id.btn_promote_fee)
    TextView btnPromoteFee;
    @Bind(R.id.btn_sure_submit)
    TextView btnSureSubmit;

    @Override
    public void init() {
        setContentView(R.layout.activity_promotion_apply);
        normalTitle.setText("晋级申请");
    }



    @OnClick({R.id.btn_promote_log, R.id.btn_sure_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_promote_log:
                startActivityForNoResult(new Intent(getActivity(),PromotionLogActivity.class));
                break;
            case R.id.btn_sure_submit:
                addPromotion();
                break;
        }
    }

    private void addPromotion(){
        UserBean userBean = CurrentUser.getUser();
        if (userBean == null){
            return;
        }
        LoadingDialog.ShowLoading(getActivity());
        EditUserInfoApi.addPromotion(userBean.getTid(), new RequestBack() {
            @Override
            public void onComplete(FlpBack back) {
                LoadingDialog.DissLoading(getActivity());
                Toaster.showToast(back.message);
            }

            @Override
            public void onFlpException(FlpException e) {
                LoadingDialog.DissLoading(getActivity());
            }
        });
    }
}
