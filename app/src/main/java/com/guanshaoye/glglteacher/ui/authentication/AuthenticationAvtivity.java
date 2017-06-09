package com.guanshaoye.glglteacher.ui.authentication;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.sdk.android.oss.OSS;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.common.OSSLog;
import com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSPlainTextAKSKCredentialProvider;
import com.alibaba.sdk.android.vod.upload.VODUploadCallback;
import com.alibaba.sdk.android.vod.upload.VODUploadClient;
import com.alibaba.sdk.android.vod.upload.VODUploadClientImpl;
import com.alibaba.sdk.android.vod.upload.model.UploadFileInfo;
import com.alibaba.sdk.android.vod.upload.model.VodInfo;
import com.guanshaoye.glglteacher.R;
import com.guanshaoye.glglteacher.bean.AuthBean;
import com.guanshaoye.glglteacher.bean.ImageBean;
import com.guanshaoye.glglteacher.listener.OnClickPhotoListener;
import com.guanshaoye.glglteacher.ui.mine.EditUserInfoActivity;
import com.guanshaoye.glglteacher.ui.mine.MeActivity;
import com.guanshaoye.glglteacher.utils.CurrentUser;
import com.guanshaoye.glglteacher.utils.FileUtils;
import com.guanshaoye.glglteacher.utils.LoadingDialog;
import com.guanshaoye.glglteacher.utils.OSSConfigure;
import com.guanshaoye.glglteacher.utils.TakePhotoUtils;
import com.guanshaoye.glglteacher.utils.aliyun.PutObjectSamples;
import com.guanshaoye.glglteacher.utils.view.ActionSheetDialog;
import com.guanshaoye.glglteacher.utils.view.MyGridView;
import com.guanshaoye.mylibrary.api.AuthApi;
import com.guanshaoye.mylibrary.base.BaseActivity;
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
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by karl on 2017/5/27.
 * 申请认证
 */

public class AuthenticationAvtivity extends BaseActivity implements OnClickPhotoListener {
    @Bind(R.id.normal_title)
    TextView normalTitle;
    @Bind(R.id.tv_project)
    TextView tvProject;
    @Bind(R.id.rel_project)
    RelativeLayout relProject;
    @Bind(R.id.tv_course)
    TextView tvCourse;
    @Bind(R.id.rel_course)
    RelativeLayout relCourse;

    @Bind(R.id.gridsaishi)
    MyGridView gridsaishi;
    @Bind(R.id.gridzhengshu)
    MyGridView gridzhengshu;
    @Bind(R.id.recyclerview)
    RecyclerView recyclerview;
    @Bind(R.id.submit_btn)
    TextView submitBtn;


    private int mMediaSize;
    private DisplayMetrics mMetrics;
    private VideoAdapter videoAdapter;
    private NewPhotoAdapter saiAdapter;
    private NewPhotoAdapter zhengAdapter;
    private List<ImageBean> saiimgPaths = new ArrayList<>();
    private List<ImageBean> zhengimgPaths = new ArrayList<>();
    private List<ImageBean> imgPathsVideo = new ArrayList<>();
    private String uploadAuth = "";
    private String uploadAddress = "";
    private VODUploadClient uploader;
    private static final int TAKE_PICTURE = 0;
    private static final int RESULT_LOAD_VIDEO = 4;
    private Handler handler;
    private OSS oss;
    private int selectPosition = 0;
    public final static int PRODECT_CODE = 110;
    public final static int CLASS_CODE = 111;

    private String courseId = "";
    private String projectId = "";

    @Override
    public void init() {
        setContentView(R.layout.authentication_layou);
        normalTitle.setText("认证信息");
        initview();

    }

    private void initview() {
        // 打开日志。
        OSSLog.enableLog();
        OSSCredentialProvider credentialProvider = new OSSPlainTextAKSKCredentialProvider(OSSConfigure.accessKeyId, OSSConfigure.accessKeySecret);
        oss = new OSSClient(getActivity(), OSSConfigure.endpoint, credentialProvider);
        uploader = new VODUploadClientImpl(getApplicationContext());
        // 点播上传。每次上传都是独立的鉴权，所以初始化时，不需要设置鉴权
        uploader.init(callback);

        recyclerview.setLayoutManager(new GridLayoutManager(this, 2));
        videoAdapter = new VideoAdapter();
        recyclerview.setAdapter(videoAdapter);
        videoAdapter.setOnItemClickListener(this);


        mMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(mMetrics);
        float ds = mMetrics.density;
        mMediaSize = (mMetrics.widthPixels - (int) (30 * ds) - (int) (9 * ds)) / 4;
        gridsaishi.setVerticalSpacing((int) (3 * ds));
        gridsaishi.setHorizontalSpacing((int) (3 * ds));
        saiAdapter = new NewPhotoAdapter(getActivity(), saiimgPaths, mMediaSize, new NewPhotoAdapter.VideoPhotoAdapterCallback() {
            @Override
            public void clickImag() {
                selectPosition = 1;
                selectPhoto();
            }
        });
        gridsaishi.setAdapter(saiAdapter);

        zhengAdapter = new NewPhotoAdapter(getActivity(), zhengimgPaths, mMediaSize, new NewPhotoAdapter.VideoPhotoAdapterCallback() {
            @Override
            public void clickImag() {
                selectPosition = 2;
                selectPhoto();
            }
        });
        gridzhengshu.setAdapter(zhengAdapter);

        ImageBean imageBean = new ImageBean();
        imgPathsVideo.add(imageBean);
        videoAdapter.setDataList(imgPathsVideo);

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                videoAdapter.onNotify();
            }
        };

    }

    @OnClick({R.id.rel_project, R.id.rel_course,R.id.submit_btn})
    public void onClick(View view) {
        Intent intent = new Intent(getActivity(), SelectListActivity.class);
        switch (view.getId()) {
            case R.id.rel_project:
                intent.putExtra("CODE", PRODECT_CODE);
                startActivityForResult(intent, PRODECT_CODE);
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                break;
            case R.id.rel_course:
                if (TextUtils.isEmpty(projectId)) {
                    Toaster.showToast("请选择项目");
                    return;
                }
                intent.putExtra("CODE", CLASS_CODE);
                intent.putExtra("SELECT_ID", projectId);
                startActivityForResult(intent, CLASS_CODE);
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                break;
            case R.id.submit_btn:
                submitAuth();
                break;
        }
    }

    /**
     * 老师认证接口
     */
    private void submitAuth() {
        if (TextUtils.isEmpty(projectId)){
            Toaster.showToast("请选择项目");
            return;
        }
        if (TextUtils.isEmpty(courseId)){
            Toaster.showToast("请选择课程");
            return;
        }
//        if (imgPathsVideo.size() == 1){
//            Toaster.showToast("请选上传视频");
//            return;
//        }
//        if (saiimgPaths.size() == 0){
//            Toaster.showToast("请上传赛事照片");
//            return;
//        }
//        if (zhengimgPaths.size() == 0){
//            Toaster.showToast("请上传证书照片");
//            return;
//        }

        for (ImageBean imageBean : imgPathsVideo) {
            if (imageBean.getStatus() == 0 && !TextUtils.isEmpty(imageBean.path)){
                Toaster.showToast("有视频正在上传中");
                return;
            }
            if(imageBean.getStatus() == -1 && !TextUtils.isEmpty(imageBean.path)){
                Toaster.showToast("有视频上传失败，请重新上传");
                return;
            }
        }
        String video_url = "";
        for (ImageBean imageBean : imgPathsVideo) {
            if(!TextUtils.isEmpty(imageBean.displayName)){
                video_url = video_url + imageBean.displayName + ",";
            }
        }
        if (video_url.length() > 1)
            video_url = video_url.substring(0, video_url.length() - 1);

        String s_url = "";
        for (ImageBean imageBean : zhengimgPaths) {
            s_url = s_url + imageBean.displayName + ",";
        }
        if (s_url.length() > 1)
            s_url = s_url.substring(0, s_url.length() - 1);

        String z_url = "";
        for (ImageBean imageBean : zhengimgPaths) {
            z_url = z_url + imageBean.displayName + ",";
        }
        if (z_url.length() > 1)
            z_url = z_url.substring(0, z_url.length() - 1);


        LoadingDialog.ShowLoading(getActivity());
        AuthApi.submitAuth(CurrentUser.getUser().getTid(), projectId, courseId, video_url, s_url, z_url, new RequestBack() {
            @Override
            public void onComplete(FlpBack back) {
                LoadingDialog.DissLoading(getActivity());
                Toaster.showToast(back.message);
                startActivityForNoResult(new Intent(getActivity(), MeActivity.class));
                finish();
            }

            @Override
            public void onFlpException(FlpException e) {
                LoadingDialog.DissLoading(getActivity());
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case PRODECT_CODE:
                if (data != null) {
                    String name = data.getExtras().getString("Gsy_name");
                    projectId = data.getExtras().getString("Gsy_id");
                    tvProject.setText(name);
                }
                break;
            case CLASS_CODE:
                if (data != null) {
                    String name = data.getExtras().getString("Gsy_name");
                    courseId = data.getExtras().getString("Gsy_id");
                    tvCourse.setText(name);
                }
                break;
            case RESULT_LOAD_VIDEO:
                String videoPath = "";
                if (data != null) {
                    Uri selectedUri = data.getData();
                    videoPath = FileUtils.getPath(getActivity(), selectedUri);
                }
                Log.e("-path-", videoPath);
                if (TextUtils.isEmpty(videoPath)) {
                    Toaster.showToast("视频获取失败");
                    return;
                }
                if (!videoPath.toLowerCase().endsWith(".mp4")) {
                    Toaster.showToast("只能上传mp4格式视频");
                    return;
                }

                for (ImageBean imageBean : imgPathsVideo) {
                    if (imageBean.path.equals(videoPath)) {
                        Toaster.showToast("视频文件已存在");
                        return;
                    }
                }

                ImageBean imageBean = new ImageBean();
                imageBean.path = videoPath;

                getAuther(imageBean);
                break;
            case TakePhotoUtils.CHECKPHOTO7:
                // 判断手机系统版本号
                if (Build.VERSION.SDK_INT >= 19) {
                    // 4.4及以上系统使用这个方法处理图片
                    TakePhotoUtils.handleImageOnKitKat(AuthenticationAvtivity.this,data);
                } else {
                    // 4.4以下系统使用这个方法处理图片
                    TakePhotoUtils.handleImageBeforeKitKat(AuthenticationAvtivity.this,data);
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
                    TakePhotoUtils.startPhotoZoom(AuthenticationAvtivity.this,new File(TakePhotoUtils.mPath+".jpg"), 350);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;



        }
    }

    private void getAuther(final ImageBean imageBean) {
        final String videoName = "android_video" + System.currentTimeMillis() + ".mp4";
        LoadingDialog.ShowLoading(getActivity());
        AuthApi.getAuth("3", videoName, "android认证审核", new RequestBack() {
            @Override
            public void onComplete(FlpBack back) {
                LoadingDialog.DissLoading(getActivity());

                if (back.errorCode == 200) {
                    AuthBean authBean = JSON.parseObject(back.data, AuthBean.class);
                    imageBean.displayName = authBean.getResponse().getVideoId();
                    imgPathsVideo.add(0, imageBean);
                    if (imgPathsVideo.size() == 3) {
                        imgPathsVideo.remove(2);
                    }
                    videoAdapter.setDataList(imgPathsVideo);
                    Log.e("path=", imageBean.path);
                    uploadAuth = authBean.getResponse().getUploadAuth();
                    uploadAddress = authBean.getResponse().getUploadAddress();
                    upLoadVideo(imageBean.path);

                }

            }

            @Override
            public void onFlpException(FlpException e) {
                LoadingDialog.DissLoading(getActivity());
            }
        });
    }

    private void upLoadVideo(String path) {

        uploader.addFile(path, getVodInfo());
        uploader.start();
    }


    VODUploadCallback callback = new VODUploadCallback() {
        @Override
        public void onUploadSucceed(UploadFileInfo info) {
            OSSLog.logD("onsucceed ------------------" + info.getFilePath());

            for (int i = 0; i < imgPathsVideo.size(); i++) {
                ImageBean imageBean = imgPathsVideo.get(i);
                if (info.getFilePath().equals(imageBean.path)) {
                    imgPathsVideo.get(i).setStatus(1);
                }
            }
            handler.sendEmptyMessage(0);
        }

        @Override
        public void onUploadFailed(UploadFileInfo info, String code, String message) {
            OSSLog.logE("onfailed ------------------ " + info.getFilePath() + " " + code + " " + message);
            for (int i = 0; i < imgPathsVideo.size(); i++) {
                ImageBean imageBean = imgPathsVideo.get(i);
                if (info.getFilePath().equals(imageBean.path)) {
                    imgPathsVideo.get(i).setStatus(-1);
                }
            }
            handler.sendEmptyMessage(0);
        }

        @Override
        public void onUploadProgress(UploadFileInfo info, long uploadedSize, long totalSize) {
            OSSLog.logD("onProgress ------------------ " + info.getFilePath() + " " + uploadedSize + " " + totalSize);


        }

        @Override
        public void onUploadTokenExpired() {
            OSSLog.logE("onExpired ------------- ");

        }

        @Override
        public void onUploadRetry(String code, String message) {
            OSSLog.logE("onUploadRetry ------------- ");
        }

        @Override
        public void onUploadRetryResume() {
            OSSLog.logE("onUploadRetryResume ------------- ");
        }

        @Override
        public void onUploadStarted(UploadFileInfo uploadFileInfo) {
            OSSLog.logE("onUploadStarted ------------- ");
            uploader.setUploadAuthAndAddress(uploadFileInfo, uploadAuth, uploadAddress);
            OSSLog.logD("file path:" + uploadFileInfo.getFilePath() +
                    ", endpoint: " + uploadFileInfo.getEndpoint() +
                    ", bucket:" + uploadFileInfo.getBucket() +
                    ", object:" + uploadFileInfo.getObject() +
                    ", status:" + uploadFileInfo.getStatus());
        }
    };

    private VodInfo getVodInfo() {
        VodInfo vodInfo = new VodInfo();
        vodInfo.setTitle("android认证审核");
        vodInfo.setDesc("描述." + "认证审核");
        vodInfo.setIsProcess(true);
        List<String> tags = new ArrayList<>();
        vodInfo.setTags(tags);
        vodInfo.setIsShowWaterMark(false);
        vodInfo.setPriority(7);
        return vodInfo;
    }

    /**
     * 选择视频
     */
    private void ClickAddVideo() {
        new ActionSheetDialog(getActivity())
                .builder()
                .setCancelable(true)
                .setCanceledOnTouchOutside(true)
                .addSheetItem("从相册获取", ActionSheetDialog.SheetItemColor.Lan,
                        new ActionSheetDialog.OnSheetItemClickListener() {

                            @Override
                            public void onClick(int which) {
                                if (!CheckPermission.newInstance(getActivity()).getFilePermission()) {
                                    return;
                                }
                                Intent it = new Intent(Intent.ACTION_PICK, MediaStore.Video.Media.EXTERNAL_CONTENT_URI);
                                it.setDataAndType(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, "video/*");
                                startActivityForResult(it, RESULT_LOAD_VIDEO);
                            }
                        }).show();
    }


    @Override
    public void removeVideo(ImageBean imageBean) {
        for (int i = 0; i < imgPathsVideo.size(); i++) {
            if (imageBean.path.equals(imgPathsVideo.get(i).path)) {
                if (imageBean.getStatus() == 0) {
                    uploader.deleteFile(i);
                }
                imgPathsVideo.remove(i);
            }
        }
        videoAdapter.setDataList(imgPathsVideo);

    }

    @Override
    public void clickImag() {
        ClickAddVideo();
    }

    private void upLoadPicture(final String path) {
        ImageBean imageBean = new ImageBean();
        String uploadObject = "android_pic" + System.currentTimeMillis() + ".png";
        String imageName = new PutObjectSamples(oss, OSSConfigure.bucketName, uploadObject, path).putObjectFromLocalFile();
        Log.e("=imageName=", imageName);
        imageBean.path = path;
        imageBean.displayName = imageName;
        if (selectPosition == 1) {
            saiimgPaths.add(imageBean);
            saiAdapter.notifyDataSetChanged();
        } else if (selectPosition == 2) {
            zhengimgPaths.add(imageBean);
            zhengAdapter.notifyDataSetChanged();
        }


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
                        TakePhotoUtils.takePhoto(AuthenticationAvtivity.this);
                    }
                });
        actionSheetDialog.addSheetItem("从相册获取", ActionSheetDialog.SheetItemColor.Lan,
                new ActionSheetDialog.OnSheetItemClickListener() {

                    @Override
                    public void onClick(int which) {
                        TakePhotoUtils.requestOpenPicture(AuthenticationAvtivity.this);
                    }
                });

        actionSheetDialog.show();
    }

}
