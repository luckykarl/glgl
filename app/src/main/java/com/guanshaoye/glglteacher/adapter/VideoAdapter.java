package com.guanshaoye.glglteacher.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.guanshaoye.glglteacher.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ${张梦博} on 2017/5/13.
 * Whenever,Wherever,Whatever,I believe I can handle everything
 */

public class VideoAdapter extends RecyclerView.Adapter {
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_video, null);
        return new ViewHolder(view);
      }
        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

         }
        @Override
        public int getItemCount(){
            return 4;
           }
         class ViewHolder  extends RecyclerView.ViewHolder{
            @Bind(R.id.img_video)
            ImageView imgVideo;
             public  ViewHolder(View view){
              super(view);
              ButterKnife.bind(this, view);
            }
        }
}
