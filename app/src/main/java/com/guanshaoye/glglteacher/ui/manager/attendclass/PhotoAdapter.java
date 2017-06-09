package com.guanshaoye.glglteacher.ui.manager.attendclass;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.guanshaoye.glglteacher.R;
import com.guanshaoye.glglteacher.bean.ImageBean;
import com.guanshaoye.glglteacher.utils.PicassoUtils;
import java.util.HashMap;
import java.util.List;

/**
 * Created by karl on 2017/5/26.
 */

public class PhotoAdapter extends BaseAdapter {
    private final Context mContext;
    private final LayoutInflater mInflater;
    private MediaHolder holder;
    private String strFileUrl;
    private List<ImageBean> imgPaths;
    private final int mMediaSize;
    private String videoPath;

    private final PhotoAdapterCallback mCallback;
    private boolean showFlag;
    public PhotoAdapter(Context context,List<ImageBean> mmPost,int MediaSize,String mvideoPath,boolean mshowFlag,PhotoAdapterCallback callback) {
        this.mContext = context;
        this.imgPaths=mmPost;
        mMediaSize=MediaSize;
        mCallback=callback;
        videoPath=mvideoPath;
        this.showFlag = mshowFlag;
        this.mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        if (0 == imgPaths.size())
            return 1;
        else if(imgPaths.size() < 4)
            return imgPaths.size()+1;
        else
            return imgPaths.size();
    }

    @Override
    public Object getItem(int position) {
        if (0== imgPaths.size() || position >= imgPaths.size())
            return 0;
        else
            return imgPaths.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (position >= imgPaths.size()) {
            // 显示十字按钮
            convertView = mInflater.inflate(R.layout.gridview_item_plus, null);
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!showFlag)
                    mCallback.clickImag();
                }
            });

            if(imgPaths.size() == 4){
                convertView.setVisibility(View.GONE);
            }else{
                convertView.setVisibility(View.VISIBLE);
            }
            if (showFlag)
                convertView.setVisibility(View.GONE);
        }else {
            if (convertView == null || convertView.getTag() == null) {
                convertView = mInflater.inflate(R.layout.photo_list_item, null);
                holder = new MediaHolder();
                holder.image = (ImageView) convertView.findViewById(R.id.photo_list_item_img);
                holder.delete = (ImageView) convertView.findViewById(R.id.photo_list_item_del);
                holder.videoView= (ImageView) convertView.findViewById(R.id.videoView);
                convertView.setTag(holder);
            } else {
                holder = (MediaHolder) convertView.getTag();
            }
            holder.videoView.setVisibility(View.GONE);
            holder.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    imgPaths.remove(position);
                    notifyDataSetChanged();
                }
            });
            if (!showFlag)
            holder.delete.setVisibility(View.VISIBLE);
            holder.videoView.setVisibility(View.GONE);
            ImageBean clip = imgPaths.get(position);
            if (clip.isPhoto()) {
                // 图片
                if (!TextUtils.isEmpty(clip.url)) {
                    PicassoUtils.showPhoto(mContext,clip.url,holder.image);
                }else {
                    PicassoUtils.showPhoto(mContext,"file://"+clip.path,holder.image);
                }

            } else {
                // 视频
                    // 新增加
//                    strFileUrl = clip.getPath();
//                    holder.image.setTag(R.id.tag_imageview_url, strFileUrl);
//                    Bitmap bmp = createVideoThumbnail(strFileUrl);
//                    holder.image.setImageBitmap(bmp);
//                    holder.videoView.setVisibility(View.VISIBLE);

            }

        }

        convertView.setLayoutParams(new AbsListView.LayoutParams(mMediaSize, mMediaSize));
        return convertView;
    }

    class MediaHolder {
        ImageView image, delete,videoView;
    }

    public void setFlag(boolean b){
        showFlag = b;
    }
    public interface PhotoAdapterCallback {
        void setSelected_record();
        void setSelected_photography();
        void clickImag();
    }
}