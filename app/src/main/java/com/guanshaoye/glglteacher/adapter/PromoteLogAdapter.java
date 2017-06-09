package com.guanshaoye.glglteacher.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.guanshaoye.glglteacher.R;
import com.guanshaoye.glglteacher.bean.PromoteLog;
import com.guanshaoye.glglteacher.utils.PicassoUtils;
import com.guanshaoye.mylibrary.view.CircleImg;

import butterknife.Bind;

/**
 * Created by ${张梦博} on 2017/5/16.
 * Whenever,Wherever,Whatever,I believe I can handle everything
 */

public class PromoteLogAdapter extends BaseRecyclerAdapter<PromoteLog> {
    private Context context;

    public PromoteLogAdapter(Context mcontext) {
        this.context = mcontext;
    }

    @Override
    public CommonHolder<PromoteLog> setViewHolder(ViewGroup parent) {
        return new PromoteLogAdapter.CardHolder(parent.getContext(), parent);
    }


    class CardHolder extends CommonHolder<PromoteLog> {


        @Bind(R.id.photo_img)
        CircleImg photoImg;
        @Bind(R.id.theory_score_txt)
        TextView theoryScoreTxt;
        @Bind(R.id.skill_score_txt)
        TextView skillScoreTxt;
        @Bind(R.id.tv_promote_time)
        TextView tvPromoteTime;
        @Bind(R.id.tv_start_rank)
        TextView tvStartRank;
        @Bind(R.id.img_transition)
        ImageView imgTransition;
        @Bind(R.id.tv_end_rank)
        TextView tvEndRank;
        @Bind(R.id.img_promote_status)
        ImageView imgPromoteStatus;
        @Bind(R.id.tv_promote_status)
        TextView tvPromoteStatus;

        public CardHolder(Context context, ViewGroup root) {
            super(context, root, R.layout.item_promote_log);
        }

        @Override
        public void bindData(PromoteLog bean) {

            PicassoUtils.showPersionImg(context, bean.getGsy_portrait(), photoImg);
            theoryScoreTxt.setText(bean.getGsy_theory_score());
            skillScoreTxt.setText(bean.getGsy_skill_score());
            tvPromoteTime.setText(bean.getGsy_add_time());
            tvStartRank.setText(bean.getGsy_before_level()+"");
            tvEndRank.setText(bean.getGsy_after_level()+"");

            int status = bean.getGsy_status();
            if(status == 0){
                tvPromoteStatus.setText("晋级中");
                tvPromoteStatus.setTextColor(Color.parseColor("#28ce89"));
                imgPromoteStatus.setBackgroundResource(R.drawable.promote_success_icon);
            }else if(status == 1){
                tvPromoteStatus.setText("晋级成功");
                tvPromoteStatus.setTextColor(Color.parseColor("#28ce89"));
                imgPromoteStatus.setBackgroundResource(R.drawable.promote_success_icon);
            }else if(status == -1){
                tvPromoteStatus.setText("晋级失败");
                tvPromoteStatus.setTextColor(Color.parseColor("#df5151"));
                imgPromoteStatus.setBackgroundResource(R.drawable.promote_fail_icon);
            }
        }
    }
}
