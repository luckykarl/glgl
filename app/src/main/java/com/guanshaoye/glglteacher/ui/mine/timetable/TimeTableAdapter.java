package com.guanshaoye.glglteacher.ui.mine.timetable;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.guanshaoye.glglteacher.R;
import com.guanshaoye.glglteacher.adapter.BaseRecyclerAdapter;
import com.guanshaoye.glglteacher.adapter.CommonHolder;
import com.guanshaoye.glglteacher.adapter.OnItemClickListener;
import com.guanshaoye.glglteacher.bean.ItemListBean;

import butterknife.Bind;

/**
 * Created by karl on 2017/5/22.
 */

public class TimeTableAdapter extends BaseRecyclerAdapter<ItemListBean> {
    private OnItemClickListener mOnItemClickListener;

    @Override
    public CommonHolder<ItemListBean> setViewHolder(ViewGroup parent) {
        return new TimeTableAdapter.CardHolder(parent.getContext(), parent);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    class CardHolder extends CommonHolder<ItemListBean> {

        @Bind(R.id.bg_lay)
        LinearLayout bgLay;

        public CardHolder(Context context, ViewGroup root) {
            super(context, root, R.layout.item_timetable_lay);
        }

        @Override
        public void bindData(final ItemListBean listBean) {
            int status = listBean.getGsy_status();
            if (status == 3) { //取消
                bgLay.setBackgroundResource(R.drawable.shape_red);
            } else if (status == 4 || status == 5) {
                bgLay.setBackgroundResource(R.drawable.shape_green);
            } else if (status == 0 || status == 1 || status == 2 || status == 6) {
                bgLay.setBackgroundResource(R.drawable.shape_light_gray);
            } else {
                bgLay.setBackgroundResource(R.drawable.shape_gray);
            }

            bgLay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onItemClick(listBean);
                    }

                }
            });
        }
    }
}