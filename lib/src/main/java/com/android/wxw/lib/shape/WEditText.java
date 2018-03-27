package com.android.wxw.lib.shape;


import android.content.Context;
import android.util.AttributeSet;

import com.android.wxw.lib.shape.helper.WViewHelper;


/***
 * 设置圆角
 * @author wxw
 */
public class WEditText extends android.support.v7.widget.AppCompatEditText {

	public WViewHelper mViewHelper;
	public WEditText(Context context) {
		super(context);
		init(context, null);
	}

	public WEditText(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context, attrs);
	}

	public WEditText(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init(context, attrs);
	}

	private void init(Context context, AttributeSet attrs){
		mViewHelper = new WViewHelper();
		mViewHelper.init(context, attrs, this);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}
}
