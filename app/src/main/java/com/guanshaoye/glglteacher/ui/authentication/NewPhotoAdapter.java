package com.guanshaoye.glglteacher.ui.authentication;

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
import com.guanshaoye.glglteacher.ui.manager.attendclass.PhotoAdapter;
import com.guanshaoye.glglteacher.utils.PicassoUtils;

import java.util.HashMap;
import java.util.List;

/**
 * Created by karl on 2017/6/2.
 */

public class NewPhotoAdapter extends BaseAdapter {
    private final Context mContext;
    private final LayoutInflater mInflater;
    private MediaHolder holder;
    private List<ImageBean> imgPaths;
    private final int mMediaSize;

    private final VideoPhotoAdapterCallback mCallback;
    public NewPhotoAdapter(Context context,List<ImageBean> mmPost,int MediaSize,VideoPhotoAdapterCallback callback) {
        this.mContext = context;
        this.imgPaths=mmPost;
        mMediaSize=MediaSize;
        mCallback=callback;
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
            convertView = mInflater.inflate(R.layout.gridview_item_add, null);
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                        mCallback.clickImag();
                }
            });

            if(imgPaths.size() == 4){
                convertView.setVisibility(View.GONE);
            }else{
                convertView.setVisibility(View.VISIBLE);
            }
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
                     String  strFileUrl = clip.path;
                    holder.image.setTag(R.id.tag_imageview_url, strFileUrl);
                    Bitmap bmp = createVideoThumbnail(strFileUrl);
                    holder.image.setImageBitmap(bmp);
                    holder.videoView.setVisibility(View.VISIBLE);

            }

        }

        convertView.setLayoutParams(new AbsListView.LayoutParams(mMediaSize, mMediaSize));
        return convertView;
    }

    class MediaHolder {
        ImageView image, delete,videoView;
    }

    // 得到视频截图
    public Bitmap createVideoThumbnail(String url) {
        Bitmap bitmap;
        int kind = MediaStore.Video.Thumbnails.FULL_SCREEN_KIND;
        bitmap = ThumbnailUtils.createVideoThumbnail(url, kind);
        bitmap = ThumbnailUtils.extractThumbnail(bitmap, 480, 480, ThumbnailUtils.OPTIONS_RECYCLE_INPUT);

        return bitmap;
    }
    private boolean isNotPhoto(@NonNull String path) {
        return TextUtils.isEmpty(path) || (!path.endsWith(".jpg") && !path.endsWith(".jpeg") && !path.endsWith(".JPEG") && !path.endsWith(".JPG") && !path.endsWith(".png") && !path.endsWith(".gif"));
    }
    // 从url中得到文件名称
    private  String getFileNameFromUrl(@NonNull String strUrl) {
        String str;
        int pos1 = strUrl.lastIndexOf('/');
        int pos2 = strUrl.lastIndexOf('\\');
        int pos = Math.max(pos1, pos2);
        if (pos < 0) {
            str = strUrl;
        } else {
            str = strUrl.substring(pos + 1);
        }
        return str;
    }

    public interface VideoPhotoAdapterCallback {
        void clickImag();
    }
}
