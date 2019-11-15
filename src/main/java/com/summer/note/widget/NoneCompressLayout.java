package com.summer.note.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.summer.myapplication.R;

/**
 * CreateBy:lijheng at 2019/11/8
 * describe:完整显示某个View的布局
 */
public class NoneCompressLayout extends ViewGroup {

    public static final String TAG = "NoneCompressLayout";

    public NoneCompressLayout(Context context) {
        super(context);
    }

    public NoneCompressLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public NoneCompressLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int left = 0;
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            final int width = child.getMeasuredWidth();
            Log.d(TAG, "onLayout: " + width);
            final int height = child.getMeasuredHeight();
            final int yOffset = Math.abs((b - t - height) / 2);
            MarginLayoutParams params = (MarginLayoutParams) child.getLayoutParams();
            left += params.leftMargin;
            child.layout(left, yOffset, left + width, b - yOffset);
            Log.d(TAG, "onLayout: [left:" + (l + left) + "  top:" + (t + yOffset) + "  right:" + (l + left + width) + " bottom:" + (b - yOffset));
            left += width + params.rightMargin;
        }
        Log.d(TAG, "onLayout: width = " + getMeasuredWidth() + "  height = " + getMeasuredHeight());
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
    }

    @Override
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams p) {
        if (p instanceof LayoutParams){
            return p;
        }
        return generateDefaultLayoutParams();
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new LayoutParams(getContext(), attrs);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = 0, height = 0;
        //已使用的宽
        int widthUsed = 0;
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            LayoutParams params = (LayoutParams) child.getLayoutParams();
            widthUsed += params.leftMargin;
            widthUsed += params.rightMargin;
            //如果是固定宽度的布局
            if (params.complete) {
                measureChildWithMargins(child, widthMeasureSpec, widthUsed, heightMeasureSpec, 0);
                widthUsed += child.getMeasuredWidth();
            }
        }
        width += widthUsed;
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            LayoutParams params = (LayoutParams) child.getLayoutParams();
            widthUsed += params.leftMargin;
            widthUsed += params.rightMargin;
            if (!params.complete) {
                measureChildWithMargins(child, widthMeasureSpec, widthUsed, heightMeasureSpec, 0);
            }
            width += widthUsed;
            width += child.getMeasuredWidth();
            height = Math.max(height, params.topMargin + params.bottomMargin + child.getMeasuredHeight());
        }
        setMeasuredDimension(getDefaultSize(width, widthMeasureSpec), getDefaultSize(height, heightMeasureSpec));
    }

    public static int getDefaultSize(int size, int measureSpec) {
        int result = size;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        switch (specMode) {
            case MeasureSpec.UNSPECIFIED:
            case MeasureSpec.AT_MOST:
                result = size;
                break;
            case MeasureSpec.EXACTLY:
                result = specSize;
                break;
        }
        return result;
    }

    @Override
    protected boolean checkLayoutParams(ViewGroup.LayoutParams p) {
        return p instanceof LayoutParams;
    }

    public class LayoutParams extends MarginLayoutParams{

        /**
         * 任何情况下都优先显示完整
         */
        private boolean complete = false;

        public LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
            TypedArray a = c.obtainStyledAttributes(attrs, R.styleable.NoneCompressLayout_Layout);
            complete = a.getBoolean(R.styleable.NoneCompressLayout_Layout_complete,false);
            a.recycle();
        }

        public LayoutParams(int width, int height) {
            super(width, height);
        }

        public LayoutParams(LayoutParams source) {
            super(source);
            this.complete = source.complete;
        }

        public boolean isComplete() {
            return complete;
        }
    }
}
