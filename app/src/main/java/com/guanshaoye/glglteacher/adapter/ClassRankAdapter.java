package com.guanshaoye.glglteacher.adapter;

import android.app.Activity;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.guanshaoye.glglteacher.R;
import com.guanshaoye.glglteacher.bean.GsyLevelListBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Created by ${张梦博} on 2017/5/15.
 * Whenever,Wherever,Whatever,I believe I can handle everything
 */

public class ClassRankAdapter extends RecyclerView.Adapter implements View.OnClickListener {
    public  List<GsyLevelListBean> levelList;
    private List<Boolean> isClicks;//控件是否被点击,默认为false，如果被点击，改变值，控件根据值改变自身颜色
    private OnRecyclerViewItemClickListener mOnItemClickListener = null;

    public interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, GsyLevelListBean data);
    }

    public ClassRankAdapter(Activity activity) {
        isClicks = new ArrayList<>();
        levelList = new ArrayList<>();

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rank, parent, false);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        for (int i = 0; i < levelList.size(); i++) {
                if (i == levelList.size()-1) {
                    isClicks.add(true);
                } else {
                    isClicks.add(false);
                }
        }
        holder.itemView.setTag(levelList.get(position));

        // 如果设置了回调，则设置点击事件
        if (mOnItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (int i = 0; i < levelList.size(); i++) {
                        isClicks.set(i, false);
                    }
                    isClicks.set(position, true);
                    notifyDataSetChanged();
                    mOnItemClickListener.onItemClick(holder.itemView, levelList.get(position));
                }
            });
        }
        ((ClassRankAdapter.ViewHolder) holder).bindView(position);
    }

    @Override
    public int getItemCount() {
        return levelList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.tv_rank)
        TextView tvRank;
        ViewHolder(View view) {
            super(view);

            ButterKnife.bind(this, view);
        }

        public void bindView(int position) {
            tvRank.setText(levelList.get(position).getLevel());
            if (isClicks.get(position)) {
                tvRank.setTextColor(Color.parseColor("#ffffff"));
                tvRank.setBackgroundResource(R.drawable.shape_red_textview);
            } else {
                tvRank.setTextColor(Color.parseColor("#313131"));
                tvRank.setBackgroundResource(R.drawable.shape_white);
            }
        }
    }

    @Override
    public void onClick(View v) {
// TODO Auto-generated method stub
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取数据
            mOnItemClickListener.onItemClick(v, (GsyLevelListBean) v.getTag());

        }
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }
}