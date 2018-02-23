package com.android.wxw.lib.shape;


import android.content.Context;
import android.util.AttributeSet;
import android.widget.RadioGroup;

import com.android.wxw.lib.shape.helper.WViewHelper;

/***
 * 设置圆角
 * @author wxw
 */
public class WRadioGroup extends RadioGroup {

	private WViewHelper mViewHelper;
	public WRadioGroup(Context context) {
		super(context);
		init(context, null);
	}

	public WRadioGroup(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context, attrs);
	}

	private void init(Context context, AttributeSet attrs){
		mViewHelper = new WViewHelper();
		mViewHelper.init(context, attrs, this);
	}
}
