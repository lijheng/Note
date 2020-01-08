package com.summer.note.util;

import android.content.res.Resources;

/**
 * CreateBy:lijheng at 2019/11/18
 * describe:像素转换工具
 */
@SuppressWarnings("unused")
public class DensityUtil {
    /**
     * 根据手机分辨率将dp转换为px(像素)
     *
     * @param dpValue dp值
     * @return 转换后的px值
     */
    public static int dp2px(final float dpValue) {
        final float scale = Resources.getSystem().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机分辨率将px(像素)转换为dp
     *
     * @param pxValue px值.
     * @return dp 值
     */
    public static int px2dp(final float pxValue) {
        final float scale = Resources.getSystem().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * sp->px
     *
     * @param spValue sp值.
     * @return px值
     * */
    public static int sp2px(final float spValue) {
        final float fontScale = Resources.getSystem().getDisplayMetrics().density;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * px->sp
     *
     * @param pxValue px值.
     * @return sp值
     */
    public static int px2sp(final float pxValue) {
        final float fontScale = Resources.getSystem().getDisplayMetrics().density;
        return (int) (pxValue / fontScale + 0.5f);
    }
}
