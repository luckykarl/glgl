package com.guanshaoye.glglteacher.ui.message;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.guanshaoye.glglteacher.R;
import com.guanshaoye.glglteacher.adapter.BaseRecyclerAdapter;
import com.guanshaoye.glglteacher.adapter.CommonHolder;
import com.guanshaoye.glglteacher.bean.MessageInfoBean;

import butterknife.Bind;

/**
 * Created by karl on 2017/6/3.
 */

public class ShowMessageAdapter extends BaseRecyclerAdapter<MessageInfoBean> {
    private Context context;

    public ShowMessageAdapter(Context mcontext) {
        this.context = mcontext;
    }

    @Override
    public CommonHolder<MessageInfoBean> setViewHolder(ViewGroup parent) {
        return new ShowMessageAdapter.CardHolder(parent.getContext(), parent);
    }


    class CardHolder extends CommonHolder<MessageInfoBean> {


        @Bind(R.id.tv_title)
        TextView tvTitle;
        @Bind(R.id.tv_content)
        TextView tvContent;
        @Bind(R.id.tv_time)
        TextView tvTime;
        @Bind(R.id.tv_role)
        TextView tvRole;

        public CardHolder(Context context, ViewGroup root) {
            super(context, root, R.layout.item_showmsg_lay);

        }

        @Override
        public void bindData(final MessageInfoBean bean) {

            tvTitle.setText(bean.getGsy_class_name());
            tvContent.setText(bean.getGsy_title());
            tvTime.setText(bean.getGsy_add_time());
            tvRole.setText(bean.getGsy_admin_name());

        }
    }
}
