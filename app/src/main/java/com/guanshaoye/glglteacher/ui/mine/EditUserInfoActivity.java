package com.guanshaoye.glglteacher.ui.mine;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.sdk.android.oss.OSS;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSPlainTextAKSKCredentialProvider;
import com.guanshaoye.glglteacher.EditNormalActivity;
import com.guanshaoye.glglteacher.R;
import com.guanshaoye.glglteacher.adapter.OnItemClickListener;
import com.guanshaoye.glglteacher.bean.AttendClassBean;
import com.guanshaoye.glglteacher.bean.ImageBean;
import com.guanshaoye.glglteacher.bean.ImageSucessBean;
import com.guanshaoye.glglteacher.bean.ItemListBean;
import com.guanshaoye.glglteacher.bean.UserBean;
import com.guanshaoye.glglteacher.bean.UserInfoBean;
import com.guanshaoye.glglteacher.ui.authentication.AuthenticationAvtivity;
import com.guanshaoye.glglteacher.utils.CurrentUser;
import com.guanshaoye.glglteacher.utils.DL;
import com.guanshaoye.glglteacher.utils.LoadingDialog;
import com.guanshaoye.glglteacher.utils.OSSConfigure;
import com.guanshaoye.glglteacher.utils.PicassoUtils;
import com.guanshaoye.glglteacher.utils.TakePhotoUtils;
import com.guanshaoye.glglteacher.utils.aliyun.PutObjectSamples;
import com.guanshaoye.glglteacher.utils.view.ActionSheetDialog;
import com.guanshaoye.glglteacher.widget.DialogUtils;
import com.guanshaoye.mylibrary.api.EditUserInfoApi;
import com.guanshaoye.mylibrary.api.UpLoadApi;
import com.guanshaoye.mylibrary.base.BaseActivity;
import com.guanshaoye.mylibrary.http.BaseUrl;
import com.guanshaoye.mylibrary.http.FlpBack;
import com.guanshaoye.mylibrary.http.FlpException;
import com.guanshaoye.mylibrary.http.RequestBack;
import com.guanshaoye.mylibrary.utils.CheckPermission;
import com.guanshaoye.mylibrary.utils.TakePhotoUpload;
import com.guanshaoye.mylibrary.utils.Toaster;

import org.feezu.liuli.timeselector.TimeSelector;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by karl on 2017/5/25.
 * 个人信息
 */

public class EditUserInfoActivity extends BaseActivity {
    @Bind(R.id.tv_name)
    TextView tvName;
    @Bind(R.id.tv_sex)
    TextView tvSex;
    @Bind(R.id.tv_job)
    TextView tvJob;
    @Bind(R.id.tv_tel)
    TextView tvTel;
    @Bind(R.id.tv_mail)
    TextView tvMail;
    @Bind(R.id.tv_live_address)
    TextView tvLiveAddress;
    @Bind(R.id.tv_urgent_person)
    TextView tvUrgentPerson;
    @Bind(R.id.tv_urgent_person_tel)
    TextView tvUrgentPersonTel;
    @Bind(R.id.tv_birthday)
    TextView tvBirthday;
    @Bind(R.id.tv_native_place)
    TextView tvNativePlace;
    @Bind(R.id.tv_nation)
    TextView tvNation;
    @Bind(R.id.tv_id_card)
    TextView tvIdCard;
    @Bind(R.id.tv_marriage_condition)
    TextView tvMarriageCondition;
    @Bind(R.id.tv_have_children)
    TextView tvHaveChildren;
    @Bind(R.id.tv_family_address)
    TextView tvFamilyAddress;
    @Bind(R.id.tv_education)
    TextView tvEducation;
    @Bind(R.id.tv_graduate_school)
    TextView tvGraduateSchool;
    @Bind(R.id.tv_graduate_date)
    TextView tvGraduateDate;
    @Bind(R.id.tv_arrive_job_date)
    TextView tvArriveJobDate;
    @Bind(R.id.tv_name_lay)
    RelativeLayout tvNameLay;
    @Bind(R.id.tv_sex_lay)
    RelativeLayout tvSexLay;
    @Bind(R.id.tv_job_lay)
    RelativeLayout tvJobLay;
    @Bind(R.id.tv_tel_lay)
    RelativeLayout tvTelLay;
    @Bind(R.id.tv_mail_lay)
    RelativeLayout tvMailLay;
    @Bind(R.id.tv_live_address_lay)
    RelativeLayout tvLiveAddressLay;
    @Bind(R.id.tv_urgent_person_lay)
    RelativeLayout tvUrgentPersonLay;
    @Bind(R.id.tv_urgent_person_tel_lay)
    RelativeLayout tvUrgentPersonTelLay;
    @Bind(R.id.tv_birthday_lay)
    RelativeLayout tvBirthdayLay;
    @Bind(R.id.tv_native_place_lay)
    RelativeLayout tvNativePlaceLay;
    @Bind(R.id.tv_nation_lay)
    RelativeLayout tvNationLay;
    @Bind(R.id.tv_id_card_lay)
    RelativeLayout tvIdCardLay;
    @Bind(R.id.tv_marriage_condition_lay)
    RelativeLayout tvMarriageConditionLay;
    @Bind(R.id.tv_have_children_lay)
    RelativeLayout tvHaveChildrenLay;
    @Bind(R.id.tv_family_address_lay)
    RelativeLayout tvFamilyAddressLay;
    @Bind(R.id.tv_education_lay)
    RelativeLayout tvEducationLay;
    @Bind(R.id.tv_graduate_school_lay)
    RelativeLayout tvGraduateSchoolLay;
    @Bind(R.id.tv_graduate_date_lay)
    RelativeLayout tvGraduateDateLay;
    @Bind(R.id.tv_arrive_job_date_lay)
    RelativeLayout tvArriveJobDateLay;
    @Bind(R.id.photo_img)
    ImageView photoImg;
    @Bind(R.id.normal_title)
    TextView normalTitle;
    @Bind(R.id.submit_btn)
    TextView submitBtn;

    private Bitmap mBitmap;
    private List<ItemListBean> list = new ArrayList<>();
    private Dialog myDialog;
    public static final int NAME_CODE = 100;
    public static final int POSITION_CODE = 101;
    public static final int CONTACT_CODE = 102;
    public static final int MAIL_CODE = 103;
    public static final int LIVE_ADDRESS_CODE = 104;
    public static final int EMERGENCY_CONTACT_CODE = 105;
    public static final int EMERGENCY_CONTACT_MODE_CODE = 106;
    public static final int PLACE_OF_ORIGN = 107;
    public static final int NATION_CODE = 108;
    public static final int ID_CARD_CODE = 109;
    public static final int HOME_ADDRESS_CODE = 110;
    public static final int EDUCATION_CODE = 111;
    public static final int EDUCATION_SCHOOL_CODE = 112;
    public static final int UPDATE_GENDER = 113;
    public static final int UPDATE_ISMARRY = 114;
    public static final int UPDATE_CHILDREN = 115;
    public static final int UPDATE_BIRTHDAY = 116;
    public static final int UPDATE_GRADUATE_DATE = 117;
    public static final int UPDATE_HIRE_DATE = 118;

    private UserBean userBean;
    private boolean isRegister;
    private OSS oss;
    @Override
    public void init() {
        setContentView(R.layout.edituserinfo_layout);
        normalTitle.setText("个人信息");

        List<ItemListBean> la = JSON.parseArray(BaseUrl.GRADUATE_DATE, ItemListBean.class);
        if (la != null) {
            list.addAll(la);
            myDialog = DialogUtils.shaoDateDialog(getActivity(), list, itemClickListener);
        }

        isRegister = getIntent().getBooleanExtra("isRegister", false);
        userBean = CurrentUser.getUser();
        tvTel.setText(userBean.getMobile());
        OSSCredentialProvider credentialProvider = new OSSPlainTextAKSKCredentialProvider(OSSConfigure.accessKeyId, OSSConfigure.accessKeySecret);
        oss = new OSSClient(getActivity(), OSSConfigure.endpoint, credentialProvider);
        if (isRegister) {
            submitBtn.setVisibility(View.VISIBLE);
        }
        getUserInfo();

    }

    private void getUserInfo() {
        UserBean userBean = CurrentUser.getUser();
        if (userBean == null) {
            return;
        }
        LoadingDialog.ShowLoading(getActivity());
        EditUserInfoApi.getUserInfo(userBean.getTid(), new RequestBack() {
            @Override
            public void onComplete(FlpBack back) {
                LoadingDialog.DissLoading(getActivity());
                if (back.errorCode == 200) {
                    UserInfoBean muserInfo = JSON.parseObject(back.data, UserInfoBean.class);
                    if (muserInfo != null) {
                        initData(muserInfo);
                    }
                }
            }

            @Override
            public void onFlpException(FlpException e) {
                LoadingDialog.DissLoading(getActivity());
            }
        });
    }

    private void initData(UserInfoBean userInfo) {

        PicassoUtils.showPersionImg(getActivity(), userInfo.getGsy_portrait(), photoImg);
        tvName.setText(userInfo.getGsy_realname());
        int gender = userInfo.getGsy_gender();
        if (gender == 0) {
            tvSex.setText("");
        } else if (gender == 1) {
            tvSex.setText("男");
        } else if (gender == 2) {
            tvSex.setText("女");
        }
        tvJob.setText(userInfo.getGsy_role_name());
        tvTel.setText(userInfo.getGsy_mobile());
        tvMail.setText(userInfo.getGsy_email());
        tvLiveAddress.setText(userInfo.getGsy_address());
        tvUrgentPerson.setText(userInfo.getGsy_emergency_contact_name());
        tvUrgentPersonTel.setText(userInfo.getGsy_emergency_contact_mobi());
        tvBirthday.setText(userInfo.getGsy_birthdate());
        tvNativePlace.setText(userInfo.getGsy_native_place());
        tvNation.setText(userInfo.getGsy_nation());
        tvIdCard.setText(userInfo.getGsy_idcard());

        int isMarry = userInfo.getGsy_is_marry();
        if (isMarry == 0) {
            tvMarriageCondition.setText("");
        } else if (isMarry == 1) {
            tvMarriageCondition.setText("已婚");
        } else if (isMarry == 2) {
            tvMarriageCondition.setText("未婚");
        }

        int haceChildren = userInfo.getGsy_have_children();
        if (haceChildren == 0) {
            tvHaveChildren.setText("");
        } else if (haceChildren == 1) {
            tvHaveChildren.setText("有");
        } else if (haceChildren == 2) {
            tvHaveChildren.setText("无");
        }

        tvFamilyAddress.setText(userInfo.getGsy_home_address());

        int educationLevel = userInfo.getGsy_education_level();
        if (educationLevel == 0) {
            tvEducation.setText("");
        } else {
            tvEducation.setText(getGraduate(educationLevel));
        }
        tvGraduateSchool.setText(userInfo.getGsy_graduate_school());
        tvGraduateDate.setText(userInfo.getGsy_graduate_date());
        tvArriveJobDate.setText(userInfo.getGsy_hiredate());
    }

    @OnClick({R.id.submit_btn,R.id.photo_img, R.id.tv_name_lay, R.id.tv_sex_lay, R.id.tv_job_lay, R.id.tv_tel_lay, R.id.tv_mail_lay, R.id.tv_live_address_lay, R.id.tv_urgent_person_lay,
            R.id.tv_urgent_person_tel_lay, R.id.tv_birthday_lay, R.id.tv_native_place_lay, R.id.tv_nation_lay, R.id.tv_id_card_lay, R.id.tv_marriage_condition_lay,
            R.id.tv_have_children_lay, R.id.tv_family_address_lay, R.id.tv_education_lay, R.id.tv_graduate_school_lay, R.id.tv_graduate_date_lay, R.id.tv_arrive_job_date_lay})
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.submit_btn:
                if (TextUtils.isEmpty(tvName.getText().toString())){
                    Toaster.showToast("请填写姓名");
                    return;
                }
                if (TextUtils.isEmpty(tvSex.getText().toString())){
                    Toaster.showToast("请选择性别");
                    return;
                }
                if (TextUtils.isEmpty(tvIdCard.getText().toString())){
                    Toaster.showToast("请填写身份证号");
                    return;
                }
                startActivityForNoResult(new Intent(getActivity(), AuthenticationAvtivity.class));
                finish();
                break;
            case R.id.photo_img:
                takePhoto();
                break;
            case R.id.tv_name_lay:
                if (TextUtils.isEmpty(tvName.getText().toString())) {
                    intent.setClass(getActivity(), EditNormalActivity.class);
                    intent.putExtra("from_title", getResources().getString(R.string.name_txt));
//                    intent.putExtra("CONTENT", tvName.getText());
                    intent.putExtra("RESULT_CODE", NAME_CODE);
                    startActivityForResult(intent, NAME_CODE);
                }
                break;
            case R.id.tv_sex_lay:
                if (TextUtils.isEmpty(tvSex.getText().toString())) {
                    showSexSelect(UPDATE_GENDER);
                }
                break;
            case R.id.tv_job_lay:
//                intent.setClass(getActivity(), EditNormalActivity.class);
//                intent.putExtra("from_title", getResources().getString(R.string.position_txt));
//                intent.putExtra("CONTENT", tvJob.getText());
//                intent.putExtra("RESULT_CODE", POSITION_CODE);
//                startActivityForResult(intent, POSITION_CODE);
                break;
            case R.id.tv_tel_lay:
//                intent.setClass(getActivity(), EditNormalActivity.class);
//                intent.putExtra("from_title", getResources().getString(R.string.contact_txt));
//                intent.putExtra("CONTENT", tvTel.getText());
//                intent.putExtra("RESULT_CODE", CONTACT_CODE);
//                startActivityForResult(intent, CONTACT_CODE);
                break;
            case R.id.tv_mail_lay:
                intent.setClass(getActivity(), EditNormalActivity.class);
                intent.putExtra("from_title", getResources().getString(R.string.mail_txt));
                intent.putExtra("CONTENT", tvMail.getText());
                intent.putExtra("RESULT_CODE", MAIL_CODE);
                startActivityForResult(intent, MAIL_CODE);
                break;
            case R.id.tv_live_address_lay:
                intent.setClass(getActivity(), EditNormalActivity.class);
                intent.putExtra("from_title", getResources().getString(R.string.live_address_txt));
                intent.putExtra("CONTENT", tvLiveAddress.getText());
                intent.putExtra("RESULT_CODE", LIVE_ADDRESS_CODE);
                startActivityForResult(intent, LIVE_ADDRESS_CODE);
                break;
            case R.id.tv_urgent_person_lay:
                intent.setClass(getActivity(), EditNormalActivity.class);
                intent.putExtra("from_title", getResources().getString(R.string.emergency_contact_txt));
                intent.putExtra("CONTENT", tvUrgentPerson.getText());
                intent.putExtra("RESULT_CODE", EMERGENCY_CONTACT_CODE);
                startActivityForResult(intent, EMERGENCY_CONTACT_CODE);
                break;
            case R.id.tv_urgent_person_tel_lay:
                intent.setClass(getActivity(), EditNormalActivity.class);
                intent.putExtra("from_title", getResources().getString(R.string.emergency_contact_mode_txt));
                intent.putExtra("CONTENT", tvUrgentPersonTel.getText());
                intent.putExtra("RESULT_CODE", EMERGENCY_CONTACT_MODE_CODE);
                startActivityForResult(intent, EMERGENCY_CONTACT_MODE_CODE);
                break;
            case R.id.tv_birthday_lay:
                changeDateTime(UPDATE_BIRTHDAY);
                break;
            case R.id.tv_native_place_lay:
                intent.setClass(getActivity(), EditNormalActivity.class);
                intent.putExtra("from_title", getResources().getString(R.string.palce_of_orign_txt));
                intent.putExtra("CONTENT", tvNativePlace.getText());
                intent.putExtra("RESULT_CODE", PLACE_OF_ORIGN);
                startActivityForResult(intent, PLACE_OF_ORIGN);
                break;
            case R.id.tv_nation_lay:
                intent.setClass(getActivity(), EditNormalActivity.class);
                intent.putExtra("from_title", getResources().getString(R.string.nation_txt));
                intent.putExtra("CONTENT", tvNation.getText());
                intent.putExtra("RESULT_CODE", NATION_CODE);
                startActivityForResult(intent, NATION_CODE);
                break;
            case R.id.tv_id_card_lay:
                if (TextUtils.isEmpty(tvIdCard.getText().toString())) {
                    intent.setClass(getActivity(), EditNormalActivity.class);
                    intent.putExtra("from_title", getResources().getString(R.string.id_txt));
                    intent.putExtra("RESULT_CODE", ID_CARD_CODE);
                    startActivityForResult(intent, ID_CARD_CODE);
                }

                break;
            case R.id.tv_marriage_condition_lay:
                showSexSelect(UPDATE_ISMARRY);
                break;
            case R.id.tv_have_children_lay:
                showSexSelect(UPDATE_CHILDREN);
                break;
            case R.id.tv_family_address_lay:
                intent.setClass(getActivity(), EditNormalActivity.class);
                intent.putExtra("from_title", getResources().getString(R.string.home_address_txt));
                intent.putExtra("CONTENT", tvFamilyAddress.getText());
                intent.putExtra("RESULT_CODE", HOME_ADDRESS_CODE);
                startActivityForResult(intent, HOME_ADDRESS_CODE);
                break;
            case R.id.tv_education_lay:
                showEducaitonLevel();
                break;
            case R.id.tv_graduate_school_lay:
                intent.setClass(getActivity(), EditNormalActivity.class);
                intent.putExtra("from_title", getResources().getString(R.string.graduation_school_txt));
                intent.putExtra("CONTENT", tvGraduateSchool.getText());
                intent.putExtra("RESULT_CODE", EDUCATION_SCHOOL_CODE);
                startActivityForResult(intent, EDUCATION_SCHOOL_CODE);
                break;
            case R.id.tv_graduate_date_lay:
                changeDateTime(UPDATE_GRADUATE_DATE);
                break;
            case R.id.tv_arrive_job_date_lay:
                changeDateTime(UPDATE_HIRE_DATE);
                break;
        }
    }

    private void takePhoto() {
        ActionSheetDialog actionSheetDialog = new ActionSheetDialog(getActivity());

        actionSheetDialog.builder();
        actionSheetDialog.setCancelable(true);
        actionSheetDialog.setCanceledOnTouchOutside(true);
        actionSheetDialog.addSheetItem("拍照", ActionSheetDialog.SheetItemColor.Lan,
                new ActionSheetDialog.OnSheetItemClickListener() {

                    @Override
                    public void onClick(int which) {
                        TakePhotoUtils.takePhoto(EditUserInfoActivity.this);
                    }
                });
        actionSheetDialog.addSheetItem("从相册获取", ActionSheetDialog.SheetItemColor.Lan,
                new ActionSheetDialog.OnSheetItemClickListener() {

                    @Override
                    public void onClick(int which) {
                        TakePhotoUtils.requestOpenPicture(EditUserInfoActivity.this);

                    }
                });

        actionSheetDialog.show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case TakePhotoUtils.CHECKPHOTO7:
                // 判断手机系统版本号
                if (Build.VERSION.SDK_INT >= 19) {
                    // 4.4及以上系统使用这个方法处理图片
                    TakePhotoUtils.handleImageOnKitKat(EditUserInfoActivity.this,data);
                } else {
                    // 4.4以下系统使用这个方法处理图片
                    TakePhotoUtils.handleImageBeforeKitKat(EditUserInfoActivity.this,data);
                }
                break;
            case TakePhotoUtils.CROPPHOTO7:
                try {
                    if (resultCode == RESULT_OK) {
                        mBitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(Uri.fromFile(new File(TakePhotoUtils.cachPath))));
                        upLoadPicture(TakePhotoUtils.cachPath);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;

            case TakePhotoUtils.TAKEPHOTO7:
                try {
                    // 将拍摄的照片显示出来
                    TakePhotoUtils.startPhotoZoom(EditUserInfoActivity.this,new File(TakePhotoUtils.mPath+".jpg"), 350);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case NAME_CODE:
                if (data != null) {
                    String name = data.getExtras().getString("input_data");
                    tvName.setText(name);
//                    userInfo.setGsy_realname(name);
                }
                break;
            case POSITION_CODE:
                if (data != null) {
                    String name = data.getExtras().getString("input_data");
                    tvJob.setText(name);
//                    userInfo.setGsy_role_name(name);
                }
                break;
            case CONTACT_CODE:
                if (data != null) {
                    String name = data.getExtras().getString("input_data");
                    tvTel.setText(name);
//                    userInfo.setGsy_mobile(name);

                }
                break;
            case MAIL_CODE:
                if (data != null) {
                    String name = data.getExtras().getString("input_data");
                    tvMail.setText(name);
//                    userInfo.setGsy_email(name);
                }
                break;
            case LIVE_ADDRESS_CODE:
                if (data != null) {
                    String name = data.getExtras().getString("input_data");
                    tvLiveAddress.setText(name);
//                    userInfo.setGsy_address(name);
                }
                break;
            case EMERGENCY_CONTACT_CODE:
                if (data != null) {
                    String name = data.getExtras().getString("input_data");
                    tvUrgentPerson.setText(name);
//                    userInfo.setGsy_emergency_contact_name(name);
                }
                break;
            case EMERGENCY_CONTACT_MODE_CODE:
                if (data != null) {
                    String name = data.getExtras().getString("input_data");
                    tvUrgentPersonTel.setText(name);
//                    userInfo.setGsy_emergency_contact_mobi(name);
                }
                break;
            case PLACE_OF_ORIGN:
                if (data != null) {
                    String name = data.getExtras().getString("input_data");
                    tvNativePlace.setText(name);
//                    userInfo.setGsy_native_place(name);
                }
                break;
            case NATION_CODE:
                if (data != null) {
                    String name = data.getExtras().getString("input_data");
                    tvNation.setText(name);
//                    userInfo.setGsy_nation(name);
                }
                break;
            case ID_CARD_CODE:
                if (data != null) {
                    String name = data.getExtras().getString("input_data");
                    tvIdCard.setText(name);
//                    userInfo.setGsy_idcard(name);
                }
                break;
            case HOME_ADDRESS_CODE:
                if (data != null) {
                    String name = data.getExtras().getString("input_data");
                    tvFamilyAddress.setText(name);
//                    userInfo.setGsy_home_address(name);
                }
                break;
            case EDUCATION_SCHOOL_CODE:
                if (data != null) {
                    String name = data.getExtras().getString("input_data");
                    tvGraduateSchool.setText(name);
//                    userInfo.setGsy_graduate_school(name);
                }
                break;

        }
    }

    /**
     * 修改性别
     */
    private void showSexSelect(final int type) {
        String title1 = "";
        String title2 = "";
        if (type == UPDATE_GENDER) {
            title1 = "男";
            title2 = "女";
        } else if (type == UPDATE_ISMARRY) {
            title1 = "已婚";
            title2 = "未婚";
        } else if (type == UPDATE_CHILDREN) {
            title1 = "有";
            title2 = "无";
        }

        ActionSheetDialog actionSheetDialog = new ActionSheetDialog(getActivity());

        actionSheetDialog.builder();
        actionSheetDialog.setCancelable(true);
        actionSheetDialog.setCanceledOnTouchOutside(true);

        actionSheetDialog.addSheetItem(title1, ActionSheetDialog.SheetItemColor.Lan,
                new ActionSheetDialog.OnSheetItemClickListener() {

                    @Override
                    public void onClick(int which) {
                        updateInfo(type, "1");
                    }
                });
        actionSheetDialog.addSheetItem(title2, ActionSheetDialog.SheetItemColor.Lan,
                new ActionSheetDialog.OnSheetItemClickListener() {

                    @Override
                    public void onClick(int which) {
                        updateInfo(type, "2");
                    }
                });
        actionSheetDialog.show();
    }

    private void updateInfo(final int type, final String value) {
        LoadingDialog.ShowLoading(getActivity());
        String key = "";
        Map<String, Object> params = new HashMap<>();
        if (type == UPDATE_GENDER) {
            key = DL.gsy_gender;
        } else if (type == UPDATE_ISMARRY) {
            key = DL.gsy_is_marry;
        } else if (type == UPDATE_CHILDREN) {
            key = DL.gsy_have_children;
        } else if (type == UPDATE_BIRTHDAY) {
            key = DL.gsy_birthdate;
        } else if (type == UPDATE_GRADUATE_DATE) {
            key = DL.gsy_graduate_date;
        } else if (type == UPDATE_HIRE_DATE) {
            key = DL.gsy_hiredate;
        } else if (type == EDUCATION_CODE) {
            key = DL.gsy_education_level;
        }

        params.put(key, value + "");
        params.put("tid", userBean.getTid());
        EditUserInfoApi.updateUserInfo(params, new RequestBack() {
            @Override
            public void onComplete(FlpBack back) {
                LoadingDialog.DissLoading(getActivity());
                if (back.errorCode == 200) {
                    if (type == UPDATE_GENDER) {
                        if (value.equals("1")) {
                            tvSex.setText("男");
                        } else if (value.equals("2")) {
                            tvSex.setText("女");
                        }
//                        userInfo.setGsy_gender(Integer.parseInt(value));
                    } else if (type == UPDATE_ISMARRY) {
                        if (value.equals("1")) {
                            tvMarriageCondition.setText("已婚");
                        } else if (value.equals("2")) {
                            tvMarriageCondition.setText("未婚");
                        }
//                        userInfo.setGsy_is_marry(Integer.parseInt(value));
                    } else if (type == UPDATE_CHILDREN) {
                        if (value.equals("1")) {
                            tvHaveChildren.setText("有");
                        } else if (value.equals("2")) {
                            tvHaveChildren.setText("无");
                        }
//                        userInfo.setGsy_have_children(Integer.parseInt(value));
                    } else if (type == UPDATE_BIRTHDAY) {
                        tvBirthday.setText(value);
//                        userInfo.setGsy_birthdate(value);
                    } else if (type == UPDATE_GRADUATE_DATE) {
                        tvGraduateDate.setText(value);
//                        userInfo.setGsy_graduate_date(value);
                    } else if (type == UPDATE_HIRE_DATE) {
                        tvArriveJobDate.setText(value);
//                        userInfo.setGsy_hiredate(value);
                    } else if (type == EDUCATION_CODE) {
                        tvEducation.setText(getGraduate(Integer.parseInt(value)));
//                        userInfo.setGsy_education_level(Integer.parseInt(value));

                    }
                }
                Toast.makeText(getActivity(), back.message, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFlpException(FlpException e) {
                LoadingDialog.DissLoading(getActivity());
            }
        });
    }

    /**
     * 上传头像
     *
     * @param pathname
     */
    private void upLoadPicture(String pathname) {
        String uploadObject = "android_pic"+System.currentTimeMillis() + ".png";
        String imageName = new PutObjectSamples(oss, OSSConfigure.bucketName, uploadObject, pathname).putObjectFromLocalFile();

        LoadingDialog.ShowLoading(getActivity());
        UpLoadApi.upLoadPicture(userBean.getTid(), imageName,  new RequestBack() {
            @Override
            public void onComplete(FlpBack back) {
                LoadingDialog.DissLoading(getActivity());

                Toaster.showToast(back.message);
                if (back.errorCode == 200) {
                    photoImg.setImageBitmap(mBitmap);
                    ImageSucessBean im = JSON.parseObject(back.data, ImageSucessBean.class);
//                    if (im != null)
//                        userInfo.setGsy_portrait(im.getPic_url());
                }
            }

            @Override
            public void onFlpException(FlpException e) {
                LoadingDialog.DissLoading(getActivity());
            }
        });
    }

    /**
     * 日期修改
     */
    private void changeDateTime(final int type) {
        TimeSelector timeSelector = new TimeSelector(getActivity(), new TimeSelector.ResultHandler() {
            @Override
            public void handle(String time) {
                updateInfo(type, time);
            }
        }, "1950-01-01", "2100-12-31");

        timeSelector.setMode(TimeSelector.MODE.YMD);//只显示 年月日
        timeSelector.show();
    }

    private void showEducaitonLevel() {
        if (list.size() == 0) {
            return;
        }
        myDialog.show();
    }

    OnItemClickListener itemClickListener = new OnItemClickListener() {
        @Override
        public void onItemClick(ItemListBean listBean) {
            myDialog.dismiss();
            if (listBean != null)
                updateInfo(EDUCATION_CODE, listBean.getDialog_id());
        }

        @Override
        public void onReviewItemClick() {

        }

        @Override
        public void onClickAttendClass(AttendClassBean attendClass) {

        }
    };

    private String getGraduate(int level) {
        if (level > list.size()) {
            return "";
        }
        return list.get(level - 1).getDialog_content();
    }

}


