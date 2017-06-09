package com.guanshaoye.glglteacher.utils;


import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.ViewTreeObserver.OnPreDrawListener;
import android.widget.TextView;

import com.guanshaoye.glglteacher.R;


public class LoadingDialog extends Dialog {

	static LoadingDialog dialog=null;
	Context context;
	static TextView tv_content;
	public LoadingDialog(Context context, int theme) {
		super(context, theme);
		this.context=context;
	}
	
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.loading_dailog);
		tv_content=(TextView) findViewById(R.id.tv_content);
      final AnimationDrawable animDance = (AnimationDrawable)findViewById(R.id.img_progress).getBackground();
		
		findViewById(R.id.img_progress).getViewTreeObserver().addOnPreDrawListener(new OnPreDrawListener() {
			@Override
			public boolean onPreDraw() {
				animDance.start();
				return true;
			}
		});
		
		dialog.setOnDismissListener(new OnDismissListener() {
			
			@Override
			public void onDismiss(DialogInterface arg0) {
				// TODO Auto-generated method stub
				if(dialog!=null){
					if(!((Activity)context).isFinishing()){
						dialog.dismiss();
					}
					dialog = null;
				}
			}
		});
		
	}
	
	public static void ShowLoading(Context context){
	
		if (dialog==null) {
			dialog = new LoadingDialog(context,R.style.dialog);//创建Dialog并设置样式主�?
			dialog.setCanceledOnTouchOutside(false);//设置点击Dialog外部任意区域关闭Dialog
		}
		   dialog.show();
	}
	
	public static LoadingDialog getLoadingDialog(){
		
		return dialog;
		
	}
	
	public static boolean DissLoading(Context context){
	
		if(dialog==null){
			return false;
		}
		if(dialog!=null || dialog.isShowing()){
			dialog.dismiss();
			dialog = null;
		}
		return true;
	}
}
