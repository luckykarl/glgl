package com.guanshaoye.mylibrary.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Adapter;
import android.widget.AdapterView;
import com.guanshaoye.mylibrary.R;
import com.guanshaoye.mylibrary.utils.CommonUtil;
import butterknife.ButterKnife;

/**
 * 工具类
 */
public abstract class BaseActivity extends FragmentActivity implements
		View.OnClickListener, AdapterView.OnItemClickListener, TextWatcher {
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		AtyContainer.getInstance().addActivity(this);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
			Window window = getWindow();
			// Translucent status bar
			window.setFlags(
					WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
					WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
			window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
			// Translucent navigation bar
//			window.setFlags(
//					WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION,
//					WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
		          }
		init();
	}
	@Override
	public void setContentView(int layoutResID){
		super.setContentView(layoutResID);
		ButterKnife.bind(this);
	}
	public Context getActivity() {
		// TODO Auto-generated method stub
		return this;
	}
	@Override
	protected void onResume() {
		super.onResume();
	}
	@Override
	protected void onPause() {
		super.onPause();
		CommonUtil.hideSoftInput(this);
	}
	@Override
	protected void onDestroy() {
		super.onDestroy();
		ButterKnife.unbind(this);
		AtyContainer.getInstance().removeActivity(this);
	}
	/**
	 * 返回键onClick="back"
	 */
	public void back(View v) {	
		finish();
		overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
	}
	public abstract void init();
	/**
	 * 设置标题String
	 * 
	 * @param str
	 */
	public void setTitle(String str) {
		/*TextView view = find(R.id.normal_title);
		if (view != null) {
			if (view instanceof EditText) {
				view.setHint(str);
			} else {
				view.setText(str);
			}
		}*/
	}
	/**
	 * findViewById
	 * 
	 * @param id
	 * @param <T>
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T extends View> T find(int id) {
		try {
			return (T) findViewById(id);
		} catch (ClassCastException e) {
			Log.e("BaseActivity", "Could not cast View to concrete class");
			throw e;
		}
	}
	/**
	 * View.findViewById
	 * 
	 * @param id
	 * @param <T>
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T extends View> T find(View view, int id) {

		try {
			return (T) view.findViewById(id);
		} catch (ClassCastException e) {
			Log.e("BaseActivity", "Could not cast View to concrete class");
			throw e;
		}
	}
	/**
	 * findViewById.setOnClickListener
	 * 
	 * @param id
	 */
	public void setClick(int id) {
		if (find(id) != null) {
			find(id).setOnClickListener(this);
		}
	}

	public void setItemClick(int id) {
		AdapterView<?> adapterView = find(id);
		if (adapterView != null) {
			setItemClick(adapterView);
		}
	}

	public void setItemClick(AdapterView<?> view) {
		view.setOnItemClickListener(this);
	}

	public void setAdapter(int id, Adapter a) {
		AdapterView<Adapter> parent = find(id);
		parent.setAdapter(a);
	}

	public <T extends Activity> void startActivity(Class<T> clazz) {

		startActivity(new Intent(getBaseContext(), clazz));

	}


	@Override
	public void onClick(View v){
		
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {
	}
	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {

	}

	@Override
	public void afterTextChanged(Editable s) {

	}

	protected void startActivityForNoResult(Intent intent){
		startActivity(intent);
		overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
	}

}
