package com.guanshaoye.glglteacher.listener;

import android.view.View;

import com.guanshaoye.glglteacher.utils.view.FlowTagLayout;

/**
 * Created by karl on 2017/5/30.
 */

public interface OnTagClickListener {
    void onItemClick(FlowTagLayout parent, View view, int position);
}
