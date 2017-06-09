package com.guanshaoye.glglteacher.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.guanshaoye.glglteacher.R;
import com.guanshaoye.glglteacher.ui.login.LoginActivity;
import com.guanshaoye.glglteacher.utils.guide.AbsGuideActivity;
import com.guanshaoye.glglteacher.utils.guide.SinglePage;
import java.util.ArrayList;
import java.util.List;

public class ExampleGuideActivity extends AbsGuideActivity {

    @Override
    public List<SinglePage> buildGuideContent() {
        // prepare the information for our guide
        List<SinglePage> guideContent = new ArrayList<SinglePage>();


        SinglePage page01 = new SinglePage();
        SinglePage page02 = new SinglePage();




            page01.mBackground = getResources().getDrawable(R.mipmap.bg_page_01);
            page02.mBackground = getResources().getDrawable(R.mipmap.bg_page_02);



        guideContent.add(page01);
        guideContent.add(page02);

        SinglePage page05 = new SinglePage();
        page05.mCustomFragment = new EntryFragment();
        guideContent.add(page05);

        return guideContent;
    }

    @Override
    public Bitmap dotDefault() {
        return BitmapFactory.decodeResource(getResources(), R.mipmap.ic_dot_default);
    }

    @Override
    public Bitmap dotSelected() {
        return BitmapFactory.decodeResource(getResources(), R.mipmap.ic_dot_selected);
    }

    @Override
    public boolean drawDot() {
        return true;
    }

    public void entryApp() {
        // Time to entry your app! We just finish the activity, replace it with
        // your code.
        startActivity(new Intent(ExampleGuideActivity.this,LoginActivity.class));
        finish();
    }

    /**
     * You need provide an id to the pager. You could define an id in
     * values/ids.xml and use it.
     */
    @Override
    public int getPagerId() {
        return R.id.guide_container;
    }
}
