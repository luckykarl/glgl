package com.guanshaoye.glglteacher.ui.manager;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.guanshaoye.glglteacher.R;
import com.guanshaoye.glglteacher.ui.manager.attendclass.AttendClassActivity;
import com.guanshaoye.glglteacher.ui.manager.teacher.TeacherReviewsActivity;
import com.guanshaoye.glglteacher.ui.manager.train.TrainActivity;
import com.guanshaoye.mylibrary.base.BaseActivity;

import butterknife.Bind;
import butterknife.OnClick;
/**
 * Created by ${张梦博} on 2017/5/12.
 * Whenever,Wherever,Whatever,I believe I can handle everything
 */

public class WorkManageActivity extends BaseActivity {
    @Bind(R.id.normal_title)
    TextView normalTitle;
    @Bind(R.id.tv_main_title)
    TextView tvMainTitle;
    @Bind(R.id.tv_vice_title)
    TextView tvViceTitle;
    @Bind(R.id.tv_comment_teacher_main_title)
    TextView tvCommentTeacherMainTitle;
    @Bind(R.id.tv_comment_teacher_vice_title)
    TextView tvCommentTeacherViceTitle;
    @Bind(R.id.tv_train_manage_main_title)
    TextView tvTrainManageMainTitle;
    @Bind(R.id.tv_train_manage_vice_title)
    TextView tvTrainManageViceTitle;
    @Override
    public void init() {
        setContentView(R.layout.activtiy_work_manage);
        normalTitle.setText("工作管理");
    }
    @OnClick({R.id.rel_take_class_sign_in, R.id.rel_teacher_comment, R.id.rel_train_manage})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rel_take_class_sign_in:
                startActivityForNoResult(new Intent(getActivity(),AttendClassActivity.class));
                break;
            case R.id.rel_teacher_comment:
                startActivityForNoResult(new Intent(getActivity(),TeacherReviewsActivity.class));
                break;
            case R.id.rel_train_manage:
                startActivityForNoResult(new Intent(getActivity(),TrainActivity.class));
                break;
        }
    }
}
