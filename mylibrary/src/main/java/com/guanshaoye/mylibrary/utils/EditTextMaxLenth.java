package com.guanshaoye.mylibrary.utils;

import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;
public class EditTextMaxLenth implements TextWatcher {
	private int maxLen = 0;
	private EditText editText = null;
	private TextView tv = null;
	public EditTextMaxLenth(int maxLen, EditText editText) {
		this.maxLen = maxLen;
		this.editText = editText;
	}
	public EditTextMaxLenth(int maxLen, EditText editText, TextView tv) {
		this.maxLen = maxLen;
		this.editText = editText;
		this.tv = tv;
	 }
	public void afterTextChanged(Editable arg0) {
		// TODO Auto-generated method stub
	 }
	public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,int arg3) {
		// TODO Auto-generated method stub
	 }
	public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3){
		// TODO Auto-generated method stub
		Editable editable = editText.getText();
		int len = editable.length();
		if(tv!=null){
		tv.setText(arg0.length()+"");
		}
		if (len > maxLen) {
			Toaster.showToast("您已超出字数限制");
			int selEndIndex = Selection.getSelectionEnd(editable);
			String str = editable.toString();
			// 截取新字符串
			String newStr = str.substring(0, maxLen);
			editText.setText(newStr);
			editable = editText.getText();
			// 新字符串的长度
			int newLen = editable.length();
			// 旧光标位置超过字符串长度
			if (selEndIndex > newLen) {
				selEndIndex = editable.length();
			}
			// 设置新光标所在的位置
			Selection.setSelection(editable, selEndIndex);
		}
	}

}
