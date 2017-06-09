package com.guanshaoye.glglteacher.ui.manager.train;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.guanshaoye.glglteacher.R;
import com.guanshaoye.glglteacher.adapter.BaseRecyclerAdapter;
import com.guanshaoye.glglteacher.adapter.CommonHolder;
import com.guanshaoye.glglteacher.adapter.OnTrainItemClickListener;
import com.guanshaoye.glglteacher.bean.TrainBean;
import com.guanshaoye.glglteacher.utils.PicassoUtils;
import com.guanshaoye.mylibrary.view.CircleImg;

import butterknife.Bind;

/**
 * Created by karl on 2017/5/24.
 */

public class ShowTrainAdapter extends BaseRecyclerAdapter<TrainBean> {
    private OnTrainItemClickListener mOnItemClickListener;
    private Context context;
    public ShowTrainAdapter(Context mcontext) {
        this.context = mcontext;
    }

    @Override
    public CommonHolder<TrainBean> setViewHolder(ViewGroup parent) {
        return new ShowTrainAdapter.CardHolder(parent.getContext(), parent);
    }

    public void setOnItemClickListener(OnTrainItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    class CardHolder extends CommonHolder<TrainBean> {


        @Bind(R.id.img_photo)
        CircleImg imgPhoto;
        @Bind(R.id.tv_teacher)
        TextView tvTeacher;
        @Bind(R.id.tv_name)
        TextView tvName;
        @Bind(R.id.tv_status)
        TextView tvStatus;

        public CardHolder(Context context, ViewGroup root) {
            super(context, root, R.layout.item_showtrain_lay);
        }

        @Override
        public void bindData(final TrainBean trainBean) {
            PicassoUtils.showPersionImg(context, trainBean.getGsy_course_pic(), imgPhoto);
            tvTeacher.setText(trainBean.getGsy_teacher_name());
            tvName.setText(trainBean.getGsy_course_name()+trainBean.getGsy_teacher_level()+"级");
            final int status = trainBean.getGsy_signin_status();
            if(status == 0){
                tvStatus.setText("未签到");
                tvStatus.setTextColor(Color.parseColor("#FFFFFF"));
                tvStatus.setBackgroundResource(R.drawable.shape_green_item);
            }else  if(status == 1){
                tvStatus.setText("已签到");
                tvStatus.setTextColor(Color.parseColor("#df5151"));
                tvStatus.setBackgroundResource(R.drawable.shape_red_line);
            }
            tvStatus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(status == 1){
                        return;
                    }
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onItemClick(trainBean);
                    }
                }
            });
        }
    }
}