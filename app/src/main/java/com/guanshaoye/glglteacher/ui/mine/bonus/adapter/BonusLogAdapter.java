package com.guanshaoye.glglteacher.ui.mine.bonus.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.guanshaoye.glglteacher.R;
import com.guanshaoye.glglteacher.bean.Bonus;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import butterknife.ButterKnife;

/**
 * Created by ${张梦博} on 2017/5/17.
 * Whenever,Wherever,Whatever,I believe I can handle everything
 */

public class BonusLogAdapter extends RecyclerArrayAdapter<Bonus> {
    public BonusLogAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent);
    }
    protected class ViewHolder extends BaseViewHolder<Bonus>{

        public ViewHolder(ViewGroup group) {
            super(group, R.layout.item_bonus_log);
            ButterKnife.bind(this,this.itemView);
        }

        @Override
        public void setData(Bonus data) {
            super.setData(data);
        }
    }
}
