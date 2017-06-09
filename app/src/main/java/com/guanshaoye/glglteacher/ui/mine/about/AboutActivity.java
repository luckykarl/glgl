package com.guanshaoye.glglteacher.ui.mine.about;

import android.webkit.WebView;
import android.widget.TextView;

import com.guanshaoye.glglteacher.R;
import com.guanshaoye.mylibrary.base.BaseActivity;

import butterknife.Bind;

/**
 * Created by ${张梦博} on 2017/5/17.
 * Whenever,Wherever,Whatever,I believe I can handle everything
 */

public class AboutActivity extends BaseActivity {
    @Bind(R.id.normal_title)
    TextView normalTitle;
    @Bind(R.id.webView)
    WebView webView;

    @Override
    public void init() {
        setContentView(R.layout.activity_about);
        normalTitle.setText("关于");
    }


}
