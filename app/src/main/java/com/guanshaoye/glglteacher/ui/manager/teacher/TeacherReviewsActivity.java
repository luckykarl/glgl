package com.guanshaoye.glglteacher.ui.manager.teacher;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.widget.TextView;

import com.guanshaoye.glglteacher.R;
import com.guanshaoye.glglteacher.utils.view.BarEntity;
import com.guanshaoye.glglteacher.utils.view.BottomTabBar;
import com.guanshaoye.mylibrary.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by karl
 * 老师评价
 */

public class TeacherReviewsActivity extends BaseActivity implements BottomTabBar.OnSelectListener {
    @Bind(R.id.tb)
    BottomTabBar tb;
    @Bind(R.id.normal_title)
    TextView normalTitle;
    private FragmentManager manager;
    private List<BarEntity> bars;

    private TeacherNoReviewFragment noReviewFragment;
    private TeacherReviewFragment haveReviewFragment;

    @Override
    public void init() {
        setContentView(R.layout.teacher_review_layout);
        normalTitle.setText("老师评价");
        manager = getSupportFragmentManager();
        tb.setManager(manager);
        tb.setOnSelectListener(this);
        bars = new ArrayList<>();
        bars.add(new BarEntity("未评价", 0, 0));
        bars.add(new BarEntity("已评价", 0, 0));
        tb.setBars(bars);
    }

    @Override
    public void onSelect(int position) {

        switch (position) {
            case 0:
                if (noReviewFragment == null) {
                    noReviewFragment = new TeacherNoReviewFragment();
                }
                tb.switchContent(noReviewFragment);

                break;
            case 1:
                if (haveReviewFragment == null) {
                    haveReviewFragment = new TeacherReviewFragment();
                }
                tb.switchContent(haveReviewFragment);

                break;
        }
    }

}
