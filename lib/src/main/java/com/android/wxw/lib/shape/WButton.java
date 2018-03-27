package com.android.wxw.lib.shape;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import com.android.wxw.lib.shape.helper.WViewHelper;


/***
 * 设置圆角
 * @author wxw
 */
public class WButton extends android.support.v7.widget.AppCompatButton {

	public WViewHelper mViewHelper;
	public WButton(Context context) {
		super(context);
		init(context, null);
	}

	public WButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context, attrs);
	}

	public WButton(Context context, AttributeSet attrs, int defStyleAttr) {
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
		setPadding(0, 0, 0, 0);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		Drawable[] drawables = getCompoundDrawables();
		if (drawables != null) {
			Drawable drawableLeft = drawables[0];
			if (drawableLeft != null) {
				float textWidth = getPaint().measureText(getText().toString()) + 20;
				int drawablePadding = getCompoundDrawablePadding();
				int drawableWidth = drawableLeft.getIntrinsicWidth();
				float bodyWidth = textWidth + drawableWidth + drawablePadding;
				canvas.translate((getWidth() - bodyWidth) / 2, 0);
			}
		}
		super.onDraw(canvas);
	}
}
