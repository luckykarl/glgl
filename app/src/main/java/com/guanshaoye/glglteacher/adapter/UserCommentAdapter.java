package com.guanshaoye.glglteacher.adapter;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.TextView;

import com.guanshaoye.glglteacher.R;
import com.guanshaoye.glglteacher.bean.UserCommentBean;
import com.guanshaoye.glglteacher.utils.PicassoUtils;
import com.guanshaoye.glglteacher.utils.view.RatingBar;
import com.guanshaoye.mylibrary.view.CircleImg;

import butterknife.Bind;

/**
 * Created by karl on 2017/5/30.
 */

public class UserCommentAdapter extends BaseRecyclerAdapter<UserCommentBean> {
    private Context context;

    public UserCommentAdapter(Context mcontext) {
        this.context = mcontext;
    }

    @Override
    public CommonHolder<UserCommentBean> setViewHolder(ViewGroup parent) {
        return new UserCommentAdapter.CardHolder(parent.getContext(), parent);
    }


    class CardHolder extends CommonHolder<UserCommentBean> {


        @Bind(R.id.photo_img)
        CircleImg photoImg;
        @Bind(R.id.tv_name)
        TextView tvName;
        @Bind(R.id.tv_level)
        TextView tvLevel;
        @Bind(R.id.tv_time)
        TextView tvTime;
        @Bind(R.id.img_transition)
        TextView imgTransition;
        @Bind(R.id.rb)
        RatingBar rb;

        public CardHolder(Context context, ViewGroup root) {
            super(context, root, R.layout.item_usercomment_lay);
        }

        @Override
        public void bindData(UserCommentBean bean) {

            PicassoUtils.showPersionImg(context, bean.getGsy_course_pic(), photoImg);
            tvName.setText(bean.getGsy_course_class_name());
            tvTime.setText(bean.getGsy_course_time());
            imgTransition.setText(bean.getGsy_store_name());
            rb.setStar(bean.getGsy_course_star());



        }
    }
}
