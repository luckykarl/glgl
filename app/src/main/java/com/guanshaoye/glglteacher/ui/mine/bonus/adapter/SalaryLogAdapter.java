package com.guanshaoye.glglteacher.ui.mine.bonus.adapter;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.TextView;

import com.guanshaoye.glglteacher.R;
import com.guanshaoye.glglteacher.adapter.BaseRecyclerAdapter;
import com.guanshaoye.glglteacher.adapter.CommonHolder;
import com.guanshaoye.glglteacher.bean.TeacherCommissionListBean;
import com.guanshaoye.glglteacher.utils.PicassoUtils;
import com.guanshaoye.mylibrary.view.CircleImg;

import butterknife.Bind;

/**
 * Created by karl on 2017/5/22.
 */

public class SalaryLogAdapter extends BaseRecyclerAdapter<TeacherCommissionListBean> {
    private Context context;

    public SalaryLogAdapter(Context mcontext) {
        this.context = mcontext;
    }

    @Override
    public CommonHolder<TeacherCommissionListBean> setViewHolder(ViewGroup parent) {
        return new CardHolder(parent.getContext(), parent);
    }

    class CardHolder extends CommonHolder<TeacherCommissionListBean> {

        @Bind(R.id.img_teacher_head)
        CircleImg imgTeacherHead;
        @Bind(R.id.tv_store_name)
        TextView tvStoreName;
        @Bind(R.id.tv_take_class_status)
        TextView tvTakeClassStatus;
        @Bind(R.id.tv_class_rank)
        TextView tvClassRank;
        @Bind(R.id.tv_take_class_tiem)
        TextView tvTakeClassTiem;
        @Bind(R.id.tv_time)
        TextView tvTime;

        public CardHolder(Context context, ViewGroup root) {
            super(context, root, R.layout.item_salarylog_lay);
        }

        @Override
        public void bindData(TeacherCommissionListBean bean) {
            PicassoUtils.showPersionImg(context, bean.getGsy_portrait(), imgTeacherHead);
            tvStoreName.setText(bean.getGsy_store_name());
            tvClassRank.setText(bean.getGsy_course_class_name() + bean.getGsy_level() + "级");
            tvTakeClassTiem.setText("获得工资  ¥" + bean.getGsy_money());
            tvTime.setText(bean.getGsy_course_time());
        }
    }
}
