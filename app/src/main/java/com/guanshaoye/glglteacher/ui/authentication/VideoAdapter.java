package com.guanshaoye.glglteacher.ui.authentication;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.guanshaoye.glglteacher.R;
import com.guanshaoye.glglteacher.adapter.BaseRecyclerAdapter;
import com.guanshaoye.glglteacher.adapter.CommonHolder;
import com.guanshaoye.glglteacher.bean.ImageBean;
import com.guanshaoye.glglteacher.listener.OnClickPhotoListener;
import com.guanshaoye.glglteacher.utils.FileUtils;

import butterknife.Bind;

/**
 * Created by karl on 2017/6/4.
 */

public class VideoAdapter extends BaseRecyclerAdapter<ImageBean> {
    private OnClickPhotoListener mOnItemClickListener;

    @Override
    public CommonHolder<ImageBean> setViewHolder(ViewGroup parent) {
        return new VideoAdapter.CardHolder(parent.getContext(), parent);
    }

    public void setOnItemClickListener(OnClickPhotoListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    class CardHolder extends CommonHolder<ImageBean> {


        @Bind(R.id.photo_img)
        ImageView photoImg;
        @Bind(R.id.video_icon)
        ImageView videoIcon;
        @Bind(R.id.photo_del)
        ImageView photoDel;
        @Bind(R.id.add_bottom_img)
        ImageView addBottomImg;
        @Bind(R.id.photo_list_item_img_lay)
        RelativeLayout photoListItemImgLay;
        @Bind(R.id.tv_doing)
        TextView tvDoing;

        public CardHolder(Context context, ViewGroup root) {
            super(context, root, R.layout.gridview_item_video);
        }

        @Override
        public void bindData(final ImageBean imageBean) {
            String path = imageBean.path;
            if (TextUtils.isEmpty(path)) {
                addBottomImg.setVisibility(View.VISIBLE);
                photoListItemImgLay.setVisibility(View.GONE);
                photoDel.setVisibility(View.GONE);
            } else {
                addBottomImg.setVisibility(View.GONE);
                photoListItemImgLay.setVisibility(View.VISIBLE);
                photoDel.setVisibility(View.VISIBLE);
                Bitmap bmp = FileUtils.getMMR(path);
                if (bmp != null)
                    photoImg.setImageBitmap(bmp);

                videoIcon.setVisibility(View.GONE);
                int status = imageBean.getStatus();
                if(status == 0){
                    tvDoing.setText("上传中...");
                }else if(status == 1){
                    tvDoing.setText("");
                    videoIcon.setVisibility(View.VISIBLE);
                }else if(status == -1){
                    tvDoing.setText("上传中失败...");
                }

            }
            addBottomImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.clickImag();
                    }

                }
            });
            photoDel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.removeVideo(imageBean);
                    }
                }
            });
        }
    }
}
