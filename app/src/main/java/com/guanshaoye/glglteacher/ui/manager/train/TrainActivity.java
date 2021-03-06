package com.guanshaoye.glglteacher.ui.manager.train;

import android.support.v4.app.FragmentManager;
import android.widget.TextView;

import com.guanshaoye.glglteacher.R;
import com.guanshaoye.glglteacher.utils.view.BarEntity;
import com.guanshaoye.glglteacher.utils.view.BottomTabBar;
import com.guanshaoye.mylibrary.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by karl
 * 培训签到
 */

public class TrainActivity extends BaseActivity implements BottomTabBar.OnSelectListener {
    @Bind(R.id.tb)
    BottomTabBar tb;
    @Bind(R.id.normal_title)
    TextView normalTitle;
    private FragmentManager manager;
    private List<BarEntity> bars;

    private TrainNoSignFragment noRegisterFragment;
    private TrainSignFragment registerFragment;

    @Override
    public void init() {
        setContentView(R.layout.teacher_review_layout);
        normalTitle.setText("培训签到");
        manager = getSupportFragmentManager();
        tb.setManager(manager);
        tb.setOnSelectListener(this);
        bars = new ArrayList<>();
        bars.add(new BarEntity("未签到", 0, 0));
        bars.add(new BarEntity("已签到", 0, 0));
        tb.setBars(bars);
    }

    @Override
    public void onSelect(int position) {

        switch (position) {
            case 0:
                if (noRegisterFragment == null) {
                    noRegisterFragment = new TrainNoSignFragment();
                }
                tb.switchContent(noRegisterFragment);

                break;
            case 1:
                if (registerFragment == null) {
                    registerFragment = new TrainSignFragment();
                }
                tb.switchContent(registerFragment);

                break;
        }
    }

}
