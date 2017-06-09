package com.guanshaoye.glglteacher.utils.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.guanshaoye.glglteacher.R;

import java.util.List;

/**
 * Created by karl
 */

public class BottomTabBar extends LinearLayout {
    private FragmentManager manager;
    /**
     * 设置文本颜色
     */
    private int textColor;
    /**
     * 设置文本选中颜色
     */
    private int textSelectColor;
    /**
     * 设置整体背景颜色
     */
    private int backgroundColor;

    public void setManager(FragmentManager manager) {
        this.manager = manager;
    }

    public void setBars(List<BarEntity> bars) {
        init(bars);
    }

    public BottomTabBar(Context context) {
        this(context, null);
    }

    public BottomTabBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BottomTabBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.BottomTabBar);
        textColor = typedArray.getColor(R.styleable.BottomTabBar_textColor, Color.parseColor("#666666"));
        textSelectColor = typedArray.getColor(R.styleable.BottomTabBar_textSelectColor, Color.parseColor("#666666"));
        backgroundColor = typedArray.getColor(R.styleable.BottomTabBar_backgroundColor, Color.parseColor("#313131"));
        typedArray.recycle();
    }

    /**
     * 初始化
     *
     * @param bars
     */
    private void init(final List<BarEntity> bars) {
        setOrientation(LinearLayout.VERTICAL);
        setBackgroundColor(backgroundColor);
        if (bars == null || bars.size() <= 0) {
            return;
        }
        /**
         * 添加底部导航栏菜单
         */
        LinearLayout bottom = new LinearLayout(this.getContext());
        bottom.setOrientation(LinearLayout.HORIZONTAL);
        LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        for (int i = 0; i < bars.size(); i++) {
//            final LinearLayout bar = new LinearLayout(this.getContext());
//            bar.setOrientation(LinearLayout.VERTICAL);
//            LayoutParams blp = new LayoutParams(LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1);
//            bar.setGravity(Gravity.CENTER);
//            bar.setPadding(10,16,10,16);


            LayoutInflater inflater = LayoutInflater.from(this.getContext());

            LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1);
            layoutParams.weight = 1;

            View llsearchSpec = inflater.inflate(R.layout.ui_tabhost_item, bottom, false);
            llsearchSpec.setLayoutParams(layoutParams);
            ImageView imageView = (ImageView) llsearchSpec.findViewById(R.id.iv_main);
            imageView.setImageResource(bars.get(i).getTabUnSelectedResId());
            TextView textView = (TextView) llsearchSpec.findViewById(R.id.tab_itme_txt);
            textView.setText(bars.get(i).getTabText());
            textView.setTextColor(textColor);


            final int position = i;
            textView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {


                    select(position, bars);
                }
            });
            bottom.addView(llsearchSpec, layoutParams);
        }
        addView(bottom, lp);
        /**
         * 添加一根华丽的分割线
         */
        View line = new View(getContext());
        line.setBackgroundColor(Color.parseColor("#ededed"));
        ViewGroup.LayoutParams llp = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 23);
        addView(line, llp);
        /**
         * 最上面添加一个Fragment
         */
        FrameLayout fl = new FrameLayout(getContext());
        fl.setId(R.id.f_content);
        fl.setBackgroundColor(Color.parseColor("#ffffff"));
        LayoutParams flp = new LayoutParams(LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, 1);
        addView(fl, flp);


        /**
         * 默认选中第一个菜单栏
         */
        select(0, bars);
    }

    /**
     * 选择了第几个tab
     *
     * @param position
     */
    public void select(int position, List<BarEntity> bars) {
        if (getChildAt(2) == null) {
            return;
        }
        ImageView image;
        TextView text;
        RelativeLayout relativeLayout;
        LinearLayout bottom = (LinearLayout) getChildAt(0);
        for (int i = 0; i < bottom.getChildCount(); i++) {

            if (i != 2) {
                RelativeLayout bar = (RelativeLayout) bottom.getChildAt(i);
                relativeLayout = (RelativeLayout) bar.getChildAt(0);
                image = (ImageView) relativeLayout.getChildAt(0);
                text = (TextView) relativeLayout.getChildAt(1);
                if (position == i) {
                    image.setBackgroundColor(textSelectColor);
                    text.setTextColor(textSelectColor);
                } else {
                    image.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    text.setTextColor(textColor);
                }
            }

        }
        switchByPosition(position);
    }

    private void switchByPosition(int position) {
        if (onSelectListener != null) {
            onSelectListener.onSelect(position);
        }
    }


    private Fragment current;

    /**
     * 切换当前显示的fragment
     */
    public void switchContent(Fragment to) {
        if (current != to) {
            FragmentTransaction transaction = manager.beginTransaction();

            if (current != null) {
                transaction.hide(current);
            }
            if (!to.isAdded()) { // 先判断是否被add过
                transaction.add(R.id.f_content, to).commit();
            } else {

                transaction.show(to).commit(); // 隐藏当前的fragment，显示下一个
            }
            current = to;
        }
    }

    private OnSelectListener onSelectListener;

    /**
     * 选择切换的监听，在这里处理切换fragment,防止重复创建
     */
    public interface OnSelectListener {
        public void onSelect(int position);
    }

    public void setOnSelectListener(OnSelectListener onSelectListener) {
        this.onSelectListener = onSelectListener;
    }
}
