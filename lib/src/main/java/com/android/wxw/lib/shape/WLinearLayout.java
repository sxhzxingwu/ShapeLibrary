package com.android.wxw.lib.shape;


import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.android.wxw.lib.shape.helper.WViewHelper;

/***
 * 设置圆角
 * @author wxw
 */
@SuppressLint("NewApi")
public class WLinearLayout extends LinearLayout {
	public WViewHelper mViewHelper;

	public WLinearLayout(Context context) {
		super(context);
		init(context, null);
	}

	public WLinearLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context, attrs);
	}

	public WLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init(context, attrs);
	}

	private void init(Context context, AttributeSet attrs){
		mViewHelper = new WViewHelper();
		mViewHelper.init(context, attrs, this);
	}
}
