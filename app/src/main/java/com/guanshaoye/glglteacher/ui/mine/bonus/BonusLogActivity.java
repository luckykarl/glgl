package com.guanshaoye.glglteacher.ui.mine.bonus;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.TextView;

import com.guanshaoye.glglteacher.R;
import com.guanshaoye.glglteacher.ui.mine.bonus.adapter.BonusLogAdapter;
import com.guanshaoye.glglteacher.bean.Bonus;
import com.guanshaoye.mylibrary.base.BaseActivity;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by ${张梦博} on 2017/5/17.
 * Whenever,Wherever,Whatever,I believe I can handle everything
 */

public class BonusLogActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener,RecyclerArrayAdapter.OnLoadMoreListener {
    @Bind(R.id.normal_title)
    TextView normalTitle;
    @Bind(R.id.bonus_log_recyclerView)
    EasyRecyclerView bonusLogRecyclerView;
    BonusLogAdapter mBonusLogAdapter;
    List<Bonus> mBonusList=new ArrayList<>();
    @Override
    public void init() {
        setContentView(R.layout.activity_bonus_log);
        normalTitle.setText("分红记录");
        bonusLogRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        bonusLogRecyclerView.setAdapterWithProgress(mBonusLogAdapter=new BonusLogAdapter(this));
        onRefresh();
    }


    @Override
    public void onRefresh() {
        for (int i = 0; i <5; i++) {
            Bonus bo=new Bonus();
            mBonusList.add(bo);
        }
        mBonusLogAdapter.clear();
        mBonusLogAdapter.addAll(mBonusList);
        mBonusLogAdapter.notifyDataSetChanged();
    }

    @Override
    public void onLoadMore() {

    }
}
