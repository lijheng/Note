package com.summer.note.widget;

import android.content.Context;
import android.graphics.BitmapRegionDecoder;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.IOException;
import java.io.InputStream;

/**
 * 查看图片的View
 * 支持放大缩小
 */
public class DisplayImageView extends View {

    private BitmapRegionDecoder mRegionDecoder;//区域解码器

    public DisplayImageView(Context context) {
        super(context);
    }

    public DisplayImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public DisplayImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    /**
     * 初始化
     */
    private void init(){

    }

    /**
     * 设置图片
     * @param is 图片的数据流
     */
    private void setImage(@NonNull InputStream is) throws IOException {
        try {
            mRegionDecoder = BitmapRegionDecoder.newInstance(is,false);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            is.close();
        }
    }
}
