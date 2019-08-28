package com.summer.note.widget;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.shapes.Shape;
import android.widget.LinearLayout;

import androidx.annotation.IntDef;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class ShapeDrawable extends Drawable {

    @IntDef({STROKE, FILL})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Style {
    }

    public static final int STROKE = 0x00;
    public static final int FILL = 0x01;

    private int style;
    private Shape shape;
    private int stokeColor, solidColor;//边框颜色和填充颜色
    private int strokeWidth;//边框宽度
    private int topLeftRadius, topRightRadius, bottomLeftRadius, bottomRightRadius;//矩形的圆角
    private int width, height;//drawable的宽高

    private ShapeDrawable(Shape shape, int stokeColor, int solidColor, int strokeWidth,
                          int topLeftRadius, int topRightRadius, int bottomLeftRadius,
                          int bottomRightRadius, int width, int height, int style) {
        this.shape = shape;
        this.stokeColor = stokeColor;
        this.solidColor = solidColor;
        this.strokeWidth = strokeWidth;
        this.topLeftRadius = topLeftRadius;
        this.topRightRadius = topRightRadius;
        this.bottomLeftRadius = bottomLeftRadius;
        this.bottomRightRadius = bottomRightRadius;
        this.width = width;
        this.height = height;
        this.style = style;
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        switch (shape) {
            //圆形
            case CIRCLE:
                drawCircle(canvas);
                break;
            case RECTANGLE:
                drawRectangle(canvas);
                break;
            case ROUND_REC:
                drawRoundRec(canvas);
                break;
            case SEMICIRCLE_REC:
                drawSemicircleRec(canvas);
                break;
        }
    }

    /**
     * 画圆
     *
     * @param canvas
     */
    private void drawCircle(Canvas canvas) {
        if (style == STROKE) {

        } else {

        }
    }

    /**
     * 画矩形
     *
     * @param canvas
     */
    private void drawRectangle(Canvas canvas) {

    }

    /**
     * 画圆角矩形
     *
     * @param canvas
     */
    private void drawRoundRec(Canvas canvas) {

    }

    /**
     * 画半圆
     *
     * @param canvas
     */
    private void drawSemicircleRec(Canvas canvas) {

    }

    @Override
    public void setAlpha(int i) {

    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {

    }

    /**
     * @deprecated
     */
    @Override
    public int getOpacity() {
        return PixelFormat.TRANSPARENT;
    }

    public class Builder {
        private Shape shape;
        private int stokeColor, solidColor;//边框颜色和填充颜色
        private int strokeWidth;//边框宽度
        private int topLeftRadius, topRightRadius, bottomLeftRadius, bottomRightRadius;//矩形的圆角
        private int width, height;//drawable的宽高
        private int style = FILL;


        public Builder setShape(Shape shape) {
            this.shape = shape;
            return this;
        }

        public Builder setStokeColor(int stokeColor) {
            this.stokeColor = stokeColor;
            return this;
        }

        public Builder setSoildColor(int solidColor) {
            this.solidColor = solidColor;
            return this;
        }

        public Builder setStrokeWidth(int strokeWidth) {
            this.strokeWidth = strokeWidth;
            return this;
        }

        public Builder setTopLeftRadius(int topLeftRadius) {
            this.topLeftRadius = topLeftRadius;
            return this;
        }

        public Builder setTopRightRadius(int topRightRadius) {
            this.topRightRadius = topRightRadius;
            return this;
        }

        public Builder setBottomLeftRadius(int bottomLeftRadius) {
            this.bottomLeftRadius = bottomLeftRadius;
            return this;
        }

        public Builder setBottomRightRadius(int bottomRightRadius) {
            this.bottomRightRadius = bottomRightRadius;
            return this;
        }

        public Builder setRadius(int radius) {
            this.bottomLeftRadius = this.bottomRightRadius = this.topRightRadius = this.topLeftRadius = radius;
            return this;
        }

        public Builder setWidth(int width) {
            this.width = width;
            return this;
        }

        public Builder setHeight(int height) {
            this.height = height;
            return this;
        }

        public Builder setStyle(@Style int style) {
            this.style = style;
            return this;
        }

        public ShapeDrawable build() {
            return new ShapeDrawable(shape, stokeColor, solidColor, strokeWidth,
                    topLeftRadius, topRightRadius, bottomLeftRadius, bottomRightRadius, width, height, style);
        }
    }

    /**
     * 形状
     */
    public enum Shape {
        RECTANGLE,//矩形
        CIRCLE,//圆形
        ROUND_REC,//圆角矩形
        SEMICIRCLE_REC,//半圆矩形
    }
}
