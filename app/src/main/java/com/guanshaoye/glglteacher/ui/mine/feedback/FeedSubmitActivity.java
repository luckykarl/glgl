package com.guanshaoye.glglteacher.ui.mine.feedback;

import android.widget.EditText;
import android.widget.TextView;

import com.guanshaoye.glglteacher.R;
import com.guanshaoye.glglteacher.bean.UserBean;
import com.guanshaoye.glglteacher.utils.CurrentUser;
import com.guanshaoye.glglteacher.utils.LoadingDialog;
import com.guanshaoye.mylibrary.api.SettingApi;
import com.guanshaoye.mylibrary.base.BaseActivity;
import com.guanshaoye.mylibrary.http.FlpBack;
import com.guanshaoye.mylibrary.http.FlpException;
import com.guanshaoye.mylibrary.http.RequestBack;
import com.guanshaoye.mylibrary.utils.Toaster;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by ${张梦博} on 2017/5/17.
 * Whenever,Wherever,Whatever,I believe I can handle everything
 */

public class FeedSubmitActivity extends BaseActivity {
    @Bind(R.id.normal_title)
    TextView normalTitle;
    @Bind(R.id.btn_sure_submit)
    TextView btnSureSubmit;
    @Bind(R.id.edit_feedBack)
    EditText editFeedBack;

    @Override
    public void init() {
        setContentView(R.layout.activity_help_feedback);
        normalTitle.setText("意见反馈");
    }

    @OnClick(R.id.btn_sure_submit)
    public void onViewClicked() {
        String content = editFeedBack.getText().toString();
        if(content.length() == 0){
            Toaster.showToast("请输入反馈内容");
            return;
        }
        submitFeedBack(content);
    }

    private void submitFeedBack(String content){
        UserBean userBean = CurrentUser.getUser();
        if (userBean == null) {
            return;
        }
        LoadingDialog.ShowLoading(getActivity());
        SettingApi.submitFeedBack(userBean.getTid(), content, new RequestBack() {
            @Override
            public void onComplete(FlpBack back) {
                LoadingDialog.DissLoading(getActivity());
                Toaster.showToast(back.message);
                finish();
            }

            @Override
            public void onFlpException(FlpException e) {
                LoadingDialog.DissLoading(getActivity());
            }
        });
    }
}
