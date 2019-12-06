package com.summer.myapplication.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.annotation.IntDef;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.Px;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import com.summer.myapplication.DensityUtil;
import com.summer.myapplication.R;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * CreateBy:lijheng at 2019/10/31
 * describe:自定义的toolbar
 */
@SuppressWarnings({"unused"})
public class SimpleToolBar extends ViewGroup {


    private ImageView mLeftImageView;

    private TextView mTitleTextView;
    private @ColorInt
    int mTitleTextColor;

    private View mRightView;//右侧布局
    private @Px
    int mRightTextSize;
    private @ColorInt
    int mRightTextColor;

    private Context mContext;//上下文

    private boolean hasBottomLine;

    private Paint mPaint;

    private @DrawableRes
    int mLeftImageViewRes;//左侧图片资源


    public SimpleToolBar(Context context) {
        this(context, null);
    }

    public SimpleToolBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, R.attr.toolbarStyle);
    }

    public SimpleToolBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        TypedArray array = mContext.obtainStyledAttributes(attrs, R.styleable.SimpleToolBar);
        hasBottomLine = array.getBoolean(R.styleable.SimpleToolBar_bottom_line, false);
        mLeftImageViewRes = array.getResourceId(R.styleable.SimpleToolBar_left_icon, R.drawable.ic_back_black);
        setLeftImageView();
        mRightTextSize = array.getDimensionPixelSize(R.styleable.SimpleToolBar_right_textSize, DensityUtil.sp2px(16));
        mRightTextColor = array.getColor(R.styleable.SimpleToolBar_right_textColor, 0xffffffff);
        mTitleTextColor = array.getColor(R.styleable.SimpleToolBar_title_textColor, 0xff333333);
        String title = array.getString(R.styleable.SimpleToolBar_title);
        if (!TextUtils.isEmpty(title)) {
            setTitle(title);
        }
        String rightText = array.getString(R.styleable.SimpleToolBar_right_text);
        if (!TextUtils.isEmpty(rightText)) {
            setRightText(rightText);
        }
        int drawableRes = array.getResourceId(R.styleable.SimpleToolBar_right_src,-1);
        if (drawableRes!=-1){
            setRightImage(drawableRes);
        }
        if (hasBottomLine) {
            setWillNotDraw(false);
        }
        array.recycle();
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (!changed) {
            return;
        }
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            if (child == null) {
                continue;
            }
            layoutChild(l, t, r, b, child);
        }
    }

    /**
     * 给子布局布局
     */
    private void layoutChild(int l, int t, int r, int b, View child) {
        LayoutParams params = (LayoutParams) child.getLayoutParams();
        int height = child.getMeasuredHeight();
        int top = (getHeight() - height) / 2;
        int width = child.getMeasuredWidth();
        switch (params.getLayoutGravity()) {
            //中间的view，只有一个，通常为title
            case LayoutParams.CENTER:
                int left = ((r - l) - child.getMeasuredWidth()) / 2;
                child.layout(left, top, left + width, top + height);
                break;
            //左侧布局
            case LayoutParams.LEFT:
                child.layout(l + params.leftMargin, top, l + params.leftMargin + width, top + height);
                break;
            //右侧布局
            case LayoutParams.RIGHT:
                child.layout(r - width - params.rightMargin, top, r - params.rightMargin, top + height);
                break;
        }
    }

    @Override
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams p) {
        if (p instanceof LayoutParams) {
            return p;
        } else {
            return new LayoutParams(p);
        }
    }

    @Override
    protected boolean checkLayoutParams(ViewGroup.LayoutParams p) {
        return p instanceof LayoutParams;
    }

    @Override
    protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, LayoutParams.RIGHT);
    }

    @Override
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new LayoutParams(getContext(), attrs);
    }

    /**
     * 设置左侧返回按钮
     */
    private void setLeftImageView() {
        OnClickListener mClickListener = null;
        if (mContext instanceof AppCompatActivity) {
            //初始化一个点击事件
            mClickListener = view -> ((AppCompatActivity) mContext).finish();
        }
        mLeftImageView = new ImageView(mContext);
        mLeftImageView.setImageResource(mLeftImageViewRes);
        mLeftImageView.setOnClickListener(mClickListener);
        int dp8 = DensityUtil.dp2px(8);
        mLeftImageView.setPadding(dp8, dp8, dp8, dp8);
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, LayoutParams.LEFT);
        layoutParams.leftMargin = dp8;
        addView(mLeftImageView, layoutParams);
    }

    /**
     * 设置左侧按钮点击监听
     * @param clickListener 监听
     */
    public void setLeftClickListener(OnClickListener clickListener){
        if (mLeftImageView!=null){
            mLeftImageView.setOnClickListener(clickListener);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (hasBottomLine) {
            if (mPaint == null) {
                mPaint = new Paint();
                mPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
                mPaint.setColor(0xffE7E7E7);
                mPaint.setStrokeWidth(DensityUtil.dp2px(1));
            }
            final int height = getHeight();
            final int width = getWidth();
            canvas.drawLine(0, height, width, height, mPaint);
        }
    }

    /**
     * 设置标题
     *
     * @param title 标题
     */
    public void setTitle(CharSequence title) {
        if (!TextUtils.isEmpty(title)) {
            if (mTitleTextView == null) {
                mTitleTextView = new TextView(getContext());
                mTitleTextView.setSingleLine();
                mTitleTextView.setEllipsize(TextUtils.TruncateAt.END);
                mTitleTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
                mTitleTextView.setTextColor(mTitleTextColor);
                mTitleTextView.setTypeface(Typeface.DEFAULT_BOLD);
                addView(mTitleTextView, new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, LayoutParams.CENTER));
            }
            mTitleTextView.setText(title);

        } else if (mTitleTextView != null) {
            removeView(mTitleTextView);
            mTitleTextView = null;
        }
    }

    /**
     * 设置标题
     *
     * @param resId 标题的资源id
     */
    public void setTitle(@StringRes int resId) {
        setTitle(getResources().getString(resId));
    }

    /**
     * 获得标题
     *
     * @return 标题
     */
    public CharSequence getTitle() {
        return mTitleTextView == null ? null : mTitleTextView.getText();
    }

    /**
     * 设置右侧文本
     *
     * @param resId 右侧文本资源id
     */
    public void setRightText(@StringRes int resId) {
        setRightText(getResources().getString(resId));
    }

    /**
     * 设置右侧文本
     *
     * @param rightText 右侧文本内容
     */
    public void setRightText(CharSequence rightText) {
        if (!TextUtils.isEmpty(rightText)) {
            if (!(mRightView instanceof TextView)) {
                removeRightView();
                mRightView = new TextView(getContext());
                TextView textView = (TextView) mRightView;
                textView.setTextColor(mRightTextColor);
                textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, mRightTextSize);
                LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT,LayoutParams.RIGHT);
                params.rightMargin = DensityUtil.dp2px(16);
                addView(mRightView, params);
            }
            ((TextView)mRightView).setText(rightText);
        } else if (mRightView instanceof TextView) {
            removeView(mRightView);
            mRightView = null;
        }
    }

    /**
     * 移除左侧布局
     */
    private void removeRightView(){
        if (mRightView!=null){
            removeView(mRightView);
        }
    }

    /**
     * 设置右侧图标
     * @param drawableRes drawable
     */
    public void setRightImage(@DrawableRes int drawableRes){
        setRightImage(getResources().getDrawable(drawableRes));
    }

    /**
     * 设置右侧图标
     * @param drawable 图片的drawable
     */
    public void setRightImage(@NonNull Drawable drawable){
        if (!(mRightView instanceof ImageView)){
            removeRightView();
            mRightView = new ImageView(mContext);
            LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT,LayoutParams.RIGHT);
            params.rightMargin = DensityUtil.dp2px(16);
            addView(mRightView,params);
        }
        ImageView imageView= (ImageView) mRightView;
        imageView.setImageDrawable(drawable);
    }


    /**
     * 设置右侧文本按钮点击监听
     *
     * @param listener 监听
     */
    public void setRightListener(@NonNull OnClickListener listener) {
        if (mRightView != null) {
            mRightView.setOnClickListener(listener);
        }
    }

    /**
     * 测量控件宽高
     * 重点是宽度的测量，此处需要最后测量title的宽度，为了让title在任何时候都可以居中
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        final int childCount = getChildCount();
        if (childCount>3){
            throw new IllegalArgumentException("最多只能有三个直接子View");
        }
        int leftViewWidth, rightViewWidth;//title左边的view宽度，title右边的view宽度
        leftViewWidth = rightViewWidth = 0;
        int titlePosition = -1;

        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            if (child == null) {
                continue;
            }

            measureChildWithMargins(child, widthMeasureSpec, 0, heightMeasureSpec, 0);
            LayoutParams params = (LayoutParams) child.getLayoutParams();
            //如果是标题，且是第一个
            if (params.getLayoutGravity()==LayoutParams.CENTER&&titlePosition==-1){
                titlePosition = i;
                continue;
            }else if (params.getLayoutGravity()==LayoutParams.CENTER){
                //不允许出现两个中间布局
                throw new IllegalStateException("不支持两个居中View,请使用ViewGroup包裹");
            }

            //在标题左边
            if (params.getLayoutGravity() == LayoutParams.LEFT) {
                leftViewWidth += child.getMeasuredWidth();
            } else {
                rightViewWidth += child.getMeasuredWidth();
            }

        }
        int width;
        //测量中间View(通常为title)
        if (titlePosition != -1) {
            View centerView = getChildAt(titlePosition);
            measureChildWithMargins(centerView, widthMeasureSpec, Math.max(leftViewWidth, rightViewWidth) * 2, heightMeasureSpec, 0);
            width = centerView.getMeasuredWidth() + Math.max(leftViewWidth, rightViewWidth) * 2;
        } else {
            width = leftViewWidth + rightViewWidth;
        }
        final int height = getDefaultViewSize(getSuggestedMinimumHeight(), heightMeasureSpec);
        setMeasuredDimension(getDefaultViewSize(width, widthMeasureSpec), height);
    }


    /**
     * 获取尺寸
     *
     * @param size        默认尺寸
     * @param measureSpec 测量参数
     * @return 实际尺寸
     */
    private int getDefaultViewSize(int size, int measureSpec) {
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

    /**
     * {@link SimpleToolBar}的布局参数
     */
    public static class LayoutParams extends MarginLayoutParams {
        @Documented
        @IntDef({LEFT, RIGHT, CENTER})
        @Target({ElementType.PARAMETER, ElementType.FIELD, ElementType.METHOD})
        @Retention(RetentionPolicy.SOURCE)
        public @interface Gravity {

        }

        public static final int LEFT = 0;//位于标题左侧
        public static final int RIGHT = 1;//位于标题右侧
        public static final int CENTER = 2;//标题

        private int layoutGravity;//相对于title的位置，左或右

        public LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
            TypedArray array = c.obtainStyledAttributes(attrs, R.styleable.SimpleToolBar_Layout);
            setLayoutGravity(array.getInt(R.styleable.SimpleToolBar_Layout_layout_gravity, RIGHT));
            array.recycle();
        }

        public LayoutParams(int width, int height, @Gravity int layoutGravity) {
            super(width, height);
            setLayoutGravity(layoutGravity);
        }

        public LayoutParams(MarginLayoutParams source) {
            super(source);
        }

        public LayoutParams(ViewGroup.LayoutParams source) {
            super(source);
        }

        public void setLayoutGravity(@Gravity int layoutGravity) {
            this.layoutGravity = layoutGravity;
        }

        public @Gravity
        int getLayoutGravity() {
            return layoutGravity;
        }
    }
}
