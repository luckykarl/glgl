package com.guanshaoye.glglteacher.ui.login;

import android.content.Intent;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.guanshaoye.glglteacher.R;
import com.guanshaoye.glglteacher.bean.SmsBean;
import com.guanshaoye.glglteacher.bean.UserBean;
import com.guanshaoye.glglteacher.ui.MainActivity;
import com.guanshaoye.glglteacher.ui.mine.EditUserInfoActivity;
import com.guanshaoye.glglteacher.utils.CurrentUser;
import com.guanshaoye.glglteacher.utils.LoadingDialog;
import com.guanshaoye.mylibrary.api.LoginApi;
import com.guanshaoye.mylibrary.base.BaseActivity;
import com.guanshaoye.mylibrary.http.FlpBack;
import com.guanshaoye.mylibrary.http.FlpException;
import com.guanshaoye.mylibrary.http.RequestBack;
import com.guanshaoye.mylibrary.utils.Toaster;

import java.util.HashSet;
import java.util.Set;

import butterknife.Bind;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;

/**
 * Created by ${张梦博} on 2017/5/11.
 * Whenever,Wherever,Whatever,I believe I can handle everything
 */
public class LoginActivity extends BaseActivity {
    @Bind(R.id.edit_tel)
    EditText editTel;
    @Bind(R.id.edit_checkCode)
    EditText editCheckCode;
    @Bind(R.id.btn_get_check_code)
    TextView btnGetCheckCode;
    @Bind(R.id.btn_login)
    TextView btnLogin;
    private boolean isFirstRegister;
    private TimeCount time;

    @Override
    public void init() {
        setContentView(R.layout.activity_login);
        time = new TimeCount(60000, 1000);

    }


    @OnClick({R.id.btn_get_check_code, R.id.btn_login})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_get_check_code:
                getSmsCode();
                break;
            case R.id.btn_login:
                String phone = editTel.getText().toString();
                String code = editCheckCode.getText().toString();

                if (checkPhone(phone) && checkCode(code)) {

                    if (isFirstRegister) {
                        loginRequest(phone, code);
                    } else {
                        registerRequest(phone, code);
                    }
                }
                break;
        }
    }


    /**
     * 发送验证码接口
     */
    private void sendSmsRequest() {
        LoadingDialog.ShowLoading(LoginActivity.this);
        LoginApi.sendSms(editTel.getText().toString(), -1, new RequestBack() {
            @Override
            public void onComplete(FlpBack back) {
                LoadingDialog.DissLoading(LoginActivity.this);
                if (back.errorCode == 200) {
                    time.start();
                    SmsBean smsBean = JSON.parseObject(back.data, SmsBean.class);
                    if (smsBean.getIs_reg() == 1) {
                        isFirstRegister = true;
                    } else {
                        isFirstRegister = false;
                    }
//                    setEditCode(smsBean.getCode() + "");
                }

                Toaster.showToast(back.message);

            }

            @Override
            public void onFlpException(FlpException e) {
                LoadingDialog.DissLoading(LoginActivity.this);
            }
        });
    }

    /**
     * 用户注册
     *
     * @param phone
     * @param code
     */
    private void registerRequest(String phone, String code) {
        LoadingDialog.ShowLoading(LoginActivity.this);
        LoginApi.userRegister(phone, code, new RequestBack() {
            @Override
            public void onComplete(FlpBack back) {
                LoadingDialog.DissLoading(LoginActivity.this);
                if (back.errorCode == 200) {
                    UserBean userBean = JSON.parseObject(back.data, UserBean.class);
                    setTags(userBean.getTid());
                    CurrentUser.setCurrentUser(userBean);
                    CurrentUser.setShare(getActivity(), userBean);
                    //界面跳转
                    startActivityForNoResult(new Intent(getActivity(), EditUserInfoActivity.class).putExtra("isRegister",true));
                    finish();
                }
                Toaster.showToast(back.message);


            }

            @Override
            public void onFlpException(FlpException e) {
                LoadingDialog.DissLoading(LoginActivity.this);
            }
        });
    }

    /**
     * 用户登录
     *
     * @param phone
     * @param code
     */
    private void loginRequest(String phone, String code) {
        LoadingDialog.ShowLoading(LoginActivity.this);
        LoginApi.userLogin(phone, code, new RequestBack() {
            @Override
            public void onComplete(FlpBack back) {
                LoadingDialog.DissLoading(LoginActivity.this);

                Toaster.showToast(back.message);
                if (back.errorCode == 200) {
                    UserBean userBean = JSON.parseObject(back.data, UserBean.class);
                    setTags(userBean.getTid());
                    CurrentUser.setCurrentUser(userBean);
                    CurrentUser.setShare(getActivity(), userBean);
                    //界面跳转
                    startActivityForNoResult(new Intent(getActivity(), MainActivity.class));
                    finish();
                }

            }

            @Override
            public void onFlpException(FlpException e) {
                LoadingDialog.DissLoading(LoginActivity.this);
            }
        });
    }

    private void getSmsCode() {
        String phone = editTel.getText().toString();
        if (checkPhone(phone)) {
            sendSmsRequest();
        }
    }

    private boolean checkPhone(String phone) {

        if (TextUtils.isEmpty(phone)) {
            Toaster.showToast(getResources().getString(R.string.phone_hint));
            return false;
        } else if (phone.length() < 11) {
            Toaster.showToast(getResources().getString(R.string.phone_txt_tip));
            return false;
        }
        return true;
    }

    private boolean checkCode(String smsCode) {
        if (TextUtils.isEmpty(smsCode)) {
            Toaster.showToast(getResources().getString(R.string.code_hint));
            return false;
        }
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        time.cancel();
    }

    class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);// 参数依次为总时长,和计时的时间间隔
        }

        @Override
        public void onFinish() {// 计时完毕时触发
            btnGetCheckCode.setText(getResources().getString(R.string.send_code));
            btnGetCheckCode.setClickable(true);
        }

        @Override
        public void onTick(long millisUntilFinished) {// 计时过程显示

            btnGetCheckCode.setClickable(false);
            btnGetCheckCode.setText(millisUntilFinished / 1000 + "s");
        }
    }

    private void setEditCode(String code) {
        editCheckCode.setText(code);
    }
    private void setTags(String uid){
        Set<String> sets = new HashSet<>();
        sets.add(uid);

        JPushInterface.setTags(this, sets, new TagAliasCallback() {
            @Override
            public void gotResult(int i, String s, Set<String> set) {
                Log.d("setTags", "set tag result is" + i);
            }
        });
    }

}
