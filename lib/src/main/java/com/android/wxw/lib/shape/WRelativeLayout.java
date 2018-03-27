package com.android.wxw.lib.shape;


import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import com.android.wxw.lib.shape.helper.WViewHelper;
/***
 * 设置圆角
 * @author wxw
 */
public class WRelativeLayout extends RelativeLayout {

	public WViewHelper mWViewHelper;
	public WRelativeLayout(Context context) {
		super(context);
		init(context, null);
	}

	public WRelativeLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context, attrs);
	}

	public WRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init(context, attrs);
	}

	private void init(Context context, AttributeSet attrs){
		mWViewHelper = new WViewHelper();
		mWViewHelper.init(context, attrs, this);
	}
}
