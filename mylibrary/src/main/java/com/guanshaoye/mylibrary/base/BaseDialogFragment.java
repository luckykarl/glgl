package com.guanshaoye.mylibrary.base;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import butterknife.ButterKnife;
public class BaseDialogFragment extends DialogFragment{
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setStyle(DialogFragment.STYLE_NORMAL,android.R.style.Theme_Light_NoTitleBar);}
	   @Override
	    public void onDestroyView(){
		super.onDestroyView();
		ButterKnife.unbind(this);//解绑
	}

}
