package com.guanshaoye.glglteacher.ui.mine;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.guanshaoye.glglteacher.R;
import com.guanshaoye.glglteacher.bean.UserBean;
import com.guanshaoye.glglteacher.ui.IntentKey;
import com.guanshaoye.glglteacher.ui.authentication.AuthenticationAvtivity;
import com.guanshaoye.glglteacher.ui.authentication.ShowAuthStatusActivity;
import com.guanshaoye.glglteacher.ui.mine.about.AboutActivity;
import com.guanshaoye.glglteacher.ui.mine.bonus.SalaryLogActivity;
import com.guanshaoye.glglteacher.ui.mine.feedback.FeedBackActivity;
import com.guanshaoye.glglteacher.ui.mine.promotion.PromotionApplyActivity;
import com.guanshaoye.glglteacher.ui.mine.promotion.PromotionLogActivity;
import com.guanshaoye.glglteacher.ui.mine.setting.SettingActivity;
import com.guanshaoye.glglteacher.ui.mine.tactless.TakeClassLogActivity;
import com.guanshaoye.glglteacher.utils.CurrentUser;
import com.guanshaoye.glglteacher.utils.LoadingDialog;
import com.guanshaoye.glglteacher.utils.PicassoUtils;
import com.guanshaoye.mylibrary.api.EditUserInfoApi;
import com.guanshaoye.mylibrary.base.BaseActivity;
import com.guanshaoye.mylibrary.http.FlpBack;
import com.guanshaoye.mylibrary.http.FlpException;
import com.guanshaoye.mylibrary.http.RequestBack;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ${张梦博} on 2017/5/12.
 * Whenever,Wherever,Whatever,I believe I can handle everything
 */

public class MeActivity extends BaseActivity {
    @Bind(R.id.img_edit)
    ImageView imgEdit;
    @Bind(R.id.img_head)
    ImageView imgHead;
    @Bind(R.id.tv_nickname)
    TextView tvNickname;
    @Bind(R.id.tv_complete_rate)
    TextView tvCompleteRate;
    @Bind(R.id.rel_comment)
    RelativeLayout relComment;
    @Bind(R.id.rel_take_class_log)
    RelativeLayout relTakeClassLog;
    @Bind(R.id.tv_commission_value)
    TextView tvCommissionValue;
    @Bind(R.id.rel_commission)
    RelativeLayout relCommission;
    @Bind(R.id.rel_invest)
    RelativeLayout relInvest;
    @Bind(R.id.tv_dividend_value)
    TextView tvDividendValue;
    @Bind(R.id.rel_dividend)
    RelativeLayout relDividend;
    @Bind(R.id.rel_notice)
    RelativeLayout relNotice;
    @Bind(R.id.rel_help_feedback)
    RelativeLayout relHelpFeedback;
    @Bind(R.id.rel_about)
    RelativeLayout relAbout;

    @Bind(R.id.btn_promote)
    TextView btnPromote;
    @Bind(R.id.level_Bar)
    ProgressBar levelBar;
    @Bind(R.id.level_Bar_txt)
    TextView levelBarTxt;
    @Bind(R.id.teach_course_Bar)
    ProgressBar teachCourseBar;
    @Bind(R.id.teach_course_Bar_txt)
    TextView teachCourseBarTxt;
    @Bind(R.id.comment_Bar)
    ProgressBar commentBar;
    @Bind(R.id.comment_Bar_txt)
    TextView commentBarTxt;
    @Bind(R.id.train_course_Bar)
    ProgressBar trainCourseBar;
    @Bind(R.id.train_course_Bar_txt)
    TextView trainCourseBarTxt;
    @Bind(R.id.take_class_history_lay)
    RelativeLayout takeClassHistoryLay;
    @Bind(R.id.normal_title)
    TextView normalTitle;
    @Bind(R.id.btn_raiders)
    TextView btnRaiders;
    @Bind(R.id.aucher_status)
    TextView aucherStatus;
    @Bind(R.id.tv_classname)
    TextView tvClassname;

    private UserBean userInfo;

    @Override
    public void init() {
        setContentView(R.layout.activity_me);
        normalTitle.setText("");

    }

    @Override
    protected void onResume() {
        super.onResume();
        getUserInfo();
    }

    @OnClick({R.id.img_edit, R.id.rel_comment, R.id.rel_take_class_log, R.id.rel_commission,
            R.id.rel_invest, R.id.rel_dividend, R.id.rel_notice, R.id.rel_help_feedback,
            R.id.rel_about, R.id.btn_promote, R.id.take_class_history_lay, R.id.btn_raiders, R.id.aucher_status})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_edit:
                startActivityForNoResult(new Intent(getActivity(), EditUserInfoActivity.class));
                break;
            case R.id.rel_comment:
                startActivityForNoResult(new Intent(getActivity(), UserCommentListActitvity.class));
                break;
            case R.id.rel_take_class_log:
                startActivityForNoResult(new Intent(getActivity(), TakeClassLogActivity.class));
                break;
            case R.id.rel_commission:
                startActivityForNoResult(new Intent(getActivity(), SalaryLogActivity.class));
                break;
            case R.id.rel_invest:
                break;
            case R.id.rel_dividend:
//                startActivityForNoResult(new Intent(getActivity(), BonusLogActivity.class));
                break;
            case R.id.rel_notice:
                startActivityForNoResult(new Intent(getActivity(), SettingActivity.class));
                break;
            case R.id.rel_help_feedback:
                startActivityForNoResult(new Intent(getActivity(), FeedBackActivity.class).putExtra("IntentKey", IntentKey.FeedBack));
                break;
            case R.id.rel_about:
                startActivityForNoResult(new Intent(getActivity(), AboutActivity.class));
                break;
            case R.id.btn_promote:
                int status = userInfo.getUpgrade_botton_status();
                if (status == 1) {
                    startActivityForNoResult(new Intent(getActivity(), PromotionApplyActivity.class));
                }
                break;
            case R.id.take_class_history_lay:
                startActivityForNoResult(new Intent(getActivity(), PromotionLogActivity.class));
                break;
            case R.id.btn_raiders:
                startActivityForNoResult(new Intent(getActivity(), FeedBackActivity.class).putExtra("IntentKey", IntentKey.Raiders));
                break;
            case R.id.aucher_status:
                int userStatus = userInfo.getTeacher_info().getGsy_status();
                if (userStatus == 0){
//                    aucherStatus.setText("未认证");
                    startActivityForNoResult(new Intent(getActivity(), EditUserInfoActivity.class).putExtra("isRegister",true));
                }else {
                    startActivityForNoResult(new Intent(getActivity(), ShowAuthStatusActivity.class));
                }

                break;
        }
    }

    private void getUserInfo() {
        UserBean userBean = CurrentUser.getUser();
        if (userBean == null) {
            return;
        }
        LoadingDialog.ShowLoading(getActivity());
        EditUserInfoApi.getMineInfo(userBean.getTid(), new RequestBack() {
            @Override
            public void onComplete(FlpBack back) {
                LoadingDialog.DissLoading(getActivity());
                if (back.errorCode == 200) {
                    userInfo = JSON.parseObject(back.data, UserBean.class);
                    if (userInfo != null) {
//                        CurrentUser.getUser().setUserInfo(userInfo);
                        initViewDate();
                    }
                }
            }

            @Override
            public void onFlpException(FlpException e) {
                LoadingDialog.DissLoading(getActivity());
            }
        });
    }

    private void initViewDate() {
        PicassoUtils.showPersionImg(getActivity(), userInfo.getTeacher_info().getGsy_portrait(), imgHead);
        tvNickname.setText(userInfo.getTeacher_info().getGsy_realname());
        tvCompleteRate.setText("资料完善度" + userInfo.getTeacher_info().getGsy_complete_scale() + "%");

        int levelMax = userInfo.getTeacher_level().getMax_level();
        int levelCurrent = userInfo.getTeacher_level().getCur_level();

        levelBar.setMax(levelMax);
        levelBar.setProgress(levelCurrent);
        levelBarTxt.setText(levelCurrent + "/" + levelMax + "级");

        int teachCourseMax = userInfo.getTeach_course().getMax_count();
        int teachCourseCurrent = userInfo.getTeach_course().getCur_count();

        teachCourseBar.setMax(teachCourseMax);
        teachCourseBar.setProgress(teachCourseCurrent);
        teachCourseBarTxt.setText(teachCourseCurrent + "/" + teachCourseMax + "节");


        int comment_scale = userInfo.getGood_comment_scale();

        commentBar.setProgress(comment_scale);
        commentBarTxt.setText(comment_scale + "%");

        int trainCourseeMax = userInfo.getTrain_course().getMax_count();
        int trainCourseCurrent = userInfo.getTrain_course().getCur_count();

        trainCourseBar.setMax(trainCourseeMax);
        trainCourseBar.setProgress(trainCourseCurrent);
        trainCourseBarTxt.setText(trainCourseCurrent + "/" + trainCourseeMax + "级");

        int status = userInfo.getUpgrade_botton_status();
        if (status == 1) {
            btnPromote.setBackgroundResource(R.drawable.mine_rank_strategy_shape);
        } else if (status == 2) {
            btnPromote.setBackgroundResource(R.drawable.mine_rank_strategy_shape_grey);
        }

        tvCommissionValue.setText(userInfo.getTotal_commission());
        tvClassname.setText(userInfo.getTeacher_info().getGsy_course_class_name());

        int userStatus = userInfo.getTeacher_info().getGsy_status();
        if (userStatus == 0){
            aucherStatus.setText("未认证");
        }else if (userStatus == 1){
            aucherStatus.setText("认证成功");
        }else if (userStatus == 2){
            aucherStatus.setText("认证中");
        }else if (userStatus == -1){
            aucherStatus.setText("认证失败");
        }

    }

}
