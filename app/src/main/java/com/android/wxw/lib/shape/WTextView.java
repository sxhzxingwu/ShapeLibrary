package com.android.wxw.lib.shape;


import android.content.Context;
import android.util.AttributeSet;

import com.android.wxw.lib.shape.helper.WViewHelper;

/***
 * 设置圆角
 * @author wxw
 */
public class WTextView extends android.support.v7.widget.AppCompatTextView {

	public WViewHelper mViewHelper;
	public WTextView(Context context) {
		super(context);
		init(context, null);
	}

	public WTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context, attrs);
	}

	public WTextView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init(context, attrs);
	}

	private void init(Context context, AttributeSet attrs){
		mViewHelper = new WViewHelper();
		mViewHelper.init(context, attrs, this);
	}
}
