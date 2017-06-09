package com.guanshaoye.glglteacher.ui.manager.teacher;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.guanshaoye.glglteacher.R;
import com.guanshaoye.glglteacher.adapter.BaseRecyclerAdapter;
import com.guanshaoye.glglteacher.adapter.CommonHolder;
import com.guanshaoye.glglteacher.adapter.OnCommentClickListener;
import com.guanshaoye.glglteacher.bean.TeacherReviewBean;
import com.guanshaoye.glglteacher.utils.PicassoUtils;
import com.guanshaoye.mylibrary.view.CircleImg;

import butterknife.Bind;

/**
 * Created by karl on 2017/5/22.
 */

public class TeacherReviewAdapter extends BaseRecyclerAdapter<TeacherReviewBean> {
    private OnCommentClickListener mOnItemClickListener;
    private boolean isReview;
    private Context context;
    public TeacherReviewAdapter(Context mcontext,boolean review) {
        this.isReview = review;
        this.context = mcontext;
    }

    @Override
    public CommonHolder<TeacherReviewBean> setViewHolder(ViewGroup parent) {
        return new TeacherReviewAdapter.CardHolder(parent.getContext(), parent);
    }

    public void setOnItemClickListener(OnCommentClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    class CardHolder extends CommonHolder<TeacherReviewBean> {


        @Bind(R.id.img_teacher)
        CircleImg imgTeacher;
        @Bind(R.id.store_txt)
        TextView storeTxt;
        @Bind(R.id.levle_txt)
        TextView levleTxt;
        @Bind(R.id.time_txt)
        TextView timeTxt;
        @Bind(R.id.title_txt)
        TextView titleTxt;
        @Bind(R.id.name_teacher)
        TextView nameTeacher;
        @Bind(R.id.num_txt)
        TextView numTxt;
        @Bind(R.id.jump_txt)
        TextView jumpTxt;

        public CardHolder(Context context, ViewGroup root) {
            super(context, root, R.layout.item_treview_lay);
        }

        @Override
        public void bindData(final TeacherReviewBean teacherReviewBean) {

            PicassoUtils.showPersionImg(context, teacherReviewBean.getGsy_teacher_portrait(),imgTeacher);

            timeTxt.setText(teacherReviewBean.getGsy_course_time());
            nameTeacher.setText(teacherReviewBean.getGsy_teacher_name());
            levleTxt.setText("("+teacherReviewBean.getGsy_teacher_level()+"级)");
            storeTxt.setText(teacherReviewBean.getGsy_course_class_name());
            numTxt.setText("已预约  "+teacherReviewBean.getGsy_apply_count()+"/"+teacherReviewBean.getGsy_max_apply_count());
            if (isReview) {
                jumpTxt.setText("详情");
            } else {
                jumpTxt.setText("评价");
            }
            jumpTxt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onItemClick(teacherReviewBean);
                    }

                }
            });
        }
    }
}