package com.android.wxw.lib.shape;


import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

import com.android.wxw.lib.shape.helper.WViewHelper;

/***
 * 设置圆角
 * @author wxw
 */
public class WImageView extends AppCompatImageView {

	public WViewHelper mViewHelper;
	public WImageView(Context context) {
		super(context);
		init(context, null);
	}

	public WImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context, attrs);
	}

	public WImageView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init(context, attrs);
	}

	private void init(Context context, AttributeSet attrs){
		mViewHelper = new WViewHelper();
		mViewHelper.init(context, attrs, this);
	}
}
