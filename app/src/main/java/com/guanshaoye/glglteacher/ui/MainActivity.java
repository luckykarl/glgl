package com.guanshaoye.glglteacher.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.alibaba.fastjson.JSON;
import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.listener.CustomListener;
import com.guanshaoye.glglteacher.R;
import com.guanshaoye.glglteacher.bean.ArticleListBean;
import com.guanshaoye.glglteacher.bean.BannerBean;
import com.guanshaoye.glglteacher.bean.CardBean;
import com.guanshaoye.glglteacher.bean.CoursewareBean;
import com.guanshaoye.glglteacher.bean.HomeBean;
import com.guanshaoye.glglteacher.bean.ManagementBean;
import com.guanshaoye.glglteacher.bean.TakelessonsBean;
import com.guanshaoye.glglteacher.bean.WorkBean;
import com.guanshaoye.glglteacher.ui.manager.WorkManageActivity;
import com.guanshaoye.glglteacher.ui.message.MessageActivity;
import com.guanshaoye.glglteacher.ui.mine.MeActivity;
import com.guanshaoye.glglteacher.ui.mine.feedback.WebViewActivity;
import com.guanshaoye.glglteacher.ui.mine.tactless.RobClassActivity;
import com.guanshaoye.glglteacher.ui.mine.timetable.TimeTabeleActivity;
import com.guanshaoye.glglteacher.utils.CurrentUser;
import com.guanshaoye.glglteacher.utils.view.MyPagerGalleryView;
import com.guanshaoye.glglteacher.utils.view.pullrefreshview.AbPullToRefreshView;
import com.guanshaoye.mylibrary.api.HomeApi;
import com.guanshaoye.mylibrary.base.AtyContainer;
import com.guanshaoye.mylibrary.base.BaseActivity;
import com.guanshaoye.mylibrary.http.BaseUrl;
import com.guanshaoye.mylibrary.http.FlpBack;
import com.guanshaoye.mylibrary.http.FlpException;
import com.guanshaoye.mylibrary.http.RequestBack;
import com.guanshaoye.mylibrary.utils.Toaster;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ${张梦博} on 2017/5/11.
 * Whenever,Wherever,Whatever,I believe I can handle everything
 */

public class MainActivity extends BaseActivity {


    @Bind(R.id.btn_more_message)
    ImageView btnMoreMessage;
    @Bind(R.id.btn_rob_class)
    ImageView btnRobClass;
    @Bind(R.id.btn_work)
    ImageView btnWork;
    @Bind(R.id.btn_manage)
    ImageView btnManage;
    @Bind(R.id.btn_mine)
    ImageView btnMine;
    @Bind(R.id.btn_course)
    ImageView btnCourse;
    @Bind(R.id.msg_lay)
    RelativeLayout msgLay;
    @Bind(R.id.refreshLayout)
    AbPullToRefreshView refreshLayout;
    @Bind(R.id.fragmen_lay)
    FrameLayout fragmenLay;

    // 广告控件
    private MyPagerGalleryView gallery;
    // 圆点容器
    private LinearLayout ovalLayout;
    /**
     * 图片id的数组,本地测试用
     */
    private int[] imageId = new int[]{R.drawable.placeholder_l, R.drawable.placeholder_l, R.drawable.placeholder_l};
    private List<BannerBean> liststemphos = new ArrayList<>();
    private   OptionsPickerView pvCustomOptions;
    @Override
    public void init() {
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        refreshLayout.setLoadMoreEnable(false);
        refreshLayout.setPullRefreshEnable(true);

        btnRobClass.setClickable(false);
        btnWork.setClickable(false);
        btnManage.setClickable(false);
        btnCourse.setClickable(false);

        gallery = (MyPagerGalleryView) findViewById(R.id.adgallery); // 获取Gallery组件
        ovalLayout = (LinearLayout) findViewById(R.id.ovalLayout1);// 获取圆点容器
        gallery.start(MainActivity.this, null, imageId, 3000, ovalLayout, R.drawable.dot_focused, R.drawable.dot_normal);

        gallery.setMyOnItemClickListener(new MyPagerGalleryView.MyOnItemClickListener() {
            @Override
            public void onItemClick(int curIndex) {
                if (liststemphos != null && liststemphos.size() > 0) {
                    String url = liststemphos.get(curIndex).getGsy_banner_url();
                    startActivityForNoResult(new Intent(getActivity(), WebViewActivity.class).putExtra("WEB_URL", url));
                }
            }
        });
        if (CurrentUser.getUser() != null) {
            getShow(CurrentUser.getUser().getTid());
        }

        refreshLayout.setOnHeaderRefreshListener(new AbPullToRefreshView.OnHeaderRefreshListener() {
            @Override
            public void onHeaderRefresh(AbPullToRefreshView view) {

                getShow(CurrentUser.getUser().getTid());

            }
        });
    }

    private void getShow(String uid) {
        HomeApi.getShow(uid, new RequestBack() {
            @Override
            public void onComplete(FlpBack back) {
                refreshLayout.onHeaderRefreshFinish();
                if (back.errorCode == 200) {
                    HomeBean homeBean = JSON.parseObject(back.data, HomeBean.class);
                    if (homeBean != null) {
                        liststemphos.clear();
                        liststemphos.addAll(homeBean.getBanner_list());
                        if (liststemphos.size() > 0) {
                            String[] urlImageList = new String[liststemphos.size()];
                            for (int i = 0; i < liststemphos.size(); i++) {
                                urlImageList[i] = BaseUrl.HEAD_PHOTO + liststemphos.get(i).getGsy_banner_pho_url();
                            }
                            gallery.start(MainActivity.this, urlImageList, imageId, 3000, ovalLayout, R.drawable.dot_focused, R.drawable.dot_normal);
                        }

                        List<ArticleListBean> articleList = homeBean.getArticle_list();
                        if(articleList != null){
                            showarticle(articleList);
                        }
                        TakelessonsBean takelessons = homeBean.getTakelessons();
                        if (takelessons != null && takelessons.getBotton_auth_status() == 1) {
                            btnRobClass.setClickable(true);
                        } else {
                            btnRobClass.setClickable(false);
                        }
                        WorkBean work = homeBean.getWork();
                        if (work != null && work.getBotton_auth_status() == 1) {
                            btnWork.setClickable(true);
                        } else {
                            btnWork.setClickable(false);
                        }

                        ManagementBean management = homeBean.getManagement();
                        if (management != null && management.getBotton_auth_status() == 1) {
                            btnManage.setClickable(true);
                        } else {
                            btnManage.setClickable(false);
                        }
                        CoursewareBean courseware = homeBean.getCourseware();
                        if (courseware != null && courseware.getBotton_auth_status() == 1) {
                            btnCourse.setClickable(true);
                        } else {
                            btnCourse.setClickable(false);
                        }


                    }

                }

            }

            @Override
            public void onFlpException(FlpException e) {
                refreshLayout.onHeaderRefreshFinish();
            }
        });
    }

    @OnClick({R.id.btn_more_message, R.id.btn_rob_class, R.id.btn_work,
            R.id.btn_manage, R.id.btn_mine, R.id.btn_course, R.id.msg_lay})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_more_message:
                startActivityForNoResult(new Intent(getActivity(),MessageActivity.class));
                break;
            case R.id.btn_course:
                break;
            case R.id.btn_rob_class:
                startActivityForNoResult(new Intent(getActivity(), RobClassActivity.class));
                break;
            case R.id.btn_work:
                startActivityForNoResult(new Intent(getActivity(), TimeTabeleActivity.class));
                break;
            case R.id.btn_manage:
                startActivityForNoResult(new Intent(getActivity(), WorkManageActivity.class));
                break;
            case R.id.btn_mine:
                startActivityForNoResult(new Intent(getActivity(), MeActivity.class));
                break;
            case R.id.msg_lay:
                startActivityForNoResult(new Intent(getActivity(), MessageActivity.class));
                break;
        }
    }


    private void showarticle(List<ArticleListBean> article_list) {

        OptionsPickerView.mGravity = Gravity.LEFT;
        pvCustomOptions = new OptionsPickerView.Builder(getActivity(), new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
            }
        })
                .setLayoutRes(R.layout.pickerview_custom_options, new CustomListener() {
                    @Override
                    public void customLayout(View v) {
                    }
                })
                .setDecorView(fragmenLay)
                .setContentTextSize(10)
                .setSelectOptions(3)
                .setCyclic(true,false,false)
                .isDialog(false)
                .build();
        pvCustomOptions.setPicker(article_list);//添加数据
        pvCustomOptions.show(false);
    }

}
