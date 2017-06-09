package com.guanshaoye.glglteacher.ui.mine.setting;

import android.provider.Settings;
import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.guanshaoye.glglteacher.R;
import com.guanshaoye.glglteacher.bean.SettingBean;
import com.guanshaoye.glglteacher.bean.UserBean;
import com.guanshaoye.glglteacher.utils.CurrentUser;
import com.guanshaoye.glglteacher.utils.LoadingDialog;
import com.guanshaoye.mylibrary.api.SettingApi;
import com.guanshaoye.mylibrary.base.AtyContainer;
import com.guanshaoye.mylibrary.base.BaseActivity;
import com.guanshaoye.mylibrary.http.FlpBack;
import com.guanshaoye.mylibrary.http.FlpException;
import com.guanshaoye.mylibrary.http.RequestBack;
import com.guanshaoye.mylibrary.utils.Toaster;
import com.guanshaoye.mylibrary.view.ShSwitchView;

import butterknife.Bind;

/**
 * Created by ${张梦博} on 2017/5/17.
 * Whenever,Wherever,Whatever,I believe I can handle everything
 */
public class SettingActivity extends BaseActivity {
    @Bind(R.id.normal_title)
    TextView normalTitle;
    @Bind(R.id.switch_alert)
    ShSwitchView switchAlert;
    @Bind(R.id.switch_voice_vibrate)
    ShSwitchView switchVoiceVibrate;
    @Bind(R.id.switch_message)
    ShSwitchView switchMessage;
    private UserBean userBean;
    private boolean flag;
    @Override
    public void init() {
        setContentView(R.layout.activity_setting);
        normalTitle.setText("设置");

        userBean = CurrentUser.getUser();
        if (userBean == null){
            return;
        }
        switchAlert.setOnSwitchStateChangeListener(new ShSwitchView.OnSwitchStateChangeListener() {
            @Override
            public void onSwitchStateChange(boolean isOn) {
                if(flag)
                updateUserSetting("gsy_is_warn",isOn);
            }
        });
        switchVoiceVibrate.setOnSwitchStateChangeListener(new ShSwitchView.OnSwitchStateChangeListener() {
            @Override
            public void onSwitchStateChange(boolean isOn) {
                if(flag)
                updateUserSetting("gsy_sound_warn",isOn);
            }
        });
        switchMessage.setOnSwitchStateChangeListener(new ShSwitchView.OnSwitchStateChangeListener() {
            @Override
            public void onSwitchStateChange(boolean isOn) {
                if(flag)
                updateUserSetting("gsy_importance_warn",isOn);
            }
        });
        getUserSetting();
    }

    private void initView(SettingBean settingBean){
        switchAlert.setOn(settingBean.getGsy_is_warn() ==1 ? true:false);
        switchVoiceVibrate.setOn(settingBean.getGsy_sound_warn() ==1 ? true:false);
        switchMessage.setOn(settingBean.getGsy_importance_warn() ==1 ? true:false);
        flag =true;
    }
    /**
     * 获取用户设置信息
     */
    private void getUserSetting(){
        SettingApi.getUserSetting(userBean.getTid(), new RequestBack() {
            @Override
            public void onComplete(FlpBack back) {

                if(back.errorCode == 200){
                    SettingBean settingBean = JSON.parseObject(back.data,SettingBean.class);
                    if (settingBean !=null)
                    {
                        initView(settingBean);
                    }
                }
            }

            @Override
            public void onFlpException(FlpException e) {

            }
        });
    }
    public void loginOut(View v) {
        CurrentUser.cleanUser(getActivity());
        AtyContainer.getInstance().finishAllActivity();
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }
    /**
     * 更新设置信息
     * @param key
     * @param value
     */
    private void updateUserSetting(String key ,boolean value){
        LoadingDialog.ShowLoading(getActivity());
        SettingApi.updateUserSetting(userBean.getTid(),key , value?1:2, new RequestBack() {
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
