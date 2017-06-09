package com.guanshaoye.glglteacher.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;

import com.guanshaoye.mylibrary.R;

import butterknife.ButterKnife;


public abstract class BaseFragment<T>  extends Fragment {
	protected abstract Activity getSelfActivity();
	private View view ;
	private LayoutInflater inflater;
	private ViewGroup container ;
	public View setContentView(int resourceId) {
		view = inflater.inflate(resourceId, container, false);
		ButterKnife.bind(this, view);
		return view;
	}
	public View getContentView(){
		return  this.view ;
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		this.inflater = inflater ;
		this.container = container ;
		return super.onCreateView(inflater,container,savedInstanceState);
	}



	public View findViewById(int id){
		return view.findViewById(id) ;
	}
	protected void showToast(String content) {
		Toast.makeText(getSelfActivity(), content, Toast.LENGTH_SHORT).show();
	}

	protected void startActivityForNoIntent(Class forwordClass) {
		Intent intent = new Intent(getSelfActivity(), forwordClass);
		startActivity(intent);
		getSelfActivity().overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
	}

	protected void startActivityForIntent(Class forwordClass, Intent intent) {
		intent.setClass(getSelfActivity(), forwordClass);
		startActivity(intent);
		getSelfActivity().overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
	}

	protected int getWindowWidth() {
		WindowManager windowManager = getSelfActivity().getWindowManager();
		Display display = windowManager.getDefaultDisplay();
		return display.getWidth();
	}

	protected int getWindowHeight() {
		WindowManager windowManager = getSelfActivity().getWindowManager();
		Display display = windowManager.getDefaultDisplay();
		return display.getHeight();
	}

	/**
	 * dip转px
	 */
	protected int dip2px(Context context, float dipValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dipValue * scale + 0.5f);
	}

	/**
	 * px 转化为dip
	 */
	protected int px2dip(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}
	@Override
	public void onDestroyView() {
		super.onDestroyView();
		ButterKnife.unbind(this);
	}
}
