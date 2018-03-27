package com.android.wxw.lib.shape.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.List;

public class PieView extends View {
    private Paint mPaint;
    private Paint mTextPaint;
    private List<PieData> mList;

    //线条的宽度
    private float W = 70f;//圆弧的宽度
    private float mTextHeight;

    //float pointXY;//圆心位置
    float radius;//半径
    float textPadBottom;//文字与线的距离

    public PieView(Context context) {
        super(context);
        init();
    }

    public PieView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PieView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drawC(canvas);
    }

    public void setData(List<PieData> list){
        if(list == null || list.size() == 0){
            setVisibility(GONE); return;
        }

        this.mList = list;
        postInvalidate();
    }

    private void drawC(Canvas canvas){
        if(mList == null || mList.size() == 0) return;
        int size = mList.size();

        float length = 3f*size;
        float all = 0f;
        for(PieData n:mList){
            all+=n.progress;
        }

        mItem = new Item[4][size];
        float start = -90;
        for(int i=0; i<size; i++){
            PieData data = mList.get(i);
            float progress = mList.get(i).progress/all*(360f-length);
            drawA(canvas, start, progress, data.text, data.color);
            start += 3 + progress;
        }

        mPaint.setStrokeWidth(2);
        drawItem1(canvas, mItem[0]);
        drawItem2(canvas, mItem[1]);
        drawItem3(canvas, mItem[2]);
        drawItem4(canvas, mItem[3]);
    }

    private Item[][] mItem;
    private void drawA(Canvas canvas, float start, float length, String text, int color){
        Path path = new Path();
        mPaint.setStrokeWidth(W);
        mPaint.setColor(color);
        float pointX, pointY;
        pointX = canvas.getWidth()/2f;
        pointY = canvas.getHeight()/2f;
        path.addArc(new RectF(pointX - radius, pointY -radius, pointX + radius, pointY + radius), start, length);

        canvas.drawPath(path, mPaint);

        mPaint.setStrokeWidth(W*0.1f);
        float point = start + length/2f;
        float l = dip2px(getContext(), 17);//斜线的长度

        float line2W = dip2px(getContext(), 65);//文字底部线的长度
        float radiusP = radius + l;

        Item item = new Item();
        item.text = text;
        item.color = color;
        mPaint.setStrokeWidth(2);
        if(point<90 && point>=-90){
            if(point<90 && point > 0){
                item.px = (float) (pointX + radiusP*cos(point));
                item.py = (float) (pointY + radiusP * sin(point));

                item.line1x = (float) (item.px + l*sin(45)); item.line1y =  (float) (item.py +l*sin(45));
                item.line2x = (float) (item.px + l*sin(45)) + line2W; item.line2y = (float) (item.py +l*sin(45));
                item.tx = item.line1x; item.ty = item.line1y - textPadBottom;
                addItem(mItem[1], item);
            }else{
                item.px = (float) (pointX + radiusP*cos((-point)));
                item.py = (float) (pointY - radiusP * sin((-point)));

                item.line1x = (float) (item.px + l*sin(45)); item.line1y = (float) (item.py -l*sin(45));
                item.line2x = (float) (item.px + l*sin(45)) + line2W; item.line2y = (float) (item.py -l*sin(45));
                item.tx = item.line1x; item.ty = item.line1y - textPadBottom;
                addItem(mItem[0], item);
            }
        }else{
            if(point<180 && point > 90){
                item.px = (float) (pointX - radiusP*sin(point - 90));
                item.py = (float) (pointY + radiusP * cos(point -90));

                item.line1x = (float) (item.px - l*sin(45)); item.line1y = (float) (item.py +l*sin(45));
                item.line2x = (float) (item.px - l*sin(45)) - line2W; item.line2y = (float) (item.py +l*sin(45));
                item.tx = item.line1x - mTextPaint.measureText(text); item.ty = item.line1y - textPadBottom;
                addItem(mItem[2], item);
            }else{
                item.px = (float) (pointX + radiusP*cos((-point)));
                item.py = (float) (pointY - radiusP * sin((-point)));

                item.line1x = (float) (item.px - l*sin(45)); item.line1y = (float) (item.py -l*sin(45));
                item.line2x = (float) (item.px - l*sin(45)) - line2W; item.line2y = (float) (item.py -l*sin(45));
                item.tx = item.line1x - mTextPaint.measureText(text); item.ty = item.line1y - textPadBottom;
                addItem(mItem[3], item);
            }
        }
    }

    private void drawItem1(Canvas canvas, Item[] items){
        float ty =0;
        float h = mTextHeight;
        for(int i=items.length -1; i>=0; i--){
            Item item = items[i];
            if(item == null) continue;

            if(item.line1y + h + textPadBottom>=ty && ty !=0){
                item.ty = ty - h -textPadBottom;
                item.line1y = ty - h;
                item.line2y = ty - h;
            }
            drawItem(canvas, item);
            ty = item.ty;
        }
    }

    private void drawItem2(Canvas canvas, Item[] items){
        float ty =0;
        float h = mTextHeight;
        for (Item item : items) {
            if (item == null) break;

            if (item.ty - h - textPadBottom <= ty && ty != 0) {
                item.ty = ty + h - textPadBottom;
                item.line1y = ty + h;
                item.line2y = ty + h;
            }
            drawItem(canvas, item);
            ty = item.line1y;
        }
    }

    private void drawItem3(Canvas canvas, Item[] items){
        float ty =0;
        float h = mTextHeight;
        for(int i=items.length -1; i>=0; i--){
            Item item = items[i];
            if(item == null) continue;

            if(item.line1y - h - textPadBottom<=ty && ty !=0){
                item.ty = ty + h -textPadBottom;
                item.line1y = ty + h;
                item.line2y = ty + h;
            }
            drawItem(canvas, item);
            ty = item.line1y;
        }
    }

    private void drawItem4(Canvas canvas, Item[] items){
        float ty =0;
        float h = mTextHeight;
        for (Item item : items) {
            if (item == null) break;

            if (item.line1y + h + textPadBottom >= ty && ty != 0) {
                item.ty = ty - h - textPadBottom;
                item.line1y = ty - h;
                item.line2y = ty - h;
            }
            drawItem(canvas, item);
            ty = item.line1y;
        }
    }


    private void drawItem(Canvas canvas, Item item){
        mPaint.setStrokeWidth(5);
        mPaint.setColor(item.color);

        Path pathL = new Path();
        pathL.moveTo(item.px, item.py);

        pathL.lineTo(item.line1x, item.line1y);
        pathL.lineTo(item.line2x, item.line2y);
        mPaint.setStrokeWidth(2);

        canvas.drawPath(pathL, mPaint);
        mTextPaint.setColor(item.color);
        canvas.drawCircle(item.px, item.py, dip2px(getContext(),2), mTextPaint);
        canvas.drawText(item.text, item.tx, item.ty, mTextPaint);
    }

    private void addItem(Item[] items, Item item){
        for(int i=0; i<items.length; i++){
            if(items[i] == null){
                items[i] = item;
                break;
            }
        }
    }

    private class Item{
        float px, line1x, line2x;
        float py, line1y, line2y;
        float tx, ty;

        String text;
        int color;
    }
    private double cos(float point){
        return Math.cos(2*Math.PI/360*(point));
    }

    private double sin(float point){
        return Math.sin(2*Math.PI/360*(point));
    }

    private void init(){

        mTextHeight = dip2px(getContext(), 14);
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(W);
        mPaint.setAntiAlias(true);

        mTextPaint = new Paint();
        mTextPaint.setStrokeWidth(1);
        mTextPaint.setColor(0xffffffff);
        mTextPaint.setTextSize(dip2px(getContext(), 12));
        mTextPaint.setAntiAlias(true);
        mTextPaint.setStyle(Paint.Style.FILL);

        W = dip2px(getContext(), 25);
        radius = dip2px(getContext(), 40) + W/2f;
        textPadBottom = dip2px(getContext(), 3);//文字与线的距离
    }

    public int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static class PieData{
        public float progress;
        public String text;
        public int color;
    }
}
