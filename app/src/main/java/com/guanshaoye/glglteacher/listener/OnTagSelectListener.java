package com.guanshaoye.glglteacher.listener;

import com.guanshaoye.glglteacher.utils.view.FlowTagLayout;

import java.util.List;

/**
 * Created by karl on 2017/5/30.
 */

public interface OnTagSelectListener {
    void onItemSelect(FlowTagLayout parent, List<Integer> selectedList);
}

