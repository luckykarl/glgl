package com.guanshaoye.glglteacher.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.guanshaoye.glglteacher.R;
import com.guanshaoye.glglteacher.utils.SharedPreferencesUtils;


public class EntryFragment extends Fragment {
    private TextView tv_jump;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_entry, null);
        tv_jump = (TextView) v.findViewById(R.id.tv_jump);
        tv_jump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ExampleGuideActivity activity = (ExampleGuideActivity) getActivity();
                activity.entryApp();
            }
        });
//
//        Runnable r = new Runnable() {
//            @Override
//            public void run() {
//                // TODO Auto-generated method stub
//                startGoPage();
//                ExampleGuideActivity activity = (ExampleGuideActivity) getActivity();
//                activity.entryApp();
//            }
//        };
//        new Handler().postDelayed(r, 2000);
        SharedPreferencesUtils.putValue(getContext(),SharedPreferencesUtils.ISFIRST,"1");
        return v;
    }

}
