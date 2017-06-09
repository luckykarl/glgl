package com.guanshaoye.glglteacher.ui.authentication;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.guanshaoye.glglteacher.R;
import com.guanshaoye.glglteacher.adapter.BaseRecyclerAdapter;
import com.guanshaoye.glglteacher.adapter.CommonHolder;
import com.guanshaoye.glglteacher.bean.ContentBean;
import com.guanshaoye.glglteacher.listener.SelectListener;

import butterknife.Bind;

/**
 * Created by karl on 2017/6/4.
 */

public class SelectListAdapter extends BaseRecyclerAdapter<ContentBean> {
    private SelectListener clickItemListener;

    public SelectListAdapter(SelectListener mclickItemListener) {
        this.clickItemListener = mclickItemListener;
    }

    @Override
    public CommonHolder<ContentBean> setViewHolder(ViewGroup parent) {
        return new SelectListAdapter.CardHolder(parent.getContext(), parent);
    }


    class CardHolder extends CommonHolder<ContentBean> {


        @Bind(R.id.tv_title)
        TextView tvTitle;
        @Bind(R.id.click_lay)
        RelativeLayout clickLay;

        public CardHolder(Context context, ViewGroup root) {
            super(context, root, R.layout.item_select_lay);
        }

        @Override
        public void bindData(final ContentBean bean) {

            tvTitle.setText(bean.getGsy_name());

            clickLay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickItemListener.onItemClick(bean);
                }
            });

        }
    }
}
