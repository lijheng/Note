package com.summer.note.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.summer.note.R;


/**
 * CreateBy:lijheng at 2019/11/8
 * describe:完整显示某个View的布局
 */
public class NoneCompressLayout extends ViewGroup {

    public static final String TAG = "NoneCompressLayout";

    private static final int CENTER_HORIZONTAL = 0x01;
    private static final int TOP = 0x02;
    private static final int BOTTOM = 0x03;


    private int gravity = CENTER_HORIZONTAL;

    public NoneCompressLayout(Context context) {
        this(context, null);
    }

    public NoneCompressLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NoneCompressLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (attrs != null) {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.NoneCompressLayout);
            gravity = a.getInt(R.styleable.NoneCompressLayout_android_gravity, CENTER_HORIZONTAL);
            a.recycle();
        }
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
        setMeasuredDimension(resolveSize(width, widthMeasureSpec), resolveSize(height, heightMeasureSpec));
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        Log.d(TAG, "onLayout: l = " + l + ", t = " + t + ", r = " + r + ", b = " + b);
        int left = 0;
        //默认上下居中
        final int layoutHeight = b - t;
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            final int width = child.getMeasuredWidth();
            final int height = child.getMeasuredHeight();
            final int offset = getOffset(layoutHeight, height);
            MarginLayoutParams params = (MarginLayoutParams) child.getLayoutParams();
            left += params.leftMargin;
            child.layout(left, offset, left + width, height + offset);
            Log.d(TAG, "onLayout: child = " + child + " , l = " + left + ", t = " + (t + offset) + ", r = " + (r + width) + ", b = " + (b - offset));
            left += width + params.rightMargin;
        }

        for (int i = 0; i < getChildCount(); i++) {
            Log.d(TAG, "onLayout end: child = " + getChildAt(i) + ", height = " + getChildAt(i).getHeight());
        }
    }

    private int getOffset(int layoutHeight, int height) {
        int offset;
        if (gravity == CENTER_HORIZONTAL) {
            offset = (layoutHeight - height) / 2;
        } else if (gravity == TOP) {
            offset = 0;
        } else {
            offset = layoutHeight - height;
        }
        return offset;
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
    }

    @Override
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams p) {
        if (p instanceof LayoutParams) {
            return p;
        }
        return generateDefaultLayoutParams();
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new LayoutParams(getContext(), attrs);
    }


    @Override
    protected boolean checkLayoutParams(ViewGroup.LayoutParams p) {
        return p instanceof LayoutParams;
    }

    public static class LayoutParams extends MarginLayoutParams {

        /**
         * 任何情况下都优先显示完整
         */
        private boolean complete = false;


        public LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
            TypedArray a = c.obtainStyledAttributes(attrs, R.styleable.NoneCompressLayout_Layout);
            complete = a.getBoolean(R.styleable.NoneCompressLayout_Layout_complete, false);
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
