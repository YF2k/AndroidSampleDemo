package com.joker.demo.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import com.joker.demo.R;

import androidx.annotation.Nullable;

public class JokerView extends View {
    String mTitleText;
    int mTitleTextColor;
    int mTitleTextSize;
    Paint mPaint;
    Rect mBound;

    public JokerView(Context context) {
        super(context);
    }

    public JokerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        //获取自定义属性
        TypedArray ta=context.obtainStyledAttributes(attrs, R.styleable.JokerView);
        int count=ta.getIndexCount();
        for(int i=0;i<count;i++){
            int attr=ta.getIndex(i);
            switch (attr){
                case R.styleable.JokerView_titleText:
                    mTitleText=ta.getString(attr);
                    break;
                case R.styleable.JokerView_titleTextColor:
                    mTitleTextColor = ta.getColor(attr, Color.BLACK);
                    break;
                case R.styleable.JokerView_titleTextSize:
                    // 默认设置为16sp，TypeValue也可以把sp转化为px
                    mTitleTextSize = ta.getDimensionPixelSize(attr,16);
                    break;
            }
        }

        ta.recycle();
        mPaint=new Paint();
        mPaint.setTextSize(mTitleTextSize);

        mBound=new Rect();
        mPaint.getTextBounds(mTitleText,0,mTitleText.length(),mBound);

    }

    public JokerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public JokerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(Color.YELLOW);
        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), mPaint);

        mPaint.setColor(mTitleTextColor);
        canvas.drawText(mTitleText, getWidth() / 2 - mBound.width() / 2, getHeight() / 2 + mBound.height() / 2, mPaint);

    }
}
