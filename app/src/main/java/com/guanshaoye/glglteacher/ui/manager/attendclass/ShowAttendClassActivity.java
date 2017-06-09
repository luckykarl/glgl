package com.guanshaoye.glglteacher.ui.manager.attendclass;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.alibaba.fastjson.JSON;
import com.alibaba.sdk.android.oss.OSS;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSPlainTextAKSKCredentialProvider;
import com.guanshaoye.glglteacher.R;
import com.guanshaoye.glglteacher.bean.AttendClassBean;
import com.guanshaoye.glglteacher.bean.ImageBean;
import com.guanshaoye.glglteacher.bean.PicBean;
import com.guanshaoye.glglteacher.bean.ReserveListBean;
import com.guanshaoye.glglteacher.bean.ShowAttendClassBean;
import com.guanshaoye.glglteacher.listener.OnClickItemListener;
import com.guanshaoye.glglteacher.ui.mine.EditUserInfoActivity;
import com.guanshaoye.glglteacher.utils.CurrentUser;
import com.guanshaoye.glglteacher.utils.FileUtils;
import com.guanshaoye.glglteacher.utils.LoadingDialog;
import com.guanshaoye.glglteacher.utils.OSSConfigure;
import com.guanshaoye.glglteacher.utils.PicassoUtils;
import com.guanshaoye.glglteacher.utils.TakePhotoUtils;
import com.guanshaoye.glglteacher.utils.aliyun.PutObjectSamples;
import com.guanshaoye.glglteacher.utils.view.ActionSheetDialog;
import com.guanshaoye.glglteacher.utils.view.MyGridView;
import com.guanshaoye.glglteacher.utils.view.pullrefreshview.AbPullToRefreshView;
import com.guanshaoye.mylibrary.api.ManagerApi;
import com.guanshaoye.mylibrary.base.BaseActivity;
import com.guanshaoye.mylibrary.http.BaseUrl;
import com.guanshaoye.mylibrary.http.FlpBack;
import com.guanshaoye.mylibrary.http.FlpException;
import com.guanshaoye.mylibrary.http.RequestBack;
import com.guanshaoye.mylibrary.utils.CheckPermission;
import com.guanshaoye.mylibrary.utils.TakePhotoUpload;
import com.guanshaoye.mylibrary.utils.Toaster;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by karl on 2017/5/26.
 */

public class ShowAttendClassActivity extends BaseActivity implements PhotoAdapter.PhotoAdapterCallback,OnClickItemListener {
    @Bind(R.id.normal_title)
    TextView normalTitle;

    MyGridView gridMedia;
    ImageView sureImg;
    @Bind(R.id.listView)
    ListView listView;
    @Bind(R.id.refreshLayout)
    AbPullToRefreshView refreshLayout;

    private ShowAttendAdapter showAttendAdapter;
    private List<ReserveListBean> reserve_list = new ArrayList<>();
    private ShowAttendClassBean showAttend;
    private AttendClassBean attendClass;
    private boolean isSign;
    private boolean isShowPhoto;
    private int mMediaSize;
    private DisplayMetrics mMetrics;
    private PhotoAdapter photoAdapter;
    private String videoPath;
    private String path;
    private Uri photoUri;
    private static final int TAKE_PICTURE = 0;
    private List<ImageBean> imgPaths = new ArrayList<>();
    private OSS oss;

    @Override
    public void init() {
        setContentView(R.layout.show_attendclass_layout);
        normalTitle.setText("上课签到");
        initView();
    }

    private void initView() {
        OSSCredentialProvider credentialProvider = new OSSPlainTextAKSKCredentialProvider(OSSConfigure.accessKeyId, OSSConfigure.accessKeySecret);
        oss = new OSSClient(getActivity(), OSSConfigure.endpoint, credentialProvider);


        isSign = getIntent().getBooleanExtra("showFlag", false);
        attendClass = (AttendClassBean) getIntent().getSerializableExtra("model");
        if (attendClass == null) {
            return;
        }

        refreshLayout.setLoadMoreEnable(false);
        refreshLayout.setPullRefreshEnable(false);

        View headerView = LayoutInflater.from(getActivity()).inflate(R.layout.show_attendclass_header_lay, null);
        ImageView photo_img = (ImageView) headerView.findViewById(R.id.photo_img);
        TextView tv_name = (TextView) headerView.findViewById(R.id.tv_name);
        TextView tv_levle = (TextView) headerView.findViewById(R.id.tv_levle);
        TextView tv_teacher = (TextView) headerView.findViewById(R.id.tv_teacher);
        TextView tv_location = (TextView) headerView.findViewById(R.id.tv_location);
        TextView tv_time = (TextView) headerView.findViewById(R.id.tv_time);

        PicassoUtils.showPersionImg(getActivity(), attendClass.getGsy_teacher_portrait(), photo_img);
        tv_name.setText(attendClass.getGsy_course_class_name());
        tv_levle.setText("(" + attendClass.getGsy_course_level() + "级)");
        tv_time.setText(attendClass.getCourse_time());
        tv_teacher.setText(attendClass.getGsy_teacher_name());
        tv_location.setText(attendClass.getGsy_store_name());

        mMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(mMetrics);
        float ds = mMetrics.density;
        View footerView = LayoutInflater.from(getActivity()).inflate(R.layout.show_attendclass_footer_lay, null);
        gridMedia = (MyGridView) footerView.findViewById(R.id.grid_media);
        sureImg = (ImageView) footerView.findViewById(R.id.sure_img);
        sureImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pushSignPic();
            }
        });
        gridMedia.setVerticalSpacing((int) (3 * ds));
        gridMedia.setHorizontalSpacing((int) (3 * ds));
        mMediaSize = (mMetrics.widthPixels - (int) (30 * ds) - (int) (9 * ds)) / 4;
        photoAdapter = new PhotoAdapter(getActivity(), imgPaths, mMediaSize, videoPath, isShowPhoto, this);
        gridMedia.setAdapter(photoAdapter);
        gridMedia.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                HubUtil.showImage(NewPostActivity.this, mPost.getPhoto(), position);
            }
        });

        showAttendAdapter = new ShowAttendAdapter(getActivity(),isSign,reserve_list,this);
        listView.setAdapter(showAttendAdapter);
        listView.addHeaderView(headerView);
        listView.addFooterView(footerView);

        sureImg.setVisibility(View.GONE);

        getTAttendClassList();
    }

    private void initViewDate() {
        List<ReserveListBean> rese = showAttend.getReserve_list();
        if (rese != null ){
            reserve_list.clear();
            reserve_list.addAll(rese);
            showAttendAdapter.notifyDataSetChanged();
        }

            List<PicBean> listPic = showAttend.getPic_list();
            if (listPic != null && listPic.size() > 0) {
                isShowPhoto = true;
                photoAdapter.setFlag(isShowPhoto);
                sureImg.setVisibility(View.GONE);
                for (PicBean bean : listPic) {
                    ImageBean imageBean = new ImageBean();
                    imageBean.path = bean.getGsy_pic_url();
                    imageBean.url = BaseUrl.HEAD_PHOTO_OSS + bean.getGsy_pic_url();
                    imgPaths.add(imageBean);
                }
                photoAdapter.notifyDataSetChanged();
            }else{
                sureImg.setVisibility(View.VISIBLE);
            }


    }

    /**
     * 获取学员签到列表
     */
    private void getTAttendClassList() {
        LoadingDialog.ShowLoading(getActivity());
        ManagerApi.AttendClassList(CurrentUser.getUser().getTid(), attendClass.getGsy_course_schedule_detail_id(), new RequestBack() {
            @Override
            public void onComplete(FlpBack back) {
                LoadingDialog.DissLoading(getActivity());
                if (back.errorCode == 200) {
                    showAttend = JSON.parseObject(back.data, ShowAttendClassBean.class);
                    if (showAttend != null) {
                        initViewDate();
                    }
                }

            }

            @Override
            public void onFlpException(FlpException e) {
                LoadingDialog.DissLoading(getActivity());
            }
        });

    }

    /**
     * 添加签到
     *
     * @param position
     */
    private void signAttendClass(final int position) {
        ReserveListBean bean = reserve_list.get(position);
        LoadingDialog.ShowLoading(getActivity());
        ManagerApi.signAttendClass(CurrentUser.getUser().getTid(), bean.getGsy_course_reserve_id(), new RequestBack() {
            @Override
            public void onComplete(FlpBack back) {
                LoadingDialog.DissLoading(getActivity());
                if (back.errorCode == 200) {
                    reserve_list.get(position).setGsy_signin_status(1);
                    initViewDate();
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
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case TakePhotoUtils.CHECKPHOTO7:
                // 判断手机系统版本号
                if (Build.VERSION.SDK_INT >= 19) {
                    // 4.4及以上系统使用这个方法处理图片
                    TakePhotoUtils.handleImageOnKitKat(ShowAttendClassActivity.this,data);
                } else {
                    // 4.4以下系统使用这个方法处理图片
                    TakePhotoUtils.handleImageBeforeKitKat(ShowAttendClassActivity.this,data);
                }
                break;
            case TakePhotoUtils.CROPPHOTO7:
                try {
                    if (resultCode == RESULT_OK) {
                        upLoadPicture(TakePhotoUtils.cachPath);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;

            case TakePhotoUtils.TAKEPHOTO7:
                try {
                    // 将拍摄的照片显示出来
                    TakePhotoUtils.startPhotoZoom(ShowAttendClassActivity.this,new File(TakePhotoUtils.mPath+".jpg"), 350);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;

        }


    }


    @Override
    public void setSelected_record() {

    }

    @Override
    public void setSelected_photography() {

    }

    @Override
    public void clickImag() {
        selectPhoto();
    }

    private void selectPhoto() {
        ActionSheetDialog actionSheetDialog = new ActionSheetDialog(getActivity());
        actionSheetDialog.builder();
        actionSheetDialog.setCancelable(true);
        actionSheetDialog.setCanceledOnTouchOutside(true);
        actionSheetDialog.addSheetItem("拍照", ActionSheetDialog.SheetItemColor.Lan,
                new ActionSheetDialog.OnSheetItemClickListener() {

                    @Override
                    public void onClick(int which) {
                        TakePhotoUtils.takePhoto(ShowAttendClassActivity.this);
                    }
                });
        actionSheetDialog.addSheetItem("从相册获取", ActionSheetDialog.SheetItemColor.Lan,
                new ActionSheetDialog.OnSheetItemClickListener() {

                    @Override
                    public void onClick(int which) {
                        TakePhotoUtils.requestOpenPicture(ShowAttendClassActivity.this);
                    }
                });

        actionSheetDialog.show();
    }



    private void upLoadPicture(final String path) {
        ImageBean imageBean = new ImageBean();
        String uploadObject = "android_pic"+System.currentTimeMillis() + ".png";
        String imageName = new PutObjectSamples(oss, OSSConfigure.bucketName, uploadObject, path).putObjectFromLocalFile();
        Log.e("=imageName=", imageName);
        imageBean.path = path;
        imageBean.displayName = imageName;
//        imageBean.url = BaseUrl.HEAD_PHOTO_OSS+imageName;
        imgPaths.add(imageBean);
        photoAdapter.notifyDataSetChanged();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//
//            }
//        }).start();
    }

    public void pushSignPic() {
        if (imgPaths.size() == 0) {
            Toaster.showToast("请选择照片后提交");
            return;
        }
        LoadingDialog.ShowLoading(getActivity());
        String imgName = "";
        for (ImageBean imageBean : imgPaths) {
            imgName = imgName + imageBean.displayName + ",";
        }
        if (imgName.length() > 1)
            imgName = imgName.substring(0, imgName.length() - 1);
        ManagerApi.signAttendPic(CurrentUser.getUser().getTid(), attendClass.getGsy_course_schedule_detail_id(), imgName, new RequestBack() {
            @Override
            public void onComplete(FlpBack back) {
                LoadingDialog.DissLoading(getActivity());
                Toaster.showToast(back.message);
//                sureImg.setVisibility(View.GONE);
//                photoAdapter.setFlag(true);
//                photoAdapter.notifyDataSetChanged();
                finish();
            }

            @Override
            public void onFlpException(FlpException e) {
                LoadingDialog.DissLoading(getActivity());
            }
        });
    }



    @Override
    public void onItemClick(int position) {
        signAttendClass(position);
    }
}
