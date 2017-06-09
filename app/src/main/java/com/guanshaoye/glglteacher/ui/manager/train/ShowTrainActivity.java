package com.guanshaoye.glglteacher.ui.manager.train;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import com.alibaba.fastjson.JSON;
import com.guanshaoye.glglteacher.R;
import com.guanshaoye.glglteacher.adapter.OnTrainItemClickListener;
import com.guanshaoye.glglteacher.bean.TrainBean;
import com.guanshaoye.glglteacher.utils.CurrentUser;
import com.guanshaoye.glglteacher.utils.LoadingDialog;
import com.guanshaoye.mylibrary.api.ManagerApi;
import com.guanshaoye.mylibrary.base.BaseActivity;
import com.guanshaoye.mylibrary.http.FlpBack;
import com.guanshaoye.mylibrary.http.FlpException;
import com.guanshaoye.mylibrary.http.RequestBack;
import com.guanshaoye.mylibrary.utils.Toaster;

import java.util.List;

import butterknife.Bind;

/**
 * Created by karl on 2017/5/24.
 */

public class ShowTrainActivity extends BaseActivity implements OnTrainItemClickListener{
    @Bind(R.id.recyclerview)
    RecyclerView recyclerview;
    @Bind(R.id.normal_title)
    TextView normalTitle;
    private ShowTrainAdapter adapter;
    private TrainBean trainBean;
    @Override
    public void init() {
        setContentView(R.layout.show_train_layout);
        normalTitle.setText("培训签到");

        adapter = new ShowTrainAdapter(this);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        recyclerview.setAdapter(adapter);
        adapter.setOnItemClickListener(this);

        trainBean = (TrainBean) getIntent().getSerializableExtra("model");
        if (trainBean == null){
            return;
        }
        LoadingDialog.ShowLoading(getActivity());
        getTrainTeacherList();
    }

    /**
     * 参与培训老师接口
     */
    private void getTrainTeacherList(){

        ManagerApi.getTrainTeacherList(CurrentUser.getUser().getTid(), trainBean.getGsy_train_id(), new RequestBack() {
            @Override
            public void onComplete(FlpBack back) {
                LoadingDialog.DissLoading(getActivity());
                if (back.errorCode == 200){
                    List<TrainBean> beanList = JSON.parseArray(back.data,TrainBean.class);
                    if(beanList != null){
                        adapter.setDataList(beanList);
                    }
                }
                Toaster.showToast(back.message);
            }

            @Override
            public void onFlpException(FlpException e) {
                LoadingDialog.DissLoading(getActivity());
            }
        });
    }

    /**
     * 培训签到接口
     * @param bean
     */
    private void signTrain(TrainBean bean){
        LoadingDialog.ShowLoading(getActivity());
        ManagerApi.signTrain(CurrentUser.getUser().getTid(), bean.getGsy_train_detail_id(), new RequestBack() {
            @Override
            public void onComplete(FlpBack back) {
                LoadingDialog.DissLoading(getActivity());
                if (back.errorCode == 200){
                    getTrainTeacherList();
                }
                Toaster.showToast(back.message);
            }

            @Override
            public void onFlpException(FlpException e) {
                LoadingDialog.DissLoading(getActivity());
            }
        });
    }

    @Override
    public void onItemClick(TrainBean bean) {
        if(bean == null){
            return;
        }
        signTrain(bean);
    }
}
