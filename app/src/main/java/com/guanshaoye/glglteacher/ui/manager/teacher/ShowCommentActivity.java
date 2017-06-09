package com.guanshaoye.glglteacher.ui.manager.teacher;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.sdk.android.oss.OSS;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSPlainTextAKSKCredentialProvider;
import com.guanshaoye.glglteacher.R;
import com.guanshaoye.glglteacher.bean.CommentPicBean;
import com.guanshaoye.glglteacher.bean.ImageBean;
import com.guanshaoye.glglteacher.bean.MemberCommentListBean;
import com.guanshaoye.glglteacher.bean.ShowCommentBean;
import com.guanshaoye.glglteacher.bean.TeacherReviewBean;
import com.guanshaoye.glglteacher.ui.manager.attendclass.PhotoAdapter;
import com.guanshaoye.glglteacher.utils.CurrentUser;
import com.guanshaoye.glglteacher.utils.LoadingDialog;
import com.guanshaoye.glglteacher.utils.OSSConfigure;
import com.guanshaoye.glglteacher.utils.PicassoUtils;
import com.guanshaoye.glglteacher.utils.TakePhotoUtils;
import com.guanshaoye.glglteacher.utils.aliyun.PutObjectSamples;
import com.guanshaoye.glglteacher.utils.view.ActionSheetDialog;
import com.guanshaoye.glglteacher.utils.view.MyGridView;
import com.guanshaoye.glglteacher.utils.view.RatingBar;
import com.guanshaoye.glglteacher.utils.view.pullrefreshview.AbPullToRefreshView;
import com.guanshaoye.mylibrary.api.ManagerApi;
import com.guanshaoye.mylibrary.base.BaseActivity;
import com.guanshaoye.mylibrary.http.FlpBack;
import com.guanshaoye.mylibrary.http.FlpException;
import com.guanshaoye.mylibrary.http.RequestBack;
import com.guanshaoye.mylibrary.utils.CheckPermission;
import com.guanshaoye.mylibrary.utils.Toaster;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by karl on 2017/5/29.
 */

public class ShowCommentActivity extends BaseActivity implements PhotoAdapter.PhotoAdapterCallback {


    @Bind(R.id.normal_title)
    TextView normalTitle;
    @Bind(R.id.sure_img)
    ImageView sureImg;
    @Bind(R.id.refreshLayout)
    AbPullToRefreshView refreshLayout;
    @Bind(R.id.listView)
    ListView listView;
    private CommentAdapter adapter;
    private int page = 1;
    private int mMediaSize;
    private DisplayMetrics mMetrics;
    private PhotoAdapter photoAdapter;
    private String videoPath;
    private List<ImageBean> imgPaths = new ArrayList<>();
    private OSS oss;
    private MyGridView gridMedia;
    private TeacherReviewBean commentBean;
    private boolean isComment;
    private int startNum;
    private EditText edit_txt;
    private boolean isSelect;
    private RatingBar rb;
    private TextView teacher_title;
    private TextView txt_comment;
    private List<CommentPicBean> headimgPaths = new ArrayList<>();
    private List<MemberCommentListBean> list = new ArrayList<>();
    private RecyclerView recyclerview;
    private ShowPhotoAdapter videoAdapter;
    @Override
    public void init() {
        setContentView(R.layout.comment_layout);
        normalTitle.setText("老师评价");
        initview();
    }

    private void initview() {

        isComment = getIntent().getBooleanExtra("showFlag", false);
        commentBean = (TeacherReviewBean) getIntent().getSerializableExtra("model");
        if (commentBean == null) {
            return;
        }
        mMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(mMetrics);
        float ds = mMetrics.density;
        mMediaSize = (mMetrics.widthPixels - (int) (100 * ds) - (int) (9 * ds)) / 4;

        View headerView = null;
        View bottomView = null;
        if (isComment) {
            sureImg.setVisibility(View.GONE);
            headerView = LayoutInflater.from(getActivity()).inflate(R.layout.have_comment_header_layout, null);
            teacher_title = (TextView) headerView.findViewById(R.id.teacher_title);
            rb = (RatingBar) headerView.findViewById(R.id.rb);
            txt_comment = (TextView) headerView.findViewById(R.id.txt_comment);

            recyclerview = (RecyclerView) headerView.findViewById(R.id.recyclerview);
            recyclerview.setLayoutManager(new GridLayoutManager(this, 4));
            videoAdapter = new ShowPhotoAdapter(this);
            recyclerview.setAdapter(videoAdapter);

        } else {
            sureImg.setVisibility(View.VISIBLE);

            headerView = LayoutInflater.from(getActivity()).inflate(R.layout.comment_header_layout, null);
            bottomView = LayoutInflater.from(getActivity()).inflate(R.layout.bottom_lay, null);
            edit_txt = (EditText) headerView.findViewById(R.id.edit_txt);
            RatingBar rb = (RatingBar) headerView.findViewById(R.id.rb);
            rb.setOnRatingChangeListener(new RatingBar.OnRatingChangeListener() {
                @Override
                public void onRatingChange(float ratingCount) {
                    startNum = (int) ratingCount;
                }
            });
            final TextView button_select = (TextView) headerView.findViewById(R.id.button_select);
            button_select.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (isSelect) {
                        isSelect = false;
                        button_select.setBackgroundResource(R.drawable.shape_green_item);
                    } else {
                        isSelect = true;
                        button_select.setBackgroundResource(R.drawable.shape_red_item);
                    }
                }
            });


            gridMedia = (MyGridView) headerView.findViewById(R.id.grid_media);

            gridMedia.setVerticalSpacing((int) (3 * ds));
            gridMedia.setHorizontalSpacing((int) (3 * ds));
            photoAdapter = new PhotoAdapter(getActivity(), imgPaths, mMediaSize, videoPath, false, this);
            gridMedia.setAdapter(photoAdapter);
            gridMedia.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                HubUtil.showImage(NewPostActivity.this, mPost.getPhoto(), position);
                }
            });
//            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) recyclerView.getLayoutParams();
//            layoutParams.bottomMargin = 100;
//            recyclerView.setLayoutParams(layoutParams);
        }

        ImageView photo_img = (ImageView) headerView.findViewById(R.id.photo_img);
        ImageView center_img = (ImageView) headerView.findViewById(R.id.center_img);
        TextView tv_name = (TextView) headerView.findViewById(R.id.tv_name);
        TextView tv_levle = (TextView) headerView.findViewById(R.id.tv_levle);
        TextView tv_teacher = (TextView) headerView.findViewById(R.id.tv_teacher);
        TextView tv_location = (TextView) headerView.findViewById(R.id.tv_location);
        TextView tv_time = (TextView) headerView.findViewById(R.id.tv_time);

        PicassoUtils.showPersionImg(getActivity(), commentBean.getGsy_teacher_portrait(), photo_img);
        PicassoUtils.showPersionImg(getActivity(), commentBean.getGsy_teacher_portrait(), center_img);
        tv_name.setText(commentBean.getGsy_course_class_name());
        tv_teacher.setText(commentBean.getGsy_teacher_name());
        tv_levle.setText("(" + commentBean.getGsy_teacher_level() + "级)");
        tv_time.setText(commentBean.getGsy_course_time());
        tv_location.setText(commentBean.getGsy_store_name());


        OSSCredentialProvider credentialProvider = new OSSPlainTextAKSKCredentialProvider(OSSConfigure.accessKeyId, OSSConfigure.accessKeySecret);
        oss = new OSSClient(getActivity(), OSSConfigure.endpoint, credentialProvider);


        adapter = new CommentAdapter(getActivity(),list);
        listView.setAdapter(adapter);
        listView.addHeaderView(headerView);
        if (!isComment)
            listView.addFooterView(bottomView);
        refreshLayout.setOnHeaderRefreshListener(new AbPullToRefreshView.OnHeaderRefreshListener() {
            @Override
            public void onHeaderRefresh(AbPullToRefreshView view) {
                page = 1;
                getShowComment();

            }
        });
        refreshLayout.setOnFooterLoadListener(new AbPullToRefreshView.OnFooterLoadListener() {
            @Override
            public void onFooterLoad(AbPullToRefreshView view) {
                getShowComment();
            }
        });


        getShowComment();
    }

    private void getShowComment() {
        LoadingDialog.ShowLoading(getActivity());
//        ManagerApi.showComment("5", "10", "20", page, new RequestBack() {
        ManagerApi.showComment(CurrentUser.getUser().getTid(), commentBean.getGsy_course_schedule_detail_id(), commentBean.getGsy_course_grap_id(), page, new RequestBack() {
            @Override
            public void onComplete(FlpBack back) {
                LoadingDialog.DissLoading(getActivity());
                refreshLayout.onHeaderRefreshFinish();
                refreshLayout.onFooterLoadFinish();
                if (back.errorCode == 200) {
                    if (page == 1) {
                        list.clear();
                    }
                    ShowCommentBean coBean = JSON.parseObject(back.data, ShowCommentBean.class);
                    if (coBean != null) {
                        if (isComment) {
                            initHeadView(coBean);
                        }
                        List<MemberCommentListBean> listComment = coBean.getMember_comment_list();
                        if (listComment != null) {
                            list.addAll(listComment);
//                            adapter.setDataList(list);
                            adapter.notifyDataSetChanged();
                        }
                        page++;
                    }
                }
            }

            @Override
            public void onFlpException(FlpException e) {
                LoadingDialog.DissLoading(getActivity());
                refreshLayout.onHeaderRefreshFinish();
                refreshLayout.onFooterLoadFinish();
            }
        });
    }

    private void initHeadView(ShowCommentBean coBean) {
        int isReplace = coBean.getComment_info().getGsy_is_replace();
        teacher_title.setText("开课老师： " + commentBean.getGsy_teacher_name()+(isReplace == 1 ?"(爽约)":""));
        rb.setStar(coBean.getComment_info().getGsy_star_num());
        txt_comment.setText(coBean.getComment_info().getGsy_content());
        List<CommentPicBean> pa = coBean.getComment_pic_list();
        if (pa != null) {
            headimgPaths.clear();
            headimgPaths.addAll(pa);
            videoAdapter.setDataList(headimgPaths);
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
                        TakePhotoUtils.takePhoto(ShowCommentActivity.this);
                    }
                });
        actionSheetDialog.addSheetItem("从相册获取", ActionSheetDialog.SheetItemColor.Lan,
                new ActionSheetDialog.OnSheetItemClickListener() {

                    @Override
                    public void onClick(int which) {
                        if (!CheckPermission.newInstance(getActivity()).getFilePermission()) {
                            return;
                        }
                        TakePhotoUtils.requestOpenPicture(ShowCommentActivity.this);
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

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case TakePhotoUtils.CHECKPHOTO7:
                // 判断手机系统版本号
                if (Build.VERSION.SDK_INT >= 19) {
                    // 4.4及以上系统使用这个方法处理图片
                    TakePhotoUtils.handleImageOnKitKat(ShowCommentActivity.this,data);
                } else {
                    // 4.4以下系统使用这个方法处理图片
                    TakePhotoUtils.handleImageBeforeKitKat(ShowCommentActivity.this,data);
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
                    TakePhotoUtils.startPhotoZoom(ShowCommentActivity.this,new File(TakePhotoUtils.mPath+".jpg"), 350);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;

        }

    }

    @OnClick(R.id.sure_img)
    public void addComment() {
        if (startNum == 0) {
            Toaster.showToast("请选择评分");
            return;
        }

        String content = edit_txt.getText().toString();
        if (TextUtils.isEmpty(content)) {
            Toaster.showToast("请填写评论内容");
            return;
        }

        String imgName = "";
        for (ImageBean imageBean : imgPaths) {
            imgName = imgName + imageBean.displayName + ",";
        }
        if (imgName.length() > 1)
            imgName = imgName.substring(0, imgName.length() - 1);

        LoadingDialog.ShowLoading(getActivity());
        ManagerApi.addComment(CurrentUser.getUser().getTid(), commentBean.getGsy_course_schedule_detail_id(), commentBean.getGsy_course_grap_id(), startNum, content,
                isSelect ? 1 : 0, imgName, new RequestBack() {
                    @Override
                    public void onComplete(FlpBack back) {
                        LoadingDialog.DissLoading(getActivity());
                        Toaster.showToast(back.message);
                        if (back.errorCode == 200)
                            finish();

                    }

                    @Override
                    public void onFlpException(FlpException e) {
                        LoadingDialog.DissLoading(getActivity());

                    }
                });


    }
}
