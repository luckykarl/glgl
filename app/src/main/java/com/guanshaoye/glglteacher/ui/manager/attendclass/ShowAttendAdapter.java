package com.guanshaoye.glglteacher.ui.manager.attendclass;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.guanshaoye.glglteacher.R;
import com.guanshaoye.glglteacher.bean.ReserveListBean;
import com.guanshaoye.glglteacher.listener.OnClickItemListener;
import com.guanshaoye.glglteacher.utils.PicassoUtils;
import com.guanshaoye.mylibrary.view.CircleImg;

import java.util.List;

/**
 * Created by karl on 2017/5/22.
 */

public class ShowAttendAdapter extends BaseAdapter {
    private LayoutInflater mInflater;

    private Context mContext;
    private boolean isSign;
    private List<ReserveListBean> reserve_list;
    private OnClickItemListener listener;
    public ShowAttendAdapter(Context mContext,boolean mis,List<ReserveListBean> mreserve_list,OnClickItemListener onInitSelectedPosition) {
        this.mInflater = LayoutInflater.from(mContext);
        this.mContext = mContext;
        this.isSign = mis;
        this.reserve_list = mreserve_list;
        this.listener = onInitSelectedPosition;
    }

    @Override
    public int getCount() {
        return reserve_list.size();
    }

    @NonNull
    @Override
    public Object getItem(int position) {
        return reserve_list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Nullable
    @Override
    public View getView(final int position, @Nullable View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (null == convertView) {
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.item_showattend_lay, null);
            holder.mphoto_img = (CircleImg) convertView.findViewById(R.id.photo_img);
            holder.mtv_name = (TextView) convertView.findViewById(R.id.tv_name);
            holder.mtv_levle = (TextView) convertView.findViewById(R.id.tv_levle);
            holder.mtv_classname = (TextView) convertView.findViewById(R.id.tv_classname);
            holder.mtv_status = (TextView) convertView.findViewById(R.id.tv_status);
            holder.show_status = (TextView) convertView.findViewById(R.id.show_status);
            holder.show_idnum = (TextView) convertView.findViewById(R.id.show_idnum);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        ReserveListBean bean = reserve_list.get(position);
        PicassoUtils.showPersionImg(mContext, bean.getGsy_memberportrait(), holder.mphoto_img);
        holder.mtv_name.setText(bean.getGsy_realname());
        holder.mtv_levle.setText("(" + bean.getGsy_level() + "级)");
        holder.mtv_classname.setText(bean.getGsy_course_class_name());

        if (isSign) {
            holder.show_status.setVisibility(View.VISIBLE);
            holder.show_idnum.setVisibility(View.VISIBLE);
            holder.mtv_status.setVisibility(View.GONE);
            holder.show_idnum.setText("保险单号" + bean.getGsy_insurance_no());
        } else {
            final int status = bean.getGsy_signin_status();
            if (status == 1) {  //已签到
//                holder.mtv_status.setText("已签到");
//                holder.mtv_status.setBackgroundResource(R.drawable.shape_green_item)
                holder.show_status.setVisibility(View.VISIBLE);
                holder.show_idnum.setVisibility(View.VISIBLE);
                holder.mtv_status.setVisibility(View.GONE);
                holder.show_idnum.setText("保险单号" + bean.getGsy_insurance_no());
            } else if (status == 2) { //未签到
                holder.mtv_status.setText("未签到");
                holder.mtv_status.setBackgroundResource(R.drawable.shape_red_item);
                holder.show_status.setVisibility(View.GONE);
                holder.show_idnum.setVisibility(View.GONE);
                holder.mtv_status.setVisibility(View.VISIBLE);
            }
            holder.mtv_status.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (status == 2) { //未签到
//                        signAttendClass(position, bean);
                        listener.onItemClick(position);
                    }
                }
            });
        }

        return convertView;
    }



     class ViewHolder {
         CircleImg mphoto_img;
         TextView mtv_name;
         TextView mtv_levle;
         TextView mtv_classname;
         TextView mtv_status;
         TextView show_status;
         TextView show_idnum;

    }
}