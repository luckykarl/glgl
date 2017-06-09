package com.guanshaoye.glglteacher.ui.mine.feedback;

import android.graphics.Bitmap;
import android.net.http.SslError;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import com.guanshaoye.glglteacher.R;
import com.guanshaoye.glglteacher.utils.LoadingDialog;
import com.guanshaoye.mylibrary.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by karl on 2017/6/3.
 */

public class WebViewActivity extends BaseActivity {

    @Bind(R.id.normal_title)
    TextView normalTitle;
    @Bind(R.id.mywebview)
    WebView mywebview;
    private String WEB_URL = "";
    @Override
    public void init() {
        setContentView(R.layout.webview_layout);
        initView();
    }

    private void initView() {
        WEB_URL = getIntent().getStringExtra("WEB_URL");

        mywebview.setSaveEnabled(true);
        mywebview.getSettings().setJavaScriptEnabled(true); // 设置支持javascript脚本
        mywebview.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        mywebview.getSettings().setLoadWithOverviewMode(true);
        mywebview.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        mywebview.getSettings().setDefaultTextEncodingName("utf-8");
        mywebview.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);

        LoadingDialog.ShowLoading(getActivity());
        mywebview.setWebViewClient(new WebViewClient() {

            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return super.shouldOverrideUrlLoading(view, url);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                LoadingDialog.DissLoading(getActivity());
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                // TODO Auto-generated method stub
                handler.proceed();//数字证书
            }

        });

        mywebview.loadUrl(WEB_URL);
    }



}
