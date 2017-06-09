package com.guanshaoye.glglteacher.ui.mine.tactless;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.TextView;

import com.guanshaoye.glglteacher.R;
import com.guanshaoye.glglteacher.adapter.BaseRecyclerAdapter;
import com.guanshaoye.glglteacher.adapter.CommonHolder;
import com.guanshaoye.glglteacher.bean.TakeClassLog;
import com.guanshaoye.glglteacher.utils.PicassoUtils;
import com.guanshaoye.mylibrary.view.CircleImg;

import butterknife.Bind;

/**
 * Created by karl on 2017/5/22.
 */

public class TaskLogAdapter extends BaseRecyclerAdapter<TakeClassLog> {
    private Context context;

    public TaskLogAdapter(Context mcontext) {
        this.context = mcontext;
    }

    @Override
    public CommonHolder<TakeClassLog> setViewHolder(ViewGroup parent) {
        return new TaskLogAdapter.CardHolder(parent.getContext(), parent);
    }


    class CardHolder extends CommonHolder<TakeClassLog> {


        @Bind(R.id.img_teacher_head)
        CircleImg imgTeacherHead;
        @Bind(R.id.tv_store_name)
        TextView tvStoreName;
        @Bind(R.id.tv_class_rank)
        TextView tvClassRank;
        @Bind(R.id.tv_take_class_status)
        TextView tvTakeClassStatus;
        @Bind(R.id.tv_time)
        TextView tvTime;

        public CardHolder(Context context, ViewGroup root) {
            super(context, root, R.layout.item_take_class_log);
        }

        @Override
        public void bindData(TakeClassLog bean) {

            PicassoUtils.showPersionImg(context, bean.getGsy_portrait(), imgTeacherHead);
            tvStoreName.setText(bean.getGsy_store_name());
            tvTime.setText(bean.getGsy_course_time());
            tvClassRank.setText(bean.getGsy_course_class_name());
            int status = bean.getGsy_status();
            if(status == 5){
                tvTakeClassStatus.setText("上课成功");
            }else if(status == 6){
                tvTakeClassStatus.setText("缺课");
            }

        }
    }
}
