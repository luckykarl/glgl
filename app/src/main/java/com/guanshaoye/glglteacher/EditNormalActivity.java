package com.guanshaoye.glglteacher;

import android.content.Intent;
import android.os.Bundle;
import android.text.Selection;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.guanshaoye.glglteacher.bean.UserBean;
import com.guanshaoye.glglteacher.ui.mine.EditUserInfoActivity;
import com.guanshaoye.glglteacher.utils.CurrentUser;
import com.guanshaoye.glglteacher.utils.DL;
import com.guanshaoye.mylibrary.api.EditUserInfoApi;
import com.guanshaoye.mylibrary.base.BaseActivity;
import com.guanshaoye.mylibrary.http.FlpBack;
import com.guanshaoye.mylibrary.http.FlpException;
import com.guanshaoye.mylibrary.http.RequestBack;
import com.guanshaoye.mylibrary.utils.Toaster;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by karl on 2017/5/21.
 */

public class EditNormalActivity extends BaseActivity {
    @Bind(R.id.edit_txt)
    EditText editTxt;
    @Bind(R.id.normal_title)
    TextView normalTitle;
    @Bind(R.id.sure_btn)
    TextView sureBtn;

    private String title = "";
    private int resultCode ;
    private String key = "";
    private String content = "";
    @Override
    public void init() {
        setContentView(R.layout.editnormal_lay);
        sureBtn.setVisibility(View.VISIBLE);
        initdata();
    }

    private void initdata() {

        title = getIntent().getStringExtra("from_title");
        content = getIntent().getStringExtra("CONTENT");
        resultCode = getIntent().getIntExtra("RESULT_CODE",0);
        normalTitle.setText(title);
        if(TextUtils.isEmpty(content)){

            editTxt.setHint("请输入"+title);
        }else {
            editTxt.setText(content);
            editTxt.setSelection(content.length());
        }

        if(resultCode == EditUserInfoActivity.NAME_CODE){ //姓名
            key = DL.gsy_realname;
        }else if(resultCode == EditUserInfoActivity.POSITION_CODE){ //部门职位
            key = DL.gsy_role;
        }else if(resultCode == EditUserInfoActivity.CONTACT_CODE){ //联系方式
            key = DL.gsy_mobile;
        }else if(resultCode == EditUserInfoActivity.MAIL_CODE){//邮箱
            key = DL.gsy_email;
        }else if(resultCode == EditUserInfoActivity.LIVE_ADDRESS_CODE){//居住地址
            key = DL.gsy_address;
        }else if(resultCode == EditUserInfoActivity.EMERGENCY_CONTACT_CODE){//紧急联系人
            key = DL.gsy_emergency_contact_name;
        }else if(resultCode == EditUserInfoActivity.EMERGENCY_CONTACT_MODE_CODE){//紧急联系人方式
            key = DL.gsy_emergency_contact_mobi;
        }else if(resultCode == EditUserInfoActivity.PLACE_OF_ORIGN){//籍贯
            key = DL.gsy_native_place;
        }else if(resultCode == EditUserInfoActivity.NATION_CODE){//民族
            key = DL.gsy_nation;
        }else if(resultCode == EditUserInfoActivity.ID_CARD_CODE){//身份证号
            key = DL.gsy_idcard;
        }else if(resultCode == EditUserInfoActivity.HOME_ADDRESS_CODE){//家庭地址
            key = DL.gsy_home_address;
        }else if(resultCode == EditUserInfoActivity.EDUCATION_SCHOOL_CODE){//毕业院校
            key = DL.gsy_graduate_school;
        }


    }
    @OnClick(R.id.sure_btn)
    public void onSureClicked(){

        String input = editTxt.getText().toString();
        if(TextUtils.isEmpty(input)){
            Toaster.showToast(getResources().getString(R.string.empty_hint));
            return;
        }

        updateInfo(input);

    }

    private void updateInfo(String input){
        UserBean userBean = CurrentUser.getUser();
        if(userBean == null){
            return;
        }
        Map<String, Object> params = new HashMap<>();
        params.put(key, input);
        params.put("tid", userBean.getTid());

        EditUserInfoApi.updateUserInfo(params, new RequestBack() {
            @Override
            public void onComplete(FlpBack back) {
                if(back.errorCode == 200){
                    Intent intent = new Intent();
                    Bundle b = new Bundle();
                    b.putString("input_data",editTxt.getText().toString());
                    intent.putExtras(b);
                    setResult(resultCode, intent);
                    finish();
                }
                Toaster.showToast(back.message);

            }

            @Override
            public void onFlpException(FlpException e) {

            }
        });
    }


}
