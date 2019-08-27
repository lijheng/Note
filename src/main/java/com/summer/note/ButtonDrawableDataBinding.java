package com.summer.note;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.StateListDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.graphics.drawable.shapes.Shape;
import android.view.View;
import android.widget.Button;

import androidx.databinding.BindingAdapter;

/**
 * 作者:lijheng
 * 时间:2019年8月27日
 * 功能描述：为View添加drawable
 */
public class ButtonDrawableDataBinding {

    /**
     * 由边框背景 组成的矩形drawable
     *
     * @param view
     * @param borderColor     边框颜色
     * @param backgroundColor 背景颜色
     */
    @BindingAdapter("android:borderDrawable")
    public static void setBorderDrawable(View view, int borderColor, int backgroundColor) {
        StateListDrawable stateListDrawable = new StateListDrawable();
        ShapeDrawable pressedDrawable = new ShapeDrawable();
        RectShape rectShape = new RectShape();
//        pressedDrawable.setShape(new RectShape());
//        pressedDrawable.set

//        stateListDrawable.addState(new int[]{android.R.attr.state_pressed, android.R.attr.state_enabled}, );
//        stateListDrawable.addState(new int[]{android.R.attr.state_pressed}, );
    }

    /**
     * 获取比当前颜色更深的颜色，即按下的颜色
     *
     * @param color
     * @return
     */
    private static int getDarkerColor(int color) {
        float[] hsv = new float[3];
        Color.colorToHSV(color, hsv); // convert to hsv
        // make darker
        hsv[1] = hsv[1] + 0.1f; // 饱和度更高
        hsv[2] = hsv[2] - 0.1f; // 明度降低
        return Color.HSVToColor(hsv);
    }

}
