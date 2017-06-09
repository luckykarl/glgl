package com.guanshaoye.glglteacher.ui.manager.train;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.guanshaoye.glglteacher.R;
import com.guanshaoye.glglteacher.adapter.BaseRecyclerAdapter;
import com.guanshaoye.glglteacher.adapter.CommonHolder;
import com.guanshaoye.glglteacher.adapter.OnItemClickListener;
import com.guanshaoye.glglteacher.adapter.OnTrainItemClickListener;
import com.guanshaoye.glglteacher.bean.TrainBean;
import com.guanshaoye.glglteacher.utils.PicassoUtils;
import com.guanshaoye.mylibrary.view.CircleImg;

import butterknife.Bind;

/**
 * Created by karl on 2017/5/22.
 */

public class TrainAdapter extends BaseRecyclerAdapter<TrainBean> {
    private OnTrainItemClickListener mOnItemClickListener;
    private boolean isRegister;
    private Context context;

    public TrainAdapter(Context mcontext, boolean review) {
        this.context = mcontext;
        this.isRegister = review;
    }

    @Override
    public CommonHolder<TrainBean> setViewHolder(ViewGroup parent) {
        return new TrainAdapter.CardHolder(parent.getContext(), parent);
    }

    public void setOnItemClickListener(OnTrainItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    class CardHolder extends CommonHolder<TrainBean> {


        @Bind(R.id.photo_img)
        CircleImg photoImg;
        @Bind(R.id.tv_name)
        TextView tvName;
        @Bind(R.id.tv_levle)
        TextView tvLevle;
        @Bind(R.id.tv_time)
        TextView tvTime;
        @Bind(R.id.tv_jump)
        TextView tvJump;
        @Bind(R.id.tv_location)
        TextView tvLocation;

        public CardHolder(Context context, ViewGroup root) {
            super(context, root, R.layout.item_train_lay);
        }

        @Override
        public void bindData(final TrainBean trainBean) {

            PicassoUtils.showPersionImg(context, trainBean.getGsy_course_pic(), photoImg);

            tvName.setText(trainBean.getGsy_course_name());
            tvLevle.setText("(" + trainBean.getGsy_train_level_name() + ")");
            tvLocation.setText(trainBean.getGsy_train_address());
            tvTime.setText(trainBean.getGsy_train_time());
            if (isRegister) {
                tvJump.setText("详情");
            } else {
                tvJump.setText("未签到(" + trainBean.getGsy_signin_count() + "/" + trainBean.getGsy_train_count() + ")");
            }
            tvJump.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onItemClick(trainBean);
                    }

                }
            });
        }
    }
}