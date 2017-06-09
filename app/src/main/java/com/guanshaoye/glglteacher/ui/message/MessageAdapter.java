package com.guanshaoye.glglteacher.ui.message;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.guanshaoye.glglteacher.R;
import com.guanshaoye.glglteacher.adapter.BaseRecyclerAdapter;
import com.guanshaoye.glglteacher.adapter.CommonHolder;
import com.guanshaoye.glglteacher.bean.MessageBean;
import com.guanshaoye.glglteacher.bean.MessageInfoBean;
import com.guanshaoye.glglteacher.listener.OnClickItemListener;
import com.guanshaoye.glglteacher.utils.PicassoUtils;
import com.guanshaoye.mylibrary.view.CircleImg;

import butterknife.Bind;

/**
 * Created by karl on 2017/6/3.
 */

public class MessageAdapter extends BaseRecyclerAdapter<MessageBean> {
    private Context context;
    private OnClickItemListener clickItemListener;

    public MessageAdapter(Context mcontext, OnClickItemListener mclickItemListener) {
        this.context = mcontext;
        this.clickItemListener = mclickItemListener;
    }

    @Override
    public CommonHolder<MessageBean> setViewHolder(ViewGroup parent) {
        return new MessageAdapter.CardHolder(parent.getContext(), parent);
    }


    class CardHolder extends CommonHolder<MessageBean> {


        @Bind(R.id.img_icon)
        CircleImg imgIcon;
        @Bind(R.id.title_txt)
        TextView titleTxt;
        @Bind(R.id.content_txt)
        TextView contentTxt;
        @Bind(R.id.red_txt)
        TextView redTxt;
        @Bind(R.id.tv_time)
        TextView tvTime;
        @Bind(R.id.vie_lay)
        RelativeLayout vieLay;

        public CardHolder(Context context, ViewGroup root) {
            super(context, root, R.layout.item_message_lay);
        }

        @Override
        public void bindData(final MessageBean bean) {

            PicassoUtils.showPersionImg(context, bean.getMessage_class_pic_url(), imgIcon);
            titleTxt.setText(bean.getMessage_class_name());
            tvTime.setText("");
            MessageInfoBean messageInfo = bean.getMessage_info();
            if (messageInfo != null){
                contentTxt.setText(messageInfo.getGsy_title());
                tvTime.setText(messageInfo.getGsy_add_time());
            }

            int num = bean.getNo_scan_num();

            if (num == 0) {
                redTxt.setVisibility(View.GONE);
            } else {
                redTxt.setVisibility(View.VISIBLE);
                redTxt.setText(num + "");
            }

            vieLay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickItemListener.onItemClick(bean.getGsy_class_id());
                }
            });

        }
    }
}
