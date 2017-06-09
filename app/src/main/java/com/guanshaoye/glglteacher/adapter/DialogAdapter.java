package com.guanshaoye.glglteacher.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.guanshaoye.glglteacher.R;
import com.guanshaoye.glglteacher.bean.ItemListBean;

import butterknife.Bind;

/**
 * Created by karl on 2017/5/25.
 */

public class DialogAdapter extends BaseRecyclerAdapter<ItemListBean> {
    private OnItemClickListener mOnItemClickListener;


    @Override
    public CommonHolder<ItemListBean> setViewHolder(ViewGroup parent) {
        return new DialogAdapter.CardHolder(parent.getContext(), parent);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    class CardHolder extends CommonHolder<ItemListBean> {


        @Bind(R.id.jump_txt)
        TextView jumpTxt;

        public CardHolder(Context context, ViewGroup root) {
            super(context, root, R.layout.item_dialog_lay);
        }

        @Override
        public void bindData(final ItemListBean itemListBean) {
            jumpTxt.setText(itemListBean.getDialog_content());
            jumpTxt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onItemClick(itemListBean);
                    }

                }
            });
        }
    }
}
