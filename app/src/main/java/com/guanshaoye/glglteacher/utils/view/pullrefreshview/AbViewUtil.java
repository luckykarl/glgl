package com.guanshaoye.glglteacher.utils.view.pullrefreshview;
/*
 * Copyright (C) 2013 www.418log.org
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


import android.content.Context;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.AbsListView;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

// TODO: Auto-generated Javadoc

/**
 * The Class AbViewUtil.
 */
public class AbViewUtil {

	/**
	 * 描述：重置AbsListView的高度.
	 * item 的最外层布局要用 RelativeLayout,如果计算的不准，就为RelativeLayout指定一个高度
	 * @param absListView the abs list view
	 * @param lineNumber 每行几个  ListView一行一个item
	 * @param verticalSpace the vertical space
	 */
	public static void setAbsListViewHeight(AbsListView absListView,int lineNumber,int verticalSpace) {
		
		int totalHeight = getAbsListViewHeight(absListView,lineNumber,verticalSpace);
		ViewGroup.LayoutParams params = absListView.getLayoutParams();
		params.height = totalHeight;
		((MarginLayoutParams) params).setMargins(0, 0, 0, 0);
		absListView.setLayoutParams(params);
	}
	
	/**
	 * 描述：获取AbsListView的高度.
	 * @param absListView the abs list view
	 * @param lineNumber 每行几个  ListView一行一个item
	 * @param verticalSpace the vertical space
	 */
	public static int getAbsListViewHeight(AbsListView absListView,int lineNumber,int verticalSpace) {
		int totalHeight = 0;
		int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
	    int h = View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED);
	    absListView.measure(w, h);
	    ListAdapter mListAdapter = absListView.getAdapter();
		if (mListAdapter == null) {
			return totalHeight;
		}
		
		int count = mListAdapter.getCount();
		if(absListView instanceof ListView){
			for (int i = 0; i < count; i++) {
				View listItem = mListAdapter.getView(i, null, absListView);
				listItem.measure(w, h);
				totalHeight += listItem.getMeasuredHeight();
			}
			if (count == 0) {
				totalHeight = verticalSpace;
			} else {
				totalHeight = totalHeight + (((ListView)absListView).getDividerHeight() * (count - 1));
			}
			
		}else if(absListView instanceof GridView){
			int remain = count % lineNumber;
			if(remain>0){
				remain = 1;
			}
			if(mListAdapter.getCount()==0){
				totalHeight = verticalSpace;
			}else{
				View listItem = mListAdapter.getView(0, null, absListView);
				listItem.measure(w, h);
				int line = count/lineNumber + remain;
				totalHeight = line*listItem.getMeasuredHeight()+(line-1)*verticalSpace;
			}
			
		}
		return totalHeight;

	}
	
	/**
	 * 测量这个view，最后通过getMeasuredWidth()获取宽度和高度.
	 *
	 * @param v 要测量的view
	 * @return 测量过的view
	 */
	public static void measureView(View v){
		if(v == null){
			return;
		}
		int w = View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED); 
	    int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
	    v.measure(w, h);
	}

	/**
	 * 设置PX padding.
	 *
	 * @param view the view
	 * @param left the left padding in pixels
	 * @param top the top padding in pixels
	 * @param right the right padding in pixels
	 * @param bottom the bottom padding in pixels
	 */
	public static void setPadding(View view, int left,
								  int top, int right, int bottom) {
		int scaledLeft = scaleValue(view.getContext(), left);
		int scaledTop = scaleValue(view.getContext(), top);
		int scaledRight = scaleValue(view.getContext(), right);
		int scaledBottom = scaleValue(view.getContext(), bottom);
		view.setPadding(scaledLeft, scaledTop, scaledRight, scaledBottom);
	}

	/**
	 * 描述：根据分辨率获得字体大小.
	 *
	 * @param screenWidth the screen width
	 * @param screenHeight the screen height
	 * @param textSize the text size
	 * @return the int
	 */
	public static int resizeTextSize(int screenWidth,int screenHeight,int textSize){
		float ratio =  1;
		try {
			float ratioWidth = (float)screenWidth / 480; 
			float ratioHeight = (float)screenHeight / 800; 
			ratio = Math.min(ratioWidth, ratioHeight); 
		} catch (Exception e) {
		}
		return Math.round(textSize * ratio);
	}
	
	/**
	 * 
	 * 描述：dip转换为px
	 * @param context
	 * @param dipValue
	 * @return
	 * @throws 
	 */
	public static int dip2px(Context context, float dipValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dipValue * scale + 0.5f);
	}

	/**
	 * 
	 * 描述：px转换为dip
	 * @param context
	 * @param pxValue
	 * @return
	 * @throws 
	 */
	public static int px2dip(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}

	/**
	 * 描述：根据屏幕大小缩放.
	 *
	 * @param context the context
	 * @return the int
	 */
	public static int scaleValue(Context context, float value) {
		DisplayMetrics mDisplayMetrics = AbAppUtil.getDisplayMetrics(context);
		//为了兼容尺寸小密度大的情况
		int width = mDisplayMetrics.widthPixels;
		int height = mDisplayMetrics.heightPixels;
		//解决横屏比例问题
		if(width > height){
			width = mDisplayMetrics.heightPixels;
			height = mDisplayMetrics.widthPixels;
		}
		if(mDisplayMetrics.scaledDensity >= 2){
			//密度
			if(width > 720){
				value = value*(1.3f - 1.0f/mDisplayMetrics.scaledDensity);
			}else if(width < 720){
				value = value*(1.0f - 1.0f/mDisplayMetrics.scaledDensity);
			}
		}else{
			//密度小屏幕大:缩小比例
			float offset = 2-mDisplayMetrics.scaledDensity;
			if(offset > 0.5f){
				value = value * 0.9f;
			}else{
				value = value * 0.95f;
			}

		}
		return scale(mDisplayMetrics.widthPixels,mDisplayMetrics.heightPixels, value);
	}

	/**
	 * 描述：根据屏幕大小缩放.
	 *
	 * @param displayWidth the display width
	 * @param displayHeight the display height
	 * @param pxValue the px value
	 * @return the int
	 */
	public static int scale(int displayWidth, int displayHeight, float pxValue) {
		if(pxValue == 0 ){
			return 0;
		}
		float scale = 1;
		try {
			int width = displayWidth;
			int height = displayHeight;
			//解决横屏比例问题
			if(width > height){
				width = displayHeight;
				height = displayWidth;
			}
			float scaleWidth = (float) width / 720;
			float scaleHeight = (float) height / 1280;
			scale = Math.min(scaleWidth, scaleHeight);
		} catch (Exception e) {
		}
		return Math.round(pxValue * scale + 0.5f);
	}

	/**
	 * 缩放文字大小,这样设置的好处是文字的大小不和密度有关，
	 * 能够使文字大小在不同的屏幕上显示比例正确
	 * @param textView button
	 * @param sizePixels px值
	 * @return
	 */
	public static void setTextSize(TextView textView,float sizePixels) {
		float scaledSize = scaleTextValue(textView.getContext(),sizePixels);
		textView.setTextSize(TypedValue.COMPLEX_UNIT_PX,scaledSize);
	}

	/**
	 * 描述：根据屏幕大小缩放文本.
	 *
	 * @param context the context
	 * @return the int
	 */
	public static int scaleTextValue(Context context, float value) {
		return scaleValue(context, value);
	}
	
}
