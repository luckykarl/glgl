package com.guanshaoye.glglteacher.ui.manager.teacher;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.guanshaoye.glglteacher.R;
import com.guanshaoye.glglteacher.adapter.BaseRecyclerAdapter;
import com.guanshaoye.glglteacher.adapter.CommonHolder;
import com.guanshaoye.glglteacher.bean.CommentPicBean;
import com.guanshaoye.glglteacher.utils.PicassoUtils;

import butterknife.Bind;

/**
 * Created by karl on 2017/6/6.
 */

public class ShowPhotoAdapter extends BaseRecyclerAdapter<CommentPicBean> {
    private Context context;
    public ShowPhotoAdapter(Context mcontext) {
        this.context =mcontext;
    }

    @Override
    public CommonHolder<CommentPicBean> setViewHolder(ViewGroup parent) {
        return new ShowPhotoAdapter.CardHolder(parent.getContext(), parent);
    }

    class CardHolder extends CommonHolder<CommentPicBean> {

        @Bind(R.id.photo_list_item_img)
        ImageView photoListItemImg;

        public CardHolder(Context context, ViewGroup root) {
            super(context, root, R.layout.shaophoto_item);
        }

        @Override
        public void bindData(final CommentPicBean imageBean) {
            PicassoUtils.showPersionImg(context, imageBean.getGsy_pic_url(),photoListItemImg);
        }
    }
}
