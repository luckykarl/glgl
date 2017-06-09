package com.guanshaoye.glglteacher.ui.manager.teacher;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.guanshaoye.glglteacher.R;
import com.guanshaoye.glglteacher.bean.CommentPicBean;
import com.guanshaoye.glglteacher.bean.MemberCommentListBean;
import com.guanshaoye.glglteacher.utils.PicassoUtils;
import com.guanshaoye.glglteacher.utils.view.FlowTagLayout;
import com.guanshaoye.glglteacher.utils.view.MyGridView;
import com.guanshaoye.glglteacher.utils.view.RatingBar;
import com.guanshaoye.mylibrary.view.CircleImg;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by karl on 2017/5/22.
 */

public class CommentAdapter extends BaseAdapter {
    private LayoutInflater mInflater;

    private Context mContext;
    private List<MemberCommentListBean> list;

    public CommentAdapter(Context mContext, List<MemberCommentListBean> mlist) {
        this.mInflater = LayoutInflater.from(mContext);
        this.mContext = mContext;
        this.list = mlist;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @NonNull
    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Nullable
    @Override
    public View getView(int position, @Nullable View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (null == convertView) {
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.item_comment_lay, null);
            holder.photoImg = (CircleImg) convertView.findViewById(R.id.photo_img);
            holder.tvName = (TextView) convertView.findViewById(R.id.tv_name);
            holder.rb = (RatingBar) convertView.findViewById(R.id.rb);
            holder.tagviewHoat = (FlowTagLayout) convertView.findViewById(R.id.tagview_hoat);
            holder.recyclerview = (RecyclerView) convertView.findViewById(R.id.recyclerview);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        MemberCommentListBean bean = list.get(position);
        PicassoUtils.showPersionImg(mContext, bean.getGsy_memberportrait(), holder.photoImg);
        holder.tvName.setText(bean.getGsy_realname());

        int startNum = bean.getGsy_star_num();
        holder.rb.setStar(startNum);

        TagAdapter tagAdapter = new TagAdapter<>(mContext);
        holder.tagviewHoat.setAdapter(tagAdapter);
        List<String> dataSource = new ArrayList<>();
        if (bean.getGsy_hudong_item() == 1) {
            dataSource.add("互动性");
        }
        if (bean.getGsy_jishu_item() == 1) {
            dataSource.add("技术示范");
        }
        if (bean.getGsy_jiaoxue_item() == 1) {
            dataSource.add("教学表达");
        }
        if (bean.getGsy_xunlian_item() == 1) {
            dataSource.add("训练气氛");
        }
        if (bean.getGsy_neirong_item() == 1) {
            dataSource.add("教学内容丰富");
        }
        tagAdapter.onlyAddAll(dataSource);

        List<CommentPicBean> Pic_list = bean.getPic_list();
        if (Pic_list != null && Pic_list.size() > 0) {
            holder.recyclerview.setLayoutManager(new GridLayoutManager(mContext, 4));
            ShowPhotoAdapter photoAdapter = new ShowPhotoAdapter(mContext);
            holder.recyclerview.setAdapter(photoAdapter);
            photoAdapter.setDataList(bean.getPic_list());
        }
//        List<CommentPicBean> Pic_list = new ArrayList<>();
//        for(int i=0;i<4;i++){
//            CommentPicBean commentPicBean = new CommentPicBean();
//            commentPicBean.setGsy_pic_url("android_pic1496723343624.png");
//            Pic_list.add(commentPicBean);
//        }
//        holder.recyclerview.setLayoutManager(new GridLayoutManager(mContext, 4));
//        ShowPhotoAdapter photoAdapter = new ShowPhotoAdapter(mContext);
//        holder.recyclerview.setAdapter(photoAdapter);
//        photoAdapter.setDataList(Pic_list);
        return convertView;
    }


    class ViewHolder {
        CircleImg photoImg;
        TextView tvName;
        RatingBar rb;
        FlowTagLayout tagviewHoat;
        RecyclerView recyclerview;

    }
}