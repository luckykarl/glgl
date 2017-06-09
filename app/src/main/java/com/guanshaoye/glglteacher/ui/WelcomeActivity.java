package com.guanshaoye.glglteacher.ui;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.guanshaoye.glglteacher.R;
import com.guanshaoye.glglteacher.service.UpdateService;
import com.guanshaoye.glglteacher.bean.CheckVesion;
import com.guanshaoye.glglteacher.bean.UserBean;
import com.guanshaoye.glglteacher.ui.login.LoginActivity;
import com.guanshaoye.glglteacher.utils.CurrentUser;
import com.guanshaoye.glglteacher.utils.SharedPreferencesUtils;
import com.guanshaoye.mylibrary.api.SettingApi;
import com.guanshaoye.mylibrary.base.BaseActivity;
import com.guanshaoye.mylibrary.http.FlpBack;
import com.guanshaoye.mylibrary.http.FlpException;
import com.guanshaoye.mylibrary.http.RequestBack;

import java.util.HashSet;
import java.util.Set;

import butterknife.Bind;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;

/**
 * Created by karl on 2017/6/5.
 */

public class WelcomeActivity extends BaseActivity {
    @Bind(R.id.txt_alert)
    TextView txtAlert;
    @Bind(R.id.tv_version)
    TextView tvVersion;
    @Bind(R.id.txt_version)
    TextView txtVersion;
    @Bind(R.id.rel_version)
    RelativeLayout relVersion;
    @Bind(R.id.tv_update_time)
    TextView tvUpdateTime;
    @Bind(R.id.txt_update_time)
    TextView txtUpdateTime;
    @Bind(R.id.rel_update_time)
    RelativeLayout relUpdateTime;
    @Bind(R.id.tv_size)
    TextView tvSize;
    @Bind(R.id.txt_size)
    TextView txtSize;
    @Bind(R.id.tv_update_content)
    TextView tvUpdateContent;
    @Bind(R.id.txt_update_content)
    TextView txtUpdateContent;
    @Bind(R.id.btn_at_once_update)
    TextView btnAtOnceUpdate;
    @Bind(R.id.up_lay)
    LinearLayout upLay;
    private UserBean userBean;
    private String upUrl = "";
    @Override
    public void init() {
        setContentView(R.layout.welcome_layout);
        isFirst();
    }

    private void  isFirst(){
        String isFirst = SharedPreferencesUtils.getValue(getActivity(),SharedPreferencesUtils.ISFIRST);
        if(TextUtils.isEmpty(isFirst)){
            startActivity(new Intent(getActivity(),ExampleGuideActivity.class));
            finish();
        }else{
            checkVesion();
        }

    }

    private void initView() {
        userBean = CurrentUser.getUser();
        Runnable r = new Runnable() {
            @Override
            public void run() {
                if (userBean != null && !TextUtils.isEmpty(userBean.getTid())) {
                    setTags(userBean.getTid());
                    startActivityForNoResult(new Intent(getActivity(), MainActivity.class));
                } else {
                    startActivityForNoResult(new Intent(getActivity(), LoginActivity.class));
                }
                finish();
            }
        };
        new Handler().postDelayed(r, 1000);
    }

    private void setTags(String uid) {
        Set<String> sets = new HashSet<>();
        sets.add(uid);

        JPushInterface.setTags(this, sets, new TagAliasCallback() {
            @Override
            public void gotResult(int i, String s, Set<String> set) {
                Log.d("setTags", "set tag result is" + i);
            }
        });
    }

    private void checkVesion() {
        SettingApi.checkCesionCode(new RequestBack() {
            @Override
            public void onComplete(FlpBack back) {
                if (back.errorCode == 200) {
                    CheckVesion vesion = JSON.parseObject(back.data, CheckVesion.class);
                    if (vesion != null) {
                        String reId = vesion.getGsy_cur_vesion_id().replace(".", "");
                        int newVesionId = Integer.parseInt(reId);
                        String pkName = getActivity().getPackageName();
                        try {
                            String versionName = getActivity().getPackageManager().getPackageInfo(pkName, 0).versionName;
                            int vId = Integer.parseInt(versionName.replace(".", ""));
                            if (newVesionId > vId) {
                                initVesionLay(vesion);
                            } else {
                                initView();
                            }

                        } catch (PackageManager.NameNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

            @Override
            public void onFlpException(FlpException e) {
                initView();
            }
        });
    }

    private void initVesionLay(CheckVesion vesion) {
        upLay.setVisibility(View.VISIBLE);
        txtVersion.setText(vesion.getGsy_cur_vesion_id());
        txtSize.setText(vesion.getGsy_size());
        txtUpdateTime.setText(vesion.getGsy_update_time());
        txtUpdateContent.setText(vesion.getGsy_content());
        upUrl = vesion.getGsy_url();
    }


    @OnClick(R.id.btn_at_once_update)
    public void UpDate() {
        Intent intent = new Intent(getActivity(), UpdateService.class);
        intent.putExtra("url",upUrl);
        startService(intent);
    }
}
