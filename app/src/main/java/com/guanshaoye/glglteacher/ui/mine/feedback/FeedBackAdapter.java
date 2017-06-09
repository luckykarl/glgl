package com.guanshaoye.glglteacher.ui.mine.feedback;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.guanshaoye.glglteacher.R;
import com.guanshaoye.glglteacher.adapter.BaseRecyclerAdapter;
import com.guanshaoye.glglteacher.adapter.CommonHolder;
import com.guanshaoye.glglteacher.bean.FeedBackBean;
import com.guanshaoye.glglteacher.listener.OnClickItemListener;

import butterknife.Bind;

/**
 * Created by karl on 2017/6/3.
 */

public class FeedBackAdapter extends BaseRecyclerAdapter<FeedBackBean> {
    private OnClickItemListener clickItemListener;

    public FeedBackAdapter(OnClickItemListener mclickItemListener) {
        this.clickItemListener = mclickItemListener;
    }

    @Override
    public CommonHolder<FeedBackBean> setViewHolder(ViewGroup parent) {
        return new FeedBackAdapter.CardHolder(parent.getContext(), parent);
    }


    class CardHolder extends CommonHolder<FeedBackBean> {


        @Bind(R.id.tv_title)
        TextView tvTitle;
        @Bind(R.id.click_lay)
        RelativeLayout clickLay;

        public CardHolder(Context context, ViewGroup root) {
            super(context, root, R.layout.item_feedback_lay);
        }

        @Override
        public void bindData(final FeedBackBean bean) {

            tvTitle.setText(bean.getGsy_article_title());

            clickLay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickItemListener.onItemClick(bean.getId());
                }
            });

        }
    }
}
