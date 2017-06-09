package com.guanshaoye.glglteacher.ui.manager.attendclass;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.guanshaoye.glglteacher.R;
import com.guanshaoye.glglteacher.adapter.BaseRecyclerAdapter;
import com.guanshaoye.glglteacher.adapter.CommonHolder;
import com.guanshaoye.glglteacher.adapter.OnItemClickListener;
import com.guanshaoye.glglteacher.bean.AttendClassBean;
import com.guanshaoye.glglteacher.utils.PicassoUtils;
import com.guanshaoye.mylibrary.view.CircleImg;

import butterknife.Bind;

/**
 * Created by karl on 2017/5/22.
 */

public class AttendClassAdapter extends BaseRecyclerAdapter<AttendClassBean> {
    private OnItemClickListener mOnItemClickListener;
    private boolean isRegister;
    private Context context;
    public AttendClassAdapter(Context mcontext,boolean review) {
        this.context =mcontext;
        this.isRegister = review;
    }

    @Override
    public CommonHolder<AttendClassBean> setViewHolder(ViewGroup parent) {
        return new AttendClassAdapter.CardHolder(parent.getContext(), parent);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    class CardHolder extends CommonHolder<AttendClassBean> {


        @Bind(R.id.photo_img)
        CircleImg photoImg;
        @Bind(R.id.tv_name)
        TextView tvName;
        @Bind(R.id.tv_levle)
        TextView tvLevle;
        @Bind(R.id.tv_time)
        TextView tvTime;
        @Bind(R.id.tv_teacher)
        TextView tvTeacher;
        @Bind(R.id.tv_finish)
        TextView tvFinish;
        @Bind(R.id.tv_jump)
        TextView tvJump;

        public CardHolder(Context context, ViewGroup root) {
            super(context, root, R.layout.item_attendclass_lay);
        }

        @Override
        public void bindData(final AttendClassBean attendClassBean) {

            PicassoUtils.showPersionImg(context, attendClassBean.getGsy_teacher_portrait(),photoImg);

            tvName.setText(attendClassBean.getGsy_course_class_name());
            tvLevle.setText("("+attendClassBean.getGsy_course_level()+"级)");
            tvTime.setText(attendClassBean.getCourse_time());
            tvTeacher.setText(attendClassBean.getGsy_store_name());
            if (isRegister) {
                tvJump.setText("详情");
                tvFinish.setText("上课人数  "+attendClassBean.getGsy_signin_count());
            } else {
                tvFinish.setText("已预约  "+attendClassBean.getGsy_apply_count()+"/"+attendClassBean.getGsy_max_apply_count());
                tvJump.setText("签到  "+attendClassBean.getGsy_signin_count()+"/"+attendClassBean.getMax_signin_count());
            }
            tvJump.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onClickAttendClass(attendClassBean);
                    }

                }
            });
        }
    }
}